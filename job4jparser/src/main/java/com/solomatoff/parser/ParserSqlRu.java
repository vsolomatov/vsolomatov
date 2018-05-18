package com.solomatoff.parser;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.io.File;
import java.sql.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *  Основной класс для распарсивания вакансий с сайта SQL.RU
 * @author Vyacheslav Solomatov
 */
public class ParserSqlRu {
    // Инициализация логера root
    public static final Logger LOGGERROOR = Logger.getRootLogger();
    // Инициализация логера loggerParser
    public static final Logger LOGGERPARSER = Logger.getLogger(ParserSqlRu.class.getName());


    public static void main(String[] args) {
        // Инициализация приложения
        Connection conn = createConnection();
        init(conn);
        // Проверяем настройки частоты запуска приложения, настраиваем таймер
        String launchfrequency = ParserProperty.getProperty("launchfrequency");
        String hhfrequency = launchfrequency.substring(0, 2);
        String mmfrequency = launchfrequency.substring(3, 5);
        // период запуска в миллисекундах
        long period = (Integer.parseInt(hhfrequency) * 60 + Integer.parseInt(mmfrequency)) * 60 * 1000;
        System.out.println("Период запуска в минутах: " + period / 60000);
        // разница между текущим моментом и последней датой запуска в миллисекундах
        Timestamp finishDate = getDateFromDateKeeper("selectmaxdatefinish", conn);
        System.out.printf("    Предыдущая дата: %1$ty-%1$tm-%1$td %1$tH:%1$tM%n", finishDate);
        long diffDate = getDateDiff(finishDate, new Date());
        System.out.println("    Разница текущего времени и предыдущего запуска в минутах: " + diffDate / 60000);
        long delay = (period - diffDate > 0) ? (period - diffDate) : 0L;
        System.out.printf("    Отсрочка до следующего запуска в миллисекундах: %s (в минутах: %s)%n", delay, delay / 60000);
        // планируем запуски
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runWork();
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, delay, period);
        closeConnection(conn);
        System.out.println("Основной поток завершил работу!");
    }

    /**
     * Метод инициализирует приложение
     */
    public static void init(Connection conn) {
        // настраиваем логгеры
        String filename = "log4j.properties"; //"log4j.xml";
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("It is not possible to load the given log4j properties file :" + file.getAbsolutePath());
        } else {
            PropertyConfigurator.configure(file.getAbsolutePath());
        }
        // Создаем таблицы, если их нет; даем права на таблицу parserlog
        String createParserLog = ParserProperty.getProperty("createparserlog");
        String grantParserLog = ParserProperty.getProperty("grantparserlog");
        String createVacancies = ParserProperty.getProperty("createvacancies");
        String createDateKeeper = ParserProperty.getProperty("createdatekeeper");
        try (Statement st = conn.createStatement()) {
            st.execute(createParserLog);
            st.execute(grantParserLog);
            st.execute(createVacancies);
            st.execute(createDateKeeper);
        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection(conn);
            System.exit(1);
        }
    }

    /**
     * Метод выполняет основную работу по разбору вакансий
     */
    public static void runWork() {
        // коннктимся к базе данных
        Connection conn = createConnection();
        // Формируем дату до которой мы должны проверять вакансии
        Timestamp completionDate = getDateFromDateKeeper("selectmaxdatevacancy", conn);
        ResultParserPage resultParserPage = new ResultParserPage();
        String urlPage;
        Integer index = 1;
        Timestamp vacancyDate = null;
        // разбираем страницы сайта в цикле
        while (!resultParserPage.flagFinish) {
            urlPage = "http://www.sql.ru/forum/job-offers/" + index;
            System.out.println("Начинаем разбор страницы " + urlPage);
            if (index == 1) { // это первая страница
                resultParserPage = ParserPage.parserForumTable(urlPage, completionDate, conn);
                vacancyDate = resultParserPage.vacancyDate;
            } else {
                resultParserPage = ParserPage.parserForumTable(urlPage, completionDate, conn);
            }
            index++;
        }
        // добавить в таблицу datekeeper дату последнего запуска и дату последей проверенной вакансии в этом запуске
        putIntoDateKeeper(vacancyDate, conn);
        closeConnection(conn);
    }

    /**
     * Метод возвращает из таблицы datekeeper дату последней проверенной вакансии на сайте
     * или дату последнего запуска приложения (в зависимости от параметра)
     * @return возвращает дату (в зависимости от параметра)
     */
    private static Timestamp getDateFromDateKeeper(String property, Connection conn) {
        Timestamp lastDate = null;
        String select = ParserProperty.getProperty(property);
        try (Statement st = conn.createStatement()) {
            try (ResultSet rs = st.executeQuery(select)) {
                rs.next();
                lastDate = rs.getTimestamp(1);
            } catch (SQLException e) {
                ParserSqlRu.LOGGERPARSER.error(String.format("<   %s   > %s %s", e.getMessage(), property, select), e);
            }
        } catch (SQLException e) {
            ParserSqlRu.LOGGERPARSER.error(String.format("<   %s   > %s", e.getMessage(), property), e);
        }
        // Если будет пусто в таблице
        if (lastDate == null) {
            lastDate = new Timestamp(1514746800000L); // 01/01/2018 00:00
        }
        return lastDate;
    }

    /**
     * Метод добавляет в таблицу datekeeper дату последнего запуска и дату последей проверенной вакансии
     */
    public static void putIntoDateKeeper(Timestamp vacancyDate, Connection conn) {
        String insertDateKeeper = ParserProperty.getProperty("insertdatekeeper");
        // формируем объект currentDate с текущей датой и временем
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        try (PreparedStatement stInsert = conn.prepareStatement(insertDateKeeper)) {
            stInsert.setTimestamp(1, currentDate);
            stInsert.setTimestamp(2, vacancyDate);
            System.out.printf("     Добавляем в таблицу дату окончания текущего разбора: <%s>  и дату последей вакансии <%s>%n", currentDate, vacancyDate);
            stInsert.executeUpdate();
        } catch (SQLException e) {
            ParserSqlRu.LOGGERPARSER.error(String.format("<   %s   > %s %s", e.getMessage(), currentDate, vacancyDate), e);
        }
    }


    /**
     * Метод получает разницу между двумя датами в миллисекундах
     *
     * @param date1 старая дата
     * @param date2 новая дата
     * @return разницу между датами в миллисекундах
     */
    private static long getDateDiff(Date date1, Date date2) {
        return date2.getTime() - date1.getTime();
    }

    /**
     * Метод открывает коннект к базе данных
     */
    public static Connection createConnection() {
        // читаем настройки из parser.properties
        Connection conn = null;
        String url = ParserProperty.getProperty("url");
        String username = ParserProperty.getProperty("username");
        String password = ParserProperty.getProperty("password");
        // коннектимся к базе данных
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Метод закрывает коннект к базе данных
     */
    public static void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
