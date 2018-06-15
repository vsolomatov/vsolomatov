package com.solomatoff.chapterjunior001.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.lang.Thread.sleep;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleListTest {

    @Test
    public void add() {
        SimpleList<Integer> oSimpleContainer = new SimpleList<>();
        oSimpleContainer.add(1);
        oSimpleContainer.add(2);
        oSimpleContainer.add(3);
        oSimpleContainer.add(4);

        Integer result = oSimpleContainer.get(1);
        assertThat(result, is(2));
    }

    @Test
    public void whenGetShouldSuccess() {
        SimpleList<String> oSimpleContainer = new SimpleList<>();
        oSimpleContainer.add("1");
        oSimpleContainer.add("2");
        oSimpleContainer.add("3");

        String result = oSimpleContainer.get(1);
        assertThat(result, is("2"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetShouldException() {
        SimpleList<String> oSimpleContainer = new SimpleList<>();
        oSimpleContainer.add("1");
        oSimpleContainer.add("2");
        oSimpleContainer.add("3");

        oSimpleContainer.get(3);
    }

    @Test
    public void whenIteratorAndDontChangeContainer() {
        SimpleList<Integer> oSimpleContainer = new SimpleList<>();
        Iterator<Integer> it;

        oSimpleContainer.add(10);
        oSimpleContainer.add(20);
        oSimpleContainer.add(30);

        it = oSimpleContainer.iterator();

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(10));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(20));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(30));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorAndChangeContainer() {
        SimpleList<Integer> oSimpleContainer = new SimpleList<>();
        Iterator<Integer> it;

        oSimpleContainer.add(10);
        oSimpleContainer.add(20);
        oSimpleContainer.add(30);

        it = oSimpleContainer.iterator();

        assertThat(it.hasNext(), is(true));

        oSimpleContainer.add(40);
        it.next();
    }
    @Test
    public void whenTwoThreadRunListGetAdd() {
        SimpleList<Integer> oSimpleContainer = new SimpleList<>();
        oSimpleContainer.add(10);
        oSimpleContainer.add(20);
        oSimpleContainer.add(150);
        Thread threadOne = new Thread(() -> {
            try {
                sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            oSimpleContainer.add(40);
        });
        Thread threadTwo = new Thread(() -> {
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            oSimpleContainer.add(50);
        });
        threadOne.start();
        threadTwo.start();
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Integer anOSimpleContainer : oSimpleContainer) {
            System.out.println(anOSimpleContainer);
        }
    }
}