package com.solomatoff.chaptertrainee003.search;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author Vyacheslav Solomatov (solomatoff.vyacheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позицию определять по полю приоритет.
     * Для вставки использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {

        // Приоритет добавляемой задачи
        int priority = task.getPriority();
        //System.out.println("priority = " + priority);

        //Находим элемент нашего связного списка с приоритетом меньше чем у добавляемой задачи
        // другими словами, надо получить индекс (в списке) задачи с приоритетом меньшим чем у вставляемой задачи
        int index = this.tasks.size(); // Вставляем элемент в конец, если вдруг не окажется задач с приоритетом больше чем у новой задачи

        ListIterator<Task> myiterator = this.tasks.listIterator();
        while (myiterator.hasNext()) {
            if (myiterator.next().getPriority() >= priority) {
                index = myiterator.previousIndex();
                break;
            }
        }

        //System.out.println("index= " + index);
        this.tasks.add(index, task);

        /*for (Task task1 : tasks) {
            System.out.print(task1.getDesc() + " ");
        }
        System.out.println(" ");*/
    }

    public Task take() {
        return this.tasks.poll();
    }
}