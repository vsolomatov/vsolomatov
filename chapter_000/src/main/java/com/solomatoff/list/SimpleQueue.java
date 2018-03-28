package com.solomatoff.list;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private NodeContainer<T> container = new NodeContainer<>();

    public T poll() throws NoSuchElementException {
        return container.deleteFirst();
    }

    public void push(T value) {
        container.add(value);
    }
}
