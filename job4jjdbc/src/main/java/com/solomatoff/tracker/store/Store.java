package com.solomatoff.tracker.store;

import com.solomatoff.tracker.Item;

import java.util.List;

public interface Store {
    String add(Item item);

    void commentsInsert(String id, String[] commentsArray);

    void replace(String id, Item item);

    void delete(String id);

    void deleteAll();

    List<Item> findAll();

    List<Item> findByName(String key);

    Item findById(String id);
}
