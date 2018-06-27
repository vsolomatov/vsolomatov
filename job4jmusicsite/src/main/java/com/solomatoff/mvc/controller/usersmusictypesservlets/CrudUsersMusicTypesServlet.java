package com.solomatoff.mvc.controller.usersmusictypesservlets;

import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.MusicType;
import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.entities.UsersMusicTypes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CrudUsersMusicTypesServlet extends HttpServlet {
    // Переменная для объекта Controller
    private static final Controller CONTROLLER = Controller.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        //System.out.println("    CrudUsersMusicTypesServlet doGet " + " " + request.getRequestURI() + "?" + request.getQueryString());
        response.setContentType("text/html;charset=utf-8");
        String pAction = request.getParameter("action");
        String pUserId = request.getParameter("userid");
        String pMusicTypeId = request.getParameter("musictypeid");
        Integer intUserId = Integer.parseInt(pUserId);
        Integer intMusicTypeId;

        User user = new User();
        user.setId(intUserId);
        request.setAttribute("user", user);

        List<UsersMusicTypes> list = CONTROLLER.getLogic().findByIdUsersMusicTypes(new UsersMusicTypes(intUserId, null));
        intMusicTypeId = list.size() + 1; // для новой записи usersMusicTypes
        if (pMusicTypeId != null) { // если удаляем уже существующую
            intMusicTypeId = Integer.parseInt(pMusicTypeId);
        }

        UsersMusicTypes usersMusicTypes = new UsersMusicTypes(intUserId, intMusicTypeId);
        request.setAttribute("usersmusictypes", usersMusicTypes);

        List<MusicType> musicTypes = CONTROLLER.getLogic().findAllMusicTypes(new MusicType());
        request.setAttribute("musictypes", musicTypes);

        // Отображаем страницу для создания или удаления UserMusicType
        if (pAction.equals("Create UsersMusicTypes")) {
            CONTROLLER.getPresentation().showCreateUsersMusicTypes(request, response);
        } else {
            CONTROLLER.getPresentation().showRudUsersMusicTypes(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        //System.out.println("    CrudUsersMusicTypesServlet doPost " + " " + request.getRequestURI() + "?" + request.getQueryString());
        response.setContentType("text/html;charset=utf-8");
        String pAction = request.getParameter("action");
        String pUserId = request.getParameter("userid");
        String pMusicTypeId = request.getParameter("musictypeid");

        Integer intUserId = Integer.parseInt(pUserId);
        Integer intMusicTypeId = Integer.parseInt(pMusicTypeId);
        UsersMusicTypes usersMusicTypes = new UsersMusicTypes(intUserId, intMusicTypeId);
        switch (pAction) {
            case "Create":
                CONTROLLER.getLogic().addUsersMusicTypes(usersMusicTypes);
                break;
            case "Find By Id":
                CONTROLLER.getLogic().findByIdUsersMusicTypes(usersMusicTypes);
                break;
            case "Update":
                CONTROLLER.getLogic().updateUsersMusicTypes(usersMusicTypes);
                break;
            case "Delete":
                CONTROLLER.getLogic().deleteUsersMusicTypes(usersMusicTypes);
                break;
            default:
                CONTROLLER.getLogic().findByIdUsersMusicTypes(usersMusicTypes);
        }
        // Переходим на страницу списка UsersMusicTypes
        try {
            response.sendRedirect(String.format("%s/protected/listusersmusictypes?userid=%s", request.getContextPath(), pUserId));
        } catch (IOException e) {
            LoggerApp.getInstance().getLog().error(e.getMessage(), e);
        }
    }
}
