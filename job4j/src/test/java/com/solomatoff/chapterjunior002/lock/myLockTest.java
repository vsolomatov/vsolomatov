package com.solomatoff.chapterjunior002.lock;

import org.junit.Test;

public class myLockTest {

    @Test
    public void whenTestMyLock() {
        for (int i = 0; i < 100; i++) {
            new Thread(new WorkInThread()).start();
        }
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}