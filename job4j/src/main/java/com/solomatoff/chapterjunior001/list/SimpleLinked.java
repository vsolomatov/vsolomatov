package com.solomatoff.chapterjunior001.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@ThreadSafe
public class SimpleLinked<E> implements SimpleContainer<E> {
    // Счетчик изменений коллекции
    @GuardedBy("this")
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

    synchronized public void add(E e) {
        String threadName = Thread.currentThread().getName();
        System.out.println("    Начинаем add " + e + " из потока " + threadName);
        Node<E> newItem = new Node<>(null, e, null);
        if (this.lastItem == null) {
            this.firstItem = newItem;
            this.lastItem = newItem;
        } else {
            this.lastItem.next = newItem;
            newItem.prev = lastItem;
            this.lastItem = newItem;
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        this.size++;
        this.modCount++;
        System.out.println("    Заканчиваем add " + e + " из потока " + threadName);
    }

    synchronized public E get(int position) throws NoSuchElementException {
        if (position >= this.size) {
            throw new NoSuchElementException();
        }
        E result = this.firstItem.item;
        Node<E> currItem = this.firstItem;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < this.size; i++) {
            if (i == position) {
                result = currItem.item;
            }
            currItem = currItem.next;
        }
        return result;
    }

    synchronized public void delete(int position) throws NoSuchElementException {
        String threadName = Thread.currentThread().getName();
        System.out.println("        Начинаем delete с индексом " + position + " из потока " + threadName);
        if (position >= this.size) {
            throw new NoSuchElementException();
        }
        Node<E> currItem = this.firstItem;
        Node<E> prevItem = currItem.prev;
        Node<E> nextItem = currItem.next;
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        System.out.println("        Заканчиваем delete с индексом " + position + " из потока " + threadName);
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
    synchronized public Iterator<E> iterator() throws ConcurrentModificationException {
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
/**
 *  Интерфейс задает структуру динамического контейнера.
 * @param <E> параметр типа для данного вида контейнера.
 */
interface SimpleContainer<E> extends Iterable<E> {
    void add(E e);
    E get(int index);
}
