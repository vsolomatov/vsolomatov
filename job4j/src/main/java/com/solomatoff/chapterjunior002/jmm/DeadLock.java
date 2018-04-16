package com.solomatoff.chapterjunior002.jmm;

class DeadLock implements Runnable {
    private A a = new A();
    private B b = new B();

    private DeadLock() {
        Thread.currentThread().setName("MainThread");
        Thread t = new Thread(this, "BranchThread");
        t.start();
        a.methodWorkingWithB(b);
        System.out.println("Exit from constructor DeadLock");
    }

    public void run() {
        b.methodWorkingWithA(a); // get lock on b in other thread.
        System.out.println("Exit from run()");
    }

    public static void main(String[] args) {
        new DeadLock();
    }
}

class A {
    synchronized void methodWorkingWithB(B b) {
        String name = Thread.currentThread().getName();
        System.out.println("Поток " + name + ". Вход в метод объекта A, работающий с объектом B");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Метод, работающий с объектом B прерван.");
        }
        System.out.println("Поток " + name + " пытается вызвать b.methodFromB()");
        b.methodFromB();
    }

    synchronized void methodFromA() {
        System.out.println("Печать внутри methodFromA");
    }
}

class B {
    synchronized void methodWorkingWithA(A a) {
        String name = Thread.currentThread().getName();
        System.out.println("Поток " + name + ". Вход в метод объекта B, работающий с объектом A");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Метод, работающий с объектом A прерван.");
        }
        System.out.println("Поток " + name + " пытается вызвать a.methodFromA()");
        a.methodFromA();
    }

    synchronized void methodFromB() {
        System.out.println("Печать внутри methodFromB");
    }
}