package ru.job4j.loop;

/**
 * @author Viacheslav Solomatov (solomatoff.vyacheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public class Factorial {
    /**
     * Вычисляет факториал от n
     *
     * @param n
     * @return Ответ.
     */
    public int calc(int n) {
        int rslt = 1;
        for (int index = 1; index <= n; index++) {
            rslt = rslt * index;
        }
        return rslt;
    }
}
