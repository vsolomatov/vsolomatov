package com.solomatoff.chaptertrainee003.search;

/**
 * Класс описывает Задачу, имеющую строкоое описание
 * и целочисленное поле приоритет.
 */
public class Task {
    private String desc;
    private int priority;

    public Task(String desc, int priority) {
        this.desc = desc;
        this.priority = priority;
    }

    public String getDesc() {
        return desc;
    }

    public int getPriority() {
        return priority;
    }
}