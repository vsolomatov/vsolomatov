package com.solomatoff.jdbc;

import org.junit.Test;

import static org.junit.Assert.*;

public class WorkWithDBTest {

    @Test
    public void whenMainTest() {
        String[] args = new String[] {"jdbc:postgresql://localhost:5432/my_db", "postgres", "password", "100"};
        WorkWithDB.main(args);
    }
}