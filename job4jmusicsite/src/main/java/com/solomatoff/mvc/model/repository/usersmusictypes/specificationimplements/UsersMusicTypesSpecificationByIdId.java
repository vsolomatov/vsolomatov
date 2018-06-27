package com.solomatoff.mvc.model.repository.usersmusictypes.specificationimplements;

import com.solomatoff.mvc.model.repository.SqlSpecification;

import java.util.Objects;

public class UsersMusicTypesSpecificationByIdId implements SqlSpecification {
    private int userId;
    private int musicTypeId;

    public UsersMusicTypesSpecificationByIdId(int userId, int musicTypeId) {
        this.userId = userId;
        this.musicTypeId = musicTypeId;
    }

    @Override
    public String toSqlClauses() {
        return String.format("userid = %s and musictypeid = %s", userId, musicTypeId);
    }

    @Override
    public String toString() {
        return "UsersMusicTypesSpecificationByIdId{userId = " + userId + ", musicTypeId = " + musicTypeId + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UsersMusicTypesSpecificationByIdId that = (UsersMusicTypesSpecificationByIdId) o;
        return userId == that.userId && musicTypeId == that.musicTypeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, musicTypeId);
    }
}
