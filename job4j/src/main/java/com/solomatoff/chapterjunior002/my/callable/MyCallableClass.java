package com.solomatoff.chapterjunior002.my.callable;

import java.util.concurrent.Callable;

public class MyCallableClass implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("    Начинаем очередной расчет в потоке " + Thread.currentThread().getName());
        Integer result = (int) (Math.random() * 10) % 10;
        Thread.sleep(350);
        System.out.println("    Заканчиваем выполнение расчетов в потоке " + Thread.currentThread().getName());
        return result;
    }
}
