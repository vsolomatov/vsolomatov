package com.solomatoff.chaptertrainee001.array;

/**
 * @author Viacheslav Solomatov (solomatoff.vyacheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public class BubbleSort {
    /**
     * Метод сортирует массив по возрастанию
     *
     * @param arr Исходный массив
     * @return Тот же массив, но с элементами отсортированными по возрастанию
     */
    public int[] sort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int t = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = t;
                }
                /*for (int x : arr) {
                    System.out.print(x + " ");
                }
                System.out.println(" ");*/
            }
        }
        return arr;
    }
}
