package com.solomatoff.mvc.entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class MusicTypeTest {

    @Test
    public void getId() {
        MusicType musicType = new MusicType();
        musicType.setId(1);
        int expected = 1;
        int result = musicType.getId();
        assertEquals(result, expected);
    }

    @Test
    public void setId() {
        MusicType musicType = new MusicType();
        musicType.setId(2);
        int expected = 2;
        int result = musicType.getId();
        assertEquals(result, expected);
    }

    @Test
    public void getMusicTypeName() {
        MusicType musicType = new MusicType();
        musicType.setMusicTypeName("POP");
        String expected = "POP";
        String result = musicType.getMusicTypeName();
        assertEquals(result, expected);
    }

    @Test
    public void setMusicTypeName() {
        MusicType musicType = new MusicType();
        musicType.setMusicTypeName("ROCK");
        String expected = "ROCK";
        String result = musicType.getMusicTypeName();
        assertEquals(result, expected);
    }

    @Test
    public void equals() {
        MusicType musicType = new MusicType();
        musicType.setId(1);
        musicType.setMusicTypeName("RAP");

        MusicType expected = new MusicType();
        expected.setId(1);
        expected.setMusicTypeName("RAP");

        assertEquals(musicType, expected);

        expected = new MusicType();
        expected.setId(1);
        expected.setMusicTypeName("BLUES");

        assertNotEquals(musicType, expected);
    }

    @Test
    public void testHashCode() {
        MusicType musicType = new MusicType();
        musicType.setId(1);
        musicType.setMusicTypeName("CHANSON");

        MusicType expected = new MusicType();
        expected.setId(1);
        expected.setMusicTypeName("CHANSON");

        assertEquals(musicType.hashCode(), expected.hashCode());
    }

    @Test
    public void testToString() {
        MusicType musicType = new MusicType();
        musicType.setId(1);
        musicType.setMusicTypeName("JAZZ");

        String expected = "MusicType{id=1, musicTypeName='JAZZ'}";
        assertEquals(musicType.toString(), expected);
    }
}