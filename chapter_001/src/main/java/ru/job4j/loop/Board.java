package ru.job4j.loop;

/**
 * @author Viacheslav Solomatov (solomatoff.vyacheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public class Board {
    /**
     * Метод формирует строку в виде псевдографики шахматной доски.
     *
     * @param width  Ширина шахматной доски
     * @param height Высота шахматной доски
     * @return Строка (псевдографика шахматной доски)
     */
    public String paint(int width, int height) {

        StringBuilder screen = new StringBuilder();
        String ln = System.getProperty("line.separator");

        for (int index = 1; index <= height; index++) {
            for (int ind = 1; ind <= width; ind++) {
                // условие проверки, что писать пробел или X
                if ((index + ind) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            // добавляем перевод на новую строку.
            screen.append(ln);
        }
        return screen.toString();
    }
}

