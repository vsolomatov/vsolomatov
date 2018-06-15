package com.solomatoff.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class TrackerTest {
    @Test
    public void whenAddItems() {
        Tracker tracker = new Tracker("tracker.properties");
        // Добавляем заявки в трекер и комментарии к ним.
        String[] comments = new String[] {"Комментарий #1", "Комментарий #2", "Комментарий #3"};
        Item item = new Item(null, "test1", "test1 Description", null, comments);
        tracker.add(item);

        item = new Item(null, "test2", "test2 Description", null, null);
        String itemId = tracker.add(item);
        comments = new String[] {"Комментарий #1 к заявке " + itemId, "Комментарий #2 к заявке " + itemId, "Комментарий #3 к заявке " + itemId};
        tracker.commentsInsert(itemId, comments);

        item = new Item(null, "test3", "test3 Description", null, null);
        itemId = tracker.add(item);
        tracker.commentsInsert(itemId, null);

        item = new Item(null, "test5", "test5 Description", null, null);
        itemId = tracker.add(item);
        comments = new String[] {"Комментарий #1 к заявке " + itemId};
        tracker.commentsInsert(itemId, comments);

        item = new Item(null, "test6", "test6 Description", null, null);
        itemId = tracker.add(item);
        comments = new String[] {};
        tracker.commentsInsert(itemId, comments);

       //System.out.println("Список ВСЕХ заявок");
        int index = 1;
        for (Item itemLoop : tracker.findAll()) {
           //System.out.println("    [" + index + "] " + itemLoop.getId() + " " + itemLoop.getName() + " " + itemLoop.getDescription() + " " + itemLoop.getCreated() + " " + itemLoop.commentsToString());
            index++;
        }
    }

    @Test
    public void whenReplaceItemNameAndDescriptionThenReturnNewItemNameAndDescription() {
        Tracker tracker = new Tracker("tracker.properties");
        // Добавляем заявку в трекер и комментарии к ней.
        Item item = new Item(null, "test5", "test5 Description", null, null);
        String itemId = tracker.add(item);
        String[] comments = new String[] {"Комментарий #1 к заявке " + itemId};
        tracker.commentsInsert(itemId, comments);

        item = tracker.findById(itemId);
       //System.out.println("    Заявка ДО ИЗМЕНЕНИЯ " + item.getId() + " " + item.getName() + " " + item.getDescription() + " " + item.getCreated() + " " + item.commentsToString());

        Item itemForReplace = new Item(null, "test replace", "test replace Description", null, null);
        // Обновляем заявку в трекере.
       //System.out.println("Изменяем заявку с itemId = " + itemId);
        tracker.replace(itemId, itemForReplace);

        // Проверяем, что заявка с таким itemId имеет новое имя test6.
        assertThat(tracker.findById(itemId).getName(), is("test replace"));
        // Проверяем, что заявка с таким id имеет новое описание testDescription2.
        assertThat(tracker.findById(itemId).getDescription(), is("test replace Description"));

        Item itemReplaced = tracker.findById(itemId);
       //System.out.println("    Заявка ПОСЛЕ ИЗМЕНЕНИЯ " + itemReplaced.getId() + " " + itemReplaced.getName() + " " + itemReplaced.getDescription() + " " + itemReplaced.getCreated() + " " + itemReplaced.commentsToString());
    }

    @Test
    public void whenDeleteItemById() {
        Tracker tracker = new Tracker("tracker.properties");
        // Добавляем заявку в трекер и комментарии к ней.
        Item item = new Item(null, "test5", "test5 Description", null, null);
        String itemId = tracker.add(item);
        String[] comments = new String[] {"Комментарий #1 к заявке " + itemId};
        tracker.commentsInsert(itemId, comments);

       //System.out.println("Список заявок ДО УДАЛЕНИЯ");
        for (Item itemLoop : tracker.findAll()) {
            System.out.println("    " + itemLoop.getId() + " " + itemLoop.getName() + " " + itemLoop.getDescription() + " " + itemLoop.getCreated() + " " + itemLoop.commentsToString());
        }

       //System.out.println("Удаляем заявку с itemId = " + itemId);
        // Удаляем заявку в трекере.
        tracker.delete(itemId);

        // Проверяем, что заявка с таким id отсутствует
        assertThat(tracker.findById(itemId), is(nullValue()));

       //System.out.println("Список заявок ПОСЛЕ УДАЛЕНИЯ");
        for (Item itemLoop : tracker.findAll()) {
            System.out.println("    " + itemLoop.getId() + " " + itemLoop.getName() + " " + itemLoop.getDescription() + " " + itemLoop.getCreated() + " " + itemLoop.commentsToString());
        }
    }

    @Test
    public void whenFindByName() {
        Tracker tracker = new Tracker("tracker.properties");
        // Добавляем заявку в трекер и комментарии к ней.
        Item item = new Item(null, "test5", "test5 Description", null, null);
        String itemId = tracker.add(item);
        String[] comments = new String[] {"Комментарий #1 к заявке " + itemId};
        tracker.commentsInsert(itemId, comments);

        // Находим такие заявки в трекере.
       //System.out.println("Список заявок c name = test5");
        for (Item itemLoop : tracker.findByName("test5")) {
            System.out.println("    " + itemLoop.getId() + " " + itemLoop.getName() + " " + itemLoop.getDescription() + " " + itemLoop.getCreated() + " " + itemLoop.commentsToString());
        }
    }
}
