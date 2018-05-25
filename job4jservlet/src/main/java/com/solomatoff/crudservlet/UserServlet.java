package com.solomatoff.crudservlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class UserServlet extends HttpServlet {
    public static final Logger LOG = LoggerFactory.getLogger(UserServlet.class);
    // Слой логики
    private final ValidateService logic = ValidateService.getInstance();
    // Перечень поддерживаемых операций
    enum TypeAction { CREATE, UPDATE, DELETE, FINDBYID, FINDALL }
    /**
     * Contains actions
     */
    private final Map<TypeAction, Function<User, List<User>>> actions = new HashMap<>();

    /**
     * Init's
     */
    @Override
     public void init() {
        this.load(TypeAction.CREATE, this.userAdd());
        this.load(TypeAction.UPDATE, this.userUpdate());
        this.load(TypeAction.DELETE, this.userDelete());
        this.load(TypeAction.FINDBYID, this.userFindById());
        this.load(TypeAction.FINDALL, this.userFindAll());
    }

    /**
     * Load handlers for actions
     * @param typeAction action type.
     * @param handle Handler.
     */
    private void load(TypeAction typeAction, Function<User, List<User>> handle) {
        this.actions.put(typeAction, handle);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        String pAction = request.getParameter("action");
        String pId = request.getParameter("id");
        String pName = request.getParameter("name");
        String pLogin = request.getParameter("login");
        String pEmail = request.getParameter("email");
        User user = new User(Integer.parseInt(pId), pName, pLogin, pEmail, new Timestamp(System.currentTimeMillis()));
        TypeAction typeAction = null;
        switch (pAction) {
            case "create":
                typeAction = TypeAction.CREATE;
                break;
            case "update":
                typeAction = TypeAction.UPDATE;
                break;
            case "delete":
                typeAction = TypeAction.DELETE;
                break;
            case "findbyid":
                typeAction = TypeAction.FINDBYID;
                break;
            default:
        }
        if (typeAction != null) {
            executeAction(typeAction, user);
        }
        try {
            doGet(request, response);
        } catch (IOException e) {
            UserServlet.LOG.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        List<User> users = executeAction(TypeAction.FINDALL, new User());
        if (users != null) {
            // Записываем в response.getWriter()
            UtilForOutput utilForOutput = new UtilForOutput();
            Document document = utilForOutput.getUsersAsXmlDocument(users);
            utilForOutput.documentSaveToPrintWriter(document, pw);
        }
        pw.flush();
    }

    /**
     * Add User
     * @return null
     */
    private Function<User, List<User>> userAdd() {
        return logic::add;
    }

    /**
     * Update User
     * @return user updated
     */
    private Function<User, List<User>> userUpdate() {
        return logic::update;
    }

    /**
     * Delete User
     * @return user updated
     */
    private Function<User, List<User>> userDelete() {
        return logic::delete;
    }

    /**
     * Find User
     * @return user updated
     */
    private Function<User, List<User>> userFindById() {
        return logic::findById;
    }

    /**
     * Receives All Users
     * @return User List
     */
    private Function<User, List<User>> userFindAll() {
        return logic::findAll;
    }

    /**
     * Execute action
     * @param typeAction type action
     * @return User List
     */
    private List<User> executeAction(final TypeAction typeAction, User user) {
        return this.actions.get(typeAction).apply(user);
    }
}
