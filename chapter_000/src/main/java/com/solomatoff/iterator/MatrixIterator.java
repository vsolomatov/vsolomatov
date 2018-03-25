package com.solomatoff.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator implements Iterator<Integer> {
    private final int[][] values;
    private int i = 0;
    private int j = 0;

    public MatrixIterator(int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        boolean exist = false;
        //System.out.println("From hasNext: i = " + i + " j = " + j);

        if (i < this.values.length) {
            if (j < this.values[i].length) {
                exist = true;
            }
        }
        return exist;
    }

    @Override
    public Integer next() throws NoSuchElementException {
        int val = 0;
        if (i < this.values.length) {
            val = this.values[i][j];
            if (j < this.values[i].length - 1) {
                j++;
            } else {
                i++;
                j = 0;
            }
        }
        //System.out.println("From next: i = " + i + ", j = " + j);
        if ((i == 0) && (j == 0)) {
            throw new NoSuchElementException();
        }
        return val;
    }
}