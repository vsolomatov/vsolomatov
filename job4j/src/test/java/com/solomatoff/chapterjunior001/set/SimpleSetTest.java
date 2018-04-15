package com.solomatoff.chapterjunior001.set;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void add() {
        SimpleSet<Integer> oSimpleContainer = new SimpleSet<>();
        boolean result = oSimpleContainer.add(2);
        assertThat(result, is(true));

        result = oSimpleContainer.add(1);
        assertThat(result, is(true));

        result = oSimpleContainer.add(3);
        assertThat(result, is(true));

        result = oSimpleContainer.add(2);
        assertThat(result, is(false));
    }

    @Test
    public void whenIteratorAndDontChangeContainer() {
        SimpleSet<Integer> oSimpleContainer = new SimpleSet<>();
        Iterator<Integer> it;

        oSimpleContainer.add(10);
        oSimpleContainer.add(30);
        oSimpleContainer.add(20);

        it = oSimpleContainer.iterator();

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(10));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(30));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(20));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorAndChangeContainer() {
        SimpleSet<Integer> oSimpleContainer = new SimpleSet<>();
        Iterator<Integer> it;

        oSimpleContainer.add(10);
        oSimpleContainer.add(30);
        oSimpleContainer.add(20);

        it = oSimpleContainer.iterator();

        assertThat(it.hasNext(), is(true));

        oSimpleContainer.add(40);
        it.next();
    }
}