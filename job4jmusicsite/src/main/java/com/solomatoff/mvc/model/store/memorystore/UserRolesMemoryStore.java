package com.solomatoff.mvc.model.store.memorystore;

import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.entities.UserRoles;
import com.solomatoff.mvc.model.dao.UserRolesDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class UserRolesMemoryStore implements UserRolesDAO {
    /**
     * Contains userRoless
     */
    private static final Map<UserRoles, UserRoles> USERROLES_MAP = new ConcurrentSkipListMap<>();

    @Override
    public List<UserRoles> addUserRoles(UserRoles userRoles) {
        List<UserRoles> result = new ArrayList<>();
        UserRoles userRolesResult = USERROLES_MAP.get(userRoles);
        // Добавляем только, если еще не существует
        if (userRolesResult == null) {
            USERROLES_MAP.put(userRoles, userRoles);
            result.add(userRoles); // добавляем нового UserRoles в результирующий список
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Add in Memory: %s", userRoles));
        }
        return result;
    }

    @Override
    public List<UserRoles> updateUserRoles(UserRoles userRoles) {
        List<UserRoles> result = new ArrayList<>();
        UserRoles userRolesResult = USERROLES_MAP.put(userRoles, userRoles);
        if (userRolesResult != null) {
            result.add(userRolesResult);
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Before Update in Memory: %s", userRolesResult));
            LoggerApp.getInstance().getLog().info(String.format("    After Update in Memory: %s", userRoles));
        }
        return result;
    }

    @Override
    public List<UserRoles> deleteUserRoles(UserRoles userRoles) {
        List<UserRoles> result = new ArrayList<>();
        UserRoles userRolesResult = USERROLES_MAP.remove(userRoles);
        if (userRolesResult != null) {
            result.add(userRolesResult);
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Delete from Memory: %s", userRolesResult));
        }
        return result;
    }

    /**
     * Метод находит все записи в UserRoles c userId таким как у параметра метода
     * @param userRoles входящий userRoles
     * @return список usersMusicTypes
     */
    @Override
    public List<UserRoles> findByIdUserUserRoles(UserRoles userRoles) {
        List<UserRoles> result = new ArrayList<>();
        List<UserRoles> list = Controller.getInstance().getLogic().findAllUserRoles(new UserRoles());
        for (UserRoles userRolesLoop : list) {
            if (userRolesLoop.getUserId().equals(userRoles.getUserId())) {
                result.add(userRolesLoop);
                // Записываем в LOG
                LoggerApp.getInstance().getLog().info(String.format("    Find By Id in Memory: %s", userRolesLoop));
            }
        }
        return result;
    }

    @Override
    public List<UserRoles> findAllUserRoles(UserRoles userRoles) {
        List<UserRoles> result = new ArrayList<>(USERROLES_MAP.values());
        // Записываем в LOG
        LoggerApp.getInstance().getLog().info("  Find All UserRoles in Memory.");
        return result;
    }
}
