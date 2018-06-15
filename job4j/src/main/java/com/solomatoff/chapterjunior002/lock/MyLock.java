package com.solomatoff.chapterjunior002.lock;

import static java.lang.Thread.sleep;

class MyLock {
    private final Object monitor = new Object();
    private boolean freeLock = true;

    void lock() {
        synchronized (this.monitor) {
            while (!this.freeLock) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.freeLock = false;
        }
    }

    void unlock() {
        synchronized (monitor) {
            if (!this.freeLock) {
                // освобождаем
                this.freeLock = true;
                monitor.notifyAll();
            }
        }
    }
}

class WorkInThread implements Runnable {
    private static MyLock myLock = new MyLock();

    @Override
    public void run() {
        myLock.lock();
       //System.out.println("Start Working");
        try {
           //System.out.println("        Working in " + Thread.currentThread().getName());
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
           //System.out.println("Finish Working");
           //System.out.println("");
            myLock.unlock();
        }
    }
}

