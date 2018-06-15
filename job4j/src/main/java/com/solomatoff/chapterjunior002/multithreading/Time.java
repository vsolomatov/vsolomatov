package com.solomatoff.chapterjunior002.multithreading;

public class Time implements Runnable {
    private int interruptTime;

    Time(int interruptTime) {
        this.interruptTime = interruptTime;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        long currentTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < interruptTime) {
            currentTime = System.currentTimeMillis();
        }
       //System.out.println("Время закончилось! (" + currentTime + ")");
    }
}
