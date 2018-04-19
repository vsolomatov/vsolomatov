package com.solomatoff.chapterjunior002.producerconsumer;

class PC {
    public static void main(String args[]) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        new Producer<>(queue, 0);
        new Producer<>(queue, 1);
        new Producer<>(queue, 2);
        new Consumer<>(queue);
        new Consumer<>(queue);
        new Consumer<>(queue);
        new Consumer<>(queue);
        new Consumer<>(queue);
    }
}