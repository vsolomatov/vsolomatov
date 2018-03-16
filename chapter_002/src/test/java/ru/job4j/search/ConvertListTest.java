package ru.job4j.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class ConvertListTest {
    @Test
    public void whenConvertListToList() {
        ConvertList convert = new ConvertList();
        List<int[]> origin = Arrays.asList(new int[] {1, 2}, new int[] {3, 4, 5, 6});
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> result = convert.convert(origin);
        assertThat(result, is(expected));
    }

    @Test
    public void whenConvertArrayToList() {
        ConvertList convert = new ConvertList();
        int[][] origin = new int[][] {{1, 2}, {3, 4, 5, 6}, {7, 8, 9}};
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> result = convert.toList(origin);
        assertThat(result, is(expected));
    }

    @Test
    public void listToArrayWithPaddingZeros() {
        ConvertList convert = new ConvertList();
        int[][] expected = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 0, 0}};
        List<Integer> origin = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        int[][] result = convert.toArray(origin, 3);
        assertThat(result, is(expected));
    }

    @Test
    public void listToArray74() {
        ConvertList convert = new ConvertList();
        int[][] expected = new int[][]{{1, 2}, {3, 4}, {5, 6}, {7, 0}};
        List<Integer> origin = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        int[][] result = convert.toArray(origin, 4);
        assertThat(result, is(expected));
    }

    @Test
    public void listToArray75() {
        ConvertList convert = new ConvertList();
        int[][] expected = new int[][]{{1, 2}, {3, 4}, {5, 6}, {7, 0}, {0, 0}};
        List<Integer> origin = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        int[][] result = convert.toArray(origin, 5);
        assertThat(result, is(expected));
    }

    @Test
    public void listToArray124() {
        ConvertList convert = new ConvertList();
        int[][] expected = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        List<Integer> origin = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        int[][] result = convert.toArray(origin, 4);
        assertThat(result, is(expected));
    }

    @Test
    public void listToArray125() {
        ConvertList convert = new ConvertList();
        int[][] expected = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}, {0, 0, 0}};
        List<Integer> origin = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        int[][] result = convert.toArray(origin, 5);
        assertThat(result, is(expected));
    }

    @Test
    public void listToArray126() {
        ConvertList convert = new ConvertList();
        int[][] expected = new int[][]{{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}, {11, 12}};
        List<Integer> origin = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        int[][] result = convert.toArray(origin, 6);
        assertThat(result, is(expected));
    }

    @Test
    public void listToArray127() {
        ConvertList convert = new ConvertList();
        int[][] expected = new int[][]{{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}, {11, 12}, {0, 0}};
        List<Integer> origin = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        int[][] result = convert.toArray(origin, 7);
        assertThat(result, is(expected));
    }

    @Test
    public void listToArray1215() {
        ConvertList convert = new ConvertList();
        int[][] expected = new int[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}, {9}, {10}, {11}, {12}, {0}, {0}, {0}};
        List<Integer> origin = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        int[][] result = convert.toArray(origin, 15);
        assertThat(result, is(expected));
    }

    @Test
    public void listToArray178() {
        ConvertList convert = new ConvertList();
        int[][] expected = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}, {13, 14, 15}, {16, 17, 0}, {0, 0, 0}, {0, 0, 0}};
        List<Integer> origin = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
        int[][] result = convert.toArray(origin, 8);
        assertThat(result, is(expected));
    }
}