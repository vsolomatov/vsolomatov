package com.solomatoff.tracker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Properties;

/**
 * Класс для сохранения и извлечения настроек проиложения, хранящихся в файле tracker.properties
 */
public class TrackerProperty {
    private String fileProperties;

    public TrackerProperty(String fileProperties) {
        this.fileProperties = fileProperties;
        if (!new File(fileProperties).exists()) {
            setPropertyAsDefault(this.fileProperties);
        }
    }

    public void setPropertyAsDefault(String fileProperties) {
        this.fileProperties = fileProperties;
        setProperty("url", "jdbc:postgresql://localhost:5432/db_solomatov");
        setProperty("username", "solomatov");
        setProperty("password", "123");
        setProperty("createitems", "CREATE TABLE IF NOT EXISTS solomatov.items(id serial PRIMARY KEY, name character varying, description character varying, item_created timestamp DEFAULT now())");
        setProperty("createcomments", "CREATE TABLE IF NOT EXISTS solomatov.comments(id serial PRIMARY KEY, text character varying, item_id integer NOT NULL references items)");
        setProperty("insertitems", "INSERT INTO solomatov.items (name, description) VALUES (?, ?)");
        setProperty("insertcomments", "INSERT INTO solomatov.comments (text, item_id) VALUES (?, ?)");
        setProperty("selectitems", "SELECT * FROM solomatov.items");
        setProperty("selectcomments", "SELECT * FROM solomatov.comments");
        setProperty("selectcountcomments", "SELECT count(*) FROM solomatov.comments");
        setProperty("updateitems", "UPDATE solomatov.items SET name =?, description =?");
        setProperty("deleteitems", "DELETE FROM solomatov.items");
        setProperty("deletecomments", "DELETE FROM solomatov.comments");
    }

    public void setProperty(String key, String value) {
        File file = new File(fileProperties);
        //Создаем объект свойст
        Properties properties = new Properties();
        if (file.exists()) {
            //Загружаем ВСЕ свойства из файла
            try (FileInputStream inputFile = new FileInputStream(file)) {
                properties.load(inputFile);
            } catch (IOException e) {
               //System.out.println("Ошибка чтения файла tracker.properties.");
            }
        }
        try (FileOutputStream outputFile = new FileOutputStream(file)) {
            //Устанавливаем значение конкретного свойста
            properties.setProperty(key, value);
            //Сохраняем ВСЕ свойства в файл.
            properties.store(outputFile, "This is properties file for Tracker application by Solomatov Vyacheslav");
        } catch (IOException e) {
           //System.out.println("Ошибка записи файла tracker.properties.");
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        String value = null;
        try (FileInputStream inputFile = new FileInputStream(new File(this.fileProperties))) {
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
}
