package ru.job4j.search;

import java.lang.reflect.Array;
import java.util.*;

public class ConvertList {
    /**
     * Метод должен пройтись по всем элементам всех массивов в списке list
     * и добавить их в один общий лист Integer.
     *
     * @param list список массивов
     */
    public List<Integer> convert (List<int[]> list) {
        List<Integer> resultList = new ArrayList<>();
        for (int[] listitr : list) {
            for (int initr : listitr) {
                resultList.add(initr);
            }
        }
        return resultList;
    }

    public List<Integer> toList (int[][] array) {
        List<Integer> li = new ArrayList<>();
        for (int[] outArray : array) {
            for (int inArray : outArray) {
                li.add(inArray);
            }
        }
        return li;
    }

    public int[][] toArray (List<Integer> list, int rows) {
        int lsize = list.size(); // размер списка
        //System.out.format("%s %02d %s %02d %n", "Длина списка: ", lsize, "Кол-во строк: ", rows);
        int fullrows = lsize/rows;
        //System.out.println("Целое от деления кол-ва элементов на кол-во строк: " + fullrows);
        int lastrow = lsize % rows;
        //System.out.println("Остаток от деления кол-ва элементов на кол-во строк: " + lastrow);
        int numElemInRow = (lastrow > 0) ? fullrows + 1 : fullrows; // количество элементов в строке
        //System.out.println("Кол-во элементов в строке: " + numElemInRow);

        int[][] resultArray = new int[rows][numElemInRow]; // массив-результат

        int y = 0; // переменная для перебора элементов списка
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < numElemInRow; j++) {
                if (y < lsize) {
                    resultArray[i][j] = list.get(y);
                    y++;
                } else {
                    resultArray[i][j] = 0;
                }
            }
        }
        /*for (int i = 0; i < rows; i++) {
            for (int j = 0; j < numElemInRow; j++) {
                System.out.print(resultArray[i][j] + " ");
            }
            System.out.println(" ");
        }*/
        return resultArray;
    }
}