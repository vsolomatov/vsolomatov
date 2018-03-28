package com.solomatoff.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class NodeContainerTest {

    @Test
    public void add() {
        NodeContainer<Integer> oSimpleContainer = new NodeContainer<>();
        oSimpleContainer.add(1);
        oSimpleContainer.add(2);
        oSimpleContainer.add(3);
        oSimpleContainer.add(4);

        Integer result = oSimpleContainer.get(1);
        assertThat(result, is(2));
    }

    @Test
    public void whenGetShouldSuccess() {
        NodeContainer<String> oSimpleContainer = new NodeContainer<>();
        oSimpleContainer.add("1");
        oSimpleContainer.add("2");
        oSimpleContainer.add("3");

        String result = oSimpleContainer.get(1);
        assertThat(result, is("2"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetShouldException() {
        NodeContainer<String> oSimpleContainer = new NodeContainer<>();
        oSimpleContainer.add("1");
        oSimpleContainer.add("2");
        oSimpleContainer.add("3");

        oSimpleContainer.get(3);
    }

    @Test
    public void whenDeleteShouldSuccess() {
        NodeContainer<String> oSimpleContainer = new NodeContainer<>();
        oSimpleContainer.add("1");
        oSimpleContainer.add("2");
        oSimpleContainer.add("3");

        oSimpleContainer.delete(1);
        String result = oSimpleContainer.get(1);
        assertThat(result, is("3"));
    }

    @Test
    public void whenDeleteFirstShouldSuccess() {
        NodeContainer<String> oSimpleContainer = new NodeContainer<>();
        oSimpleContainer.add("3");
        oSimpleContainer.add("2");
        oSimpleContainer.add("1");

        String expacted = oSimpleContainer.deleteFirst();
        assertThat(expacted, is("3"));
    }

    @Test
    public void whenDeleteLastShouldSuccess() {
        NodeContainer<String> oSimpleContainer = new NodeContainer<>();
        oSimpleContainer.add("3");
        oSimpleContainer.add("2");
        oSimpleContainer.add("1");

        String expacted = oSimpleContainer.deleteLast();
        assertThat(expacted, is("1"));
    }

    @Test
    public void whenDeleteLastOnlyShouldSuccess() {
        NodeContainer<String> oSimpleContainer = new NodeContainer<>();
        oSimpleContainer.add("3");

        String expacted = oSimpleContainer.deleteLast();
        assertThat(expacted, is("3"));
    }

    @Test
    public void whenDeleteFirstOnlyShouldSuccess() {
        NodeContainer<String> oSimpleContainer = new NodeContainer<>();
        oSimpleContainer.add("3");

        String expacted = oSimpleContainer.deleteFirst();
        assertThat(expacted, is("3"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteLastOnlyShouldException() {
        NodeContainer<String> oSimpleContainer = new NodeContainer<>();
        oSimpleContainer.add("3");

        String expacted = oSimpleContainer.deleteLast();
        assertThat(expacted, is("3"));
        oSimpleContainer.deleteLast();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirstOnlyShouldException() {
        NodeContainer<String> oSimpleContainer = new NodeContainer<>();
        oSimpleContainer.add("3");

        String expacted = oSimpleContainer.deleteFirst();
        assertThat(expacted, is("3"));
        oSimpleContainer.deleteFirst();
    }


    @Test(expected = NoSuchElementException.class)
    public void whenDeleteShouldException() {
        NodeContainer<String> oSimpleContainer = new NodeContainer<>();
        oSimpleContainer.add("1");
        oSimpleContainer.add("2");
        oSimpleContainer.add("3");

        oSimpleContainer.delete(1);
        oSimpleContainer.get(2);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenContainerIsEmptyAndDeleteShouldException() {
        NodeContainer<String> oSimpleContainer = new NodeContainer<>();

        oSimpleContainer.delete(0);
        //String result = oSimpleContainer.get(0);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenContainerIsEmptyAndGetShouldException() {
        NodeContainer<String> oSimpleContainer = new NodeContainer<>();

        oSimpleContainer.get(0);
    }

    @Test
    public void whenIteratorAndDontChangeContainer() {
        NodeContainer<Integer> oSimpleContainer = new NodeContainer<>();
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
    public void whenIteratorAndChangeAddContainer() {
        NodeContainer<Integer> oSimpleContainer = new NodeContainer<>();
        Iterator<Integer> it;

        oSimpleContainer.add(10);
        oSimpleContainer.add(20);
        oSimpleContainer.add(30);

        it = oSimpleContainer.iterator();

        assertThat(it.hasNext(), is(true));

        oSimpleContainer.add(40);
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorAndChangeDeleteContainer() {
        NodeContainer<Integer> oSimpleContainer = new NodeContainer<>();
        Iterator<Integer> it;

        oSimpleContainer.add(10);
        oSimpleContainer.add(20);
        oSimpleContainer.add(30);

        it = oSimpleContainer.iterator();

        assertThat(it.hasNext(), is(true));

        oSimpleContainer.delete(1);
        it.next();
    }
}