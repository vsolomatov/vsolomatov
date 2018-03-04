package ru.job4j.tracker;

public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private UserAction[] menus = new UserAction[7];

    // Конструктор
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }
    /**
     * Общий АБСТРАКТНЫЙ класс для действий.
     */
    public abstract class BaseAction implements UserAction {
        private final int key;
        private final String name;

        protected BaseAction(final int key, final String name) {
            this.key = key;
            this.name = name;
        }

        @Override
        public int returnKey() {
            return this.key;
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key, this.name);
        }
    }

    /**
     * Класс реализует добавленяи новый заявки в хранилище.
     */
    private class AddItem extends BaseAction {
        public AddItem(int key, String name) {
            super(key, name);
        }

        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            Item newItem = tracker.add(new Item(name, desc, 123L));
            System.out.println("------------ Создана новая заявка с Id : " + newItem.getId() + " -----------");
        }
    }

    /**
     * Класс реализует редактирование заявки в хранилище.
     */
    private class EditItem extends BaseAction {
        public EditItem(int key, String name) {
            super(key, name);
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
    }

    /**
     * Класс реализует вывод всех заявок в консоль.
     */
    private class ShowItems extends BaseAction {
        public ShowItems(int key, String name) {
            super(key, name);
        }
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Все заявки --------------");
            for (Item item : tracker.findAll()) {
                System.out.println(item.getId() + " " + item.getName() + " " + item.getDescription());
            }
            System.out.println("--------------------------------------");
        }
    }

    /**
     * Класс реализует удаление заявки из хранилища.
     */
    private class DeleteItem extends BaseAction {
        public DeleteItem(int key, String name) {
            super(key, name);
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
    }

    /**
     * Класс реализует поиск заявки в хранилище по Id.
     */
    private class FindItemById extends BaseAction {
        public FindItemById(int key, String name) {
            super(key, name);
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
    }

    /**
     * Класс реализует поиск заявки в хранилище по имени
     */
    private class FindItemByName extends BaseAction {
        public FindItemByName(int key, String name) {
            super(key, name);
        }
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявки по Name --------------");
            String name = input.ask("Введите имя заявки, которую необходимо найти :");
            for (Item item : tracker.findByName(name)) {
                System.out.println(item.getId() + " " + item.getName() + " " + item.getDescription());
            }
            System.out.println("------------ Поиск заявки с Name : " + name + " завершен -----------");
        }
    }

    /**
     * Класс реализует пункт меню "выход из меню"
     */
    private class ExitFromMenu extends BaseAction {
        public ExitFromMenu(int key, String name) {
            super(key, name);
        }
        public void execute(Input input, Tracker tracker) {
            // просто ничего не делает
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
    /**
     * Метод реализует заполнение меню пунктами
     */
    public void fillMenus() {
        this.menus[0] = new AddItem(0, "Add new Item");
        this.menus[1] = new ShowItems(1, "Show all items");
        this.menus[2] = new EditItem(2, "Edit item");
        this.menus[3] = new DeleteItem(3, "Delete item");
        this.menus[4] = new FindItemById(4, "Find item by Id");
        this.menus[5] = new FindItemByName(5, "Find items by name");
        this.menus[6] = new ExitFromMenu(6, "Exit Program");
    }

    /**
     * Метод возвращает массив номеров пунктов меню в виде целых чисел
     */
    public int[] menusNumber() {
        int[] numbers = new int[this.menus.length];
        int index = 0;
        for (UserAction menuitem : this.menus) {
            numbers[index] = menuitem.returnKey();
            index++;
            //System.out.println(menuitem.returnKey());
        }
        return numbers;
    }

    public void select(int key) {
        this.menus[key].execute(this.input, this.tracker);
    }
}
