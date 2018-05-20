package com.solomatoff.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ParserVacancy {
    /**
     * Метод для проверки текста вакансии с сайта sql.ru на содержание
     * @param url адрес страницы вакансии
     * @return true, если содержит в тексте нужные нам фразы, иначе - false
     */
    public boolean checkVacancyText(String url) {
        List<String> wordForSearch = Arrays.asList("Java", "Java,", "Java.", "Java ", "Java!", "Java(", "Java/", "Java$", "Java%", "Java@", "Java+", "Java:");
        boolean result = false;
        String textVacancy = getVacancyText(url);
        //System.out.println("textVacancy = " + textVacancy);
        for (String word : wordForSearch) {
            int posJavaPrev = 0;
            int posJavaCurr = 0;
            int posJavaScript;
            int posJavascript;
            int posJavaSpaceScript;
            int posJavaSpacescript;
            while (posJavaCurr >= 0) {
                posJavaCurr = textVacancy.indexOf(word, posJavaPrev + 1);
                posJavaScript = textVacancy.indexOf("JavaScript", posJavaPrev + 1);
                posJavascript = textVacancy.indexOf("Javascript", posJavaPrev + 1);
                posJavaSpaceScript = textVacancy.indexOf("Java Script", posJavaPrev + 1);
                posJavaSpacescript = textVacancy.indexOf("Java script", posJavaPrev + 1);
                if (posJavaCurr > 0 && (posJavaCurr != posJavaScript) && (posJavaCurr != posJavascript) && (posJavaCurr != posJavaSpaceScript) && (posJavaCurr != posJavaSpacescript)) {
                    result = true;
                    break;
                }
                posJavaPrev = posJavaCurr;
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    /**
     * Метод для получения текста вакансии со страницы с форума по конкретной вакансии сайта sql.ru
     * @param url адрес страницы
     * @return текст вакансии
     */
    public String getVacancyText(String url) {
        String result = null;
        Document doc;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla").get();
            Elements msgTable = doc.body().getElementsByClass("msgTable");
            Elements tdClass = msgTable.first().getElementsByClass("msgBody");
            Element trRecord = tdClass.last();
            result = trRecord.text();
            result = result.replaceAll("'", " "); // убираем апострофы, мешают при добавлении в таблицы
            //System.out.printf("trRecord.text=<%s>%n", trRecord.text());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
