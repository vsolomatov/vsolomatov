package com.solomatoff.chaptertrainee002.professions;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DoctorTest {
    @Test
    public void whenDoctorHeal1() {
        Doctor doctor = new Doctor();
        doctor.name = "Иван";
        doctor.profession = "Отоларинголог";
        Doctor.Pacient pacient = new Doctor.Pacient("Сергея");

        String result = doctor.heal(pacient);
        String value = "Доктор Иван лечит Сергея";
        assertThat(result, is(value));
    }
    @Test
    public void whenDoctorHeal2() {
        Doctor doctor = new Doctor();
        doctor.name = "Василий";
        doctor.profession = "Хирург";
        Doctor.Pacient pacient = new Doctor.Pacient("Андрея");

        String result = doctor.heal(pacient);
        String value = "Доктор Василий лечит Андрея";
        assertThat(result, is(value));
    }
}
