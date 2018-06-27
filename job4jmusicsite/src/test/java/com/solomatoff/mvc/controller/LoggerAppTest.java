package com.solomatoff.mvc.controller;

import com.solomatoff.mvc.entities.User;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class LoggerAppTest {

    @Test
    public void saveUsersToLog() {
        final Controller controller = Controller.getInstance();
        List<User> list = controller.getLogic().findAllUsers(new User());
        LoggerApp loggerApp = LoggerApp.getInstance();
        loggerApp.saveUsersToLog(list);
        final String FILE_NAME = "/logs/Controller.log";
        try (Stream stream = Files.lines(Paths.get(FILE_NAME), StandardCharsets.UTF_8)) {
            System.out.println("Файл /logs/Controller.log существует");
            //stream.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Файл /logs/Controller.log не найдет");
            // ничего не делать
        }
    }
}