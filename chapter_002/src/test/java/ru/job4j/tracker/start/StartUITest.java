package ru.job4j.tracker.start;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsNull.nullValue;
import ru.job4j.tracker.*;

import ru.job4j.tracker.models.*;

public class StartUITest {

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.findAll()[0].getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }

    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        // создаём Tracker
        Tracker tracker = new Tracker();
        //Напрямую добавляем заявку
        Item item = tracker.add(new Item("test name", "desc", 1L));

        //создаём StubInput с последовательностью действий
        Input input = new StubInput(new String[]{"2", item.getId(), "new test name", "new desc", "6"});

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
        Input input = new StubInput(new String[]{"3", item.getId(), "6"});

        // создаём StartUI и вызываем метод init()
        new StartUI(input, tracker).init();

        // проверяем, что в трекере не существует заявки с указанным при эмуляции id.
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }
}