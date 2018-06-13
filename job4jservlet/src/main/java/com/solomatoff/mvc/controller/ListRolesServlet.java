package com.solomatoff.mvc.controller;

import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.views.HtmlJspView;
import com.solomatoff.mvc.views.HtmlView;
import com.solomatoff.mvc.views.ViewLyaer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ListRolesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        //System.out.println("    ListRolesServlet doGet " + " " + request.getRequestURI() + "?" + request.getQueryString());
        // Переменная для объекта Controller
        final Controller CONTROLLER = Controller.getInstance();
        List<Role> roles = CONTROLLER.executeActionRole("Find All Roles", new Role());
        request.setAttribute("roles", roles);
        CONTROLLER.getPresentation().showListRoles(request, response);
    }

}
