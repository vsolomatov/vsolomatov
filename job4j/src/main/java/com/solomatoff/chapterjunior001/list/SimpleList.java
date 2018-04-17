package com.solomatoff.chapterjunior001.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

@ThreadSafe
public class SimpleList<E> implements SimpleContainer<E> {
    // Счетчик изменений коллекции
    private int modCount = 0;
    @GuardedBy("this")
    private Object[] container = new Object[3];
    private int index = 0;

    @Override
    synchronized public void add(E e) {
        String threadName = Thread.currentThread().getName();
        System.out.println("    Начинаем add " + e + " из потока " + threadName);
        int oldCapacity = this.container.length;
        if (this.index == oldCapacity) {
            int newCapacity = oldCapacity * 2;
            this.container = Arrays.copyOf(this.container, newCapacity);
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        this.container[index++] = e;
        this.modCount++;
        System.out.println("    Заканчиваем add " + e + " из потока " + threadName);
    }

    @Override
    synchronized public E get(int position) throws NoSuchElementException {
        String threadName = Thread.currentThread().getName();
        System.out.println("Начинаем get с индексом " + position + " из потока " + threadName);
        if (position >= this.index) {
            throw new NoSuchElementException();
        }
        try {
            Thread.sleep(400);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println("Заканчиваем get с индексом " + position + " из потока " + threadName);
        return (E) this.container[position];
    }

    @NotNull
    @Override
    synchronized public Iterator<E> iterator() throws ConcurrentModificationException {
        String threadName = Thread.currentThread().getName();
        System.out.println("Вход в итератор  из потока " + threadName);
        return new Iterator<E>() {
            // Ожидаемое значение счетчика изменений коллекции
            private int expectedModCount = modCount;
            private int indexIterator = 0;

            @Override
            public boolean hasNext() {
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (this.indexIterator < index);
            }

            @Override
            public E next() {
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (this.indexIterator >= index) {
                    throw new NoSuchElementException();
                }
                return (E) container[this.indexIterator++];
            }
        };
    }
}
