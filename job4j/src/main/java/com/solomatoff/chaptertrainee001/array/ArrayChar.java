package com.solomatoff.chaptertrainee001.array;

/**
 * @author Viacheslav Solomatov (solomatoff.vyacheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public class ArrayChar {

    private char[] data;

    /**
     * Присваивает полю data значение
     *
     * @param line Строковое значение
     */
    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Проверяет. что слово начинается с префикса.
     *
     * @param prefix префикс.
     * @return если слово начинаеться с префикса
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] value = prefix.toCharArray();
        // проверяем, что массив data имеет первые элементы одинаковые со вспомогательным массивом value
        for (int index = 0; index < value.length; index++) {
            if (data[index] != value[index]) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Проверяет, что слово String находится в другом слове String
     *
     * @param origin Первое слово
     * @param sub    Второе слово
     * @return true если слово находится в другом слове
     */
    public boolean contains(String origin, String sub) {
        boolean result = true;
        char[] originarray = origin.toCharArray();
        char[] subarray = sub.toCharArray();
        // проверяем, что массив data имеет элементы одинаковые со вспомогательным массивом subarray
        for (int out = 0; out < originarray.length - subarray.length + 1; out++) {
            result = true;
            // Цикл по subarray
            for (int in = 0; in < subarray.length; in++) {

                ////System.out.println( Integer.toString(out + in ) + " " + Integer.toString(in) );

                if (originarray[out + in] != subarray[in]) {
                    result = false;
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }
}