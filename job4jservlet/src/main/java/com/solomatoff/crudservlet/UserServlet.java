package com.solomatoff.crudservlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class UserServlet extends HttpServlet {
    // Ядро слоя Presentation
    private CorePresentation presentation;
    // Логгер
    private static Logger logger;

    /**
     * Init's
     */
    @Override
    public void init() {
        // Ядро слоя Presentation
        presentation = CorePresentation.getInstance();
        // Логгер
        logger = CorePresentation.getLOG();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        String pAction = request.getParameter("action");
        String pId = request.getParameter("id");
        String pName = request.getParameter("name");
        String pLogin = request.getParameter("login");
        String pEmail = request.getParameter("email");
        System.out.printf("action = <%s> id = %4s  name = %s  login = %s  email= %s%n", pAction, pId, pName, pLogin, pEmail);
        User user = new User(Integer.parseInt(pId), pName, pLogin, pEmail, new Timestamp(System.currentTimeMillis()));
        if (pAction.equals("Create User") || pAction.equals("Update User") || pAction.equals("Delete User") || pAction.equals("Find By Id")) {
            presentation.executeAction(pAction, user);
        }
        try {
            doGet(request, response);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        List<User> users = presentation.executeAction("Find All", new User());
        if (users != null) {
            // Записываем в response.getWriter() As Xml
            presentation.saveUsersAsXmlToPrintWriter(users, pw);
            // Записываем в LOGGER
            presentation.saveUsersAsXmlToLog(users, logger);
        }
        pw.flush();
    }
}