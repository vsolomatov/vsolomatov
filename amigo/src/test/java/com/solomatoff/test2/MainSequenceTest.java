package com.solomatoff.test2;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TreeMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MainSequenceTest {

    @Test
    public void createMergedSequenceWhenCalendarAndInteger() {
        MainSequence mainSequence = new MainSequence();

        SequencePair<Calendar, Integer> sequencePair1 = new SequencePair<>();
        sequencePair1.addPairToSequence(new GregorianCalendar(2018, 0, 1), 1);
        sequencePair1.addPairToSequence(new GregorianCalendar(2018, 1, 1), 2);
        sequencePair1.addPairToSequence(new GregorianCalendar(2018, 2, 1), 3);
        sequencePair1.addPairToSequence(new GregorianCalendar(2018, 3, 15), 48);
        sequencePair1.addPairToSequence(new GregorianCalendar(2018, 4, 1), 5);
        // Выведем первую последовательность
        //TreeMap<Calendar, Integer> mapSequencePair1 = sequencePair1.getMapSequencePair();
        //mapSequencePair1.forEach((key, value) -> System.out.println(String.format("mapSequencePair1: key = %s value = %s", key.getTime(), value)));

        SequencePair<Calendar, Integer> sequencePair2 = new SequencePair<>();
        sequencePair2.addPairToSequence(new GregorianCalendar(2018, 1, 15), 6);
        sequencePair2.addPairToSequence(new GregorianCalendar(2018, 2, 15), 7);
        sequencePair2.addPairToSequence(new GregorianCalendar(2018, 3, 15), 84);
        sequencePair2.addPairToSequence(new GregorianCalendar(2018, 4, 15), 9);
        sequencePair2.addPairToSequence(new GregorianCalendar(2018, 5, 15), 10);
        // Выведем вторую последовательность
        //TreeMap<Calendar, Integer> mapSequencePair2 = sequencePair2.getMapSequencePair();
        //mapSequencePair2.forEach((key, value) -> System.out.println(String.format("mapSequencePair2: key = %s value = %s", key.getTime(), value)));

        // Объединяем последовательности
        SequencePair sequencePair = mainSequence.createMergedSequence(sequencePair1, sequencePair2);
        TreeMap<Calendar, Integer> mapSequencePair = sequencePair.getMapSequencePair();

        // Выведем полученную последовательность
        //mapSequencePair.forEach((key, value) -> System.out.println(String.format("mapSequencePair: key = %s value = %s", key.getTime(), value)));
        assertThat(mapSequencePair.firstEntry().getValue(), is(6) );
        assertThat(mapSequencePair.lastEntry().getValue(), is(10) );
    }

    @Test
    public void createMergedSequenceWhenIntegerAndString() {
        MainSequence mainSequence = new MainSequence();

        SequencePair<Integer, String> sequencePair1 = new SequencePair<>();
        sequencePair1.addPairToSequence(0, "0");
        sequencePair1.addPairToSequence(2, "2");
        sequencePair1.addPairToSequence(4, "4");
        sequencePair1.addPairToSequence(5, "51");
        sequencePair1.addPairToSequence(6, "6");
        sequencePair1.addPairToSequence(8, "8");

        // Выведем первую последовательность
        //TreeMap<Integer, String> mapSequencePair1 = sequencePair1.getMapSequencePair();
        //mapSequencePair1.forEach((key, value) -> System.out.println(String.format("mapSequencePair1: key = %s value = %s", key, value)));

        SequencePair<Integer, String> sequencePair2 = new SequencePair<>();
        sequencePair2.addPairToSequence(3, "3");
        sequencePair2.addPairToSequence(5, "52");
        sequencePair2.addPairToSequence(7, "7");
        sequencePair2.addPairToSequence(9, "9");
        sequencePair2.addPairToSequence(11, "11");
        // Выведем вторую последовательность
        //TreeMap<Integer, String> mapSequencePair2 = sequencePair2.getMapSequencePair();
        //mapSequencePair2.forEach((key, value) -> System.out.println(String.format("mapSequencePair2: key = %s value = %s", key, value)));

        // Объединяем последовательности
        SequencePair sequencePair = mainSequence.createMergedSequence(sequencePair1, sequencePair2);
        TreeMap<Integer, String> mapSequencePair = sequencePair.getMapSequencePair();

        // Выведем полученную последовательность
        //mapSequencePair.forEach((key, value) -> System.out.println(String.format("mapSequencePair: key = %s value = %s", key, value)));
        assertThat(mapSequencePair.firstEntry().getValue(), is("3") );
        assertThat(mapSequencePair.lastEntry().getValue(), is("11") );
    }

    @Test
    public void createMergedSequenceWhenDoNotIntersect() {
        MainSequence mainSequence = new MainSequence();

        SequencePair<Integer, String> sequencePair1 = new SequencePair<>();
        sequencePair1.addPairToSequence(0, "0");
        sequencePair1.addPairToSequence(2, "2");
        sequencePair1.addPairToSequence(4, "4");
        sequencePair1.addPairToSequence(5, "51");
        sequencePair1.addPairToSequence(6, "6");
        sequencePair1.addPairToSequence(8, "8");

        // Выведем первую последовательность
        //TreeMap<Integer, String> mapSequencePair1 = sequencePair1.getMapSequencePair();
        //mapSequencePair1.forEach((key, value) -> System.out.println(String.format("mapSequencePair1: key = %s value = %s", key, value)));

        SequencePair<Integer, String> sequencePair2 = new SequencePair<>();
        sequencePair2.addPairToSequence(9, "9");
        sequencePair2.addPairToSequence(11, "11");
        sequencePair2.addPairToSequence(13, "13");
        sequencePair2.addPairToSequence(15, "15");
        sequencePair2.addPairToSequence(17, "17");
        // Выведем вторую последовательность
        //TreeMap<Integer, String> mapSequencePair2 = sequencePair2.getMapSequencePair();
        //mapSequencePair2.forEach((key, value) -> System.out.println(String.format("mapSequencePair2: key = %s value = %s", key, value)));

        // Объединяем последовательности
        SequencePair sequencePair = mainSequence.createMergedSequence(sequencePair1, sequencePair2);
        TreeMap<Integer, String> mapSequencePair = sequencePair.getMapSequencePair();

        // Выведем полученную последовательность
        //mapSequencePair.forEach((key, value) -> System.out.println(String.format("mapSequencePair: key = %s value = %s", key, value)));
        assertThat(mapSequencePair.firstEntry().getValue(), is("9") );
        assertThat(mapSequencePair.lastEntry().getValue(), is("17") );
    }
}