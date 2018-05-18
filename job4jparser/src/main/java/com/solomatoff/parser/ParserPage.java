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
 * Класс для распарсивания страниц сайта sql.ru
 * @author Vyacheslav Solomatov
 */
public class ParserPage {
    /**
     * Метод для распарсивания одной страницы с таблицей вакансий сайта sql.ru
     * @param url адрес страницы вакансий
     */
    public static ResultParserPage parserForumTable(String url, Timestamp completionDate, Connection conn) {
        Document doc;
        ResultParserPage resultParserPage = new ResultParserPage();
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla").get();
            Elements forumTable = doc.body().getElementsByClass("forumTable");
            Elements trAll = forumTable.first().getElementsByTag("tr");
            String textVacancy;
            int i = 0;
            for (Element trRecord : trAll) {
                String nameVacancy = null;
                String urlVacancy = null;
                String authorVacancy = null;
                Timestamp createdVacancy = null;
                // первые четыре строки нам не нужны, так как это заголовок таблицы и темы от модераторов
                if (i > 3) {
                    Elements  elementsTdFromRecord = trRecord.getElementsByTag("td");
                    int j = 0;
                    // формируем данные для вставки строки в таблицу vacancies
                    for (Element elementTdFromRecord : elementsTdFromRecord) {
                        switch (j) {
                            case 1:
                                // наименование топика (вакансии)
                                nameVacancy = elementTdFromRecord.text();
                                // убираем апострофы, мешают при добавлении в таблицы
                                nameVacancy = nameVacancy.replaceAll("'", " ");
                                // ссылка на форум топика (вакансию)
                                if (elementTdFromRecord.children().size() > 0) {
                                    Element tdElement = elementTdFromRecord.children().first();
                                    urlVacancy = tdElement.attr("abs:href");
                                }
                                break;
                            case 2:
                                // автор топика (вакансии)
                                authorVacancy = elementTdFromRecord.text();
                                break;
                            case 5:
                                // дата создания топика (вакансии)
                                // предварительно преобразуем к нужному типу
                                createdVacancy = new Timestamp(parserDate(elementTdFromRecord.text()).getTime());
                            default:
                        }
                        j++;
                    }
                    // Заканчиваем процесс если дата очередной вакансии уже меньше чем дата, до которой мы должны проверять
                    if ((createdVacancy != null) && (createdVacancy.before(completionDate))) {
                        resultParserPage.flagFinish = true;
                        break;
                    }
                    // идем по ссылке, чтобы забрать полный текст вакансии, если она нам подходит
                    if (ParserVacancy.checkVacancyText(urlVacancy)) {
                        textVacancy = ParserVacancy.getVacancyText(urlVacancy);
                        // убираем апострофы, указатель на элемент списка; мешают при добавлении в таблицы
                        textVacancy = textVacancy.replaceAll("'", " ");
                        textVacancy = textVacancy.replaceAll("\u0306", "");
                        // Первычный ключ таблицы vacancies не даст втсавить строки с одинаковыми vacancy_name, vacancy_author, vacancy_created
                        String insertvacancies = ParserProperty.getProperty("insertvacancies");
                        try (PreparedStatement stInsert = conn.prepareStatement(insertvacancies)) {
                            stInsert.setString(1, nameVacancy);
                            stInsert.setString(2, urlVacancy);
                            stInsert.setString(3, authorVacancy);
                            stInsert.setTimestamp(4, createdVacancy);
                            stInsert.setString(5, textVacancy);
                            //System.out.println("    stInsert = " + stInsert);
                            // добавляем строку о вакансии в таблицу vacancies
                            stInsert.executeUpdate();
                            // добавляем в loggerRoot информацию о подходящей вакансии
                            ParserSqlRu.LOGGERROOR.info(String.format("<%-130s> %s", trRecord.text(), textVacancy));
                        } catch (SQLException e) {
                            ParserSqlRu.LOGGERPARSER.error(String.format("<   %s   > %s", e.getMessage(), nameVacancy), e);
                        }
                    }
                    if (i == 4) { // т.е. это первая вакансия при текущем разборе
                        resultParserPage.vacancyDate = createdVacancy;
                        //System.out.println("        ParserPage: vacancyDate = " + resultParserPage.vacancyDate);
                    }
                    // добавляем в loggerParser информацию о вакансии (даже если она нам не подходит - просто информация что мы ее анализировали)
                    ParserSqlRu.LOGGERPARSER.info(String.format("Анализировали: <%s> (author=%s) (created=%3$ty-%3$tm-%3$td %3$tH:%3$tM)%n", nameVacancy, authorVacancy, createdVacancy));
                }
                i++;
            }
        } catch (IOException e) {
            ParserSqlRu.LOGGERPARSER.error(e.getMessage(), e);
        }
        return resultParserPage;
    }

    /**
     * Метод преобразует строку вида "10 май 18, 15:35" в дату
     * @param textFromTd строка с датой
     * @return дату
     */
    public static Date parserDate(String textFromTd) {
        // формируем массив "дата, время"
        String[] arrayDateTime;
        try {
            arrayDateTime = textFromTd.split(",");
        } catch (NullPointerException e) {
            arrayDateTime = new String[] {"", "00:00"};
        }
        // формируем массив "день, месяц, год"
        String[] arrayDayMonthYear;
        if (arrayDateTime.length > 0) {
            if ((arrayDateTime[0] != null) && (!arrayDateTime[0].equals(""))) {
                arrayDayMonthYear = arrayDateTime[0].split(" ");
            } else {
                arrayDayMonthYear = new String[] {"D", "M", "YY"};
            }
        } else {
            arrayDayMonthYear = new String[] {"D", "M", "YY"};
        }
        // проверяем
        if (arrayDayMonthYear.length < 3) {
            arrayDayMonthYear = new String[] {"D", "M", "YY"};
        }
        // формируем массив "часы, минуты"
        String[] arrayHourMinute;
        if (arrayDateTime.length > 1) {
            try {
                arrayHourMinute = arrayDateTime[1].split(":");
            } catch (NullPointerException e) {
                arrayHourMinute = new String[] {"00", "00"};
            }
            if (arrayHourMinute.length == 0) {
                if ((arrayDateTime[1] != null) && (!arrayDateTime[1].equals(""))) {
                    arrayHourMinute = new String[] {arrayDateTime[1], "00"};
                } else {
                    arrayHourMinute = new String[] {"00", "00"};
                }
            } else {
                if (arrayHourMinute.length == 1) {
                    arrayHourMinute = new String[] {arrayHourMinute[0], "00"};
                }
            }
            // убираем пробелы, если есть
            arrayHourMinute[0] = arrayHourMinute[0].trim();
            arrayHourMinute[1] = arrayHourMinute[1].trim();
            // проверяем еще раз
            if (arrayHourMinute[0].equals("")) {
                arrayHourMinute[0] = "00";
            }
            if (arrayHourMinute[1].equals("")) {
                arrayHourMinute[1] = "00";
            }
        } else {
            arrayHourMinute = new String[] {"00", "00"};
        }
        // формируем объект cal с текущей датой, устанавливаем нужное время
        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.util.Date(System.currentTimeMillis())); // текущая дата
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
        cal.clear(Calendar.HOUR_OF_DAY);
        cal.clear(Calendar.MINUTE);
        try {
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arrayHourMinute[0]));
            cal.set(Calendar.MINUTE, Integer.parseInt(arrayHourMinute[1]));
        } catch (NumberFormatException e) {
            arrayHourMinute[0] = "00";
            arrayHourMinute[1] = "00";
        }
        // формируем дату
        Date date = null;
        if (arrayDateTime[0].equals("сегодня")) {
            date = cal.getTime();
        } else {
            if (arrayDateTime[0].equals("вчера")) {
                cal.add(Calendar.DATE, -1);
                date = cal.getTime();
            } else {
                String sDay = arrayDayMonthYear[0];
                String sMonth = arrayDayMonthYear[1];
                String sYear = arrayDayMonthYear[2];
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
                int monthNumber = 0;
                if (mMonth != null) {
                    monthNumber = mMonth.getValue();
                }
                String textForParse;
                if (monthNumber > 0 && monthNumber < 13) {
                    textForParse = monthNumber + " " + sDay + ", " + sYear + " " + arrayHourMinute[0] + ":" + arrayHourMinute[1];
                    DateFormat format = new SimpleDateFormat("M d, yy HH:mm");
                    try {
                        date = format.parse(textForParse);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(String.format("     Невозможно преобразовать строку (%s) в дату со временем%n", textFromTd));
                }
            }
        }
        //System.out.printf("date = %1$ty-%1$tm-%1$td %1$tH:%1$tM%n", date);
        return date;
    }
}

/**
 * Класс для результата выполнения разбора страницы
 */
class ResultParserPage {
    public Timestamp vacancyDate = null;
    public boolean flagFinish = false;
}