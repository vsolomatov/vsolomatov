package com.solomatoff.list;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private SimpleLinked<T> container = new SimpleLinked<>();

    public T poll() throws NoSuchElementException {
        return container.deleteFirst();
    }

    public void push(T value) {
        container.add(value);
    }
}
