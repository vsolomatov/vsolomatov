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
        if (this.lastItem == null) {
            this.firstItem = newItem;
            this.lastItem = newItem;
        } else {
            this.lastItem.next = newItem;
            newItem.prev = lastItem;
            this.lastItem = newItem;
        }
        this.size++;
        this.modCount++;
    }

    public E get(int position) throws NoSuchElementException {
        if (position >= this.size) {
            throw new NoSuchElementException();
        }
        E result = this.firstItem.item;
        Node<E> currItem = this.firstItem;
        for (int i = 0; i < this.size; i++) {
            if (i == position) {
                result = currItem.item;
            }
            currItem = currItem.next;
        }
        return result;
    }

    public void delete(int position) throws NoSuchElementException {
        if (position >= this.size) {
            throw new NoSuchElementException();
        }
        Node<E> currItem = this.firstItem;
        Node<E> prevItem = currItem.prev;
        Node<E> nextItem = currItem.next;
        for (int i = 0; i < this.size; i++) {
            if (i == position) {
                if  (currItem.prev != null) {
                    prevItem.next = nextItem;
                }
                if  (currItem.next != null) {
                    nextItem.prev = prevItem;
                }
                break;
            } else {
                prevItem = currItem;
                currItem = currItem.next;
                nextItem = currItem.next;
            }
        }
        // Если удаляется первый элемент и он же последний
        if ((position == 0) && (size == 1)) {
            this.firstItem = null;
            this.lastItem = null;
        } else {
            // Если удаляется первый элемент
            if (position == 0) {
                this.firstItem = nextItem;
            }
            // Если удаляется последний элемент
            if (position == size - 1) {
                this.lastItem = prevItem;
            }
        }
        this.size--;
        this.modCount++;
    }

    public E deleteFirst() throws NoSuchElementException {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        E result = this.firstItem.item;
        delete(0);
        return result;
    }

    public E deleteLast() throws NoSuchElementException {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        E result = this.lastItem.item;
        delete(this.size - 1);
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
