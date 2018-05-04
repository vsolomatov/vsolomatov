package com.solomatoff.chaptertrainee002.professions;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EngineerTest {
    @Test
    public void whenEngineerMake1() {
        Engineer engineer = new Engineer();
        engineer.name = "Иван";
        engineer.profession = "Строитель";
        Engineer.House house = new Engineer.House("небоскреб");

        String result = engineer.make(house);
        String value = "Инженер Иван строит небоскреб";
        assertThat(result, is(value));
    }
    @Test
    public void whenEngineerMake2() {
        Engineer engineer = new Engineer();
        engineer.name = "Василий";
        engineer.profession = "Инженер-строитель";
        Engineer.House house = new Engineer.House("коттедж");

        String result = engineer.make(house);
        String value = "Инженер Василий строит коттедж";
        assertThat(result, is(value));
    }
}
