package com.solomatoff.mvc.views;

import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.controller.LoggerApp;

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
        show(request, response, "/WEB-INF/views/UsersView.jsp");
    }

    @Override
    public void showCreateUser(HttpServletRequest request, HttpServletResponse response) {
        show(request, response, "/WEB-INF/views/create.jsp");
    }

    @Override
    public void showRudUser(HttpServletRequest request, HttpServletResponse response) {
        show(request, response, "/WEB-INF/views/rud.jsp");
    }

    public void showListRoles(HttpServletRequest request, HttpServletResponse response) {
        show(request, response, "/WEB-INF/views/RolesView.jsp");
    }

    @Override
    public void showCreateRole(HttpServletRequest request, HttpServletResponse response) {
        show(request, response, "/WEB-INF/views/createrole.jsp");
    }

    @Override
    public void showRudRole(HttpServletRequest request, HttpServletResponse response) {
        show(request, response, "/WEB-INF/views/rudrole.jsp");
    }

    private void show(HttpServletRequest request, HttpServletResponse response, String path) {
        //System.out.println("            HtmlJspView show: request.getRequestURI() = " + request.getRequestURI() + " request.getQueryString() = " + request.getQueryString());
        try {
            //request.getServletContext().getRequestDispatcher(path).forward(request, response);
            request.getRequestDispatcher(path).forward(request, response);
        } catch (ServletException | IOException e) {
            LoggerApp.getInstance().getLog().error("Error: can not find file" + path, e);
        }
    }
}
