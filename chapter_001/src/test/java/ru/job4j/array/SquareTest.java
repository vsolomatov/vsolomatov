package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SquareTest {
    @Test
    public void whenSquareFive() {
        Square rst = new Square();
        int[] sq5 = {0, 1, 4, 9, 16, 25};

        /*for (int x : rst.calculate(5) ) {
            System.out.println(x);
        }*/
        assertThat(rst.calculate(5), is(sq5));
        }

    @Test
    public void whenSquareSeven() {
        Square rst = new Square();
        int[] sq7 = {0, 1, 4, 9, 16, 25, 36, 49};

        /*for (int x : rst.calculate(7) ) {
            System.out.println(x);
        }*/
        assertThat(rst.calculate(7), is(sq7));
    }
}
