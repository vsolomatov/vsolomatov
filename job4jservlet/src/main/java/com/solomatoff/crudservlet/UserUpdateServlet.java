package com.solomatoff.crudservlet;

import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

public class UserUpdateServlet extends HttpServlet {
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
        String pAction = request.getParameter("action");
        String pId = request.getParameter("id");
        System.out.printf("action = <%s> id = %4s%n", pAction, pId);
        User user = new User(Integer.parseInt(pId), null, null, null, null);
        List<User> list = presentation.executeAction("Find By Id", user);
        // Если user нашелся, то он нулевой
        user = list.get(0);
        // Записываем форму в response.getWriter()
        PrintWriter pw = response.getWriter();
        presentation.saveFormForActionToPrintWriter(user, pw, pAction, request.getContextPath());
        pw.flush();
    }
}
