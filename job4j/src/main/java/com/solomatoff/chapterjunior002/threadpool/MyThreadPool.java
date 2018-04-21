package com.solomatoff.chapterjunior002.threadpool;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *  Реализует пул из nThreads потоков.
 *  Каждый из потоков ожидает задание на выполнение из очереди заданий
 *  до пер пор пока не дана команда на завершение работы пула (shutdown()).
 *  Если в очереди отсутствуют задания, то
 *  поток простаивает и ожидает появления задачи или команды на завершение работы пула.
 */
class MyThreadPool {
    private final Queue<Work> workQueue = new ConcurrentLinkedQueue<>();
    private volatile boolean isRunning = true;

    MyThreadPool(int nThreads) {
        for (int i = 0; i < nThreads; i++) {
            new Thread(new ForRunWork()).start();
        }
    }

    void add(Work work) {
        if (isRunning) {
                workQueue.offer(work);
        }
    }

    public void shutdown() {
        isRunning = false;
    }

    private class ForRunWork implements Runnable {
        @Override
        public void run() {
            int count = 1;
            String nameWork;
            while (isRunning) {
                Work nextTask = workQueue.poll();
                if (nextTask != null) {
                    nameWork = "Task " + count + "[" + Thread.currentThread().getName().substring(7) + "]";
                    System.out.println("Befor start " + nameWork);
                    nextTask.executeWork(nameWork);
                    System.out.println("After finish " + nameWork);
                    System.out.println("");
                    count++;
                }

            }
        }
    }
}

class Work {
    public void executeWork(String nameWork) {
        System.out.println("    Start Working in " + nameWork);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("    Finish Working in " + nameWork);
    }
}