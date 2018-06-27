package com.solomatoff.mvc.model.store.memorystore;

import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.MusicType;
import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.entities.UserRoles;
import com.solomatoff.mvc.entities.UsersMusicTypes;
import com.solomatoff.mvc.model.dao.UserRolesDAO;
import com.solomatoff.mvc.model.dao.UsersMusicTypesDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class UsersMusicTypesMemoryStore implements UsersMusicTypesDAO {
    /**
     * Contains usersMusicTypes
     */
    private static final Map<UsersMusicTypes, UsersMusicTypes> USERSMUSICTYPES_MAP = new ConcurrentSkipListMap<>();

    @Override
    public List<UsersMusicTypes> addUsersMusicTypes(UsersMusicTypes usersMusicTypes) {
        //System.out.println("    UsersMusicTypesMemoryStore (addUsersMusicTypes) usersMusicTypes = " + usersMusicTypes);
        List<UsersMusicTypes> result = new ArrayList<>();
        UsersMusicTypes usersMusicTypesResult = USERSMUSICTYPES_MAP.get(usersMusicTypes);
        // Добавляем только, если еще не существует
        if (usersMusicTypesResult == null) {
            USERSMUSICTYPES_MAP.put(usersMusicTypes, usersMusicTypes);
            result.add(usersMusicTypes); // добавляем нового UsersMusicTypes в результирующий список
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Add in Memory: %s", usersMusicTypes));
        }
        return result;
    }

    @Override
    public List<UsersMusicTypes> updateUsersMusicTypes(UsersMusicTypes usersMusicTypes) {
        List<UsersMusicTypes> result = new ArrayList<>();
        UsersMusicTypes usersMusicTypesResult = USERSMUSICTYPES_MAP.put(usersMusicTypes, usersMusicTypes);
        if (usersMusicTypesResult != null) {
            result.add(usersMusicTypesResult);
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Before Update in Memory: %s", usersMusicTypesResult));
            LoggerApp.getInstance().getLog().info(String.format("    After Update in Memory: %s", usersMusicTypes));
        }
        return result;
    }

    @Override
    public List<UsersMusicTypes> deleteUsersMusicTypes(UsersMusicTypes usersMusicTypes) {
        List<UsersMusicTypes> result = new ArrayList<>();
        UsersMusicTypes usersMusicTypesResult = USERSMUSICTYPES_MAP.remove(usersMusicTypes);
        if (usersMusicTypesResult != null) {
            result.add(usersMusicTypesResult);
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Delete from Memory: %s", usersMusicTypesResult));
        }
        return result;
    }

    /**
     * Метод находит все записи в UsersMusicType c userId таким как у параметра метода
     * @param usersMusicTypes входящий usersMusicTypes
     * @return список usersMusicTypes
     */
    @Override
    public List<UsersMusicTypes> findByIdUsersMusicTypes(UsersMusicTypes usersMusicTypes) {
        List<UsersMusicTypes> result = new ArrayList<>();
        List<UsersMusicTypes> list = Controller.getInstance().getLogic().findAllUsersMusicTypes(new UsersMusicTypes());
        for (UsersMusicTypes usersMusicTypesLoop : list) {
            if (usersMusicTypesLoop.getUserId().equals(usersMusicTypes.getUserId())) {
                result.add(usersMusicTypesLoop);
                // Записываем в LOG
                LoggerApp.getInstance().getLog().info(String.format("    Find By Id in Memory: %s", usersMusicTypesLoop));
            }
        }
        return result;
    }

    @Override
    public List<UsersMusicTypes> findAllUsersMusicTypes(UsersMusicTypes usersMusicTypes) {
        List<UsersMusicTypes> result = new ArrayList<>(USERSMUSICTYPES_MAP.values());
        // Записываем в LOG
        LoggerApp.getInstance().getLog().info("  Find All UsersMusicTypes in Memory.");
        return result;
    }
}
