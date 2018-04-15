package com.solomatoff.chapterjunior002.multithreading;

/**
 * Класс для подсчета количества слов в тексте
 */
class CountWords implements Runnable {
    private String text;

    CountWords(String text) {
        this.text = text;
    }

    @Override
    public void run() {
        String[] splitText = this.text.split(" ");
        int count = splitText.length;
        System.out.println("Кол-во слов в тексте: " + count);
    }
}