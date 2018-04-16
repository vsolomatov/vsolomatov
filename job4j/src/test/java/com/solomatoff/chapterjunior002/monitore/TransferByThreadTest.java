package com.solomatoff.chapterjunior002.monitore;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TransferByThreadTest {

    @Test
    public void whenTreeTransferThreadRunZeroOneTwo() {
        UserStorage userStorage = new UserStorage();
        User userZero = new User(0, 200);
        userStorage.add(userZero);
        User userOne = new User(1, 100);
        userStorage.add(userOne);
        User userTwo = new User(2, 500);
        userStorage.add(userTwo);
        TransferByThread transferZero = new TransferByThread(userStorage, userZero, userOne, 90);
        TransferByThread transferOne = new TransferByThread(userStorage, userTwo, userZero, 300);
        TransferByThread transferTwo = new TransferByThread(userStorage, userZero, userOne, 40);
        Thread t0 = new Thread(transferZero, "Zero");
        Thread t1 = new Thread(transferOne, "One");
        Thread t2 = new Thread(transferTwo, "Two");
        t0.start();
        t1.start();
        t2.start();
        try {
            t0.join();
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Iterator<User> it = userStorage.getUserContainer().iterator();
        User currentuser = it.next();
        assertThat(currentuser.amount, is(370));

        currentuser = it.next();
        assertThat(currentuser.amount, is(230));

        currentuser = it.next();
        assertThat(currentuser.amount, is(200));
    }

    @Test
    public void whenTreeTransferThreadRunTwoZeroOne() {
        UserStorage userStorage = new UserStorage();
        User userZero = new User(0, 200);
        userStorage.add(userZero);
        User userOne = new User(1, 100);
        userStorage.add(userOne);
        User userTwo = new User(2, 500);
        userStorage.add(userTwo);
        TransferByThread transferZero = new TransferByThread(userStorage, userZero, userOne, 90);
        TransferByThread transferOne = new TransferByThread(userStorage, userTwo, userZero, 300);
        TransferByThread transferTwo = new TransferByThread(userStorage, userZero, userOne, 40);
        Thread t0 = new Thread(transferZero, "Zero");
        Thread t1 = new Thread(transferOne, "One");
        Thread t2 = new Thread(transferTwo, "Two");
        t2.start();
        t0.start();
        t1.start();

        try {
            t0.join();
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (User currentUser : userStorage.getUserContainer()) {
            System.out.println("id: " + currentUser.id + " amount: " + currentUser.amount);
        }
    }
}