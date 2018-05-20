package com.solomatoff.parser;

import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.*;

public class ParserSqlRuTest {

    @Test
    public void putIntoDateKeeper() {
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
        Timestamp vacancyDate = new Timestamp(System.currentTimeMillis());
        ParserSqlRu parserSqlRu = new ParserSqlRu();
        parserSqlRu.putIntoDateKeeper(vacancyDate, conn);
        try {
            conn.close();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void runWork() {
        ParserSqlRu parserSqlRu = new ParserSqlRu();
        parserSqlRu.init();
        parserSqlRu.runWork();
    }

    @Test
    public void init() {
        ParserSqlRu parserSqlRu = new ParserSqlRu();
        parserSqlRu.init();
    }

    @Test
    public void createConnection() {
        ParserSqlRu parserSqlRu = new ParserSqlRu();
        parserSqlRu.createConnection();
    }
}