package com.solomatoff.chapterjunior001.set;

import com.solomatoff.chapterjunior001.list.SimpleList;
import org.jetbrains.annotations.NotNull;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {
    private SimpleList<E> container = new SimpleList<>();

    public boolean add(E e) {
        boolean result = true;
        boolean exists = false;
        // проверить на совпадение с уже имеющимися элементами коллекции

        for (Iterator<E> i = container.iterator(); i.hasNext();) {
            if (i.next().equals(e)) {
                exists = true;
                break;
            }
        }
        if (exists) {
            result = false;
        } else {
            container.add(e);
        }
        return result;
    }

    @NotNull
    @Override
    public Iterator<E> iterator() throws ConcurrentModificationException {
        return container.iterator();
    }
}
