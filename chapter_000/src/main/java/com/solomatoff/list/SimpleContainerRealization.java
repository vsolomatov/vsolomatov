package com.solomatoff.list;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleContainerRealization<E> implements SimpleContainer<E> {
    // Счетчик изменений коллекции
    private int modCount = 0;
    private Object[] container = new Object[3];
    private int index = 0;

    @Override
    public void add(E e) {
        int oldCapacity = this.container.length;
        if (this.index == oldCapacity) {
            int newCapacity = oldCapacity * 2;
            this.container = Arrays.copyOf(this.container, newCapacity);
        }
        this.container[index++] = e;
        this.modCount++;
    }

    @Override
    public E get(int position) throws NoSuchElementException {
        if (position >= this.index) {
            throw new NoSuchElementException();
        }
        return (E) this.container[position];
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
