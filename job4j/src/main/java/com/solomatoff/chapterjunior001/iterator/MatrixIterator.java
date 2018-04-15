package com.solomatoff.chapterjunior001.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  Итератор для двухмерного массива.
 */
public class MatrixIterator implements Iterator<Integer> {
    private final int[][] values;
    private int row = 0;
    private int cell = 0;

    public MatrixIterator(int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return (row < this.values.length) && (cell < this.values[row].length);
    }

    @Override
    public Integer next() throws NoSuchElementException {
        int val = 0;
        if (row < this.values.length) {
            val = this.values[row][cell];
            if (cell < this.values[row].length - 1) {
                cell++;
            } else {
                row++;
                cell = 0;
            }
        }
        if ((row == 0) && (cell == 0)) {
            throw new NoSuchElementException();
        }
        return val;
    }
}