package com.solomatoff.list;

public class Node<T> {
    private T value;
    Node<T> next;

    public Node(T value) {
        this.value = value;
    }
}
