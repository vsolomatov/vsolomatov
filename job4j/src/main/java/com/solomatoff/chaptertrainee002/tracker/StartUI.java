package com.solomatoff.chaptertrainee002.tracker;

import java.util.ArrayList;

public class StartUI {
    /**
     * Массив содержит номера пунктов нашего меню
     */
    private ArrayList<String> range; // получать надо из меню (список ключей меню)
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
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        // Заполняем меню пунктами
        menu.fillMenus();
        this.range = menu.keysmenu();

        /*System.out.println("Вывод из StartUI");
        for (String num : this.range) {
            System.out.println(num);
        }*/

        while (!exit) {
            menu.showMenu();
            answer = this.input.ask("Выберите пункт меню : ", this.range);

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
        new StartUI(input, new Tracker()).init();
    }
}

