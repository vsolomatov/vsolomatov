package com.solomatoff.chaptertrainee001.array;

/**
 * @author Viacheslav Solomatov (solomatoff.vyacheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Turn {
    /**
     * Метод переворачивает массив задом наперед
     *
     * @param data Исходный массив
     * @return Тот же массив, но с элементами перевернутыми задом наперед
     */
    public int[] back(int[] data) {
        int temp;

        for (int index = 0; index < data.length / 2; index++) {
            temp = data[index];
            data[index] = data[data.length - 1 - index];
            data[data.length - 1 - index] = temp;
        }

        return data;
    }
}