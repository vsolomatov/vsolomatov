package com.solomatoff.chaptertrainee003.banktransfer;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {
    @Test
    public void whenAccountTransfer() {
        Account origin = new Account(1000, "Счет №1");
        Account expected = new Account(0,  "Счет №2");

        //System.out.println(origin);
        //System.out.println(expected);

        origin.transfer(expected, 500);
        double result = expected.getValues();
        assertThat(result, is(500d));

        //System.out.println(origin);
        //System.out.println(expected);
    }

    @Test
    public void whenAccountDontTransfer() {
        Account origin = new Account(400, "Счет №1");
        Account expected = new Account(600,  "Счет №2");

        //System.out.println(origin);
        //System.out.println(expected);

        boolean result = origin.transfer(expected, 500);
        assertThat(result, is(false));

        //System.out.println(origin);
        //System.out.println(expected);
    }

    @Test
    public void whenAccountEquelse() {
        Account origin = new Account(400, "Счет №1");
        Account expected = new Account(400,  "Счет №1");

        //System.out.println(origin);
        //System.out.println(expected);

        boolean result = origin.equals(expected);
        assertThat(result, is(true));

        //System.out.println(origin);
        //System.out.println(expected);
    }

    @Test
    public void whenAccountNotEquelseValue() {
        Account origin = new Account(400, "Счет №1");
        Account expected = new Account(600,  "Счет №1");

        //System.out.println(origin);
        //System.out.println(expected);

        boolean result = origin.equals(expected);
        assertThat(result, is(false));

        //System.out.println(origin);
        //System.out.println(expected);
    }

    @Test
    public void whenAccountNotEquelseReqc() {
        Account origin = new Account(400, "Счет №1");
        Account expected = new Account(400,  "Счет №2");

        //System.out.println(origin);
        //System.out.println(expected);

        boolean result = origin.equals(expected);
        assertThat(result, is(false));

        //System.out.println(origin);
        //System.out.println(expected);
    }
}
