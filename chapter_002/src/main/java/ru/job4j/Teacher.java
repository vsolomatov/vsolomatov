package ru.job4j;

/**
 * @author Viacheslav Solomatov (solomatoff.vyacheslav@yandex.ru)
 * @version $Id$
 * @since 0.1
 */

public class Teacher extends Profession {

    public static class Student {
        public String name;

        public Student(String name) {
            this.name = name;
        }
    }

    /**
     * Возвращает строку типа "Учитель Иван учит Сергея"
     *
     * @param student имя студента в родительном падеже
     * @return строку типа "Учитель Иван учит Сергея"
     */
    public String teach(Student student) {
        return "Учитель " + Teacher.this.getName() + " учит " + student.name;
    }
}