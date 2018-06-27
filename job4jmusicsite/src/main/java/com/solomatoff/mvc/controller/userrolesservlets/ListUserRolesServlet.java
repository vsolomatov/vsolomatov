package com.solomatoff.mvc.controller.userrolesservlets;

import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.entities.UserRoles;
import com.solomatoff.mvc.model.ModelLogic;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ListUserRolesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        //System.out.println("    ListUserRolesServlet doGet " + " " + request.getRequestURI() + "?" + request.getQueryString());
        // Переменная для объекта Controller
        final Controller controller = Controller.getInstance();
        final ModelLogic modelLogic = controller.getLogic();
        Integer intUserId = Integer.parseInt(request.getParameter("userid"));

        User user = new User();
        user.setId(intUserId);
        request.setAttribute("user", user);

        List<Role> roles = modelLogic.findAllRoles(new Role());
        request.setAttribute("roles", roles);

        List<UserRoles> userRoles = modelLogic.findByIdUserUserRoles(new UserRoles(intUserId, null));
        request.setAttribute("userroles", userRoles);

        controller.getPresentation().showListUserRoles(request, response);
    }

}
