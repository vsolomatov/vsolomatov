package com.solomatoff.map;

import org.junit.Test;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    @Test
    public void insert() {
        SimpleHashMap<Integer, String> simpleHashMap = new SimpleHashMap<>();
        boolean result;
        result = simpleHashMap.insert(102, "String 0");
        assertThat(result, is(true));
        result = simpleHashMap.insert(201, "String 1");
        assertThat(result, is(true));
        result = simpleHashMap.insert(302, "String 2");
        assertThat(result, is(true));
        result = simpleHashMap.insert(404, "String 3");
        assertThat(result, is(true));
        result = simpleHashMap.insert(505, "String 4");
        assertThat(result, is(true));
        result = simpleHashMap.insert(606, "String 5");
        assertThat(result, is(true));
        result = simpleHashMap.insert(707, "String 6");
        assertThat(result, is(true));
        result = simpleHashMap.insert(808, "String 7");
        assertThat(result, is(true));
        result = simpleHashMap.insert(909, "String 8");
        assertThat(result, is(true));
        result = simpleHashMap.insert(1010, "String 9");
        assertThat(result, is(true));
        result = simpleHashMap.insert(2020, "String 10");
        assertThat(result, is(true));

        result = simpleHashMap.insert(707, "String 12");
        assertThat(result, is(false));
        String resultGet = simpleHashMap.get(707);
        assertThat(resultGet, is("String 6"));

        resultGet = simpleHashMap.get(302);
        assertThat(resultGet, is("String 2"));

        //System.out.println(simpleHashMap);
    }

    @Test
    public void get() {
        SimpleHashMap<Integer, String> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert(0, "String 0");
        simpleHashMap.insert(2, "String 1");
        simpleHashMap.insert(4, "String 2");

        String result = simpleHashMap.get(2);
        assertThat(result, is("String 1"));

        result = simpleHashMap.get(0);
        assertThat(result, is("String 0"));
    }

    @Test
    public void whenDelete() {
        SimpleHashMap<Integer, String> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert(100, "String 0");
        simpleHashMap.insert(215, "String 1");
        simpleHashMap.insert(418, "String 2");
        simpleHashMap.insert(602, "String 3");

        boolean result = simpleHashMap.delete(418);
        assertThat(result, is(true));

        result = simpleHashMap.delete(100);
        assertThat(result, is(true));

        result = simpleHashMap.delete(100);
        assertThat(result, is(false));
    }

    @Test
    public void whenIterator() {
        SimpleHashMap<Integer, String> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert(100, "String 0");
        simpleHashMap.insert(215, "String 1");
        simpleHashMap.insert(418, "String 2");
        simpleHashMap.insert(602, "String 3");

        boolean hasNext = simpleHashMap.hasNext();
        assertThat(hasNext, is(true));
        simpleHashMap.next();

        hasNext = simpleHashMap.hasNext();
        assertThat(hasNext, is(true));
        simpleHashMap.next();

        hasNext = simpleHashMap.hasNext();
        assertThat(hasNext, is(true));
        simpleHashMap.next();

        hasNext = simpleHashMap.hasNext();
        assertThat(hasNext, is(true));
        simpleHashMap.next();

        hasNext = simpleHashMap.hasNext();
        assertThat(hasNext, is(false));
    }
}