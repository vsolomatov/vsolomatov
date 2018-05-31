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
        User user;
        if (pId == null || pId.equals("")) {
            user = new User(null, pName, pLogin, pEmail, new Timestamp(System.currentTimeMillis()));
        } else {
            user = new User(Integer.parseInt(pId), pName, pLogin, pEmail, new Timestamp(System.currentTimeMillis()));
        }
        if (pAction.equals("Create User") || pAction.equals("Update User") || pAction.equals("Delete User")) {
            presentation.executeAction(pAction, user);
        }
        try {
            response.sendRedirect(String.format("%s/list.jsp", request.getContextPath()));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /* Теперь за это отвечает edit.jsp
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
        //response.sendRedirect(String.format("%s/index.jsp", request.getContextPath()));
    }
    */
}
