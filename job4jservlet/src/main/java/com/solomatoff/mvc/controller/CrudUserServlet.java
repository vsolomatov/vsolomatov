package com.solomatoff.mvc.controller;

import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.entities.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class CrudUserServlet extends HttpServlet {
    // Переменная для объекта Controller
    private static final Controller CONTROLLER = Controller.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        //System.out.println("        CrudUserServlet doGet " + " " + request.getRequestURI() + "?" + request.getQueryString());
        response.setContentType("text/html;charset=utf-8");
        String pAction = request.getParameter("action");
        String pId = request.getParameter("id");
        User user = new User();
        // Установим id пользователя
        if (pAction.equals("Create User")) { // создание
            List<User> users = CONTROLLER.executeActionUser("Find All Users", new User());
            user.setId(users.size());
        } else {                            // просмотр, редактирование, удаление
            user.setId(Integer.parseInt(pId));
        }
        // Получим список всех ролей для формы
        List<Role> roles = CONTROLLER.getLogic().findAllRoles(new Role());
        request.setAttribute("roles", roles);
        // Отображаем страницу для создания или просмотра, редактирования, удаления пользователя предварительно заполнив все данные для существующего пользователя
        if (pAction.equals("Create User")) {
            request.setAttribute("user", user);
            CONTROLLER.getPresentation().showCreateUser(request, response);
        } else {
            // Заполним остальные поля user
            user = CONTROLLER.getLogic().findByIdUser(user).get(0);
            request.setAttribute("user", user);
            CONTROLLER.getPresentation().showRudUser(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        //System.out.println("        CrudUserServlet doPost " + " " + request.getRequestURI() + "?" + request.getQueryString());
        response.setContentType("text/html;charset=utf-8");
        String pAction = request.getParameter("action");
        String pId = request.getParameter("id");
        String pName = request.getParameter("name");
        String pLogin = request.getParameter("login");
        String pPassword = request.getParameter("password");
        String pEmail = request.getParameter("email");
        String pIdRole = request.getParameter("idRole");
        Integer intId;
        Integer intIdRole;
        User user;
        if (pAction.equals("Create User")) {
            if (pId == null) {
                // Определим id для нового пользователя
                List<User> users = CONTROLLER.executeActionUser("Find All Users", new User());
                intId = users.size();
            } else {
                intId = Integer.parseInt(pId);
            }
            intIdRole = Integer.parseInt(pIdRole);
        } else {
            intId = Integer.parseInt(pId);
            // Если у пользователя нет прав на редактирование роли, то pIdRole и pLogin придёт пустым (null)
            if (pIdRole == null) {
                user = new User();
                user.setId(intId);
                List<User> users = CONTROLLER.getLogic().findByIdUser(user);
                intIdRole = users.get(0).getIdRole();
                pLogin = users.get(0).getLogin();
            } else {
                intIdRole = Integer.parseInt(pIdRole);
            }
        }
        user = new User(intId, pName, pLogin, pPassword, pEmail, new Timestamp(System.currentTimeMillis()), intIdRole);
        CONTROLLER.executeActionUser(pAction, user);
        // Переходим на страницу списка пользователей
        try {
            response.sendRedirect(String.format("%s/protected/list", request.getContextPath()));
        } catch (IOException e) {
            LoggerApp.getInstance().getLog().error(e.getMessage(), e);
        }
    }
}
