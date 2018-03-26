package com.solomatoff.generic;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 *  Класс реализует коллекцию в виде массива, позволяющего хранить объекты произвольного типа T.
 * @param <T> тип хранимых объектов.
 */
public class SimpleArray<T> implements Iterable<T> {
    private Object[] objects;
    private int index = 0;
    private int indexIterator = 0;

    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    /**
     * Метод позволяет добавлять новый элемент в нашу коллекцию.
     * @param value значение нового элемента.
     */
    public void add(T value) {
        this.objects[index++] = value;
    }

    /**
     *  Метод позволяет удалять элемент с индексом position из нашей коллекции.
     * @param position индекс удаляемого элемента.
     * @throws NoSuchElementException выбрасываемое исключение, если индекс для удаляемого элемента указан неверно.
     */
    public void delete(int position) throws NoSuchElementException {
        if (position >= this.index) {
            throw new NoSuchElementException();
        }
        System.arraycopy(objects, position + 1, objects, position, this.index - position - 1);
        objects[index - 1] = null;
        index -= 1;
    }

    /**
     *  Метод позволяет устанавливать новое значение элементу с индексом position из нашей коллекции.
     * @param position индекс изменяемого элемента.
     * @param value новое значение устанавливаемое элементу
     * @throws NoSuchElementException выбрасываемое исключение, если индекс для изменяемого элемента указан неверно.
     */
    public void set(int position, T value) throws NoSuchElementException {
        if (position >= this.index) {
            throw new NoSuchElementException();
        }
        this.objects[position] = value;
    }

    /**
     *  Метод позволяет получить значение элемента с индексом position из нашей коллекции.
     * @param position индекс искомого элемента.
     * @return значение элемента с индексом position из нашей коллекции.
     * @throws NoSuchElementException выбрасываемое исключение, если индекс искомого элемента указан неверно.
     */
    public T get(int position) throws NoSuchElementException {
        if (position >= this.index) {
            throw new NoSuchElementException();
        }
        return (T) this.objects[position];
    }

    public String toString() {
        return Arrays.toString(objects);
    }

    /**
     *  Метод для реализации интерфейса Iterable<T>.
     * @return Итератор.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return (indexIterator < index);
            }

            @Override
            public T next() {
                if (indexIterator >= index) {
                    throw new NoSuchElementException();
                }
                return (T) objects[indexIterator++];
            }
        };
    }
}
