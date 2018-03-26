package ru.job4j.coffeemachine;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CoffeeMachineTest {
    @Test
    public void whenChangesPositive5000() {
        CoffeeMachine newCoffeeMachine = new CoffeeMachine();
        int[] result = newCoffeeMachine.changes(5000, 35);

        int[] expected = new int[] {2000, 2000, 500, 200, 200, 50, 10, 5};

        assertThat(result, is(expected));
    }

    @Test
    public void whenChangesPositive1000() {
        CoffeeMachine newCoffeeMachine = new CoffeeMachine();
        int[] result = newCoffeeMachine.changes(1000, 34);

        int[] expected = new int[] {500, 200, 200, 50, 10, 5, 1};

        assertThat(result, is(expected));
    }

    @Test
    public void whenChangesPositive1002() {
        CoffeeMachine newCoffeeMachine = new CoffeeMachine();
        int[] result = newCoffeeMachine.changes(1002, 34);

        int[] expected = new int[] {500, 200, 200, 50, 10, 5, 2, 1};

        assertThat(result, is(expected));
    }

    @Test
    public void whenChangesPositive500() {
        CoffeeMachine newCoffeeMachine = new CoffeeMachine();
        int[] result = newCoffeeMachine.changes(500, 43);

        int[] expected = new int[] {200, 200, 50, 5, 2};

        assertThat(result, is(expected));
    }


    @Test
    public void whenChangesPositive100() {
        CoffeeMachine newCoffeeMachine = new CoffeeMachine();
        int[] result = newCoffeeMachine.changes(100, 37);

        int[] expected = new int[] {50, 10, 2, 1};

        assertThat(result, is(expected));
    }

    @Test
    public void whenChangesPositive50() {
        CoffeeMachine newCoffeeMachine = new CoffeeMachine();
        int[] result = newCoffeeMachine.changes(50, 49);

        int[] expected = new int[] {1};

        assertThat(result, is(expected));
    }

    @Test
    public void whenChangesPositive53() {
        CoffeeMachine newCoffeeMachine = new CoffeeMachine();
        int[] result = newCoffeeMachine.changes(53, 49);

        int[] expected = new int[] {2, 2};

        assertThat(result, is(expected));
    }

    @Test
    public void whenChangesZero() {
        CoffeeMachine newCoffeeMachine = new CoffeeMachine();
        int[] result = newCoffeeMachine.changes(50, 50);

        int[] expected = new int[] {};

        assertThat(result, is(expected));
    }

    @Test
    public void whenNotEnoughMoney() {
        CoffeeMachine newCoffeeMachine = new CoffeeMachine();
        int[] result = newCoffeeMachine.changes(50, 70);

        int[] expected = new int[] {};

        assertThat(result, is(expected));
    }

}