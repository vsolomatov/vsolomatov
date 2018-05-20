package com.solomatoff.parser;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import java.sql.*;

public class ParserPageTest {

    @Test
    public void whenForumTableParser() {
        ParserSqlRu parserSqlRu = new ParserSqlRu();
        parserSqlRu.init();
        Timestamp completionDate = new Timestamp(1514746800000L); // 01/01/2018 00:00
        Connection conn = null;
        // читаем настройки из parser.properties
        String url = ParserProperty.getProperty("url");
        String username = ParserProperty.getProperty("username");
        String password = ParserProperty.getProperty("password");
        // коннектимся к базе данных
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String urlForParser = "http://www.sql.ru/forum/job-offers/6";
        ParserPage parserPage = new ParserPage(conn);
        parserPage.parserForumTable(urlForParser, completionDate);
        try {
            conn.close();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenParserDate() {
        ParserSqlRu parserSqlRu = new ParserSqlRu();
        parserSqlRu.init();
        Connection conn = null;
        // читаем настройки из parser.properties
        String url = ParserProperty.getProperty("url");
        String username = ParserProperty.getProperty("username");
        String password = ParserProperty.getProperty("password");
        // коннектимся к базе данных
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ParserPage parserPage = new ParserPage(conn);
        parserPage.parserDate("сегодня");
        parserPage.parserDate("сегодня, 15:35");
        parserPage.parserDate("вчера");
        parserPage.parserDate("вчера, 15:35");
        parserPage.parserDate("");
        parserPage.parserDate(null);
        parserPage.parserDate("10 месяц 16, 15:35");
        parserPage.parserDate("44 венера 18");
        parserPage.parserDate("10 январь 18, 15:350 ");
        parserPage.parserDate("41 декабрь 17, 15:35");
        parserPage.parserDate("10 февраль 18");
        parserPage.parserDate("10 март 18, 15: 35");
        parserPage.parserDate("10 апрель 18, 15");
        parserPage.parserDate("10 май 18, 35");
        parserPage.parserDate("10 июнь 18, ");
        parserPage.parserDate("10 июль 18, :");
        parserPage.parserDate("бред, и еще раз бред 3:0");
        parserPage.parserDate("10 сентябрь 8, 15:35");
        parserPage.parserDate("бред, 1:0");
        parserPage.parserDate("бред сивой кобылы, 2 : 0");
        parserPage.parserDate("бред январь, 4 : 0");
        parserPage.parserDate("10 декабрь 08, 05:35");
        parserPage.parserDate(", 05:35");
        parserPage.parserDate(" , ");
        parserPage.parserDate(" , : ");
        parserPage.parserDate("10 май 14 25 , 14:12 ");
        parserPage.parserDate("1000 май 25 , 140:120 ");
        try {
            conn.close();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}