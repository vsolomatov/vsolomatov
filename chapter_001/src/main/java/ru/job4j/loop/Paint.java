package ru.job4j.loop;

/**
 * @author Viacheslav Solomatov (solomatoff.vyacheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Paint {
    /**
     * Метод формирует строку в виде псевдографики пирамиды.
     *
     * @param height Высота пирамиды
     * @return Строка (псевдографика пирамиды)
     */
    public String pyramid(int height) {
        StringBuilder screen = new StringBuilder();
        int weight = 2 * height - 1;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if ( (row >= height - column - 1) && (row + height - 1 >= column) ) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}
