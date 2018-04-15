package com.solomatoff.chapterjunior002.multithreading;

import org.junit.Test;

public class CountCharForTimeTest {

    @Test
    public void whenCountAndIsSufficientTime() {
        CountCharForTime countCharForTime = new CountCharForTime();
        countCharForTime.count(1000);
    }

    @Test
    public void whenCountAndIsNotSufficientTime() {
        CountCharForTime countCharForTime = new CountCharForTime();
        countCharForTime.count(100);
    }
}