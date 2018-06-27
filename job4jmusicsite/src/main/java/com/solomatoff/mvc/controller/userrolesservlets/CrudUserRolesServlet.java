package com.solomatoff.mvc.controller.userrolesservlets;

import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.entities.UserRoles;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CrudUserRolesServlet extends HttpServlet {
    // Переменная для объекта Controller
    private static final Controller CONTROLLER = Controller.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        //System.out.println("    CrudUserRolesServlet doGet " + " " + request.getRequestURI() + "?" + request.getQueryString());
        response.setContentType("text/html;charset=utf-8");
        String pAction = request.getParameter("action");
        String pUserId = request.getParameter("userid");
        String pRoleId = request.getParameter("roleid");
        Integer intUserId = Integer.parseInt(pUserId);

        User user = new User();
        user.setId(intUserId);
        request.setAttribute("user", user);

        List<UserRoles> list = CONTROLLER.getLogic().findByIdUserUserRoles(new UserRoles(intUserId, null));
        Integer intRoleId = list.size(); // для новой записи userRoles
        if (pRoleId != null) { // если удаляем уже существующую
            intRoleId = Integer.parseInt(pRoleId);
        }

        UserRoles userRoles = new UserRoles(intUserId, intRoleId);
        request.setAttribute("userroles", userRoles);

        List<Role> roles = CONTROLLER.getLogic().findAllRoles(new Role());
        request.setAttribute("roles", roles);

        // Отображаем страницу для создания или удаления UserRole
        if (pAction.equals("Create UserRoles")) {
            CONTROLLER.getPresentation().showCreateUserRoles(request, response);
        } else {
            CONTROLLER.getPresentation().showRudUserRoles(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        //System.out.println("    CrudUserRolesServlet doPost " + " " + request.getRequestURI() + "?" + request.getQueryString());
        response.setContentType("text/html;charset=utf-8");
        String pAction = request.getParameter("action");
        String pUserId = request.getParameter("userid");
        String pRoleId = request.getParameter("roleid");

        Integer intUserId = Integer.parseInt(pUserId);
        Integer intRoleId = Integer.parseInt(pRoleId);
        UserRoles userRoles = new UserRoles(intUserId, intRoleId);
        switch (pAction) {
            case "Create":
                CONTROLLER.getLogic().addUserRoles(userRoles);
                break;
            case "Find By Id":
                CONTROLLER.getLogic().findByIdUserUserRoles(userRoles);
                break;
            case "Update":
                CONTROLLER.getLogic().updateUserRoles(userRoles);
                break;
            case "Delete":
                CONTROLLER.getLogic().deleteUserRoles(userRoles);
                break;
            default:
                CONTROLLER.getLogic().findByIdUserUserRoles(userRoles);
        }
        // Переходим на страницу списка UserRoles
        try {
            response.sendRedirect(String.format("%s/protected/listuserroles?userid=%s", request.getContextPath(), pUserId));
        } catch (IOException e) {
            LoggerApp.getInstance().getLog().error(e.getMessage(), e);
        }
    }
}
