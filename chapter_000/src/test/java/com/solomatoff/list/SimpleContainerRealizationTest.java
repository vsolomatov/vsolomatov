package com.solomatoff.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleContainerRealizationTest {

    @Test
    public void add() {
        SimpleContainerRealization<Integer> oSimpleContainer = new SimpleContainerRealization<>();
        oSimpleContainer.add(1);
        oSimpleContainer.add(2);
        oSimpleContainer.add(3);
        oSimpleContainer.add(4);

        Integer result = oSimpleContainer.get(1);
        assertThat(result, is(2));
    }

    @Test
    public void whenGetShouldSuccess() {
        SimpleContainerRealization<String> oSimpleContainer = new  SimpleContainerRealization<>();
        oSimpleContainer.add("1");
        oSimpleContainer.add("2");
        oSimpleContainer.add("3");

        String result = oSimpleContainer.get(1);
        assertThat(result, is("2"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetShouldException() {
        SimpleContainerRealization<String> oSimpleContainer = new  SimpleContainerRealization<>();
        oSimpleContainer.add("1");
        oSimpleContainer.add("2");
        oSimpleContainer.add("3");

        oSimpleContainer.get(3);
    }

    @Test
    public void whenIteratorAndDontChangeContainer() {
        SimpleContainerRealization<Integer> oSimpleContainer = new  SimpleContainerRealization<>();
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
        SimpleContainerRealization<Integer> oSimpleContainer = new SimpleContainerRealization<>();
        Iterator<Integer> it;

        oSimpleContainer.add(10);
        oSimpleContainer.add(20);
        oSimpleContainer.add(30);

        it = oSimpleContainer.iterator();

        assertThat(it.hasNext(), is(true));

        oSimpleContainer.add(40);
        it.next();
    }
}