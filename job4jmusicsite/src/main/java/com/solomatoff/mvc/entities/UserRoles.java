package com.solomatoff.mvc.entities;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class UserRoles implements Comparable<UserRoles> {
    private Integer userId;
    private Integer roleId;

    public UserRoles() {
    }

    public UserRoles(Integer userId, Integer roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserRoles that = (UserRoles) o;
        return Objects.equals(userId, that.userId) && Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }

    @Override
    public String toString() {
        return "UserRoles{" + "userId=" + userId + ", roleId=" + roleId + '}';
    }

    @Override
    public int compareTo(@NotNull UserRoles userRoles) {
        if (this.equals(userRoles)) {
            return 0;
        }
        return (this.userId > userRoles.userId) ? 1 : ((this.roleId > userRoles.roleId) ? 1 : -1);
    }
}
