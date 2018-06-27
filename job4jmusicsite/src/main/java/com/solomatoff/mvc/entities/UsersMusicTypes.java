package com.solomatoff.mvc.entities;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class UsersMusicTypes implements Comparable<UsersMusicTypes> {
    private Integer userId;
    private Integer musicTypeId;

    public UsersMusicTypes() {
    }

    public UsersMusicTypes(Integer userId, Integer musicTypeId) {
        this.userId = userId;
        this.musicTypeId = musicTypeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMusicTypeId() {
        return musicTypeId;
    }

    public void setMusicTypeId(Integer musicTypeId) {
        this.musicTypeId = musicTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UsersMusicTypes that = (UsersMusicTypes) o;
        return Objects.equals(userId, that.userId) && Objects.equals(musicTypeId, that.musicTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, musicTypeId);
    }

    @Override
    public String toString() {
        return "UsersMusicTypes{" + "userId=" + userId + ", musicTypeId=" + musicTypeId + '}';
    }

    @Override
    public int compareTo(@NotNull UsersMusicTypes usersMusicTypes) {
        if (this.equals(usersMusicTypes)) {
            return 0;
        }
        return (this.userId > usersMusicTypes.userId) ? 1 : ((this.musicTypeId > usersMusicTypes.musicTypeId) ? 1 : -1);
    }
}
