package com.solomatoff.chapterjunior002.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private List<User> userContainer = new ArrayList<>();

    synchronized boolean add(User user) {
        // проверку на уникальность id делать не стал, задача не для этого
        return this.userContainer.add(user);
    }

    private synchronized boolean update(User user, int amount) {
        boolean result = true;
        String threadName = Thread.currentThread().getName();
       //System.out.println("        Начинаем update пользователя с id = " + user.id + " из потока " + threadName);
        int index = this.userContainer.indexOf(user);
        if (index < 0) {
            result = false;
        } else {
            user.amount = amount;
            this.userContainer.set(index, user);
        }
       //System.out.println("        Закончили update пользователя с id = " + user.id + " из потока " + threadName);
        return result;
    }

    synchronized boolean delete(User user) {
        return this.userContainer.remove(user);

    }

    synchronized void transfer(User userFrom, User userTo, int amount) {
        String threadName = Thread.currentThread().getName();
        int index = this.userContainer.indexOf(userFrom);
        int amountFromTo = this.userContainer.get(index).amount;
       //System.out.println("    Начальный остаток у userFrom: " + amountFromTo + " из потока " + threadName);
        amountFromTo -= amount;
       //System.out.println("    Новый остаток у userFrom: " + amountFromTo + " из потока " + threadName);
        update(userFrom, amountFromTo);

        index = this.userContainer.indexOf(userTo);
        amountFromTo = this.userContainer.get(index).amount;
       //System.out.println("    Начальный остаток у userTo: " + amountFromTo + " из потока " + threadName);
        amountFromTo += amount;
       //System.out.println("    Новый остаток у userTo: " + amountFromTo + " из потока " + threadName);
        update(userTo, amountFromTo);
    }

    synchronized List<User> getUserContainer() {
        return this.userContainer;
    }
}
