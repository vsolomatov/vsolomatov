package com.solomatoff.mvc.entities;

import java.util.Objects;

public class MusicType {
    private Integer id;
    private String musicTypeName;

    public MusicType() {
    }

    public MusicType(Integer id, String musicTypeName) {
        this.id = id;
        this.musicTypeName = musicTypeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMusicTypeName() {
        return musicTypeName;
    }

    public void setMusicTypeName(String musicTypeName) {
        this.musicTypeName = musicTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MusicType musicType = (MusicType) o;
        return Objects.equals(id, musicType.id) && Objects.equals(musicTypeName, musicType.musicTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, musicTypeName);
    }

    @Override
    public String toString() {
        return "MusicType{" + "id=" + id + ", musicTypeName='" + musicTypeName + '\'' + '}';
    }
}
