package com.solomatoff.parser;

import org.junit.Test;

public class ParserVacancyTest {
    @Test
    public void whenCheckVacancyText() {
        String url = "http://www.sql.ru/forum/1290228/javascript-razrabotchik";
        ParserVacancy parserVacancy = new ParserVacancy();
        boolean result = parserVacancy.checkVacancyText(url);
       //System.out.println("result = " + result);
       //System.out.println("");

        url = "http://www.sql.ru/forum/943862/m-video-razrabotchik-biznes-analitik-dwh-110-130-net";
        result = parserVacancy.checkVacancyText(url);
       //System.out.println("result = " + result);
       //System.out.println("");

        url = "http://www.sql.ru/forum/1291324/risk-analitik-otp-bank";
        result = parserVacancy.checkVacancyText(url);
       //System.out.println("result = " + result);
       //System.out.println("");

        url = "http://www.sql.ru/forum/1292763/razrabotchik-java-vozmozhnyy-rost-do-timlida-java";
        result = parserVacancy.checkVacancyText(url);
       //System.out.println("result = " + result);
    }

    @Test
    public void getVacancyText() {
        String url = "http://www.sql.ru/forum/1286435/database-service-reliability-engineer";
        ParserVacancy parserVacancy = new ParserVacancy();
        String text = parserVacancy.getVacancyText(url);
       //System.out.println("text = " + text);
       //System.out.println("");
    }
}