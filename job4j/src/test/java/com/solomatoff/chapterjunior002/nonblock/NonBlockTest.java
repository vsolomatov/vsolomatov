package com.solomatoff.chapterjunior002.nonblock;

import org.junit.Test;
import javax.persistence.OptimisticLockException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;
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
           //System.out.println(task.getName() + " " + task.getDesc());
        }
        assertThat(result, is(expected));
    }

    @Test
    public void update() {
        NonBlock nonBlock = new NonBlock();
        Task task1 = new Task(1, "be", "was,were been");
        nonBlock.add(task1);
        Task task2 = new Task(2, "beat", "beat beaten");
        nonBlock.add(task2);
        Task task3 = new Task(3, "become", "became become");
        nonBlock.add(task3);

        for (int index = 0; index < 14; index++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        nonBlock.update(2, Thread.currentThread().getName(), Thread.currentThread().getName());
                    } catch (OptimisticLockException e) {
                       //System.out.println("Не удалось обновить задачу из потока " + Thread.currentThread().getName());
                    }
                }
            }).start();
        }

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Collection<Task> collection = nonBlock.getContainer().values();
        for (Task task : collection) {
           //System.out.println(task.getId() + " " + task.getName() + " " + task.getDesc() + " " + task.getVersion());
        }
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
           //System.out.println(task.getName() + " " + task.getDesc());
        }
        assertThat(result, is(expected));
    }
}