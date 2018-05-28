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

public class UsersServlet extends HttpServlet {
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        List<User> users = presentation.executeAction("Find All", new User());
        if (users != null) {
            // Записываем в response.getWriter() As Html
            presentation.saveUsersAsHtmlToPrintWriter(users, pw, request.getContextPath());
            // Записываем в LOGGER
            presentation.saveUsersAsXmlToLog(users, logger);
        }
        pw.flush();
    }
}
