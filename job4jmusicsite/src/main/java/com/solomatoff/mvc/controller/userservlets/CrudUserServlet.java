package com.solomatoff.mvc.controller.userservlets;

import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.Address;
import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.entities.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class CrudUserServlet extends HttpServlet {
    // Переменная для объекта Controller
    private static final Controller CONTROLLER = Controller.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        //System.out.println("        CrudUserServlet doGet " + " " + request.getRequestURI() + "?" + request.getQueryString());
        response.setContentType("text/html;charset=utf-8");
        String pAction = request.getParameter("action");
        String pId = request.getParameter("id");

        User user = new User();
        Address address = new Address();
        int intId;
        // Установим id пользователя
        if (pAction.equals("Create User")) { // создание
            List<User> users = CONTROLLER.executeActionUser("Find All Users", new User());
            intId = users.size();
        } else {                            // просмотр, редактирование, удаление
            intId = Integer.parseInt(pId);
        }
        user.setId(intId);
        address.setUserId(intId);
        // Отображаем страницу для создания или просмотра, редактирования, удаления пользователя предварительно заполнив все данные для существующего пользователя
        if (pAction.equals("Create User")) {
            request.setAttribute("user", user);
            request.setAttribute("address", address);
            CONTROLLER.getPresentation().showCreateUser(request, response);
        } else {
            // Заполним остальные поля user и address
            user = CONTROLLER.getLogic().findByIdUser(user).get(0);
            request.setAttribute("user", user);
            List<Address> list = CONTROLLER.getLogic().findByIdAddress(address);
            if (list.size() > 0) {
                address = list.get(0);
            }
            request.setAttribute("address", address);
            CONTROLLER.getPresentation().showRudUser(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        //System.out.println("        CrudUserServlet doPost " + " " + request.getRequestURI() + "?" + request.getQueryString());
        response.setContentType("text/html;charset=utf-8");
        String pAction = request.getParameter("action");
        String pId = request.getParameter("id");
        Integer intId;
        if (pAction.equals("Create User")) {
            if (pId == null) {
                // Определим id для нового пользователя
                List<User> users = CONTROLLER.executeActionUser("Find All Users", new User());
                intId = users.size();
            } else {
                intId = Integer.parseInt(pId);
            }
        } else {
            intId = Integer.parseInt(pId);
        }
        // User
        String pName = request.getParameter("name");
        String pLogin = request.getParameter("login");
        String pPassword = request.getParameter("password");
        String pEmail = request.getParameter("email");
        User user = new User(intId, pName, pLogin, pPassword, pEmail, new Timestamp(System.currentTimeMillis()));
        // Address
        String pStreet = request.getParameter("street");
        String pHouse = request.getParameter("house");
        String pApartment = request.getParameter("apartment");
        String pCity = request.getParameter("city");
        String pZipCode = request.getParameter("zipcode");
        String pCountry = request.getParameter("country");
        Address address = new Address(intId, pStreet, pHouse, pApartment, pCity, pZipCode, pCountry);
        List<Address> addressList;
        switch (pAction) {
            case "Create User":
                CONTROLLER.executeActionUser(pAction, user);
                CONTROLLER.getLogic().addAddress(address);
                break;
            case "Update User":
                CONTROLLER.executeActionUser(pAction, user);
                addressList = CONTROLLER.getLogic().updateAddress(address);
                if (addressList.size() == 0) {
                    CONTROLLER.getLogic().addAddress(address);
                }
                break;
            case "Delete User":
                CONTROLLER.getLogic().deleteAddress(address);
                CONTROLLER.executeActionUser(pAction, user);
                break;
            default:
                CONTROLLER.getLogic().findByIdUser(user);
        }
        // Переходим на страницу списка пользователей
        try {
            response.sendRedirect(String.format("%s/protected/listusers", request.getContextPath()));
        } catch (IOException e) {
            LoggerApp.getInstance().getLog().error(e.getMessage(), e);
        }
    }
}
