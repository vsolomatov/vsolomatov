package com.solomatoff.mvc.views;

import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
            saveUsersToPrintWriter(users, pw, request);
        } catch (IOException e) {
            LoggerApp.getInstance().getLog().error(e.getMessage(), e);
        }
    }

    @Override
    public void showCreateUser(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getAttribute("user"); // У объекта будет заполнено только поле id
        user.setName("Enter a name in this field");
        user.setLogin("Enter a login in this field");
        user.setPassword("Enter a password in this field");
        user.setEmail("Enter a e-mail in this field");
        user.setIdRole(0);
        showFormForAction(request, response, user);
    }

    @Override
    public void showRudUser(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getAttribute("user"); // У объекта будет заполнено только поле id
        List<User> users = CONTROLLER.executeActionUser("Find By Id User", user);
        if (users.size() > 0) {
            user = users.get(0);
        }
        showFormForAction(request, response, user);
    }

    private void showFormForAction(HttpServletRequest request, HttpServletResponse response, User user) {
        try {
            PrintWriter pw = response.getWriter();
            saveFormForActionToPrintWriter(user, pw, request);
        } catch (IOException e) {
            LoggerApp.getInstance().getLog().error(e.getMessage(), e);
        }
    }

    /**
     * Создает страницу со списком пользователей в виде таблицы
     *
     * @param users       список пользователей
     * @param printWriter куда выводить страницу
     */
    private void saveUsersToPrintWriter(List<User> users, PrintWriter printWriter, HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String login = getLogin(request);
        StringBuilder bodyTable = new StringBuilder();
        StringBuilder buttonNew = new StringBuilder();
        if (users != null) {
            for (User user : users) {
                bodyTable.append("\t\t\t\t<tr>\n\t\t\t\t\t<td>")
                        .append(user.getId())
                        .append("\t\t\t\t\t</td>\n\t\t\t\t\t<td>")
                        .append(user.getName())
                        .append("\t\t\t\t\t</td>\n\t\t\t\t\t<td>")
                        .append(user.getLogin())
                        .append("\t\t\t\t\t</td>\n\t\t\t\t\t<td>")
                        .append(user.getPassword())
                        .append("\t\t\t\t\t</td>\n\t\t\t\t\t<td>")
                        .append(user.getEmail())
                        .append("\t\t\t\t\t</td>\n\t\t\t\t\t<td>")
                        .append(user.getCreateDate())
                        .append("\t\t\t\t\t</td>\n\t\t\t\t\t<td>")
                        .append(user.getNameRole())
                        .append("\t\t\t\t\t<td>\n\t\t\t\t\t\t<form action='")
                        .append(contextPath)
                        .append("/protected/rud'>\n")
                        .append("\t\t\t\t\t\t\t<input type='hidden' name='action' value='Update User'>\n")
                        .append("\t\t\t\t\t\t\t<input type='hidden' name='id' value='")
                        .append(user.getId())
                        .append("'>\n")
                        .append("\t\t\t\t\t\t\t<input type='submit' value='Edit'>\n")
                        .append("\t\t\t\t\t\t</form>\n\t\t\t\t\t</td>\n")
                        .append("\t\t\t\t\t<td>\n\t\t\t\t\t\t<form action='")
                        .append(contextPath)
                        .append("/protected/rud'>\n")
                        .append("\t\t\t\t\t\t\t<input type='hidden' name='action' value='Delete User'>\n")
                        .append("\t\t\t\t\t\t\t<input type='hidden' name='id' value='")
                        .append(user.getId())
                        .append("'>\n")
                        .append("\t\t\t\t\t\t\t<input type='submit' value='Delete'>\n")
                        .append("\t\t\t\t\t\t</form>\n\t\t\t\t\t</td>\n\t\t\t\t</tr>\n");
            }
        }
        String typeUser = CONTROLLER.getTypeUser(login);
        if (typeUser != null && typeUser.equals("admin")) {
            buttonNew.append("\t\t<form action='")
                    .append(contextPath)
                    .append("/protected/create'>\n")
                    .append("\t\t\t<input type='hidden' name='action' value='Create User'>")
                    .append("\t\t\t<input type='submit' value='New'>\n")
                    .append("\t\t</form>\n");
        }
        printWriter.append("<!DOCTYPE HTML>\n")
                .append("<html>\n")
                .append("\t<head>\n")
                .append("\t\t<meta charset='utf-8'>\n")
                .append("\t\t<title>Users</title>\n")
                .append("\t</head>\n")
                .append("\t<body>\n")
                .append("\t\t<p><b>List of users</b></p>\n")
                .append("\t\t<table>\n")
                .append("\t\t\t<th>id</th>\n")
                .append("\t\t\t<th>name</th>\n")
                .append("\t\t\t<th>login</th>\n")
                .append("\t\t\t<th>password</th>\n")
                .append("\t\t\t<th>e-mail</th>\n")
                .append("\t\t\t<th>createDate</th>\n")
                .append("\t\t\t<th>role</th>\n")
                .append("\t\t\t<tbody>\n")
                .append(bodyTable.toString())
                .append("\t\t\t</tbody>\n")
                .append("\t\t</table>\n")
                .append(buttonNew.toString())
                .append("\t\t<ul>")
                .append("\t\t\t<li><a href='")
                .append(contextPath)
                .append("/'>Back to Main Page</a></li>")
                .append("\t\t\t<li><a href='")
                .append(contextPath)
                .append("/logout'>Exit</a></li>")
                .append("\t\t</ul>")
                .append("\t</body>\n")
                .append("</html>");
    }

    /**
     * Создает страницу с формой для создания, редактирования или удаления пользователя
     *
     * @param user        пользователь
     * @param printWriter куда выводить страницу
     */
    private void saveFormForActionToPrintWriter(User user, PrintWriter printWriter, HttpServletRequest request) {
        String typeForm = request.getParameter("action");
        String contextPath = request.getContextPath();
        String login = getLogin(request);
        String typeUser = CONTROLLER.getTypeUser(login);
        StringBuilder fieldForm = new StringBuilder();
        if (typeUser != null && typeUser.equals("admin")) {
            fieldForm.append("\t\t\t<b>user id:</b> <input type='number' name='id' value='")
                     .append(String.valueOf(user.getId())).append("' required><Br>\n")
                     .append("\t\t\t<b>user name:</b> <input type='text' name='name' value='")
                     .append(user.getName()).append("'><Br>\n")
                     .append("\t\t\t<b>user login:</b> <input type='text' name='login' value='")
                     .append(user.getLogin()).append("'><Br>\n")
                     .append("\t\t\t<b>user password:</b> <input type='password' name='password' value='")
                     .append(user.getPassword()).append("'><Br>\n")
                     .append("\t\t\t<b>user e-mail:</b> <input type='text' name='email' value='")
                     .append(user.getEmail()).append("'><Br>\n")
                     .append("\t\t\t<b>user idRole:</b> <input type='number' name='idRole' value='")
                     .append(String.valueOf(user.getIdRole()))
                     .append("' required><Br>\n");
        } else {
            fieldForm.append("\t\t\t<b>user id:</b> <input type='number' name='id' value='")
                     .append(String.valueOf(user.getId())).append("' readonly required><Br>\n")
                     .append("\t\t\t<b>user name:</b> <input type='text' name='name' value='")
                     .append(user.getName()).append("'><Br>\n")
                     .append("\t\t\t<b>user login:</b> <input type='text' name='login' value='")
                     .append(user.getLogin()).append("' disabled><Br>\n")
                     .append("\t\t\t<b>user password:</b> <input type='password' name='password' value='")
                     .append(user.getPassword()).append("'><Br>\n")
                     .append("\t\t\t<b>user e-mail:</b> <input type='text' name='email' value='")
                     .append(user.getEmail()).append("'><Br>\n")
                     .append("\t\t\t<b>user idRole:</b> <input type='number' name='idRole' value='")
                     .append(String.valueOf(user.getIdRole()))
                     .append("' disabled><Br>\n");
        }
        printWriter.append("<!DOCTYPE HTML>\n" + "<html>\n" + "\t<head>\n" + "\t\t<meta charset='utf-8'>\n" + "\t<title>")
                .append(typeForm)
                .append("</title>\n")
                .append("\t</head>\n")
                .append("\t<body>\n")
                .append("\t\t<p><b>")
                .append(typeForm)
                .append("</b></p>\n")
                .append("\t\t<form action='")
                .append(String.format("%s/protected/rud", contextPath))
                .append("' method='post'>\n")
                .append("\t\t\t<input type='hidden' name='action' value='")
                .append(typeForm).append("'><Br>\n")
                .append(fieldForm.toString())
                .append("\t\t\t<p><input type='submit'></p>\n")
                .append("\t\t</form>\n")
                .append("\t</body>\n")
                .append("</html>");
    }

    @Override
    public void showListRoles(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Role> roles = (List<Role>) request.getAttribute("roles");
            PrintWriter pw = response.getWriter();
            saveRolesToPrintWriter(roles, pw, request.getContextPath());
        } catch (IOException e) {
            LoggerApp.getInstance().getLog().error(e.getMessage(), e);
        }
    }

    @Override
    public void showCreateRole(HttpServletRequest request, HttpServletResponse response) {
        Role role = (Role) request.getAttribute("role"); // У объекта будет заполнено только поле id
        role.setName("Enter a name in this field");
        showFormForActionRole(request, response, role);
    }

    @Override
    public void showRudRole(HttpServletRequest request, HttpServletResponse response) {
        Role role = (Role) request.getAttribute("role"); // У объекта будет заполнено только поле id
        List<Role> roles = CONTROLLER.executeActionRole("Find By Id", role);
        if (roles.size() > 0) {
            role = roles.get(0);
        }
        showFormForActionRole(request, response, role);
    }

    private void showFormForActionRole(HttpServletRequest request, HttpServletResponse response, Role role) {
        try {
            PrintWriter pw = response.getWriter();
            String typeForm = request.getParameter("action");
            String contextPath = request.getContextPath();
            saveFormForActionRoleToPrintWriter(role, pw, typeForm, contextPath);
        } catch (IOException e) {
            LoggerApp.getInstance().getLog().error(e.getMessage(), e);
        }
    }
    /**
     * Создает страницу со списком ролей в виде таблицы
     * @param roles список пользователей
     * @param printWriter куда выводить страницу
     */
    private void saveRolesToPrintWriter(List<Role> roles, PrintWriter printWriter, String contextPath) {
        StringBuilder sb = new StringBuilder();
        for (Role role : roles) {
            sb.append("\t\t\t\t<tr>\n\t\t\t\t\t<td>")
                    .append(role.getId())
                    .append("\t\t\t\t\t</td>\n\t\t\t\t\t<td>")
                    .append(role.getName())
                    .append("\t\t\t\t\t</td>\n\t\t\t\t\t\t<form action='")
                    .append(contextPath)
                    .append("/protected/rudrole'>\n")
                    .append("\t\t\t\t\t\t\t<input type='hidden' name='action' value='Update Role'>\n")
                    .append("\t\t\t\t\t\t\t<input type='hidden' name='id' value='")
                    .append(role.getId())
                    .append("'>\n")
                    .append("\t\t\t\t\t\t\t<input type='submit' value='Edit'>\n")
                    .append("\t\t\t\t\t\t</form>\n\t\t\t\t\t</td>\n")
                    .append("\t\t\t\t\t<td>\n\t\t\t\t\t\t<form action='")
                    .append(contextPath)
                    .append("/protected/rud'>\n")
                    .append("\t\t\t\t\t\t\t<input type='hidden' name='action' value='Delete Role'>\n")
                    .append("\t\t\t\t\t\t\t<input type='hidden' name='id' value='")
                    .append(role.getId())
                    .append("'>\n")
                    .append("\t\t\t\t\t\t\t<input type='submit' value='Delete'>\n")
                    .append("\t\t\t\t\t\t</form>\n\t\t\t\t\t</td>\n\t\t\t\t</tr>\n");
        }
        printWriter.append("<!DOCTYPE HTML>\n")
                .append("<html>\n")
                .append("\t<head>\n")
                .append("\t\t<meta charset='utf-8'>\n")
                .append("\t\t<title>Roles</title>\n")
                .append("\t</head>\n")
                .append("\t<body>\n")
                .append("\t\t<p><b>List of roles</b></p>\n")
                .append("\t\t<table>\n")
                .append("\t\t\t<th>id</th>\n")
                .append("\t\t\t<th>name</th>\n")
                .append("\t\t\t<tbody>\n")
                .append(sb.toString())
                .append("\t\t\t</tbody>\n")
                .append("\t\t</table>\n")
                .append("\t\t<form action='")
                .append(contextPath)
                .append("/protected/createrole'>\n")
                .append("\t\t\t<input type='hidden' name='action' value='Create Role'>")
                .append("\t\t\t<input type='submit' value='New'>\n")
                .append("\t\t</form>\n")
                .append("\t\t<ul>")
                .append("\t\t\t<li><a href='")
                .append(contextPath)
                .append("/logout'>Exit</a></li>")
                .append("\t\t</ul>")
                .append("\t</body>\n")
                .append("</html>");
    }

    /**
     * Создает страницу с формой для создания, редактирования или удаления пользователя
     *
     * @param role        пользователь
     * @param printWriter куда выводить страницу
     */
    private void saveFormForActionRoleToPrintWriter(Role role, PrintWriter printWriter, String typeForm, String contextPath) {
        printWriter.append("<!DOCTYPE HTML>\n" + "<html>\n" + "\t<head>\n" + "\t\t<meta charset='utf-8'>\n" + "\t<title>")
                .append(typeForm)
                .append("</title>\n")
                .append("\t</head>\n")
                .append("\t<body>\n")
                .append("\t\t<p><b>")
                .append(typeForm)
                .append("</b></p>\n")
                .append("\t\t<form action='")
                .append(String.format("%s/protected/rud", contextPath))
                .append("' method='post'>\n")
                .append("\t\t\t<input type='hidden' name='action' value='")
                .append(typeForm).append("'><Br>\n")
                .append("\t\t\t<b>role id:</b> <input type='number' name='id' value='")
                .append(String.valueOf(role.getId())).append("' required><Br>\n")
                .append("\t\t\t<b>role name:</b> <input type='text' name='name' value='")
                .append(role.getName()).append("'><Br>\n")
                .append("'><Br>\n")
                .append("\t\t\t<p><input type='submit'></p>\n")
                .append("\t\t</form>\n")
                .append("\t</body>\n")
                .append("</html>");
    }

    /**
     * Получает атрибут - логин пользователя из сессии
     * @param request запрос сервлета
     * @return логин пользователя из сессии
     */
    private String getLogin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (String) session.getAttribute("login");
    }
}
