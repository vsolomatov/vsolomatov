package com.solomatoff.mvc.controller.musictypeservlets;

import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.MusicType;
import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.entities.UsersMusicTypes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrudMusicTypeServlet extends HttpServlet {
    // Переменная для объекта Controller
    private static final Controller CONTROLLER = Controller.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        //System.out.println("    CrudMusicTypeServlet doGet " + " " + request.getRequestURI() + "?" + request.getQueryString());
        response.setContentType("text/html;charset=utf-8");
        String pAction = request.getParameter("action");
        String pId = request.getParameter("id");
        String pName = request.getParameter("musictypename");

        Integer intId;
        // Определим id для нового MusicType
        if (pId == null || pId.equals("")) {
            List<MusicType> musicTypes = CONTROLLER.getLogic().findAllMusicTypes(new MusicType());
            intId = musicTypes.size() + 1;
        } else {
            intId = Integer.parseInt(pId);
        }
        MusicType musicType = new MusicType(intId, pName);
        request.setAttribute("musictype", musicType);

        // Отображаем страницу для создания или редактирования MusicType
        if (pAction.equals("Create MusicType")) {
            CONTROLLER.getPresentation().showCreateMusicType(request, response);
        } else {
            CONTROLLER.getPresentation().showRudMusicType(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        //System.out.println("    CrudMusicTypeServlet doPost " + " " + request.getRequestURI() + "?" + request.getQueryString());
        response.setContentType("text/html;charset=utf-8");
        String pAction = request.getParameter("action");
        String pId = request.getParameter("id");
        String pName = request.getParameter("musictypename");
        Integer intId;
        // Определим id для новой MusicType
        if (pId == null || pId.equals("")) {
            List<MusicType> musicTypes = CONTROLLER.getLogic().findAllMusicTypes(new MusicType());
            intId = musicTypes.size();
        } else {
            intId = Integer.parseInt(pId);
        }
        MusicType musicType = new MusicType(intId, pName);
        switch (pAction) {
            case "Create MusicType":
                CONTROLLER.getLogic().addMusicType(musicType);
                break;
            case "Find By Id MusicType":
                CONTROLLER.getLogic().findByIdMusicType(musicType);
                break;
            case "Update MusicType":
                CONTROLLER.getLogic().updateMusicType(musicType);
                break;
            case "Delete MusicType":
                CONTROLLER.getLogic().deleteMusicType(musicType);
                break;
            default:
                CONTROLLER.getLogic().findByIdMusicType(musicType);
        }
        // Переходим на страницу списка MusicType
        try {
            response.sendRedirect(String.format("%s/protected/listmusictypes", request.getContextPath()));
        } catch (IOException e) {
            LoggerApp.getInstance().getLog().error(e.getMessage(), e);
        }
    }
}
