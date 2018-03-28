package com.solomatoff.list;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class SimpleQueueTest {

    @Test
    public void whenPushAndPollQueueShouldSeccess() {
        SimpleQueue<Integer> oSimpleQueue = new SimpleQueue<>();
        oSimpleQueue.push(1);
        oSimpleQueue.push(2);
        oSimpleQueue.push(3);

        Integer element = oSimpleQueue.poll();
        assertThat(element, is(1));

        element = oSimpleQueue.poll();
        assertThat(element, is(2));

        element = oSimpleQueue.poll();
        assertThat(element, is(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenPushAndPollQueueShouldException() {
        SimpleQueue<String> oSimpleQueue = new SimpleQueue<>();
        oSimpleQueue.push("1");
        oSimpleQueue.push("2");
        oSimpleQueue.push("3");

        String element = oSimpleQueue.poll();
        assertThat(element, is("1"));

        element = oSimpleQueue.poll();
        assertThat(element, is("2"));

        element = oSimpleQueue.poll();
        assertThat(element, is("3"));
        oSimpleQueue.poll();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenQueueIsEmptyShouldException() {
        SimpleQueue<Integer> oSimpleQueue = new SimpleQueue<>();

        oSimpleQueue.poll();
    }
}