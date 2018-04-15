package com.solomatoff.chapter_junior_002.Multithreading;

import org.junit.Test;

public class CountTextTest {
    @Test
    public void whenCountText() {
        String text = "Создать программу, которая будет считать количество слов и пробелов в тексте. Здесь не надо использовать регулярные выражения. Просто в цикле перебрать символы.";

        System.out.println("Начало расчетов");
        CountSpaces countSpaces = new CountSpaces(text);
        Thread t1 = new Thread(countSpaces, "thread 1");
        t1.start();

        CountWords countWords = new CountWords(text);
        Thread t2 = new Thread(countWords, "thread 2");
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Окончание расчетов");
    }
}