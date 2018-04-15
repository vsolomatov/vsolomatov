package com.solomatoff.chapterjunior001.list;

import java.util.NoSuchElementException;

public class SimpleStack<T> {
    private SimpleLinked<T> container = new SimpleLinked<>();

    public T poll() throws NoSuchElementException {
        return container.deleteLast();
    }

    public void push(T value) {
        container.add(value);
    }
}
