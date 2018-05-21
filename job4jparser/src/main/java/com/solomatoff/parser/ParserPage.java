package com.solomatoff.parser;

import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Класс для результата выполнения разбора страницы
 */
class ResultParserPage {
    public Timestamp vacancyDate = null;
    public boolean flagFinish = false;
}

/**
 * Класс для распарсивания страниц сайта sql.ru
 * @author Vyacheslav Solomatov
 */
public class ParserPage {
    // Коннект к базе данных
    private Connection conn;

    public ParserPage(Connection conn) {
        this.conn = conn;
    }

    /**
     * Метод для распарсивания одной страницы с таблицей вакансий сайта sql.ru
     *
     * @param url            адрес страницы вакансий
     * @param completionDate дата завершения процесса разбора
     * @return объект класса ResultParserPage, содержащий дату последней вакансии (на данной странице - url) и флаг продолжения или остановки разбора
     */
    public ResultParserPage parserForumTable(String url, Timestamp completionDate) {
        ResultParserPage resultParserPage = new ResultParserPage();
        Document doc;
        Elements trAll = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla").get();
            Elements forumTable = doc.body().getElementsByClass("forumTable");
            trAll = forumTable.first().getElementsByTag("tr");
        } catch (IOException e) {
            ParserSqlRu.LOGGERPARSER.error(e.getMessage(), e);
        }
        if (trAll != null) {
            ParserVacancy parserVacancy = new ParserVacancy();
            int i = 0;
            for (Element trRecord : trAll) {
                //System.out.println("trRecord.text() = " + trRecord.text());
                String nameVacancy = null;
                String urlVacancy = null;
                String authorVacancy = null;
                Timestamp createdVacancy = null;
                String textVacancy;
                if (i > 3) { // первые четыре строки нам не нужны, так как это заголовок таблицы и темы от модераторов
                    Elements elementsTdFromRecord = trRecord.getElementsByTag("td");
                    int j = 0;
                    for (Element elementTdFromRecord : elementsTdFromRecord) {
                        switch (j) {
                            case 1:
                                nameVacancy = elementTdFromRecord.text(); // наименование топика (вакансии)
                                nameVacancy = nameVacancy.replaceAll("'", " "); // убираем апострофы, мешают при добавлении в таблицы
                                if (elementTdFromRecord.children().size() > 0) {
                                    Element tdElement = elementTdFromRecord.children().first();
                                    urlVacancy = tdElement.attr("abs:href"); // ссылка на форум топика (url-вакансии)
                                    //System.out.println("    urlVacancy = " + urlVacancy);
                                }
                                break;
                            case 2:
                                authorVacancy = elementTdFromRecord.text(); // автор топика (вакансии)
                                break;
                            case 5:
                                String stringDate; // дата создания топика (вакансии)
                                try {
                                    stringDate = elementTdFromRecord.text();
                                    createdVacancy = new Timestamp(parserDate(stringDate).getTime());
                                    //System.out.println("    createdVacancy = " + createdVacancy);
                                } catch (NullPointerException e) {
                                    ParserSqlRu.LOGGERPARSER.warn(String.format("     < У вакансии (%s) будет пустая дата >      %n", nameVacancy));
                                }
                                break;
                            default:
                        }
                        j++;
                    }
                    // Добавляем в loggerParser информацию о вакансии (даже если она нам не подходит) - просто информация что мы ее анализировали
                    ParserSqlRu.LOGGERPARSER.info(String.format("Анализировали: <%s> (author=%s) (created=%3$ty-%3$tm-%3$td %3$tH:%3$tM)%n", nameVacancy, authorVacancy, createdVacancy));
                    if (i == 4) { // т.е. это первая (верхняя в таблице топиков) вакансия при текущем разборе
                        resultParserPage.vacancyDate = createdVacancy;
                    }
                    // Заканчиваем процесс, если дата очередной вакансии уже меньше чем дата, до которой мы должны проверять
                    if ((createdVacancy != null) && (createdVacancy.before(completionDate) || createdVacancy.equals(completionDate))) {
                        resultParserPage.flagFinish = true;
                        break;
                    }
                    if (parserVacancy.checkVacancyText(urlVacancy)) { // Проверяем содержит ли текст вакансии нужные нам слова
                        textVacancy = parserVacancy.getVacancyText(urlVacancy);
                        // добавляем вакансию в таблицу вакансий
                        insertVacancyIntoTable(nameVacancy, urlVacancy, authorVacancy, createdVacancy, textVacancy);
                        // добавляем в loggerRoot информацию о подходящей вакансии
                        ParserSqlRu.LOGGERROOT.info(String.format("<%-130s> %s", trRecord.text(), textVacancy));
                    }
                }
                i++;
            }
        }
        return resultParserPage;
    }

    /**
     * Метод добавляет в таблицу вакансий информацию о подходящей вакансии
     */
    private void insertVacancyIntoTable(String nameVacancy, String urlVacancy, String authorVacancy, Timestamp createdVacancy, String textVacancy) {
        String insertvacancies = ParserProperty.getProperty("insertvacancies");
        try (PreparedStatement stInsert = conn.prepareStatement(insertvacancies)) {
            stInsert.setString(1, nameVacancy);
            stInsert.setString(2, urlVacancy);
            stInsert.setString(3, authorVacancy);
            stInsert.setTimestamp(4, createdVacancy);
            stInsert.setString(5, textVacancy);
            // Первычный ключ таблицы vacancies не даст втсавить строки с одинаковыми vacancy_name, vacancy_author, vacancy_created
            stInsert.executeUpdate();
        } catch (SQLException e) {
            ParserSqlRu.LOGGERPARSER.error(String.format("<   %s   > %s (author=%s) (created=%4$ty-%4$tm-%4$td %4$tH:%4$tM)%n", e.getMessage(), nameVacancy, authorVacancy, createdVacancy), e);
        }
    }

    /**
     * Метод преобразует строку вида "10 май 18, 15:35" в дату
     *
     * @param stringDate строка с датой
     * @return дату
     */
    public Date parserDate(String stringDate) {
        Date dateResult;
        // Выделяем подстроку со временем
        String stringDateOrTime;
        try {
            stringDateOrTime = getSubstringDateOrTime(stringDate, "Time");
        } catch (NullPointerException | ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            ParserSqlRu.LOGGERPARSER.error(String.format("     <Невозможно преобразовать строку (%s) в дату со временем>      %n", stringDate));
            return null;
        }
        stringDateOrTime = stringDateOrTime.trim(); // удаляем пробелы
        int[] arrayHourMinute;
        try {
            arrayHourMinute = getHourMinute(stringDateOrTime);
        } catch (NullPointerException | ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            ParserSqlRu.LOGGERPARSER.error(String.format("     <Невозможно преобразовать строку (%s) в дату со временем>      %n", stringDate));
            return null;
        }
        // Формируем объект Calendar с текущей датой
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(System.currentTimeMillis())); // текущая дата
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
        // Устанавливаем нужное время
        cal.set(Calendar.HOUR_OF_DAY, arrayHourMinute[0]);
        cal.set(Calendar.MINUTE, arrayHourMinute[1]);
        // Выделяем подстроку с датой
        try {
            stringDateOrTime = getSubstringDateOrTime(stringDate, "Date");
        } catch (NullPointerException | ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            ParserSqlRu.LOGGERPARSER.error(String.format("     <Невозможно преобразовать строку (%s) в дату со временем>      %n", stringDate));
            return null;
        }
        if (stringDateOrTime.equals("сегодня")) {
            dateResult = cal.getTime();
        } else {
            if (stringDateOrTime.equals("вчера")) {
                cal.add(Calendar.DATE, -1);
                dateResult = cal.getTime();
            } else {
                int[] arrayDayMonthYear;
                try {
                    arrayDayMonthYear = getDayMonthYear(stringDateOrTime);
                } catch (NullPointerException | ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                    ParserSqlRu.LOGGERPARSER.error(String.format("     <Невозможно преобразовать строку (%s) в дату со временем>      %n", stringDate));
                    return null;
                }
                cal.set(Calendar.DAY_OF_MONTH, arrayDayMonthYear[0]);
                cal.set(Calendar.MONTH, arrayDayMonthYear[1] - 1); // уменьшаем, потому что здесь месяцы нумеруются от 0 до 11
                cal.set(Calendar.YEAR, arrayDayMonthYear[2] + 2000); // увеличиваем, чтобы получить 2018 вместо 18
                dateResult = cal.getTime();
            }
        }
        //ParserSqlRu.LOGGERPARSER.info(String.format("Строка <%s> date = %2$ty-%2$tm-%2$td %2$tH:%2$tM%n", stringDate, dateResult));
        return dateResult;
    }

    /**
     * Метод извлекает подстроку, содержащую "дату" или "время" из строки вида "10 май 18, 15:35"
     *
     * @param stringDateTime строка, содержащая дату и время
     * @param flagDateOrTime признак того, что нужно выбрать "Date" - "дату", иначе "время"
     * @return подстрока, содержащая дату или время в зависимомти от параметра flagDateOrTime
     */
    private String getSubstringDateOrTime(String stringDateTime, String flagDateOrTime) {
        String[] arrayDateTime;
        try {
            arrayDateTime = stringDateTime.split(",");
        } catch (NullPointerException e) {
            arrayDateTime = new String[]{stringDateTime, "00:00"};
        }
        // проверяем
        if (arrayDateTime[0] == null || arrayDateTime[1] == null || arrayDateTime.length != 2) {
            throw new IllegalArgumentException();
        }
        return flagDateOrTime.equals("Date") ? arrayDateTime[0] : arrayDateTime[1];
    }

    /**
     * Метод извлекает "дату" из строки вида "10 май 18"
     *
     * @param stringDate строка, содержащая дату
     * @return массив из трех числовых значений: число, месяц, год
     */
    private int[] getDayMonthYear(String stringDate) {
        String[] arrayDayMonthYear;
        arrayDayMonthYear = stringDate.split(" ");
        // проверяем
        if (arrayDayMonthYear[0] == null || arrayDayMonthYear[1] == null || arrayDayMonthYear[2] == null || arrayDayMonthYear.length != 3) {
            throw new IllegalArgumentException();
        }
        // Получаем данные в виде чисел
        int iDay = Integer.parseInt(arrayDayMonthYear[0]);
        int iMonth = getMonth(arrayDayMonthYear[1]);
        if (iMonth == -1) {
            throw new IllegalArgumentException();
        }
        int iYear = Integer.parseInt(arrayDayMonthYear[2]);
        int[] arrayResult = {iDay, iMonth, iYear};
        return arrayResult;
    }

    /**
     * Метод извлекает "время" из строки вида "15:35"
     *
     * @param stringTime строка, содержащая "время"
     * @return массив из двух числовых значений: часы, минуты
     */
    private int[] getHourMinute(String stringTime) {
        String[] arrayHourMinute;
        arrayHourMinute = stringTime.split(":");
        // проверяем
        if (arrayHourMinute[0] == null || arrayHourMinute[1] == null || arrayHourMinute.length != 2) {
            throw new IllegalArgumentException();
        }
        // Получаем данные в виде чисел
        int iHour = Integer.parseInt(arrayHourMinute[0]);
        int iMinute = Integer.parseInt(arrayHourMinute[1]);
        int[] arrayResult = {iHour, iMinute};
        return arrayResult;
    }

    /**
     * Метод получает месяц в виде числа из строки
     *
     * @param sMonth месяц в виде строки
     * @return месяц в виде числа от 1 до 12, или -1 если соответствие не нашлось
     */
    private int getMonth(String sMonth) {
        Month mMonth = null;
        if (sMonth.equals("январь") || sMonth.equals("янв")) {
            mMonth = Month.JANUARY;
        } else {
            if (sMonth.equals("февраль") || sMonth.equals("фев")) {
                mMonth = Month.FEBRUARY;
            } else {
                if (sMonth.equals("март") || sMonth.equals("мар")) {
                    mMonth = Month.MARCH;
                } else {
                    if (sMonth.equals("апрель") || sMonth.equals("апр")) {
                        mMonth = Month.APRIL;
                    } else {
                        if (sMonth.equals("май")) {
                            mMonth = Month.MAY;
                        } else {
                            if (sMonth.equals("июнь") || sMonth.equals("июн")) {
                                mMonth = Month.JUNE;
                            } else {
                                if (sMonth.equals("июль") || sMonth.equals("июл")) {
                                    mMonth = Month.JULY;
                                } else {
                                    if (sMonth.equals("август") || sMonth.equals("авг")) {
                                        mMonth = Month.AUGUST;
                                    } else {
                                        if (sMonth.equals("сентябрь") || sMonth.equals("сен")) {
                                            mMonth = Month.SEPTEMBER;
                                        } else {
                                            if (sMonth.equals("октябрь") || sMonth.equals("окт")) {
                                                mMonth = Month.OCTOBER;
                                            } else {
                                                if (sMonth.equals("ноябрь") || sMonth.equals("ноя")) {
                                                    mMonth = Month.NOVEMBER;
                                                } else {
                                                    if (sMonth.equals("декабрь") || sMonth.equals("дек")) {
                                                        mMonth = Month.DECEMBER;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        int monthNumber = -1;
        if (mMonth != null) {
            monthNumber = mMonth.getValue();
        }
        return monthNumber;
    }
}

