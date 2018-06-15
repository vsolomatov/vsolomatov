package com.solomatoff.chapterjunior002.multithreading;

public class CountChar implements Runnable {
    private String text;

    CountChar(String text) {
        this.text = text;
    }

    @Override
    public void run() {
        int count = 0;
        char[] charArray = this.text.toCharArray();
       //System.out.println("Начинаем считать кол-во символов в тексте!");
        while (!Thread.currentThread().isInterrupted() && count < charArray.length) {
            //System.out.println("Текущее значение count =" + count);
            count++;
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                break;
            }
        }
        if (count == charArray.length) {
           //System.out.println("Кол-во символов в тексте: " + count);
        } else {
           //System.out.println("Программа не успела посчитать кол-во символов в тексте!");
        }
    }
}
