package ru.job4j.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class ConvertListTest {
    @Test
    public void whenConvertListToList() {
        ConvertList aCL = new ConvertList();

        List<int[]> aList = new ArrayList<>();
        aList.add(new int[] {1,2});
        aList.add(new int[] {3,4,5,6});

        List<Integer> bList = new ArrayList<>();
        bList.add(1);
        bList.add(2);
        bList.add(3);
        bList.add(4);
        bList.add(5);
        bList.add(6);

        List<Integer> resultList;
        resultList = aCL.convert(aList);

        assertThat(resultList, is(bList));

    }

    @Test
    public void whenConvertListToArray() {
        ConvertList aCL = new ConvertList();

        int[][] aArray = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}, {0, 0, 0}};
        List<Integer> aList = new ArrayList<>();
        aList.add(1);
        aList.add(2);
        aList.add(3);
        aList.add(4);
        aList.add(5);
        aList.add(6);
        aList.add(7);
        aList.add(8);
        aList.add(9);
        aList.add(10);
        aList.add(11);
        aList.add(12);

        int[][] resultArray;
        resultArray = aCL.toArray(aList,5);

        assertThat(resultArray, is(aArray));

    }

    @Test
    public void whenConvertArrayToList() {
        ConvertList aCL = new ConvertList();

        int[][] aArray = {{1, 2}, {3, 4, 5, 6}, {7, 8, 9}};
        List<Integer> aList = new ArrayList<>();
        aList.add(1);
        aList.add(2);
        aList.add(3);
        aList.add(4);
        aList.add(5);
        aList.add(6);
        aList.add(7);
        aList.add(8);
        aList.add(9);

        List<Integer> resultList;
        resultList = aCL.toList(aArray);

        assertThat(resultList, is(aList));

    }
}