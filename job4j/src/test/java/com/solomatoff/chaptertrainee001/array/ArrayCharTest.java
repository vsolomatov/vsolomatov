package com.solomatoff.chaptertrainee001.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrayCharTest {
    @Test
    public void whenStartWithPrefixThenTrue() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startWith("He");
        assertThat(result, is(true));
    }
    @Test
    public void whenNotStartWithPrefixThenFalse() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startWith("Hi");
        assertThat(result, is(false));
    }
    @Test
    public void whenContainsThenTrue() {
        ArrayChar word = new ArrayChar("");
        boolean result = word.contains("Привет", "иве");
        assertThat(result, is(true));
    }
    @Test
    public void whenContainsThenFalse() {
        ArrayChar word = new ArrayChar("");
        boolean result = word.contains("Привет", "ивт");
        assertThat(result, is(false));
    }
}