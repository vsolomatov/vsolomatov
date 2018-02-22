package ru.job4j.array;

/**
 * @author Viacheslav Solomatov (solomatoff.vyacheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public class Square {
    /**
     * Метод заполняет массив элементами от 1 до bound возведенными в квадрат
     *
     * @param bound Длина массива.
     * @return Массив заполненный элементами от 1 до bound возведенными в квадрат
     */
    public int[] calculate(int bound) {

        int[] rst = new int[bound];

        // заполнить массив через цикл элементами от 1 до bound возведенные в квадрат
        for (int index = 1; index <= bound; index++) {
            rst[index - 1] = index * index;
        }
        return rst;
    }
}
