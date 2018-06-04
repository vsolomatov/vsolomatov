package com.solomatoff.mvc.controller;

import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.views.HtmlJspView;
import com.solomatoff.mvc.views.HtmlView;
import com.solomatoff.mvc.views.ViewLyaer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MainServlet extends HttpServlet {
    // Переменная для объекта Controller
    private static final Controller CONTROLLER = Controller.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String pTypeView = request.getParameter("typeview");
        if (pTypeView != null) {
            ViewLyaer viewLyaer = new HtmlView(); // значение по умолчанию
            if (pTypeView.equals("jsp")) {
                viewLyaer = new HtmlJspView();
            }
            CONTROLLER.setPresentation(viewLyaer);
        }
        System.out.println("MainServlet doGet " + " " + request.getRequestURI() + "?" + request.getQueryString());
        List<User> users = CONTROLLER.executeAction("Find All", new User());
        request.setAttribute("users", users);
        CONTROLLER.getPresentation().showListUsers(request, response);
    }

}
