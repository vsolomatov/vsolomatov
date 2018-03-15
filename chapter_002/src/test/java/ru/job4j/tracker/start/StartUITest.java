package ru.job4j.tracker.start;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

import org.junit.After;
import org.junit.Before;
import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.StubInput;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.start.StartUI;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class StartUITest {
    // поле содержит дефолтный вывод в консоль.
    private final PrintStream stdout = System.out;
    // буфер для результата.
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    // Сформируем строку меню
    private final String menu = new StringBuilder()
            .append("     МЕНЮ    ")
            .append(System.lineSeparator())
            .append("10. Add new Item")
            .append(System.lineSeparator())
            .append("20. Show all items")
            .append(System.lineSeparator())
            .append("30. Edit item")
            .append(System.lineSeparator())
            .append("40. Delete item")
            .append(System.lineSeparator())
            .append("50. Find item by Id")
            .append(System.lineSeparator())
            .append("60. Find items by name")
            .append(System.lineSeparator())
            .append("70. Exit Program")
            .append(System.lineSeparator())
            .toString();

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    @Test
    public void whenUserChooseFindAll() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        // Добавляем заявки в трекер. И запоминаем их id в массиве itemid.
        Item itemnew;
        String[] itemid = new String[3];

        itemnew = new Item("test1", "testDescription1", 123L);
        itemid[0] = tracker.add(itemnew).getId();

        itemnew = new Item("test2", "testDescription2", 123L);
        itemid[1] = tracker.add(itemnew).getId();

        itemnew = new Item("test3", "testDescription3", 123L);
        itemid[2] = tracker.add(itemnew).getId();

        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"20", "70"});
        //   создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();
        // проверяем для случая когда введен id второй заявки
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append(menu)
                .append("------------ Все заявки --------------")
                .append(System.lineSeparator())
                .append(itemid[0] + " test1 testDescription1")
                .append(System.lineSeparator())
                .append(itemid[1] + " test2 testDescription2")
                .append(System.lineSeparator())
                .append(itemid[2] + " test3 testDescription3")
                .append(System.lineSeparator())
                .append("--------------------------------------")
                .append(System.lineSeparator())
                .append(menu)
                .toString()));
    }

    @Test
    public void whenUserChooseFindById() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        // Добавляем заявки в трекер. И запоминаем их id в массиве itemid.
        Item itemnew;
        String[] itemid = new String[3];

        itemnew = new Item("test1", "testDescription1", 123L);
        itemid[0] = tracker.add(itemnew).getId();

        itemnew = new Item("test2", "testDescription2", 123L);
        itemid[1] = tracker.add(itemnew).getId();

        itemnew = new Item("test3", "testDescription3", 123L);
        itemid[2] = tracker.add(itemnew).getId();

        //создаём StubInput с последовательностью действий, проверяем для случая когда введен id второй заявки
        // И для случая когда введен некорректный id
        Input input = new StubInput(new String[]{"50", itemid[1], "50", "1111111111", "70"});

        //   создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();

        // проверяем
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append(menu)
                .append("------------ Поиск заявки по Id --------------")
                .append(System.lineSeparator())
                .append(itemid[1] + " test2 testDescription2")
                .append(System.lineSeparator())
                .append("------------ Поиск заявки с Id : " + itemid[1] + " завершен -----------")
                .append(System.lineSeparator())
                .append(menu)
                .append("------------ Поиск заявки по Id --------------")
                .append(System.lineSeparator())
                .append("Заявка с таким id отсутствует в хранилище.")
                .append(System.lineSeparator())
                .append("------------ Поиск заявки с Id : 1111111111 завершен -----------")
                .append(System.lineSeparator())
                .append(menu)
                .toString()));
    }

    @Test
    public void whenUserChooseFindByName() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        // Добавляем заявки в трекер. И запоминаем их id в массиве itemid.
        Item itemnew;
        String[] itemid = new String[3];

        itemnew = new Item("test1", "testDescription1", 123L);
        itemid[0] = tracker.add(itemnew).getId();

        itemnew = new Item("test2", "testDescription2", 123L);
        itemid[1] = tracker.add(itemnew).getId();

        itemnew = new Item("test3", "testDescription3", 123L);
        itemid[2] = tracker.add(itemnew).getId();

        //создаём StubInput с последовательностью действий, проверяем для случая когда введен id второй заявки
        // И для случая когда введен некорректный id
        Input input = new StubInput(new String[]{"60", "test2", "60", "test4", "70"});

        //   создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();

        // проверяем
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append(menu)
                .append("------------ Поиск заявки по Name --------------")
                .append(System.lineSeparator())
                .append(itemid[1] + " test2 testDescription2")
                .append(System.lineSeparator())
                .append("------------ Поиск заявки с Name : test2 завершен -----------")
                .append(System.lineSeparator())
                .append(menu)
                .append("------------ Поиск заявки по Name --------------")
                .append(System.lineSeparator())
                .append("------------ Поиск заявки с Name : test4 завершен -----------")
                .append(System.lineSeparator())
                .append(menu)
                .toString()));
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[]{"10", "test name", "desc", "70"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.findAll().get(0).getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item("test name", "desc", 1L));

        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"30", item.getId(), "new test name", "new desc", "70"});

        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();

        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(item.getId()).getName(), is("new test name"));
    }

    @Test
    public void whenDeleteThenTrackerHasNotValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item("test name", "desc", 1L));

        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"40", item.getId(), "70"});

        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();

        // проверяем, что в трекере не существует заявки с указанным при эмуляции id.
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }
}