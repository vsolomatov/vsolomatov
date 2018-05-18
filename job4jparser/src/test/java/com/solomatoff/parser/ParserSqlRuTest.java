package com.solomatoff.parser;

import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;

import static org.junit.Assert.*;

public class ParserSqlRuTest {

    @Test
    public void putIntoDateKeeper() {
        Connection conn = ParserSqlRu.createConnection();
        Timestamp vacancyDate = new Timestamp(System.currentTimeMillis());
        ParserSqlRu.putIntoDateKeeper(vacancyDate, conn);
        ParserSqlRu.closeConnection(conn);
    }

    @Test
    public void runWork() {
        Connection conn = ParserSqlRu.createConnection();
        ParserSqlRu.init(conn);
        ParserSqlRu.closeConnection(conn);
        ParserSqlRu.runWork();
    }

    @Test
    public void init() {
        Connection conn = ParserSqlRu.createConnection();
        ParserSqlRu.init(conn);
        ParserSqlRu.closeConnection(conn);
    }

    @Test
    public void createConnection() {
        Connection conn = ParserSqlRu.createConnection();
        ParserSqlRu.closeConnection(conn);
    }

    @Test
    public void closeConnection() {
        Connection conn = ParserSqlRu.createConnection();
        ParserSqlRu.closeConnection(conn);

    }
}