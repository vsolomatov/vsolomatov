package com.solomatoff.mvc.controller;

import com.solomatoff.mvc.model.PersistentDAO;
import com.solomatoff.mvc.model.store.DbStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("    LogoutServlet doGet " + " " + request.getRequestURI() + "?" + request.getQueryString());
        request.getRequestDispatcher("/WEB-INF/views/LogoutView.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //System.out.println("    LogoutServlet doPost " + " " + request.getRequestURI() + "?" + request.getQueryString());
        String pReturn = request.getParameter("return");
        if (pReturn.equals("Yes")) {
            HttpSession session = request.getSession();
            session.invalidate();
            // В контроллере у объекта логики вернуть значение по умолчанию для объекта хранения данных
            PersistentDAO modelStore = new DbStore();
            Controller.getInstance().getLogic().setPersistent(modelStore);
            response.sendRedirect(String.format("%s/", request.getContextPath()));
        } else {
            response.sendRedirect(String.format("%s/protected/list", request.getContextPath()));
        }
    }
}
