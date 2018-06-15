package com.solomatoff.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Класс для сохранения и извлечения настроек проиложения, хранящихся в файле parserSqlRu.properties
 */
public class ParserProperty {
    private static String fileProperties = "parserSqlRu.properties";

    public static String getProperty(String key) {
        if (!new File(fileProperties).exists()) {
            setPropertyAsDefault();
        }
        String value = null;
        try (FileInputStream inputFile = new FileInputStream(new File(fileProperties))) {
            //Создаем объект свойст
            Properties properties = new Properties();
            //Загружаем свойства из файла
            properties.load(inputFile);
            //Получаем в переменную значение конкретного свойства
            value = properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    private static void setProperty(String key, String value) {
        File file = new File(fileProperties);
        //Создаем объект свойст
        Properties properties = new Properties();
        if (file.exists()) {
            //Загружаем ВСЕ свойства из файла
            try (FileInputStream inputFile = new FileInputStream(file)) {
                properties.load(inputFile);
            } catch (IOException e) {
               //System.out.println("Ошибка чтения файла parser.properties.");
            }
        }
        try (FileOutputStream outputFile = new FileOutputStream(file)) {
            //Устанавливаем значение конкретного свойста
            properties.setProperty(key, value);
            //Сохраняем ВСЕ свойства в файл.
            properties.store(outputFile, "This is properties file for Parser application by Solomatov Vyacheslav");
        } catch (IOException e) {
           //System.out.println("Ошибка записи файла parser.properties.");
            e.printStackTrace();
        }
    }
    private static void setPropertyAsDefault() {
        setProperty("url", "jdbc:postgresql://localhost:5432/db_solomatov");
        setProperty("username", "solomatov");
        setProperty("password", "123");
        setProperty("launchfrequency", "00:15"); // частота запуска приложения 05 минут
        setProperty("createparserlog", "CREATE TABLE IF NOT EXISTS solomatov.parserlog(id serial PRIMARY KEY, loggerdate timestamp without time zone, logger character varying, priority character varying, message text)");
        setProperty("grantparserlog", "GRANT ALL ON TABLE solomatov.parserlog TO public");
        setProperty("selectparserlog", "SELECT max(message) FROM solomatov.parserlog WHERE message like '%Date and time of the last selected record%'");
        setProperty("createvacancies", "CREATE TABLE IF NOT EXISTS solomatov.vacancies(vacancy_id serial, vacancy_name character varying NOT NULL, vacancy_url character varying NOT NULL, vacancy_author character varying NOT NULL, vacancy_created timestamp NOT NULL, vacancy_text text NOT NULL, CONSTRAINT vacancies_pkey PRIMARY KEY (vacancy_name, vacancy_author, vacancy_created))");
        setProperty("insertvacancies", "INSERT INTO solomatov.vacancies(vacancy_name, vacancy_url, vacancy_author, vacancy_created, vacancy_text) VALUES (?, ?, ?, ?, ?)");
        setProperty("createdatekeeper", "CREATE TABLE IF NOT EXISTS solomatov.datekeeper(datekeeper_id serial PRIMARY KEY, datekeeper_datefinish timestamp NOT NULL, datekeeper_datevacancy timestamp)");
        setProperty("insertdatekeeper", "INSERT INTO solomatov.datekeeper(datekeeper_datefinish, datekeeper_datevacancy) VALUES (?, ?)");
        setProperty("selectmaxdatefinish", "SELECT max(datekeeper_datefinish) FROM solomatov.datekeeper");
        setProperty("selectmaxdatevacancy", "SELECT max(datekeeper_datevacancy) FROM solomatov.datekeeper");
    }
}
