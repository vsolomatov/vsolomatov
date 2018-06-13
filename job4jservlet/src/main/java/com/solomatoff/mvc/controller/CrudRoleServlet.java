package com.solomatoff.mvc.controller;

import com.solomatoff.mvc.entities.Role;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class CrudRoleServlet extends HttpServlet {
    // Переменная для объекта Controller
    private static final Controller CONTROLLER = Controller.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        //System.out.println("    CrudRoleServlet doGet " + " " + request.getRequestURI() + "?" + request.getQueryString());
        response.setContentType("text/html;charset=utf-8");
        String pAction = request.getParameter("action");
        String pId = request.getParameter("id");
        String pName = request.getParameter("name");
        Integer intId;
        // Определим id для роли
        if (pId == null || pId.equals("")) {
            List<Role> roles = CONTROLLER.executeActionRole("Find All Roles", new Role());
            intId = roles.size();
        } else {
            intId = Integer.parseInt(pId);
        }
        Role role = new Role(intId, pName, false);
        request.setAttribute("role", role);
        // Отображаем страницу для создания или редактирования роли
        if (pAction.equals("Create Role")) {
            CONTROLLER.getPresentation().showCreateRole(request, response);
        } else {
            CONTROLLER.getPresentation().showRudRole(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        //System.out.println("    CrudRoleServlet doPost " + " " + request.getRequestURI() + "?" + request.getQueryString());
        response.setContentType("text/html;charset=utf-8");
        String pAction = request.getParameter("action");
        String pId = request.getParameter("id");
        String pName = request.getParameter("name");
        String pIsAdmin = request.getParameter("isAdmin");
        Integer intId;
        Boolean bIsAdmin = false;
        // Определим idRole для новой роли
        if (pId == null || pId.equals("")) {
            List<Role> roles = CONTROLLER.executeActionRole("Find All Roles", new Role());
            intId = roles.size();
        } else {
            intId = Integer.parseInt(pId);
            bIsAdmin = pIsAdmin.equals("true");
        }
        Role role = new Role(intId, pName, bIsAdmin);
        CONTROLLER.executeActionRole(pAction, role);
        // Переходим на страницу списка ролей
        try {
            response.sendRedirect(String.format("%s/protected/listroles", request.getContextPath()));
        } catch (IOException e) {
            LoggerApp.getInstance().getLog().error(e.getMessage(), e);
        }
    }
}
