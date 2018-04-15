package com.solomatoff.chapterjunior001.set;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleHashSetTest {

    @Test
    public void add() {
        SimpleHashSet<Integer> oSimpleContainer = new SimpleHashSet<>();
        boolean result;

        result = oSimpleContainer.add(1);
        assertThat(result, is(true));

        result = oSimpleContainer.add(2);
        assertThat(result, is(true));

        result = oSimpleContainer.add(3);
        assertThat(result, is(true));

        result = oSimpleContainer.add(4);
        assertThat(result, is(true));

        result = oSimpleContainer.add(2);
        assertThat(result, is(false));

        result = oSimpleContainer.add(4);
        assertThat(result, is(false));

        result = oSimpleContainer.add(5);
        assertThat(result, is(true));
    }

    @Test
    public void whenContainerIsEmptyAndContainsShouldFalse() {
        SimpleHashSet<String> oSimpleContainer = new SimpleHashSet<>();

        boolean result = oSimpleContainer.contains("String 4");
        assertThat(result, is(false));
    }

    @Test
    public void whenContainsShouldTrue() {
        SimpleHashSet<Integer> oSimpleContainer = new SimpleHashSet<>();
        oSimpleContainer.add(1);
        oSimpleContainer.add(2);
        oSimpleContainer.add(3);

        boolean result = oSimpleContainer.contains(2);
        assertThat(result, is(true));
    }

    @Test
    public void whenContainsShouldFalse() {
        SimpleHashSet<Integer> oSimpleContainer = new SimpleHashSet<>();
        oSimpleContainer.add(1);
        oSimpleContainer.add(2);
        oSimpleContainer.add(3);

        boolean result = oSimpleContainer.contains(4);
        assertThat(result, is(false));
    }

    @Test
    public void whenContainerIsEmptyAndRemoveShouldFalse() {
        SimpleHashSet<String> oSimpleContainer = new SimpleHashSet<>();

        boolean result = oSimpleContainer.remove("5");
        assertThat(result, is(false));
    }

    @Test
    public void whenRemoveShouldTrue() {
        SimpleHashSet<String> oSimpleContainer = new SimpleHashSet<>();
        oSimpleContainer.add("1");
        oSimpleContainer.add("2");
        oSimpleContainer.add("3");

        boolean result = oSimpleContainer.remove("2");
        assertThat(result, is(true));
    }

    @Test
    public void whenRemoveShouldFalse() {
        SimpleHashSet<String> oSimpleContainer = new SimpleHashSet<>();
        oSimpleContainer.add("1");
        oSimpleContainer.add("2");
        oSimpleContainer.add("3");

        boolean result = oSimpleContainer.remove("4");
        assertThat(result, is(false));
    }

    @Test
    public void whenContainsHasElementWithEqualsHashCode() {
        SimpleHashSet<String> oSimpleContainer = new SimpleHashSet<>();
        oSimpleContainer.add("Viacheslav Solomatov");
        oSimpleContainer.add("Petr Simonov");
        oSimpleContainer.add("TJmpleHashSet");
        oSimpleContainer.add("WJacheslav Solomatov");
        oSimpleContainer.add("Petr Simonov");
        oSimpleContainer.add("SimpleHashSet");

        boolean result = oSimpleContainer.contains("Viacheslav Solomatov");
        assertThat(result, is(true));

        result = oSimpleContainer.contains("WJacheslav Solomatov");
        assertThat(result, is(true));

        result = oSimpleContainer.contains("TJmpleHashSet");
        assertThat(result, is(true));

        result = oSimpleContainer.contains("SimpleHashSet");
        assertThat(result, is(true));

        //System.out.println(oSimpleContainer.toString());
    }
}