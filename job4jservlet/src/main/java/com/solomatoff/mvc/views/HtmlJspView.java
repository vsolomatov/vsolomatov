package com.solomatoff.mvc.views;

import com.solomatoff.mvc.controller.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс представляет собой реализацию интерфейса ViewLyaer
 */
public class HtmlJspView implements ViewLyaer {

    public void showListUsers(HttpServletRequest request, HttpServletResponse response) {
        show(request, response, "WEB-INF/views/UsersView.jsp");
    }

    @Override
    public void showCreateUser(HttpServletRequest request, HttpServletResponse response) {
        show(request, response, "WEB-INF/views/create.jsp");
    }

    @Override
    public void showRudUser(HttpServletRequest request, HttpServletResponse response) {
        show(request, response, "WEB-INF/views/rud.jsp");
    }

    private void show(HttpServletRequest request, HttpServletResponse response, String path) {
        try {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            Controller.getInstance().getLog().error(e.getMessage(), e);
        }
    }
}
