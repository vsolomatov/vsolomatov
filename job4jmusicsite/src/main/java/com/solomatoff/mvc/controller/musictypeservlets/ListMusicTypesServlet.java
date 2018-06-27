package com.solomatoff.mvc.controller.musictypeservlets;

import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.entities.MusicType;
import com.solomatoff.mvc.entities.Role;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ListMusicTypesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        //System.out.println("    ListRolesServlet doGet " + " " + request.getRequestURI() + "?" + request.getQueryString());
        // Переменная для объекта Controller
        final Controller CONTROLLER = Controller.getInstance();
        List<MusicType> musictypes = CONTROLLER.getLogic().findAllMusicTypes(new MusicType());
        request.setAttribute("musictypes", musictypes);
        CONTROLLER.getPresentation().showListMusicTypes(request, response);
    }

}
