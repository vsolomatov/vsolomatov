package com.solomatoff.list;

/**
 *  Интерфейс задает структуру динамического контейнера.
 * @param <E> параметр типа для данного вида контейнера.
 */
public interface SimpleContainer<E> extends Iterable<E> {
    void add(E e);
    E get(int index);
}
