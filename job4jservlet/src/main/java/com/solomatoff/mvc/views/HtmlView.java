package com.solomatoff.mvc.views;

import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Класс представляет собой реализацию интерфейса ViewLyaer
 */
public class HtmlView implements ViewLyaer {
    private static final Controller CONTROLLER = Controller.getInstance();

    @Override
    public void showListUsers(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<User> users = (List<User>) request.getAttribute("users");
            PrintWriter pw = response.getWriter();
            saveUsersToPrintWriter(users, pw, request.getContextPath());
        } catch (IOException e) {
            CONTROLLER.getLog().error(e.getMessage(), e);
        }
    }

    @Override
    public void showCreateUser(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getAttribute("user"); // У объекта будет заполнено только поле id
        System.out.printf("         user(1): %4d %s %s %s %s%n", user.getId(), user.getName(), user.getLogin(), user.getEmail(), user.getCreateDate());
        user.setName("Enter a name in this fiels");
        user.setLogin("Enter a login in this fiels");
        user.setEmail("Enter a e-mail in this fiels");
        System.out.printf("         user(2): %4d %s %s %s %s%n", user.getId(), user.getName(), user.getLogin(), user.getEmail(), user.getCreateDate());
        showFormForAction(request, response, user);
    }

    @Override
    public void showRudUser(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getAttribute("user"); // У объекта будет заполнено только поле id
        System.out.printf("         user(1): %4d %s %s %s %s%n", user.getId(), user.getName(), user.getLogin(), user.getEmail(), user.getCreateDate());
        List<User> users = CONTROLLER.executeAction("Find By Id", user);
        user = users.get(0);
        System.out.printf("         user(2): %4d %s %s %s %s%n", user.getId(), user.getName(), user.getLogin(), user.getEmail(), user.getCreateDate());
        showFormForAction(request, response, user);
    }

    private void showFormForAction(HttpServletRequest request, HttpServletResponse response, User user) {
        try {
            PrintWriter pw = response.getWriter();
            String typeForm = request.getParameter("action");
            String contextPath = request.getContextPath();
            saveFormForActionToPrintWriter(user, pw, typeForm, contextPath);
        } catch (IOException e) {
            CONTROLLER.getLog().error(e.getMessage(), e);
        }
    }

    /**
     * Создает страницу со списком пользователей в виде таблицы
     * @param users список пользователей
     * @param printWriter куда выводить страницу
     */
    private void saveUsersToPrintWriter(List<User> users, PrintWriter printWriter, String contextPath) {
        StringBuilder sb = new StringBuilder();
        for (User user : users) {
            sb.append("\t\t\t\t<tr>\n\t\t\t\t\t<td>")
                    .append(user.getId())
                    .append("\t\t\t\t\t</td>\n\t\t\t\t\t<td>")
                    .append(user.getName())
                    .append("\t\t\t\t\t</td>\n\t\t\t\t\t<td>")
                    .append(user.getLogin())
                    .append("\t\t\t\t\t</td>\n\t\t\t\t\t<td>")
                    .append(user.getEmail())
                    .append("\t\t\t\t\t</td>\n\t\t\t\t\t<td>")
                    .append(user.getCreateDate())
                    .append("\t\t\t\t\t</td>\n")
                    .append("\t\t\t\t\t<td>\n\t\t\t\t\t\t<form action='")
                    .append(contextPath)
                    .append("/rud'>\n")
                    .append("\t\t\t\t\t\t\t<input type='hidden' name='action' value='Update User'>\n")
                    .append("\t\t\t\t\t\t\t<input type='hidden' name='id' value='")
                    .append(user.getId())
                    .append("'>\n")
                    .append("\t\t\t\t\t\t\t<input type='submit' value='Edit'>\n")
                    .append("\t\t\t\t\t\t</form>\n\t\t\t\t\t</td>\n")
                    .append("\t\t\t\t\t<td>\n\t\t\t\t\t\t<form action='")
                    .append(contextPath)
                    .append("/rud'>\n")
                    .append("\t\t\t\t\t\t\t<input type='hidden' name='action' value='Delete User'>\n")
                    .append("\t\t\t\t\t\t\t<input type='hidden' name='id' value='")
                    .append(user.getId())
                    .append("'>\n")
                    .append("\t\t\t\t\t\t\t<input type='submit' value='Delete'>\n")
                    .append("\t\t\t\t\t\t</form>\n\t\t\t\t\t</td>\n\t\t\t\t</tr>\n");
        }
        printWriter.append("<!DOCTYPE HTML>\n")
                .append("<html>\n")
                .append("\t<head>\n")
                .append("\t\t<meta charset='utf-8'>\n")
                .append("\t\t<title>Users</title>\n")
                .append("\t</head>\n")
                .append("\t<body>\n")
                .append("\t\t<p><b>A list of users</b></p>\n")
                .append("\t\t<table>\n")
                .append("\t\t\t<th>id</th>\n")
                .append("\t\t\t<th>name</th>\n")
                .append("\t\t\t<th>login</th>\n")
                .append("\t\t\t<th>e-mail</th>\n")
                .append("\t\t\t<th>createDate</th>\n")
                .append("\t\t\t<tbody>\n")
                .append(sb.toString())
                .append("\t\t\t</tbody>\n")
                .append("\t\t</table>\n")
                .append("\t\t<form action='")
                .append(contextPath)
                .append("/create'>\n")
                .append("\t\t\t<input type='hidden' name='action' value='Create User'>")
                .append("\t\t\t<input type='submit' value='New'>\n")
                .append("\t\t</form>\n")
                .append("\t</body>\n")
                .append("</html>");
    }

    /**
     * Создает страницу с формой для создания, редактирования или удаления пользователя
     * @param user пользователь
     * @param printWriter куда выводить страницу
     */
    private void saveFormForActionToPrintWriter(User user, PrintWriter printWriter, String typeForm, String contextPath) {
        printWriter.append("<!DOCTYPE HTML>\n" + "<html>\n" + "\t<head>\n" + "\t\t<meta charset='utf-8'>\n" + "\t<title>")
                .append(typeForm)
                .append("</title>\n")
                .append("\t</head>\n")
                .append("\t<body>\n")
                .append("\t\t<p><b>")
                .append(typeForm)
                .append("</b></p>\n")
                .append("\t\t<form action='")
                .append(String.format("%s/rud", contextPath))
                .append("' method='post'>\n")
                .append("\t\t\t<input type='hidden' name='action' value='")
                .append(typeForm).append("'><Br>\n")
                .append("\t\t\t<b>user id:</b> <input type='' name='id' value='")
                .append(String.valueOf(user.getId())).append("'><Br>\n")
                .append("\t\t\t<b>user name:</b> <input type='text' name='name' value='")
                .append(user.getName()).append("'><Br>\n")
                .append("\t\t\t<b>user login:</b> <input type='text' name='login' value='")
                .append(user.getLogin()).append("'><Br>\n")
                .append("\t\t\t<b>user e-mail:</b> <input type='text' name='email' value='")
                .append(user.getEmail())
                .append("'><Br>\n")
                .append("\t\t\t<p><input type='submit' value='Accept'></p>\n")
                .append("\t\t</form>\n")
                .append("\t</body>\n")
                .append("</html>");
    }
}
