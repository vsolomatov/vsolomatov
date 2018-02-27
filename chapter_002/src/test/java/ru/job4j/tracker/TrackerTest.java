package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import ru.job4j.tracker.models.*;

public class TrackerTest {
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();

        // Добавляем заявки в трекер. Теперь в объектах проинициализирован id.
        Item itemone = new Item("test1", "testDescription", 123L);
        tracker.add(itemone);
        itemone = new Item("test3", "testDescription3", 123L);
        tracker.add(itemone);
        itemone = new Item("test5", "testDescription5", 123L);
        tracker.add(itemone);

        /*for (Item item : tracker.findAll()) {
            System.out.println(item.getId() + " " + item.getName() + " " + item.getDescription());
        }
        System.out.println(itemone.getId());*/

        // Создаем новую заявку.
        Item itemtwo = new Item("test2", "testDescription2", 1234L);
        // Проставляем в новую заявку старый id из itemone, который был сгенерирован выше.
        // Для того, чтобы после замены заявки у нее остался тот же id
        itemtwo.setId(itemone.getId());

        // Обновляем заявку в трекере.
        tracker.replace(itemone.getId(), itemtwo);

        // Проверяем, что заявка с таким id имеет новое имя test2.
        assertThat(tracker.findById(itemone.getId()).getName(), is("test2"));
        // Проверяем, что заявка с таким id имеет новое описание testDescription2.
        assertThat(tracker.findById(itemone.getId()).getDescription(), is("testDescription2"));

        /*for (Item item : tracker.findAll()) {
            System.out.println(item.getId() + " " + item.getName() + " " + item.getDescription());
        }*/
    }
    @Test
    public void whenDeleteIdThen() {
        Tracker tracker = new Tracker();

        // Добавляем заявки в трекер. Теперь в объектах проинициализирован id.
        Item itemone = new Item("test1", "testDescription", 123L);
        tracker.add(itemone);

        itemone = new Item("test3", "testDescription3", 123L);
        tracker.add(itemone);

        itemone = new Item("test5", "testDescription5", 123L);
        tracker.add(itemone);
        // Запоминаем id этой заявки - ее будем удалять
        String idDelete = itemone.getId();

        itemone = new Item("test7", "testDescription7", 123L);
        tracker.add(itemone);

        /*for (Item item : tracker.findAll()) {
            System.out.println(item.getId() + " " + item.getName() + " " + item.getDescription());
        }
        System.out.println(idDelete);*/

        // Удаляем заявку в трекере.
        tracker.delete(idDelete);

        // Проверяем, что заявка с таким id отсутствует
       assertThat(tracker.findById(idDelete), is(nullValue()));

        /*for (Item item : tracker.findAll()) {
            System.out.println(item.getId() + " " + item.getName() + " " + item.getDescription());
        }*/
    }
}
