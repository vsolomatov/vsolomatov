package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FactorialTest {
    @Test
    public void whenCalculateFactorialForFiveThenOneHundreedTwenty() {
        //тест, проверяющий, что факториал для числа 5 равен 120.
        Factorial rst = new Factorial();
        assertThat(
                rst.calc(5),
                is(120)
        );
    }

    @Test
    public void whenCalculateFactorialForZeroThenOne() {
        //тест, проверяющий, что факториал для числа 0 равен 1.
        Factorial rst = new Factorial();
        assertThat(
                rst.calc(0),
                is(1)
        );
    }

    @Test
    public void whenCalculateFactorialForMinusThenOne() {
        //тест, проверяющий, что факториал для числа 0 равен 1.
        Factorial rst = new Factorial();
        assertThat(
                rst.calc(-8),
                is(1)
        );
    }
}

