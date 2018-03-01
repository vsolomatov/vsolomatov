package ru.job4j.tracker;

import ru.job4j.tracker.models.*;

/**
 * Класс реализует редактирование заявки в хранилище.
 */
class EditItem implements UserAction {
    public int setKey() {
        return 2;
    }

    public void execute(Input input, Tracker tracker) {
        boolean exit = false;
        String id = null;
        System.out.println("------------ Редактирование заявки --------------");
        while (!exit) {
            id = input.ask("Введите id заявки, которую необходимо отредактировать :");
            // Проверим существует ли заявка с таким id в хранилище
            if ((tracker.findById(id) == null) && !id.equals("q")) {
                System.out.println("Вы ввели некорректный id заявки! Проверьте свои данные и введите правильный id или q - для выхода.");
            } else {
                exit = true;
            }
        }
        if (!id.equals("q")) {
            String name = input.ask("Введите новое имя заявки :");
            String desc = input.ask("Введите новое описание заявки :");
            Item item = new Item(name, desc, 1L);
            // у новой заявки устанавливаем тот же id
            item.setId(id);
            tracker.replace(id, item);
            System.out.println("------------ Заявка с Id : " + id + " отредактирована -----------");
        }
    }

    public String info() {
        return String.format("%s. %s", this.setKey(), "Edit item");
    }
}

public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private UserAction[] menus = new UserAction[7];

    // Конструктор
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    // Внутренний приватный класс

    /**
     * Класс реализует добавленяи новый заявки в хранилище.
     */
    private class AddItem implements UserAction {
        public int setKey() {
            return 0;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            Item newItem = tracker.add(new Item(name, desc, 123L));
            System.out.println("------------ Создана новая заявка с Id : " + newItem.getId() + " -----------");
        }

        public String info() {
            return String.format("%s. %s", this.setKey(), "Add new Item");
        }
    }

    // Внутренний приватный СТАТИЧЕСКИЙ класс

    /**
     * Класс реализует вывод всех заявок в консоль.
     */
    private static class ShowItems implements UserAction {
        public int setKey() {
            return 1;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Все заявки --------------");
            for (Item item : tracker.findAll()) {
                System.out.println(item.getId() + " " + item.getName() + " " + item.getDescription());
            }
            System.out.println("--------------------------------------");
        }

        public String info() {
            return String.format("%s. %s", this.setKey(), "Show all items");
        }
    }

    // Внутренний приватный СТАТИЧЕСКИЙ класс

    /**
     * Класс реализует удаление заявки из хранилища.
     */
    private static class DeleteItem implements UserAction {
        public int setKey() {
            return 3;
        }

        public void execute(Input input, Tracker tracker) {
            boolean exit = false;
            String id = null;
            System.out.println("------------ Удаление заявки --------------");
            while (!exit) {
                id = input.ask("Введите id заявки, которую необходимо удалить :");
                // Проверим существует ли заявка с таким id в хранилище
                if ((tracker.findById(id) == null) && !id.equals("q")) {
                    System.out.println("Вы ввели некорректный id заявки! Проверьте свои данные и введите правильный id или q - для выхода.");
                } else {
                    exit = true;
                }
            }
            if (!id.equals("q")) {
                tracker.delete(id);
                System.out.println("------------ Заявка с Id : " + id + " удалена -----------");
            }
        }

        public String info() {
            return String.format("%s. %s", this.setKey(), "Delete item");
        }
    }

    // Внутренний приватный СТАТИЧЕСКИЙ класс

    /**
     * Класс реализует поиск заявки в хранилище по Id.
     */
    private static class FindItemById implements UserAction {
        public int setKey() {
            return 4;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявки по Id --------------");
            String id = input.ask("Введите id заявки, которую необходимо найти :");
            Item item = tracker.findById(id);
            if (item == null) {
                System.out.println("Заявка с таким id отсутствует в хранилище.");
            } else {
                System.out.println(item.getId() + " " + item.getName() + " " + item.getDescription());
            }
            System.out.println("------------ Поиск заявки с Id : " + id + " завершен -----------");
        }

        public String info() {
            return String.format("%s. %s", this.setKey(), "Find item by Id");
        }
    }

    // Внутренний приватный СТАТИЧЕСКИЙ класс

    /**
     * Класс реализует поиск заявки в хранилище по имени
     */
    private static class FindItemByName implements UserAction {
        public int setKey() {
            return 5;
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявки по Name --------------");
            String name = input.ask("Введите имя заявки, которую необходимо найти :");
            for (Item item : tracker.findByName(name)) {
                System.out.println(item.getId() + " " + item.getName() + " " + item.getDescription());
            }
            System.out.println("------------ Поиск заявки с Name : " + name + " завершен -----------");
        }

        public String info() {
            return String.format("%s. %s", this.setKey(), "Find items by name");
        }
    }

    // Внутренний приватный СТАТИЧЕСКИЙ класс

    /**
     * Класс реализует пункт меню "выход из меню"
     */
    private static class ExitFromMenu implements UserAction {
        public int setKey() {
            return 6;
        }

        public void execute(Input input, Tracker tracker) {
        }

        public String info() {
            return String.format("%s. %s", this.setKey(), "Exit Program");
        }
    }

    public void showMenu() {
        System.out.println("     МЕНЮ    ");
        for (UserAction action : this.menus) {
            if (action != null) {
                System.out.println((action.info()));
            }
        }
    }

    public void fillMenus() {
        this.menus[0] = this.new AddItem();
        this.menus[1] = new MenuTracker.ShowItems();
        this.menus[2] = new EditItem();
        this.menus[3] = new MenuTracker.DeleteItem();
        this.menus[4] = new MenuTracker.FindItemById();
        this.menus[5] = new MenuTracker.FindItemByName();
        this.menus[6] = new MenuTracker.ExitFromMenu();
    }

    public void select(int key) {
        this.menus[key].execute(this.input, this.tracker);
    }
}
