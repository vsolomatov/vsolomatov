package ru.job4j;

/**
 * @author Viacheslav Solomatov (solomatoff.vyacheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public class Doctor extends Profession {

    public static class Pacient {
        public String name;

        public Pacient(String name) {
            this.name = name;
        }
    }

    /**
     * Возвращает строку типа "Доктор Иван лечит Сергея"
     *
     * @param pacient имя пациента в родительном падеже
     * @return строку типа "Доктор Иван лечит Сергея"
     */
    public String heal(Pacient pacient) {
        return "Доктор " + Doctor.this.getName() + " лечит " + pacient.name;
    }
}

