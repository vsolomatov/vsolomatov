package com.solomatoff.chapterjunior002.producerconsumer;

class Consumer<T> implements Runnable {
    private SimpleBlockingQueue<T> simpleQueue;

    Consumer(SimpleBlockingQueue<T> queue) {
        this.simpleQueue = queue;
        new Thread(this).start();
    }

    public void run() {
        while (true) {
            simpleQueue.poll();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}