package com.solomatoff.set;

import com.solomatoff.list.SimpleLinked;
import com.solomatoff.list.SimpleList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SimpleHashSet<E> {
    private Object[] container = new Object[100];

    private int hashCodeMaker(@NotNull E e) {
        return Math.abs(e.hashCode() % 100);
    }

    boolean add(E e) {
        boolean result = true;
        boolean exists = false;
        // определяем индекс в массиве и берем из него список объектов
        int index = hashCodeMaker(e);
        SimpleLinked<E> currItemContainer = (SimpleLinked<E>) this.container[index];

        // проверяем существование группы
        if (currItemContainer == null) {
            currItemContainer = new SimpleLinked<>();
            this.container[index] = currItemContainer;
        }

        // проверяем существование элемента в группе
        for (E iterItem : currItemContainer) {
            if (iterItem.equals(e)) {
                exists = true;
                break;
            }
        }
        if (exists) {
            result = false;
        } else {
            currItemContainer.add(e);
        }
        return result;
    }

    boolean contains(E e) {
        boolean exists = false;
        // определяем индекс в массиве и берем из него список объектов
        int index = hashCodeMaker(e);
        SimpleLinked<E> currItemContainer = (SimpleLinked<E>) this.container[index];

        // проверяем существование группы
        if (currItemContainer == null) {
            return false;
        }
        // проверяем существование элемента в группе
        for (E iterItem : currItemContainer) {
            if (iterItem.equals(e)) {
                exists = true;
                break;
            }
        }
        return exists;

    }

    boolean remove(E e) {
        boolean result = true;
        boolean exists = false;
        // определяем индекс в массиве и берем из него список объектов
        int index = hashCodeMaker(e);
        SimpleLinked<E> currItemContainer = (SimpleLinked<E>) this.container[index];

        // проверяем существование группы
        if (currItemContainer == null) {
            currItemContainer = new SimpleLinked<>();
            this.container[index] = currItemContainer;
        }

        // проверяем существование элемента в группе и запоминаем индекс, если элемент существует
        int i = 0;
        for (E iterItem : currItemContainer) {
            if (iterItem.equals(e)) {
                exists = true;
                break;
            }
            i++;
        }
        if (exists) {
            currItemContainer.delete(i);
        } else {
            result = false;
        }
        return result;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String ln = System.getProperty("line.separator");
        SimpleLinked<E> currItemContainer;

        for (int i = 0; i < container.length; i++) {
            currItemContainer = (SimpleLinked<E>) this.container[i];
            stringBuilder.append("i = ").append(i).append(" : ");
            // проверяем существование группы
            if (currItemContainer != null) {
                for (E iterItem : currItemContainer) {
                    stringBuilder.append(iterItem.toString()).append(" ");
                }
            }
            stringBuilder.append(ln);
        }
        return stringBuilder.toString();
    }
}
