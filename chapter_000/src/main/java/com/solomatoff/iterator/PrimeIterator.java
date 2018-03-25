package com.solomatoff.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIterator implements Iterator<Integer> {
    private final int[] values;
    private int indexNext;

    public PrimeIterator(int[] values) {
        this.values = values;
        this.indexNext = nextIndexPrimeNumber(-1);
    }

    private int nextIndexPrimeNumber(int beginIndex) {
        int index = -1;
        for (int j = beginIndex + 1; j < values.length; j++) {
            if (primeNumber(values[j])) {
                index = j;
                break;
            }
        }
        return index;
    }

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
        //System.out.println(number + " простое? " + result);
        return result;
    }

    @Override
    public boolean hasNext() {
        return !(this.indexNext < 0);
    }

    @Override
    public Integer next() throws NoSuchElementException {
        if (indexNext < 0) {
            throw new NoSuchElementException();
        }
        int result = this.values[indexNext];
        indexNext = nextIndexPrimeNumber(indexNext);
        return result;
    }
}
