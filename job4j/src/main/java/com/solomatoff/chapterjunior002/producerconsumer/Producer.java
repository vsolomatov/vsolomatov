package com.solomatoff.chapterjunior002.producerconsumer;

class Producer<T> implements Runnable {
    private SimpleBlockingQueue<T> simpleQueue;
    private T element;

    Producer(SimpleBlockingQueue<T> queue, T element) {
        this.simpleQueue = queue;
        this.element = element;
        new Thread(this).start();
    }

    public void run() {
        while(true) {
            simpleQueue.offer(element);
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}