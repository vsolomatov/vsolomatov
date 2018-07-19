package com.solomatoff.test2;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TreeMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SequencePairTest {

    @Test
    public void addPairToSequence() {
        SequencePair<Calendar, Integer> sequencePair = new SequencePair<>();
        sequencePair.addPairToSequence(new GregorianCalendar(2018, 0, 1), 1);
        sequencePair.addPairToSequence(new GregorianCalendar(2018, 1, 1), 2);

        TreeMap<Calendar, Integer> mapSequencePair = sequencePair.getMapSequencePair();
        //mapSequencePair.forEach((key, value) -> System.out.println(String.format("mapSequencePair: key = %s value = %s", key.getTime(), value)));
        assertThat(mapSequencePair.firstEntry().getValue(), is(1) );
        assertThat(mapSequencePair.lastEntry().getValue(), is(2) );
    }

    @Test
    public void removePairFromSequence() {
        SequencePair<Calendar, Integer> sequencePair = new SequencePair<>();
        sequencePair.addPairToSequence(new GregorianCalendar(2018, 0, 1), 1);
        sequencePair.addPairToSequence(new GregorianCalendar(2018, 1, 1), 2);

        sequencePair.removePairFromSequence(new GregorianCalendar(2018, 1, 1));

        TreeMap<Calendar, Integer> mapSequencePair = sequencePair.getMapSequencePair();
        //mapSequencePair.forEach((key, value) -> System.out.println(String.format("mapSequencePair: key = %s value = %s", key.getTime(), value)));
        assertThat(mapSequencePair.firstEntry().getValue(), is(1) );
        assertThat(mapSequencePair.lastEntry().getValue(), is(1) );
    }

    @Test
    public void getMapSequencePair() {
        SequencePair<Calendar, Integer> sequencePair = new SequencePair<>();
        sequencePair.addPairToSequence(new GregorianCalendar(2018, 0, 1), 1);
        sequencePair.addPairToSequence(new GregorianCalendar(2018, 1, 1), 2);
        sequencePair.addPairToSequence(new GregorianCalendar(2018, 2, 1), 3);

        TreeMap<Calendar, Integer> mapSequencePair = sequencePair.getMapSequencePair();
        //mapSequencePair.forEach((key, value) -> System.out.println(String.format("mapSequencePair: key = %s value = %s", key.getTime(), value)));
        assertThat(mapSequencePair.firstEntry().getValue(), is(1) );
        assertThat(mapSequencePair.lastEntry().getValue(), is(3) );
    }

    @Test
    public void setMapSequencePair() {
        TreeMap<Calendar, Integer> mapSequencePair = new TreeMap<>();
        mapSequencePair.put(new GregorianCalendar(2018, 0, 1), 1);
        mapSequencePair.put(new GregorianCalendar(2018, 1, 1), 2);
        mapSequencePair.put(new GregorianCalendar(2018, 2, 1), 3);
        mapSequencePair.put(new GregorianCalendar(2018, 3, 1), 4);

        SequencePair<Calendar, Integer> sequencePair = new SequencePair<>();
        sequencePair.setMapSequencePair(mapSequencePair);

        mapSequencePair = sequencePair.getMapSequencePair();
        //mapSequencePair.forEach((key, value) -> System.out.println(String.format("mapSequencePair: key = %s value = %s", key.getTime(), value)));
        assertThat(mapSequencePair.firstEntry().getValue(), is(1) );
        assertThat(mapSequencePair.lastEntry().getValue(), is(4) );

    }
}