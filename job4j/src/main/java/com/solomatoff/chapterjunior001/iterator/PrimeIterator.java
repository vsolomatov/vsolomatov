package com.solomatoff.chapterjunior001.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор возвращающий только простые числа.
 */
public class PrimeIterator implements Iterator<Integer> {
    private final int[] values;
    private Boolean hasNext = null;
    private int indexNextPrime = -1;

    public PrimeIterator(int[] values) {
        this.values = values;
    }

    /**
     *  Метод возвращает индекс элемента, являющегося простым числом,  в массиве values[], начиная поиск с указанной позиции beginIndex.
     * @param beginIndex начальная позиция поиска простого элемента в массиве.
     * @return индекс найденного простого числа, либо -1, если такого элемента не существует.
     */
    private int getIndexPrime(int beginIndex) {
        int result = -1;
        for (int index = beginIndex; index < values.length; index++) {
            if (primeNumber(values[index])) {
                result = index;
                break;
            }
        }
        return result;
    }

    /**
     *  Метод проверяет является ли переданное число простым.
     * @param number - проверяемое число.
     * @return возвращает истину, если переданное число, является простым, и ложь - в противном случае.
     */
    private boolean primeNumber(int number) {
        boolean result = true;
        int squareRoot = (int) Math.round(Math.sqrt(number));
        if (number == 1) {
            result = false;
        }
        for (int j = 2; j <= squareRoot; j++) {
            if ((number % j) == 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        if (this.hasNext == null) {
            this.indexNextPrime = getIndexPrime(0);
            this.hasNext = (this.indexNextPrime >= 0);
        }
        return this.hasNext;
    }

    @Override
    public Integer next() throws NoSuchElementException {
        int result;
        if (this.hasNext == null) {
            hasNext();
        }
        if (this.hasNext && (this.indexNextPrime >= 0)) {
            result = values[this.indexNextPrime];
        } else {
            throw new NoSuchElementException();
        }
        this.indexNextPrime = getIndexPrime(this.indexNextPrime + 1);
        this.hasNext = (this.indexNextPrime >= 0);
        return result;
    }
}