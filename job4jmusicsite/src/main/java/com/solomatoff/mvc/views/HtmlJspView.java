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
        show(request, response, "/WEB-INF/views/userviews/ListUsers.jsp");
    }

    @Override
    public void showCreateUser(HttpServletRequest request, HttpServletResponse response) {
        show(request, response, "/WEB-INF/views/userviews/createUser.jsp");
    }

    @Override
    public void showRudUser(HttpServletRequest request, HttpServletResponse response) {
        show(request, response, "/WEB-INF/views/userviews/rudUser.jsp");
    }


    @Override
    public void showListRoles(HttpServletRequest request, HttpServletResponse response) {
        show(request, response, "/WEB-INF/views/roleviews/ListRoles.jsp");
    }

    @Override
    public void showCreateRole(HttpServletRequest request, HttpServletResponse response) {
        show(request, response, "/WEB-INF/views/roleviews/createRole.jsp");
    }

    @Override
    public void showRudRole(HttpServletRequest request, HttpServletResponse response) {
        show(request, response, "/WEB-INF/views/roleviews/rudRole.jsp");
    }


    @Override
    public void showListMusicTypes(HttpServletRequest request, HttpServletResponse response) {
        show(request, response, "/WEB-INF/views/musictypeviews/ListMusicTypes.jsp");
    }

    @Override
    public void showCreateMusicType(HttpServletRequest request, HttpServletResponse response) {
        show(request, response, "/WEB-INF/views/musictypeviews/createMusicType.jsp");
    }

    @Override
    public void showRudMusicType(HttpServletRequest request, HttpServletResponse response) {
        show(request, response, "/WEB-INF/views/musictypeviews/rudMusicType.jsp");
    }


    public void showListUserRoles(HttpServletRequest request, HttpServletResponse response) {
        show(request, response, "/WEB-INF/views/userrolesviews/ListUserRoles.jsp");
    }

    public void showCreateUserRoles(HttpServletRequest request, HttpServletResponse response) {
        show(request, response, "/WEB-INF/views/userrolesviews/createUserRoles.jsp");
    }

    public void showRudUserRoles(HttpServletRequest request, HttpServletResponse response) {
        show(request, response, "/WEB-INF/views/userrolesviews/rudUserRoles.jsp");
    }


    public void showListUsersMusicTypes(HttpServletRequest request, HttpServletResponse response) {
        show(request, response, "/WEB-INF/views/usersmusictypesviews/ListUsersMusicTypes.jsp");
    }

    public void showCreateUsersMusicTypes(HttpServletRequest request, HttpServletResponse response) {
        show(request, response, "/WEB-INF/views/usersmusictypesviews/createUsersMusicTypes.jsp");
    }

    public void showRudUsersMusicTypes(HttpServletRequest request, HttpServletResponse response) {
        show(request, response, "/WEB-INF/views/usersmusictypesviews/rudUsersMusicTypes.jsp");
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
