package com.solomatoff.chapterjunior002.nonblock;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import javax.persistence.OptimisticLockException;

public class NonBlock {
    private Map<Integer, Task> container = new ConcurrentHashMap<>();

    private BiFunction<Integer, Task, Task> remappingFunction = new BiFunction<Integer, Task, Task>() {
        @Override
        public Task apply(Integer id, Task task) {
            return new Task(id, task.getName(), task.getDesc(), task.getVersion() + 1);
        }
    };

    public boolean add(Task task) {
        return (container.putIfAbsent(task.getId(), task) == null);
    }

    public void update(Integer id, Task newTask) throws OptimisticLockException, NoSuchElementException {
        // берем новые значения полей
        String newName = newTask.getName();
        String newDesc = newTask.getDesc();
        // запоминаем версию задачи
        int version = container.get(id).getVersion();
        System.out.println("[" + Thread.currentThread().getName() + "] До изменения коллеции. version = " + version);
        Task currentTask = container.get(id);
        currentTask.setName(newName);
        currentTask.setDesc(newDesc);
        Task updatedTask = container.computeIfPresent(id, remappingFunction); // этот метод изменяет элемент коллекции
        int updatedVersion = container.get(id).getVersion();
        System.out.println("    [" + Thread.currentThread().getName() + "] После изменения коллекции. version = " + updatedVersion);
        System.out.println("        [" + Thread.currentThread().getName() + "] " + updatedTask.getName() + " " + updatedTask.getDesc() + " " + updatedTask.getVersion());
        if (updatedVersion != version + 1) {
            throw new OptimisticLockException();
        }
    }

    public boolean delete(Integer id) {
        boolean result = false;
        Task task = container.get(id);
        if (task != null) {
            container.remove(id);
            result = true;
        }
        return result;
    }

    public Map<Integer, Task> getContainer() {
        return this.container;
    }
}

class Task {
    private int id;
    private String name;
    private String desc;
    private int version;

    Task(int id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.version = 0;
    }

    Task(int id, String name, String desc, int version) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.version = version;
    }

    int getId() {
        return id;
    }
    String getName() {
        return name;
    }

    String getDesc() {
        return desc;
    }

    int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
