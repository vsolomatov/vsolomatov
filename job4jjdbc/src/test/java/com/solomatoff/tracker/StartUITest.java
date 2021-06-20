package com.solomatoff.tracker;

import com.solomatoff.tracker.store.Store;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;

import org.junit.After;
import org.junit.Before;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class StartUITest {
    // поле содержит дефолтный вывод в консоль.
    private final PrintStream stdout = System.out;
    // буфер для результата.
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    // Сформируем строку меню
    private final String menu = "     МЕНЮ    " + System.lineSeparator() + "10. Add new Item" + System.lineSeparator() + "20. Show all items" + System.lineSeparator() + "30. Edit item" + System.lineSeparator() + "40. Delete item" + System.lineSeparator() + "50. Find item by Id" + System.lineSeparator() + "60. Find items by name" + System.lineSeparator() + "70. Exit Program" + System.lineSeparator();

    @Before
    public void loadOutput() {
       //System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
       //System.out.println("execute after method");
        //System.out.println(new String(out.toByteArray()));
    }

    @Test
    public void whenUserChooseFindAll() {
        // создаём Tracker
        Store tracker = new Tracker("tracker.properties");
        // Очищаем трекер
        tracker.deleteAll();
        // Добавляем заявки в трекер. И запоминаем их id в массиве itemid.
        String[] itemid = new String[3];
        String[] comments = new String[] {"Комментарий #1 к заявке ", "Комментарий #2 к заявке "};
        Item item = new Item(null, "test1", "test1 Description", null);
        itemid[0] = tracker.add(item);

        comments = new String[] {"Комментарий #3 к заявке ", "Комментарий #4 к заявке "};
        item = new Item(null, "test3", "test3 Description", null);
        itemid[1] = tracker.add(item);

        comments = new String[] {"Комментарий #5 к заявке ", "Комментарий #6 к заявке "};
        item = new Item(null, "test5", "test5 Description", null);
        itemid[2] = tracker.add(item);

        // Cоздаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"20", "70"});
        //   создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();

        assertThat(new String(out.toByteArray()), is(menu + "------------ Все заявки --------------" + System.lineSeparator() + itemid[0] + " test1 test1 Description" + System.lineSeparator() + itemid[1] + " test3 test3 Description" + System.lineSeparator() + itemid[2] + " test5 test5 Description" + System.lineSeparator() + "--------------------------------------" + System.lineSeparator() + menu));
    }

    @Test
    public void whenUserChooseFindById() {
        // Cоздаём Tracker
        Store tracker = new Tracker("tracker.properties");
        // Очищаем трекер
        tracker.deleteAll();
        // Добавляем заявки в трекер. И запоминаем их id в массиве itemid.
        String[] itemid = new String[3];
        String[] comments = new String[] {"Комментарий #1 к заявке ", "Комментарий #2 к заявке "};
        Item item = new Item(null, "test1", "test1 Description", null);
        itemid[0] = tracker.add(item);

        comments = new String[] {"Комментарий #3 к заявке ", "Комментарий #4 к заявке "};
        item = new Item(null, "test3", "test3 Description", null);
        itemid[1] = tracker.add(item);

        comments = new String[] {"Комментарий #5 к заявке ", "Комментарий #6 к заявке "};
        item = new Item(null, "test5", "test5 Description", null);
        itemid[2] = tracker.add(item);

        // Cоздаём StubInput с последовательностью действий
        // Проверяем два случая: когда введен id второй заявки и когда введен некорректный id
        Input input = new StubInput(new String[]{"50", itemid[1], "50", "1111111111", "70"});

        //   создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();

        // проверяем
        assertThat(new String(out.toByteArray()), is(menu + "------------ Поиск заявки по Id --------------" + System.lineSeparator() + itemid[1] + " test3 test3 Description" + System.lineSeparator() + "------------ Поиск заявки с Id : " + itemid[1] + " завершен -----------" + System.lineSeparator() + menu + "------------ Поиск заявки по Id --------------" + System.lineSeparator() + "Заявка с таким id отсутствует в хранилище." + System.lineSeparator() + "------------ Поиск заявки с Id : 1111111111 завершен -----------" + System.lineSeparator() + menu));
    }

    @Test
    public void whenUserChooseFindByName() {
        // создаём Tracker
        Store tracker = new Tracker("tracker.properties");
        // Очищаем трекер
        tracker.deleteAll();
        // Добавляем заявки в трекер. И запоминаем их id в массиве itemid.
        String[] itemid = new String[3];
        String[] comments = new String[] {"Комментарий #1 к заявке ", "Комментарий #2 к заявке "};
        Item item = new Item(null, "test1", "test1 Description", null);
        itemid[0] = tracker.add(item);

        comments = new String[] {"Комментарий #3 к заявке ", "Комментарий #4 к заявке "};
        item = new Item(null, "test2", "test2 Description", null);
        itemid[1] = tracker.add(item);

        comments = new String[] {"Комментарий #5 к заявке ", "Комментарий #6 к заявке "};
        item = new Item(null, "test5", "test5 Description", null);
        itemid[2] = tracker.add(item);

        // Cоздаём StubInput с последовательностью действий
        // Проверяем два случая: когда введен name второй заявки и когда введен некорректный name
        Input input = new StubInput(new String[]{"60", "test2", "60", "test4", "70"});

        //   создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();

        // проверяем
        assertThat(new String(out.toByteArray()), is(menu + "------------ Поиск заявки по Name --------------" + System.lineSeparator() + itemid[1] + " test2 test2 Description" + System.lineSeparator() + "------------ Поиск заявки с Name : test2 завершен -----------" + System.lineSeparator() + menu + "------------ Поиск заявки по Name --------------" + System.lineSeparator() + "------------ Поиск заявки с Name : test4 завершен -----------" + System.lineSeparator() + menu));
    }

    @Test
    public void whenUserAddItemThenTrackerHasNewItem() {
        Store tracker = new Tracker("tracker.properties");     // создаём Tracker
        // Очищаем трекер
        tracker.deleteAll();
        Input input = new StubInput(new String[]{"10", "test add", "desc", "70"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.findAll().get(0).getName(), is("test add")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        Store tracker = new Tracker("tracker.properties");
        // Очищаем трекер
        tracker.deleteAll();
        String[] comments = new String[] {"Комментарий #1 к заявке ", "Комментарий #2 к заявке "};
        Item item = new Item(null, "test1", "test1 Description", null);
        String itemid = tracker.add(item);

        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"30", itemid, "test update", "new desc", "70"});

        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();

        // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
        assertThat(tracker.findById(itemid).getName(), is("test update"));
    }

    @Test
    public void whenDeleteThenTrackerHasNotValue() {
        Store tracker = new Tracker("tracker.properties");
        // Очищаем трекер
        tracker.deleteAll();
        String[] comments = new String[] {"Комментарий #1 к заявке ", "Комментарий #2 к заявке "};
        Item item = new Item(null, "test1", "test1 Description", null);
        String itemid = tracker.add(item);

        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"40", itemid, "70"});

        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();

        // проверяем, что в трекере не существует заявки с указанным id.
       //System.out.println("itemid = " + itemid);
        assertThat(tracker.findById(itemid), is(nullValue()));
    }
}