package com.solomatoff.chaptertrainee002.professions;

/**
 * @author Viacheslav Solomatov (solomatoff.vyacheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public class Engineer extends Profession {

    public static class House {
        public String name;

        public House(String name) {
            this.name = name;
        }
    }

    /**
     * Возвращает строку типа "Инженер Вася строит коттедж"
     *
     * @param house название строения
     * @return строку типа "Инженер Вася строит коттедж"
     */
    public String make(House house) {
        return "Инженер " + Engineer.this.getName() + " строит " + house.name;
    }
}
