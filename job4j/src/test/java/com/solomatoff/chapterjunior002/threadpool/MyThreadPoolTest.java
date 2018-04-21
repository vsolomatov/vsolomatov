package com.solomatoff.chapterjunior002.threadpool;

import org.junit.Test;

public class MyThreadPoolTest {

    @Test
    public void whenMyThreadPoolTestTwo() {
        MyThreadPool mypool = new MyThreadPool(2);
        for (int i = 0; i < 100; i++) {
            mypool.add(new Work());
        }
        try {
            Thread.sleep(2700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mypool.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}