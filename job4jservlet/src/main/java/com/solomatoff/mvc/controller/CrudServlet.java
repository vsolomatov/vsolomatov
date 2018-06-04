package com.solomatoff.mvc.controller;

import com.solomatoff.mvc.entities.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class CrudServlet extends HttpServlet {
    // Переменная для объекта Controller
    private static final Controller CONTROLLER = Controller.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("    EditServlet doGet " + " " + request.getRequestURI() + "?" + request.getQueryString());
        response.setContentType("text/html;charset=utf-8");
        String pAction = request.getParameter("action");
        String pId = request.getParameter("id");
        String pName = request.getParameter("name");
        String pLogin = request.getParameter("login");
        String pEmail = request.getParameter("email");

        Integer intId;
        if (pId == null || pId.equals("")) {
            List<User> users = CONTROLLER.executeAction("Find All", new User());
            intId = users.size() + 1;
        } else {
            intId = Integer.parseInt(pId);
        }
        User user = new User(intId, pName, pLogin, pEmail, new Timestamp(System.currentTimeMillis()));
        request.setAttribute("user", user);
        // Отображаем страницу для создания или редактирования пользователя
        if (pAction.equals("Create User")) {
            CONTROLLER.getPresentation().showCreateUser(request, response);
        } else {
            if (pAction.equals("Update User") || pAction.equals("Delete User") || pAction.equals("Find By Id")) {
                CONTROLLER.getPresentation().showRudUser(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("    EditServlet doPost " + " " + request.getRequestURI() + "?" + request.getQueryString());
        response.setContentType("text/html;charset=utf-8");
        String pAction = request.getParameter("action");
        String pId = request.getParameter("id");
        String pName = request.getParameter("name");
        String pLogin = request.getParameter("login");
        String pEmail = request.getParameter("email");
        Integer intId;
        if (pId == null || pId.equals("")) {
            List<User> users = CONTROLLER.executeAction("Find All", new User());
            intId = users.size() + 1;
        } else {
            intId = Integer.parseInt(pId);
        }
        User user = new User(intId, pName, pLogin, pEmail, new Timestamp(System.currentTimeMillis()));
        List<User> users = CONTROLLER.executeAction(pAction, user);
        // Переходим на страницу списка пользователей
        try {
            response.sendRedirect(String.format("%s/list", request.getContextPath()));
        } catch (IOException e) {
            Controller.getInstance().getLog().error(e.getMessage(), e);
        }
    }
}
