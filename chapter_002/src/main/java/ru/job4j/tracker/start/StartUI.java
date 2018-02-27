package ru.job4j.tracker.start;

import ru.job4j.tracker.models.*;
import ru.job4j.tracker.*;

public class StartUI {
   /**
    * Константа меню для добавления новой заявки.
    */
    private static final String ADD = "0";

    /**
     * Константа меню для показа всех заявок.
     */
    private static final String SHOW = "1";

    /**
     * Константа меню для редактирования заявки.
     */
    private static final String EDIT = "2";

    /**
     * Константа меню для удаления заявки.
     */
    private static final String DELETE = "3";

    /**
     * Константа меню для поиска заявки по Id.
     */
    private static final String FINDID = "4";

    /**
     * Константа меню для поиска заявки по имени.
     */
    private static final String FINDNAME = "5";

    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";
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
     * @param input ввод данных.
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
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                // добавление заявки
                this.createItem();
            } else if (SHOW.equals(answer)) {
                // Показать все заявки
                this.showAllItems();
            } else if (EDIT.equals(answer)) {
                // редактирование заявки
                this.editItem();
            } else if (DELETE.equals(answer)) {
                // удаление заявки
                this.deleteItem();
            } else if (FINDID.equals(answer)) {
                // Поиск заявки по Id
                this.findIdItem();
            } else if (FINDNAME.equals(answer)) {
                // Поиск заявки по имени
                this.findNameItem();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc, 0L);
        this.tracker.add(item);
        System.out.println("------------ Создана новая заявка с Id : " + item.getId() + " -----------");
    }

    /**
     * Метод реализует вывод всех заявок в консоль.
     */
    private void showAllItems() {
        System.out.println("------------ Все заявки --------------");
        for (Item item : this.tracker.findAll()) {
            System.out.println(item.getId() + " " + item.getName() + " " + item.getDescription());
        }
        System.out.println("--------------------------------------");
    }

    /**
     * Метод реализует редактирование заявки в хранилище.
     */
    private void editItem() {
        boolean exit = false;
        String id = null;
        System.out.println("------------ Редактирование заявки --------------");
        while (!exit) {
            id = this.input.ask("Введите id заявки, которую необходимо отредактировать :");
            // Проверим существует ли заявка с таким id в хранилище
            if ((this.tracker.findById(id) == null) && !id.equals("q")) {
                System.out.println("Вы ввели некорректный id заявки! Проверьте свои данные и введите правильный id или q - для выхода.");
            } else {
                exit = true;
            }
        }
        if (!id.equals("q")) {
            String name = this.input.ask("Введите новое имя заявки :");
            String desc = this.input.ask("Введите новое описание заявки :");
            Item item = new Item(name, desc, 1L);
            // у новой заявки устанавливаем тот же id
            item.setId(id);
            this.tracker.replace(id, item);
            System.out.println("------------ Заявка с Id : " + id + " отредактирована -----------");
        }
    }

    /**
     * Метод реализует удаление заявки из хранилища.
     */
    private void deleteItem() {
        boolean exit = false;
        String id = null;
        System.out.println("------------ Удаление заявки --------------");
        while (!exit) {
            id = this.input.ask("Введите id заявки, которую необходимо удалить :");
            // Проверим существует ли заявка с таким id в хранилище
            if ((this.tracker.findById(id) == null) && !id.equals("q")) {
                System.out.println("Вы ввели некорректный id заявки! Проверьте свои данные и введите правильный id или q - для выхода.");
            } else {
                exit = true;
            }
        }
        if (!id.equals("q")) {
            this.tracker.delete(id);
            System.out.println("------------ Заявка с Id : " + id + " удалена -----------");
        }
    }

    /**
     * Метод реализует поиск заявки в хранилище по Id.
     */
    private void findIdItem() {
        System.out.println("------------ Поиск заявки по Id --------------");
        String id = this.input.ask("Введите id заявки, которую необходимо найти :");
        Item item = tracker.findById(id);
        if (item == null) {
            System.out.println("Заявка с таким id отсутствует в хранилище.");
        } else {
            System.out.println(item.getId() + " " + item.getName() + " " + item.getDescription());
        }
        System.out.println("------------ Поиск заявки с Id : " + id + " завершен -----------");
    }

    /**
     * Метод реализует поиск заявки в хранилище по имени
     */
    private void findNameItem() {
        System.out.println("------------ Поиск заявки по Name --------------");
        String name = this.input.ask("Введите имя заявки, которую необходимо найти :");
        for (Item item : this.tracker.findByName(name)) {
            System.out.println(item.getId() + " " + item.getName() + " " + item.getDescription());
        }
        System.out.println("------------ Поиск заявки с Name : " + name + " завершен -----------");
    }

    private void showMenu() {
        System.out.println("     МЕНЮ    ");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }

    /**
     * Запуск программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}

