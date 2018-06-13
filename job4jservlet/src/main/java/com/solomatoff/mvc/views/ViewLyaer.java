package com.solomatoff.mvc.views;

import com.solomatoff.mvc.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ViewLyaer {
    void showListUsers(HttpServletRequest request, HttpServletResponse response);
    void showCreateUser(HttpServletRequest request, HttpServletResponse response);
    void showRudUser(HttpServletRequest request, HttpServletResponse response);

    void showListRoles(HttpServletRequest request, HttpServletResponse response);
    void showCreateRole(HttpServletRequest request, HttpServletResponse response);
    void showRudRole(HttpServletRequest request, HttpServletResponse response);
}
