package com.solomatoff.tracker.store;

import com.solomatoff.tracker.Item;

import java.util.List;

public interface Store {
    Item add(Item item);

    boolean replace(String id, Item item);

    boolean delete(String id);

    List<Item> findAll();

    List<Item> findByName(String key);

    Item findById(String id);
}
