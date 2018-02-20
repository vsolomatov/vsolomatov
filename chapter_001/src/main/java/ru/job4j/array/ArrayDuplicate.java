package ru.job4j.array;

import java.util.Arrays;

/**
 * @author Viacheslav Solomatov (solomatoff.vyacheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ArrayDuplicate {
    /**
     * Убирает дубликаты в массиве
     * @param arr Исходный массив
     * @return Массив без дубликатов
     */
    public String[] remove(String[] arr) {
        int unique = arr.length;
        for (int out = 0; out < unique; out++) {
            for (int in = out +1; in < unique; in++) {
                if (arr[out].equals(arr[in])) {
                    arr[in] = arr[unique - 1];
                    unique--;
                    in--;
                }
            }
        }
        return Arrays.copyOf(arr,unique);
    }
}
