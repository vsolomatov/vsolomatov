package com.solomatoff.mvc.controller;

import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.model.ModelLogic;
import com.solomatoff.mvc.model.ModelStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class LogInServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Вызываем, чтобы создать объекты
        LoggerApp.getInstance();
        Controller.getInstance();
        //System.out.println("    LoginServlet doGet " + " " + request.getRequestURI() + "?" + request.getQueryString());
        request.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("    LoginServlet doPost " + " " + request.getRequestURI() + "?" + request.getQueryString());
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String pTypeView = request.getParameter("typeview");
        String pTypeStorage = request.getParameter("typestorage");
        if (Controller.getInstance().getLogic().isCredentional(login, password)) {
            String typeUser = Controller.getInstance().getTypeUser(login); // Определим роль пользователя по логину
            HttpSession session = request.getSession();
            session.setAttribute("login", login);
            session.setAttribute("typeuser", typeUser);
            response.sendRedirect(String.format("%s/protected/list?typeview=%s&typestorage=%s", request.getContextPath(), pTypeView, pTypeStorage));
        } else {
            request.setAttribute("error", "Unknown login or password, try again");
            doGet(request, response);
        }
    }
}
