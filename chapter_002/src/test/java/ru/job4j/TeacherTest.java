package ru.job4j;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import ru.vsolom.professions.*;

public class TeacherTest {
    @Test
    public void whenTeacherTeach1() {
        Teacher teacher = new Teacher();
        teacher.name = "Иван";
        teacher.profession = "Учитель русского языка";
        Teacher.Student student = new Teacher.Student("Сергея");

        String result = teacher.teach(student);
        String value = "Учитель Иван учит Сергея";
        assertThat(result, is(value));
    }
    @Test
    public void whenTeacherTeach2() {
        Teacher teacher = new Teacher();
        teacher.name = "Василий";
        teacher.profession = "Учитель математики";
        Teacher.Student student = new Teacher.Student("Андрея");

        String result = teacher.teach(student);
        String value = "Учитель Василий учит Андрея";
        assertThat(result, is(value));
    }
}
