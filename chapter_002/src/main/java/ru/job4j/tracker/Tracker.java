package ru.job4j.tracker;

import java.util.*;

public class Tracker {
    private final ArrayList<Item> items = new ArrayList<>();
    private static final Random RN = new Random();

    /**
     * Метод генерирует уникальный id для новых заявок
     *
     *  @return ссылку список items
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     *  Метод возвращает индекс в списке items элемента с заданным id
     * @param id идентификатор Item'а
     * @return индекс в списке items элемента с заданным id, возвращает -1, если элемент отсутствует
     */
    private int findIndexInItemsById(String id) {
        int index = -1;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     *  Метод добавляет новую заявку в список заявок (items)
     * @param item новая заявка
     * @return ссылку на добавленную заявку
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Заменяет уже имеющуюся заявку с идентификатором id на новую заявку (например, на вновь созданную)
     *
     * @param id Идентификатор заявки
     * @param item Заявка, на которую нужно заменить заявку с идентификатором id
     */
    public void replace(String id, Item item) {
        int index = findIndexInItemsById(id);
        if (index >= 0) {
            items.set(index, item);
        }
    }

    /**
     * Удаляет уже имеющуюся заявку с идентификатором id
     *
     * @param id Идентификатор заявки
     *
     */
    public void delete(String id) {
        int index = findIndexInItemsById(id);
        if (index >= 0) {
            items.remove(index);
        }
    }

    /**
     * Находит заявку с идентификатором id
     *
     * @param id Идентификатор заявки
     * @return ссылку на найденную заявку или null
     */
    public Item findById(String id) {
        int index = findIndexInItemsById(id);
        if (index >= 0) {
            return items.get(index);
        } else {
            return null;
        }
    }

    /**
     * Находит заявку с именем name
     *
     * @param name имя заявки
     * @return ссылку на найденную заявку или null
     */
    public ArrayList<Item> findByName(String name) {
        ArrayList<Item> result = new ArrayList<>();
        for (Item itemitr : items) {
            if (itemitr.getName().equals(name)) {
                result.add(itemitr);
            }
        }
        return result;
    }

    /**
     * Метод возвращает список всех заявок
     *
     *  @return ссылку на список items
     */
    public ArrayList<Item> findAll() {
        return this.items;
    }
}
