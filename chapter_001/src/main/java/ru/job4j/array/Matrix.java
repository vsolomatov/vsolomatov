package ru.job4j.array;
/**
 * @author Viacheslav Solomatov (solomatoff.vyacheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public class Matrix {
    /**
     * Метод создает двумерный массив, содержащий таблицу умножения
     *
     * @param size Размер таблицы
     * @return Двумерный массив, содержащий таблицу умножения
     */
    public  int[][] multiple(int size) {
        int[][] arr = new int[size][size];

        for (int i =0 ; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr[i][j]=(i+1)*(j+1);
            }
            /*for (int x : arr[i]) {
                System.out.print(x + " ");
            }
            System.out.println(" ");*/
        }
        return arr;
    }
}
