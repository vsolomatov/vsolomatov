package com.solomatoff.chapterjunior002.monitore;

public class TransferByThread implements Runnable {
    private UserStorage userStorage;
    private User userOne;
    private User userTwo;
    private int amount;

    TransferByThread(UserStorage userStorage, User userOne, User userTwo, int amount) {
        this.userStorage = userStorage;
        this.userOne = userOne;
        this.userTwo = userTwo;
        this.amount = amount;
    }

    @Override
    public void run() {
       //System.out.println("Начинает работу поток " + Thread.currentThread().getName());
        userStorage.transfer(this.userOne, this.userTwo, this.amount);
       //System.out.println("Закончил работу поток " + Thread.currentThread().getName());
    }
}
