package com.solomatoff.chaptertrainee001.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BubbleSortTest {
    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
        //напишите здесь тест, проверяющий сортировку массива из 10 элементов методом пузырька, например {1, 5, 4, 2, 3, 1, 7, 8, 0, 5}.
        BubbleSort rst = new BubbleSort();
        int[] aArr = {1, 5, 4, 2, 3, 1, 7, 8, 0, 5};
        int[] bArr = {0, 1, 1, 2, 3, 4, 5, 5, 7, 8};
        assertThat(rst.sort(aArr), is(bArr));
    }
}

