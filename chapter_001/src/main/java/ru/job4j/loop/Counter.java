package ru.job4j.loop;

/**
 * @author Viacheslav Solomatov (solomatoff.vyacheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public class Counter {

    /**
     * Вычисляет сумму четных чисел в диапазоне от start до finish
     *
     * @param start Начальное значение
     * @param finish Конечное значение
     * @return Ответ.
     */
    public int add(int start, int finish) {
        int rst = 0;
        for (int index = start; index <= finish; index++) {
            if (index % 2 == 0) {
                rst += index;
            }
        }
        return rst;
    }
}