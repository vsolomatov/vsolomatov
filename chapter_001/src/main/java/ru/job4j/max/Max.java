package ru.job4j.max;

/**
 * @author Viacheslav Solomatov (solomatoff.vyacheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public class Max {
    /**
     * Возвращает максимальное значение из двух целых чисел
     *
     * @param first
     * @param second
     * @return Ответ
     */
    public int max(int first, int second) {
        return (first > second) ? first : second;
    }

    /**
     * Возвращает максимальное значение из трех целых чисел
     *
     * @param first Первое число
     * @param second Второе число
     * @param third Третье число
     * @return Ответ
     */
    public int maxthird(int first, int second, int third) {
        int rsl;
        rsl = max(first, second);
        rsl = max(rsl, third);
        return rsl;
    }
}