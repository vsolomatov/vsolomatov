package ru.job4j.tracker.start;

import ru.job4j.tracker.*;

public class StartUI {
    /**
     * Константа для выхода из цикла.
     */
    private static final int EXIT = 6;

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
        int answer;
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        // Заполняем меню пунктами
        menu.fillMenus();

        while (!exit) {
            menu.showMenu();
            answer = Integer.parseInt(this.input.ask("Выберите пункт меню : "));
            if (answer == EXIT) {
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
    public static void main() {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}

