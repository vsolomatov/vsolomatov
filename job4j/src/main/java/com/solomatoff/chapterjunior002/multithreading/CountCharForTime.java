package com.solomatoff.chapterjunior002.multithreading;

public class CountCharForTime {
    void count(int interruptTime) {
        String text = "Использование ключевого слова synchronized даёт гарантию, что в данный момент времени некий оператор или блок будет выполняться только в одном потоке.";
        Thread tTime = new Thread(new Time(interruptTime));
        Thread tCount = new Thread(new CountChar(text));
        tTime.start();
        tCount.start();
        try {
            tTime.join();
        } catch (InterruptedException e) {
           //System.out.println("Отсчет времени был прерван!");
        }
        if (tCount.getState() != Thread.State.TERMINATED) {
            //System.out.println("Меняем состояние прерывания потока, рассчитывающего кол-во символов в тексте.");
            tCount.interrupt();
        }
       //System.out.println("Окончание главного потока");
    }
}
