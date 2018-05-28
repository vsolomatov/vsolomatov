package com.solomatoff.crudservlet;

import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

public class UserCreateServlet extends HttpServlet {
    // Ядро слоя Presentation
    private CorePresentation presentation;

    /**
     * Init's
     */
    @Override
    public void init() {
        // Ядро слоя Presentation
        presentation = CorePresentation.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        // Найдем id последнего добавленного пользователя
        List<User> list = presentation.executeAction("Find All", new User());
        int listSize = list.size();
        User user =  new User(listSize + 1, "Enter a name in this field", "Enter a login in this field", "Enter a e-mail in this field", new Timestamp(System.currentTimeMillis()));
        //System.out.printf("action = <%s> id = %4s%n", pAction, pId);
        // Записываем форму в response.getWriter()
        PrintWriter pw = response.getWriter();
        presentation.saveFormForActionToPrintWriter(user, pw, "Create User", request.getContextPath());
        pw.flush();
    }
}
