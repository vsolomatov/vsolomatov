package com.solomatoff.chapter_junior_002.Multithreading;

public class CountSpaces implements Runnable {
    private String text;

    CountSpaces(String text) {
        this.text = text;
    }

    @Override
    public void run() {
        int count = 0;
        char[] charArray = this.text.toCharArray();
        for (char simbol : charArray) {
            if (simbol == ' ') {
                count++;
            }
        }
        System.out.println("Кол-во пробелов в тексте: " + count);
    }
}
