package com.solomatoff.list;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class NodeContainer<E> implements Iterable<E> {
    // Счетчик изменений коллекции
    private int modCount = 0;
    private int size = 0;
    private Node<E> lastItem = null;
    private Node<E> firstItem = null;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public void add(E e) {
        Node<E> newItem = new Node<>(null, e, null);
        if (lastItem == null) {
            firstItem = newItem;
            lastItem = newItem;
        } else {
            lastItem.next = newItem;
            newItem.prev = lastItem;
            lastItem = newItem;
        }
        this.size++;
        this.modCount++;
    }

    public E get(int position) throws NoSuchElementException {
        if (position >= this.size) {
            throw new NoSuchElementException();
        }
        E result;
        result = this.firstItem.item;
        Node<E> currItem = this.firstItem;
        for (int i = 0; i < this.size; i++) {
            if (i == position) {
                result = currItem.item;
            }
            currItem = currItem.next;
        }
        return result;
    }

    @NotNull
    @Override
    public Iterator<E> iterator() throws ConcurrentModificationException {
        return new Iterator<E>() {
            // Ожидаемое значение счетчика изменений коллекции
            private int expectedModCount = modCount;
            private int indexIterator = 0;

            @Override
            public boolean hasNext() {
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (this.indexIterator < size);
            }

            @Override
            public E next() {
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (this.indexIterator >= size) {
                    throw new NoSuchElementException();
                }
                return get(indexIterator++);
            }
        };
    }
}
