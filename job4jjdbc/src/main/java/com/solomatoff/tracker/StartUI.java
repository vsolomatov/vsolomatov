package com.solomatoff.tracker;

import java.io.File;
import java.util.ArrayList;

public class StartUI {
    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "70";

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        String answer;
        // создаем таблицу в базе данных, для этого читаем настройки из tracker.properties

        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        // Заполняем меню пунктами
        menu.fillMenus();

        while (!exit) {
            menu.showMenu();
            answer = this.input.ask("Выберите пункт меню : ", menu.keysmenu());

            if (answer.equals(EXIT)) {
                exit = true;
            } else {
                menu.select(answer);
            }
        }
    }

    /**
     * Запуск программы.
     *
     */
    public static void main(String[] args) {
        Input input = new ValidateInput(new ConsoleInput());
        try (Tracker tracker = new Tracker("tracker.properties")) {
            new StartUI(input, tracker).init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

