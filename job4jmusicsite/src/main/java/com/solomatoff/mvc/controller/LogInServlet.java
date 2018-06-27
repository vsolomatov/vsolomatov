package com.solomatoff.mvc.controller;

import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.model.ModelLogic;
import com.solomatoff.mvc.model.store.DbStore;
import com.solomatoff.mvc.model.store.MemoryStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;

public class LogInServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoggerApp.getInstance(); // Вызываем, чтобы создать объект
        Controller.getInstance().getLogic().setPersistent(DbStore.getInstance()); // Установим способ хранения
        request.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("    LoginServlet doPost " + " " + request.getRequestURI() + "?" + request.getQueryString());
        final Controller CONTROLLER = Controller.getInstance();
        final ModelLogic MODEL_LOGIC = CONTROLLER.getLogic();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String pTypeView = request.getParameter("typeview");
        String pTypeStorage = request.getParameter("typestorage");
        MODEL_LOGIC.setPersistent(pTypeStorage.equals("db") ? new DbStore() : new MemoryStore());
        if (MODEL_LOGIC.isCredentional(login, password)) {
            String typeUser = CONTROLLER.getTypeUser(login); // Определим тип пользователя по логину
            HttpSession session = request.getSession();
            session.setAttribute("login", login);
            session.setAttribute("typeuser", typeUser);
            response.sendRedirect(String.format("%s/protected/listusers?typeview=%s&typestorage=%s", request.getContextPath(), pTypeView, pTypeStorage));
        } else {
            request.setAttribute("error", "Unknown login or password, try again");
            doGet(request, response);
        }
    }
}
