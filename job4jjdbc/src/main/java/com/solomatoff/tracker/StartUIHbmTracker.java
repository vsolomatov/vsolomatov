package com.solomatoff.tracker;

import com.solomatoff.tracker.store.HbmTracker;
import com.solomatoff.tracker.store.Store;

public class StartUIHbmTracker {
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
    private final Store tracker;

    /**
     * Конструтор инициализирующий поля.
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUIHbmTracker(Input input, Store tracker) {
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
        try (HbmTracker hbmTracker = new HbmTracker()) {
            new StartUIHbmTracker(input, hbmTracker).init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

