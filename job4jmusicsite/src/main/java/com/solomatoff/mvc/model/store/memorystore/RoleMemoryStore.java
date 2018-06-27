package com.solomatoff.mvc.model.store.memorystore;

import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.model.dao.RoleDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class RoleMemoryStore implements RoleDAO {

    /**
     * Contains roles
     */
    private static final Map<Integer, Role> ROLE_MAP = new ConcurrentSkipListMap<>();

    @Override
    public List<Role> addRole(Role role) {
        List<Role> result = new ArrayList<>();
        Role roleResult = ROLE_MAP.get(role.getId());
        // Добавляем только, если еще не существует
        if (roleResult == null) {
            ROLE_MAP.put(role.getId(), role);
            result.add(role); // добавляем новую роль в результирующий список
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Add in Memory: %s", role));
        }
        return result;
    }

    @Override
    public List<Role> updateRole(Role role) {
        List<Role> result = new ArrayList<>();
        Role roleResult = ROLE_MAP.put(role.getId(), role);
        if (roleResult != null) {
            result.add(roleResult);
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Before Update in Memory: %s", roleResult));
            LoggerApp.getInstance().getLog().info(String.format("    After Update in Memory: %s", role));
        }
        return result;
    }

    @Override
    public List<Role> deleteRole(Role role) {
        List<Role> result = new ArrayList<>();
        Role roleResult = ROLE_MAP.remove(role.getId());
        if (roleResult != null) {
            result.add(roleResult);
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Delete from Memory: %s", roleResult));
        }
        return result;
    }

    @Override
    public List<Role> findByIdRole(Role role) {
        List<Role> result = new ArrayList<>();
        Role roleResult = ROLE_MAP.get(role.getId());
        if (roleResult != null) {
            result.add(roleResult);
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Find By Id in Memory: %s", roleResult));
        }
        return result;
    }

    @Override
    public List<Role> findAllRoles(Role role) {
        List<Role> result = new ArrayList<>(ROLE_MAP.values());
        // Записываем в LOG
        LoggerApp.getInstance().getLog().info("  Find All Roles.");
        return result;
    }
}
