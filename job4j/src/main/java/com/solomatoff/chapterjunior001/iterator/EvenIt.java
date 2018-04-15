package com.solomatoff.chapterjunior001.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор возвращающий только четные числа.
  */
public class EvenIt implements Iterator<Integer> {
    private final int[] values;
    private Boolean hasNext = null;
    private int indexNextEven = -1;

    public EvenIt(int[] values) {
        this.values = values;
    }

    /**
     *  Метод возвращает индекс элемента, имеющего четное значение,  в массиве values[], начиная поиск с указанной позиции beginIndex.
     * @param beginIndex начальная позиция поиска четного элемента в массиве.
     * @return индекс найденного четного элемента, либо -1, если такого элемента не существует.
     */
    private int getIndexEven(int beginIndex) {
        int result = -1;
        for (int index = beginIndex; index < values.length; index++) {
            if ((values[index] % 2) == 0) {
                result = index;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        if (this.hasNext == null) {
            this.indexNextEven = getIndexEven(0);
            this.hasNext = (this.indexNextEven >= 0);
        }
        return this.hasNext;
    }

    @Override
    public Integer next() throws NoSuchElementException {
        int result;
        if (this.hasNext == null) {
            hasNext();
        }
        if (this.hasNext && (this.indexNextEven >= 0)) {
            result = values[this.indexNextEven];
        } else {
            throw new NoSuchElementException();
        }
        this.indexNextEven = getIndexEven(this.indexNextEven + 1);
        this.hasNext = (this.indexNextEven >= 0);
        return result;
    }
}