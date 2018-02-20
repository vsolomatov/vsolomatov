package ru.job4j.array;
/**
 * @author Viacheslav Solomatov (solomatoff.vyacheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public class ArrayChar {

    private char[] data;

    /**
     * Присваивает полю data значение
     * @param line Строковое значение
     */
    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Проверяет. что слово начинается с префикса.
     * @param prefix префикс.
     * @return если слово начинаеться с префикса
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] value = prefix.toCharArray();
        // проверяем, что массив data имеет первые элементы одинаковые со вспомогательным массивом value
        for (int index=0; index < value.length; index++) {
            if (data[index] != value[index]) {
                    result = false;
            }
        }

        return result;
    }
}