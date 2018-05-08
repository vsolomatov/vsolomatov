package com.solomatoff.chapterjunior002.nonblock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import javax.persistence.OptimisticLockException;

import static java.lang.Thread.sleep;

public class NonBlock {
    private Map<Integer, Task> container = new ConcurrentHashMap<>();

    public boolean add(Task task) {
        return (container.putIfAbsent(task.getId(), task) == null);
    }

    public void update(Integer id, String newName, String newDesc) throws OptimisticLockException {
        BiFunction<Integer, Task, Task> remappingFunction = new BiFunction<Integer, Task, Task>() {
            // Определяем анонимный класс реализующий этот интерфейс
            @Override
            public Task apply(Integer id, Task task) {
                Integer newVersion = task.getVersion() + 1;
                System.out.printf("[%9s]. Изменяем задачу: %9s. Версия: %2d   на задачу  %9s. Версия: %2d%n", Thread.currentThread().getName(), task.getName(), task.getVersion(), newName, newVersion);
                // проверяем версию (вдруг кто-то уже изменил элемент)
                if (container.get(id).getVersion() != newVersion - 1) {
                    throw new OptimisticLockException();
                }
                return new Task(id, newName, newDesc, newVersion);
            }
        };
        // Этот метод изменяет элемент коллекции с ключом id вызывая метод apply() объекта remappingFunction
        // В метод apply() в качестве аргументов будет передан текущий ключ id и текущее отображаемое значение, т.е. task соответствующая этому ключу
        container.computeIfPresent(id, remappingFunction);
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
        this.version = 1;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
