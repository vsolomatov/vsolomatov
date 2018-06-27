package com.solomatoff.mvc.controller.usersmusictypesservlets;

import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.entities.*;
import com.solomatoff.mvc.model.ModelLogic;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ListUsersMusicTypesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        //System.out.println("    ListUsersMusicTypesServlet doGet " + " " + request.getRequestURI() + "?" + request.getQueryString());
        // Переменная для объекта Controller
        final Controller controller = Controller.getInstance();
        final ModelLogic modelLogic = controller.getLogic();
        Integer intUserId = Integer.parseInt(request.getParameter("userid"));

        User user = new User();
        user.setId(intUserId);
        request.setAttribute("user", user);

        List<MusicType> musicTypes = modelLogic.findAllMusicTypes(new MusicType());
        request.setAttribute("musictypes", musicTypes);

        List<UsersMusicTypes> usersMusicTypes = modelLogic.findByIdUsersMusicTypes(new UsersMusicTypes(intUserId, null));
        request.setAttribute("usersmusictypes", usersMusicTypes);

        controller.getPresentation().showListUsersMusicTypes(request, response);
    }

}
