package ru.job4j.array;

/**
 * @author Viacheslav Solomatov (solomatoff.vyacheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public class FindLoop {
    /**
     * Метод ищет индекс нужного элемента массива
     *
     * @param data Исходный массив
     * @param el   Искомый элемент массива
     * @return Индекс искомого элемента массива, либо -1 в случае отсутствия такого элемента
     */
    public int indexOf(int[] data, int el) {

        int rsl = -1; // если элемента нет в массиве, то возвращаем -1.

        for (int index = 0; index < data.length; index++) {
            if (data[index] == el) {
                rsl = index;

                break;
            }
        }

        return rsl;
    }
}
