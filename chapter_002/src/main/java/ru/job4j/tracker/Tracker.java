package ru.job4j.tracker;

import java.util.*;
import ru.job4j.tracker.models.*;

public class Tracker {
    private final Item[] items = new Item[100];
    private int position = 0;
    private static final Random RN = new Random();

    public  Item add(Item item) {
        item.setId(this.generateId());
        this.items[position++] = item;
        return item;
    }
    /**
     * Заменяет уже имеющуюся заявку с идентификатором id на новую заявку (например, на вновь созданную)
     *
     * @param id Идентификатор заявки
     * @param item Заявка, на которую нужно заменить заявку с идентификатором id
     */
    public void replace(String id, Item item) {
        for (int index = 0; index != this.position; index++) {
            if (this.items[index].getId().equals(id)) {
                this.items[index] = item;
                break;
            }
        }
    }
    /**
     * Удаляет уже имеющуюся заявку с идентификатором id
     *
     * @param id Идентификатор заявки
     *
     */
    public void delete(String id) {
        Item[] itemarray = new Item[this.position - 1];
        for (int index = 0; index != this.position; index++) {
            if (this.items[index].getId().equals(id)) {
                if (index == 0) {
                    System.arraycopy(this.items, 1, itemarray, 0, this.position - 1);
                } else {
                    System.arraycopy(this.items, 0, itemarray, 0, index);
                    System.arraycopy(this.items, index + 1, itemarray, index, this.position - index - 1);
                }
                this.items[index] = null;
                this.position--;
                for (int i = 0; i != this.position; i++) {
                    this.items[i] = itemarray[i];
                }
                break;
            }
        }
    }
    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }
    public Item[] findByName(String name) {
        int qt = 0;
        Item[] temparray = new Item[this.position];
        for (int index = 0; index != this.position; index++) {
            if (this.items[index].getName().equals(name)) {
                temparray[qt] = this.items[index];
                qt++;
            }
        }
        Item[] result = new Item[qt];
        System.arraycopy(temparray, 0, result, 0, qt);
        return result;
    }

    String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    public Item[] findAll() {
        Item[] result = new Item[this.position];
        for (int index = 0; index != this.position; index++) {
            result[index] = this.items[index];
        }
        return result;
    }
}
