package com.solomatoff.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.Iterator;
        import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] values;
    private int indexNext;

    public EvenNumbersIterator(int[] values) {
        this.values = values;
        this.indexNext = nextIndexEvenNumber(-1);
    }

    private int nextIndexEvenNumber(int beginIndex) {
        int index = -1;
        for (int j = beginIndex + 1; j < values.length; j++) {
            if ((values[j] % 2) == 0) {
                index = j;
                break;
            }
        }
        return index;
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
        indexNext = nextIndexEvenNumber(indexNext);
        return result;
    }
}