package com.solomatoff.chapterjunior001.list;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class SimpleStackTest {

    @Test
    public void whenPushAndPollStacShouldSeccess() {
        SimpleStack<Integer> oSimpleStack = new SimpleStack<>();
        oSimpleStack.push(1);
        oSimpleStack.push(2);
        oSimpleStack.push(3);

        Integer element = oSimpleStack.poll();
        assertThat(element, is(3));

        element = oSimpleStack.poll();
        assertThat(element, is(2));

        element = oSimpleStack.poll();
        assertThat(element, is(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenPushAndPollStackShouldException() {
        SimpleStack<String> oSimpleStack = new SimpleStack<>();
        oSimpleStack.push("1");
        oSimpleStack.push("2");
        oSimpleStack.push("3");

        String element = oSimpleStack.poll();
        assertThat(element, is("3"));

        element = oSimpleStack.poll();
        assertThat(element, is("2"));

        element = oSimpleStack.poll();
        assertThat(element, is("1"));

        oSimpleStack.poll();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenStackIsEmptyShouldException() {
        SimpleStack<Integer> oSimpleStack = new SimpleStack<>();

        oSimpleStack.poll();
    }
}