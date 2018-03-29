package com.solomatoff.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

public class UserTest {

   /* @Test
    public void whenTwoUserDifferentShouldTwoElementMap() {
        User userFirst = new User("Viacheslav Solomatov");
        System.out.println(userFirst.hashCode());

        User userSecond = new User("Viacheslav Solomatov");
        System.out.println(userSecond.hashCode());

        System.out.println("Объекты эквивалентны? Ответ: " + userFirst.equals(userSecond));

        Map<User, Object> map = new HashMap<>();
        Object objectFirst = new Object();
        Object objectSecond = new Object();
        map.put(userFirst, objectFirst);
        map.put(userSecond, objectSecond);

        System.out.println(map);

        Object resultobjectFirst = map.get(userFirst);
        assertThat(resultobjectFirst, is(objectFirst));

        Object resultobjectSecond = map.get(userSecond);
        assertThat(resultobjectSecond, is(objectSecond));
    }*/

    @Test
    public void whenTwoUserDifferentShouldTwoElementMap() {
        User userFirst = new User("Viacheslav Solomatov");
        System.out.println(userFirst.hashCode());

        User userSecond = new User("Viacheslav Solomatov");
        System.out.println(userSecond.hashCode());

        System.out.println("Объекты эквивалентны? Ответ: " + userFirst.equals(userSecond));

        Map<User, Object> map = new HashMap<>();

        Object objectFirst = new Object();
        System.out.println("objectFirst = " + objectFirst);
        Object resultobjectFirst = map.put(userFirst, objectFirst);
        assertThat(resultobjectFirst, is(nullValue()));

        Object objectSecond = new Object();
        System.out.println("objectSecond = " + objectSecond);
        Object resultObjectSecond = map.put(userSecond, objectSecond);
        assertThat(resultObjectSecond, is(objectFirst));

        System.out.println(map);

    }
}