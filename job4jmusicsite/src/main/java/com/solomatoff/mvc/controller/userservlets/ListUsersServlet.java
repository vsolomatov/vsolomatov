package com.solomatoff.mvc.controller.userservlets;

import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.entities.UserRoles;
import com.solomatoff.mvc.model.PersistentDAO;
import com.solomatoff.mvc.model.store.DbStore;
import com.solomatoff.mvc.model.store.MemoryStore;
import com.solomatoff.mvc.views.HtmlJspView;
import com.solomatoff.mvc.views.ViewLyaer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class ListUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        //System.out.println("    ListUsersServlet doGet " + " " + request.getRequestURI() + "?" + request.getQueryString());
        // Переменная для объекта Controller
        final Controller CONTROLLER = Controller.getInstance();
        String login = getLogin(request);
        String typeUser = CONTROLLER.getTypeUser(login);
        // Параметр для вида списка typeview (может быть задан, а может и не быть задан)
        String pTypeView = request.getParameter("typeview");
        if (pTypeView != null) { // если параметр задан
            ViewLyaer viewLyaer = new HtmlJspView(); // значение по умолчанию
            if (pTypeView.equals("jsp")) {
                viewLyaer = new HtmlJspView();
            }
            CONTROLLER.setPresentation(viewLyaer);
        }
        // Параметр для типа харанения данных typestorage (может быть задан, а может и не быть задан)
        String pTypeStorage = request.getParameter("typestorage");
        if (pTypeStorage != null) { // если параметр задан
            PersistentDAO modelStore = new DbStore(); // значение по умолчанию
            if (pTypeStorage.equals("memory")) {
                modelStore = new MemoryStore();
            }
            // В контроллере у объекта логики установить нужную модель хранения
            CONTROLLER.getLogic().setPersistent(modelStore);
            // Если хранение в памяти, то добавить пользователя и роль (при первом обращении объекты для хранения будут пустыми)
            if (pTypeStorage.equals("memory")) {
                CONTROLLER.getLogic().getPersistent().addRole(new Role(0, "Administrator", true));
                CONTROLLER.getLogic().getPersistent().addUser(new User(0, "Administrator", "root", "root", null, new Timestamp(System.currentTimeMillis())));
                CONTROLLER.getLogic().getPersistent().addUserRoles(new UserRoles(0,  0));
            }
        }
        // Ограничиваем список пользователей, если пользователь не является администратором
        List<User> users;
        if (typeUser != null && typeUser.equals("admin")) {
            users = CONTROLLER.executeActionUser("Find All Users", new User());
        } else {
            User user = new User();
            user.setLogin(login);
            users = CONTROLLER.executeActionUser("Find By Login User", user);
        }
        request.setAttribute("users", users);
        CONTROLLER.getPresentation().showListUsers(request, response);
        // Записываем в LOG
        LoggerApp.getInstance().getLog().info("Users from ListUsersServlet doGet:");
        LoggerApp.getInstance().saveUsersToLog(users);
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
