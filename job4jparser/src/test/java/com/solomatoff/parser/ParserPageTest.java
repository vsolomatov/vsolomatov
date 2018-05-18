package com.solomatoff.parser;

import org.junit.Test;
import static org.hamcrest.core.Is.is;

import java.sql.*;

public class ParserPageTest {

    @Test
    public void whenForumTableParser() {
        Connection conn = ParserSqlRu.createConnection();
        ParserSqlRu.init(conn);
        Timestamp completionDate = new Timestamp(1514764800000L);

        String urlForParser = "http://www.sql.ru/forum/job-offers/6";
        ParserPage.parserForumTable(urlForParser, completionDate, conn);
        ParserSqlRu.closeConnection(conn);
    }

    @Test
    public void whenParserDate() {
        ParserPage.parserDate("сегодня");
        ParserPage.parserDate("сегодня, 15:35");
        ParserPage.parserDate("вчера");
        ParserPage.parserDate("вчера, 15:35");
        ParserPage.parserDate("");
        ParserPage.parserDate(null);
        ParserPage.parserDate("10 месяц 16, 15:35");
        ParserPage.parserDate("44 венера 18");
        ParserPage.parserDate("10 январь 18");
        ParserPage.parserDate("41 декабрь 17, 15:35");
        ParserPage.parserDate("10 февраль 18");
        ParserPage.parserDate("10 март 18, 15: 35");
        ParserPage.parserDate("10 апрель 18, 15");
        ParserPage.parserDate("10 май 18, 35");
        ParserPage.parserDate("10 июнь 18, ");
        ParserPage.parserDate("10 июль 18, :");
        ParserPage.parserDate("бред, и еще раз бред 3:0");
        ParserPage.parserDate("10 сентябрь 8, 15:35");
        ParserPage.parserDate("бред, 1:0");
        ParserPage.parserDate("бред сивой кобылы, 2 : 0");
        ParserPage.parserDate("бред январь, 4 : 0");
        ParserPage.parserDate("10 декабрь 08, 05:35");
        ParserPage.parserDate(", 05:35");
    }
}