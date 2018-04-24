package com.solomatoff.chapterjunior002.nonblock;

import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class NonBlockTest {

    @Test
    public void add() {
        NonBlock nonBlock = new NonBlock();
        Task task1 = new Task(1, "be", "was, were been");
        nonBlock.add(task1);
        Task task2 = new Task(2, "beat", "beat beaten");
        nonBlock.add(task2);
        Task task3 = new Task(3, "become", "became become");
        nonBlock.add(task3);

        Map<Integer, Task> result = nonBlock.getContainer();

        Map<Integer, Task> expected = new HashMap<>();
        expected.put(task1.getId(), task1);
        expected.put(task2.getId(), task2);
        expected.put(task3.getId(), task3);

        for (Task task : result.values()) {
            System.out.println(task.getName() + " " + task.getDesc());
        }
        assertThat(result, is(expected));
    }

    @Test
    public void update() {
        NonBlock nonBlock = new NonBlock();
        Task task1 = new Task(1, "be", "was, were been");
        nonBlock.add(task1);
        Task task2 = new Task(2, "beat", "beat beaten");
        nonBlock.add(task2);
        Task task3 = new Task(3, "become", "became become");
        nonBlock.add(task3);

        Task taskForUpdate1 = new Task(2, "begin", "began begun");
        Task taskForUpdate2 = new Task(2, "bend", "bent bent");
        Task taskForUpdate3 = new Task(2, "bet", "bet bet");
        Task taskForUpdate4 = new Task(2, "bite", "bit bitten");
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                nonBlock.update(2, taskForUpdate1);
            }
        }, "begin");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                nonBlock.update(2, taskForUpdate2);
            }
        }, "bend");
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                nonBlock.update(2, taskForUpdate3);
            }
        }, "bet");
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                nonBlock.update(2, taskForUpdate4);
            }
        }, "bite");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Collection<Task> collection = nonBlock.getContainer().values();
        int[] result = new int[3];
        int index = 0;
        for (Task task : collection) {
            result[index] = task.getVersion();
            System.out.println(task.getName() + " " + task.getDesc() + " " + task.getVersion());
            index++;
        }
        int[] expected = {0, 4, 0};
        assertThat(result, is(expected));
    }

    @Test
    public void delete() {
        NonBlock nonBlock = new NonBlock();
        Task task1 = new Task(1, "be", "was, were been");
        nonBlock.add(task1);
        Task task2 = new Task(2, "beat", "beat beaten");
        nonBlock.add(task2);
        Task task3 = new Task(3, "become", "became become");
        nonBlock.add(task3);
        nonBlock.delete(2);

        Map<Integer, Task> result = nonBlock.getContainer();

        Map<Integer, Task> expected = new HashMap<>();
        expected.put(task1.getId(), task1);
        expected.put(task3.getId(), task3);

        for (Task task : result.values()) {
            System.out.println(task.getName() + " " + task.getDesc());
        }
        assertThat(result, is(expected));
    }
}