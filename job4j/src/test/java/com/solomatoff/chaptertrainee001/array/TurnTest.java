package com.solomatoff.chaptertrainee001.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TurnTest {
    @Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
        //напишите здесь тест, проверяющий переворот массива с чётным числом элементов, например {2, 6, 1, 4}.
        Turn rst = new Turn();
        int[] aArr = {2, 6, 1, 4};
        int[] bArr = {4, 1, 6, 2};

        assertThat(rst.back(aArr), is(bArr));

        /*for (int x : aArr ) {
            System.out.println(x);
        }*/
    }
    @Test
    public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
        //напишите здесь тест, проверяющий переворот массива с нечётным числом элементов, например {1, 2, 3, 4, 5}.
        Turn rst = new Turn();
        int[] aArr = {1, 2, 3, 4, 5};
        int[] bArr = {5, 4, 3, 2, 1};

        assertThat(rst.back(aArr), is(bArr));

        /*for (int x : aArr ) {
            System.out.println(x);
        }*/
    }
}

