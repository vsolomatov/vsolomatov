package com.solomatoff.chapterjunior001.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.lang.Thread.sleep;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleLinkedTest {

    @Test
    public void add() {
        SimpleLinked<Integer> oSimpleContainer = new SimpleLinked<>();
        oSimpleContainer.add(1);
        oSimpleContainer.add(2);
        oSimpleContainer.add(3);
        oSimpleContainer.add(4);

        Integer result = oSimpleContainer.get(1);
        assertThat(result, is(2));
    }

    @Test
    public void whenGetShouldSuccess() {
        SimpleLinked<String> oSimpleContainer = new SimpleLinked<>();
        oSimpleContainer.add("1");
        oSimpleContainer.add("2");
        oSimpleContainer.add("3");

        String result = oSimpleContainer.get(1);
        assertThat(result, is("2"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetShouldException() {
        SimpleLinked<String> oSimpleContainer = new SimpleLinked<>();
        oSimpleContainer.add("1");
        oSimpleContainer.add("2");
        oSimpleContainer.add("3");

        oSimpleContainer.get(3);
    }

    @Test
    public void whenDeleteShouldSuccess() {
        SimpleLinked<String> oSimpleContainer = new SimpleLinked<>();
        oSimpleContainer.add("1");
        oSimpleContainer.add("2");
        oSimpleContainer.add("3");

        oSimpleContainer.delete(1);
        String result = oSimpleContainer.get(1);
        assertThat(result, is("3"));
    }

    @Test
    public void whenDeleteFirstShouldSuccess() {
        SimpleLinked<String> oSimpleContainer = new SimpleLinked<>();
        oSimpleContainer.add("3");
        oSimpleContainer.add("2");
        oSimpleContainer.add("1");

        String expacted = oSimpleContainer.deleteFirst();
        assertThat(expacted, is("3"));
    }

    @Test
    public void whenDeleteLastShouldSuccess() {
        SimpleLinked<String> oSimpleContainer = new SimpleLinked<>();
        oSimpleContainer.add("3");
        oSimpleContainer.add("2");
        oSimpleContainer.add("1");

        String expacted = oSimpleContainer.deleteLast();
        assertThat(expacted, is("1"));
    }

    @Test
    public void whenDeleteLastOnlyShouldSuccess() {
        SimpleLinked<String> oSimpleContainer = new SimpleLinked<>();
        oSimpleContainer.add("3");

        String expacted = oSimpleContainer.deleteLast();
        assertThat(expacted, is("3"));
    }

    @Test
    public void whenDeleteFirstOnlyShouldSuccess() {
        SimpleLinked<String> oSimpleContainer = new SimpleLinked<>();
        oSimpleContainer.add("3");

        String expacted = oSimpleContainer.deleteFirst();
        assertThat(expacted, is("3"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteLastOnlyShouldException() {
        SimpleLinked<String> oSimpleContainer = new SimpleLinked<>();
        oSimpleContainer.add("3");

        String expacted = oSimpleContainer.deleteLast();
        assertThat(expacted, is("3"));
        oSimpleContainer.deleteLast();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirstOnlyShouldException() {
        SimpleLinked<String> oSimpleContainer = new SimpleLinked<>();
        oSimpleContainer.add("3");

        String expacted = oSimpleContainer.deleteFirst();
        assertThat(expacted, is("3"));
        oSimpleContainer.deleteFirst();
    }


    @Test(expected = NoSuchElementException.class)
    public void whenDeleteShouldException() {
        SimpleLinked<String> oSimpleContainer = new SimpleLinked<>();
        oSimpleContainer.add("1");
        oSimpleContainer.add("2");
        oSimpleContainer.add("3");

        oSimpleContainer.delete(1);
        oSimpleContainer.get(2);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenContainerIsEmptyAndDeleteShouldException() {
        SimpleLinked<String> oSimpleContainer = new SimpleLinked<>();

        oSimpleContainer.delete(0);
        //String result = oSimpleContainer.get(0);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenContainerIsEmptyAndGetShouldException() {
        SimpleLinked<String> oSimpleContainer = new SimpleLinked<>();

        oSimpleContainer.get(0);
    }

    @Test
    public void whenIteratorAndDontChangeContainer() {
        SimpleLinked<Integer> oSimpleContainer = new SimpleLinked<>();
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
        SimpleLinked<Integer> oSimpleContainer = new SimpleLinked<>();
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
        SimpleLinked<Integer> oSimpleContainer = new SimpleLinked<>();
        Iterator<Integer> it;

        oSimpleContainer.add(10);
        oSimpleContainer.add(20);
        oSimpleContainer.add(30);

        it = oSimpleContainer.iterator();

        assertThat(it.hasNext(), is(true));

        oSimpleContainer.delete(1);
        it.next();
    }

    @Test
    public void whenTwoThreadRunListDeleteGetAdd() {
        SimpleLinked<Integer> oSimpleContainer = new SimpleLinked<>();
        oSimpleContainer.add(10);
        oSimpleContainer.add(20);
        oSimpleContainer.add(150);
        Thread threadOne = new Thread() {
            @Override
            public void run() {
                oSimpleContainer.delete(0);
                System.out.println("get возвратил: " + oSimpleContainer.get(0));
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                oSimpleContainer.add(40);
            }
        };
        Thread threadTwo = new Thread() {
            @Override
            public void run() {
                oSimpleContainer.delete(0);
                System.out.println("get возвратил: " + oSimpleContainer.get(0));
                try {
                    sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                oSimpleContainer.add(50);
            }
        };
        threadOne.start();
        threadTwo.start();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Iterator<Integer> it = oSimpleContainer.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

}