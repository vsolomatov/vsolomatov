package ru.job4j.tracker.start;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.MenuTracker;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.ValidateInput;

public class StartUI {
    /**
     * Массив содержит номера пунктов нашего меню
     */
    private int[] range; // получать надо из меню (массив номеров пунктов меню)
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
        this.range = menu.menusNumber();

        /*System.out.println("Вывод из StartUI");
        for (int num : this.range) {
            System.out.println(num);
        }*/

        while (!exit) {
            menu.showMenu();
            answer = this.input.ask("Выберите пункт меню : ", this.range);
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
    public static void main(String[] args) {
        Input input = new ValidateInput();
        new StartUI(input, new Tracker()).init();
    }
}

