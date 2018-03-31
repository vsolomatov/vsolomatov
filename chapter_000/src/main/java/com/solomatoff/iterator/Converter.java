package com.solomatoff.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            private Iterator<Integer> currentIterator = null;

            @Override
            public boolean hasNext() {
                selectCurrentIterator();
                return (currentIterator != null && currentIterator.hasNext());
            }

            @Override
            public Integer next() {
                selectCurrentIterator();
                if (currentIterator == null) {
                    throw new NoSuchElementException();
                }
                return currentIterator.next();
            }

            private void selectCurrentIterator() {
                if (currentIterator == null || !currentIterator.hasNext()) {
                    currentIterator = null;
                    while (it.hasNext()) {
                        Iterator<Integer> nextIterator = it.next();
                        if (nextIterator.hasNext()) {
                            currentIterator = nextIterator;
                            break;
                        }
                    }
                }
            }
        };
    }
}
