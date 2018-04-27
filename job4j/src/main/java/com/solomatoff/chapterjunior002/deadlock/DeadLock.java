package com.solomatoff.chapterjunior002.deadlock;

public class DeadLock {

    static class Friend {
        private final String name;
        public Friend(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }

        public synchronized void bow(Friend bower) {
            System.out.println("[" + Thread.currentThread().getId() + "] Метод bow заблокировал объект " + this.name);
            bower.bowBack(this);
            System.out.println("[" + Thread.currentThread().getId() + "] Метод bow разблокирует объект " + this.name);
        }
        public synchronized void bowBack(Friend bower) {
            System.out.println("    [" + Thread.currentThread().getId() + "] Метод bowBack заблокировал объект " + this.name);
            System.out.println("    [" + Thread.currentThread().getId() + "] Метод bowBack разблокирует объект " + this.name);
        }
    }

    public static void main(String[] args) {
        final Friend alphonse =
                new Friend("Alphonse");
        final Friend gaston =
                new Friend("Gaston");
        new Thread(new Runnable() {
            public void run() {
                alphonse.bow(gaston);
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                gaston.bow(alphonse);
            }
        }).start();
    }
}