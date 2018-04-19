package com.solomatoff.chapterjunior002.producerconsumer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private static final int CAPACITY = 7;

    /**
     * Метод заполняет значениями блокирующую очередь
     * @param value - добавляемое значение
     */
    synchronized void offer(T value) {
        while (!this.queue.isEmpty()) { // ждем опустошения очереди
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        }
        while (queue.size() < CAPACITY ) {
            this.queue.offer(value);
        }
        System.out.println("    Producer " + Thread.currentThread().getName() + " Put: " + value );
        notifyAll();
    }

    /**
     * Метод возвращает очередное значение из блокирующей очереди
     * @return очередное значение из блокирующей очереди
     */
    synchronized T poll() {
        T value;
        while (this.queue.isEmpty()) { // ждем пока не будут добавлены значения в блокирующую очередь продюсерами
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        }
        value = this.queue.poll();
        System.out.println("    Got: " + value + " in " + Thread.currentThread().getName());
        notifyAll();
        return value;
    }
}