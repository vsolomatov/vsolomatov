package com.solomatoff.parser;

import org.junit.Test;

public class ParserVacancyTest {
    @Test
    public void whenCheckVacancyText() {
        String url = "http://www.sql.ru/forum/1290228/javascript-razrabotchik";
        boolean result = ParserVacancy.checkVacancyText(url);
        System.out.println("result = " + result);
        System.out.println("");

        url = "http://www.sql.ru/forum/943862/m-video-razrabotchik-biznes-analitik-dwh-110-130-net";
        result = ParserVacancy.checkVacancyText(url);
        System.out.println("result = " + result);
        System.out.println("");

        url = "http://www.sql.ru/forum/1291324/risk-analitik-otp-bank";
        result = ParserVacancy.checkVacancyText(url);
        System.out.println("result = " + result);
        System.out.println("");

        url = "http://www.sql.ru/forum/1292763/razrabotchik-java-vozmozhnyy-rost-do-timlida-java";
        result = ParserVacancy.checkVacancyText(url);
        System.out.println("result = " + result);
    }

    @Test
    public void getVacancyText() {
        String url = "http://www.sql.ru/forum/1292763/razrabotchik-java-vozmozhnyy-rost-do-timlida-java";
        String text = ParserVacancy.getVacancyText(url);
        System.out.println("text = " + text);
        System.out.println("");
    }
}