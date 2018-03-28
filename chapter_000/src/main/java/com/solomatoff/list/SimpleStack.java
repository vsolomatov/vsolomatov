package com.solomatoff.list;

import java.util.NoSuchElementException;

public class SimpleStack<T> {
    private NodeContainer<T> container = new NodeContainer<>();

    public T poll() throws NoSuchElementException {
        return container.deleteLast();
    }

    public void push(T value) {
        container.add(value);
    }
}
