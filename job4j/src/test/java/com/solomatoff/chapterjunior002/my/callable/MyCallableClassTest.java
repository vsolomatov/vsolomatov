package com.solomatoff.chapterjunior002.my.callable;

import org.junit.Test;

import java.util.concurrent.FutureTask;

public class MyCallableClassTest {
    @Test
    public void whenCallableRun() {
        int result;
        MyCallableClass myCallable = new MyCallableClass();
        FutureTask<Integer>[] myFutureArray = new FutureTask[10];
        for (int i = 0; i < 10; i++) {
            myFutureArray[i] = new FutureTask<>(myCallable);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(myFutureArray[i]).start();
        }
        int count = 0;
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Пытаемся получить результат i=" + i);
                result = myFutureArray[i].get();
                count += result;
                System.out.println("Получили результат i=" + i + " (" + result + ")");
                System.out.println("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Сумма результатов " + count);
    }
}