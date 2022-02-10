package com.solomatoff.mvc.model.store.dbstore;

import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.model.dao.RoleDAO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDbStore extends CommonDbStore implements RoleDAO {
    private final CommonDbStore commonDbStore = CommonDbStore.getInstance();
    private final BasicDataSource dataSource = commonDbStore.getDataSource();
    private final String schemaName = commonDbStore.getSchemaName();
    
    /**
     * Метод добавляет role в таблицу roles
     * @param role добавляемая роль
     * @return список List<Role>, состоящий из добавленной role
     */
    @Override
    public List<Role> addRole(Role role) {
        List<Role> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement st;
            if (role.getId() == null) {
                st = connection.prepareStatement(String.format("INSERT INTO %s.roles(name, is_admin) VALUES (?, ?)", schemaName), Statement.RETURN_GENERATED_KEYS);
                // Определим максимальный id ролей в таблице
                Statement st2 = connection.createStatement();
                st2.executeQuery(String.format("SELECT max(id) FROM %s.roles", schemaName));
                ResultSet resultSet = st2.getResultSet();
                if (resultSet.next()) {
                    int nextIdRole = resultSet.getInt(1) + 1;
                    alterSequenceRolesIdSeq(nextIdRole);
                }
                st2.close();
                st.setString(1, role.getName());
                st.setBoolean(2, role.getIsAdmin());
            } else {
                st = connection.prepareStatement(String.format("INSERT INTO %s.roles (id, name, is_admin) VALUES (?, ?, ?)", schemaName));
                st.setInt(1, role.getId());
                st.setString(2, role.getName());
                st.setBoolean(3, role.getIsAdmin());
            }
            try {
                st.executeUpdate();
                ResultSet autoGenerated = st.getGeneratedKeys();
                Integer roleId;
                if (autoGenerated.next()) {
                    roleId = autoGenerated.getInt(1);
                } else {
                    roleId = role.getId();
                }
                role.setId(roleId);
                // Добавляем роль в результирующий список
                result.add(role);
                // Записываем в LOG
                LoggerApp.getInstance().getLog().info(String.format("    Add: %s", role));
            } catch (SQLException e) {
                LoggerApp.getInstance().getLog().error(String.format("(ADD Role) An error occurred while adding role with id = %4d (Role already exists)", role.getId()));
            }
            st.close();
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(ADD) An error occurred while adding user with id = %4d", role.getId()), e);
        }
        return result;
    }

    @Override
    public List<Role> updateRole(Role role) {
        List<Role> result = new ArrayList<>();
        // Получим предыдущее значение полей роли
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.roles WHERE id=%d", schemaName, role.getId()))) {
            result = getRolesFromResultSet(rs);
            if (result.size() > 0) {
                // Записываем в LOG
                LoggerApp.getInstance().getLog().info(String.format("    Before Update: %s", result.get(0)));
            }
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(UpdateRole) An error occurred while updating role with id = %4d", role.getId()), e);
        }
        // Собственно изменяем
        if (result.size() > 0) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement st = connection.prepareStatement(String.format("UPDATE %s.roles SET name=?, is_admin=? WHERE id=?", schemaName))) {
                st.setString(1, role.getName());
                st.setBoolean(2, role.getIsAdmin());
                st.setInt(3, role.getId());
                // Изменяем роль
                int recInsert = st.executeUpdate();
                if (recInsert > 0) {
                    // Записываем в LOG
                    LoggerApp.getInstance().getLog().info(String.format("   After Update: %s", role));
                }
            } catch (SQLException e) {
                LoggerApp.getInstance().getLog().error(String.format("(UPDATE Role) An error occurred while updating role with id = %4d", role.getId()), e);
            }
        }
        return result;
    }

    @Override
    public List<Role> deleteRole(Role role) {
        List<Role> result = new ArrayList<>();
        // Получим предыдущее значение полей роли
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.roles WHERE id=%d", schemaName, role.getId()))) {
            result = getRolesFromResultSet(rs);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(DeleteRole) An error occurred while deleting role with id = %4d", role.getId()), e);
        }
        // Собственно удаляем
        if (result.size() > 0) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement st = connection.prepareStatement(String.format("DELETE FROM %s.roles WHERE id=?", schemaName))) {
                st.setInt(1, role.getId());
                int recDelete = st.executeUpdate();
                if (recDelete > 0) {
                    result.add(role);
                    // Записываем в LOG
                    LoggerApp.getInstance().getLog().info(String.format("           Delete: %s", result.get(0)));
                }
            } catch (SQLException e) {
                LoggerApp.getInstance().getLog().error(String.format("(DELETE Role) An error occurred while deleting role with id = %4d", role.getId()), e);
            }
        }
        return result;
    }

    @Override
    public List<Role> findByIdRole(Role role) {
        List<Role> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.roles WHERE id=%d", schemaName, role.getId()))) {
            result = getRolesFromResultSet(rs);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(findByIdRole) An error occurred while finding role with id = %4d", role.getId()), e);
        }
        if (result.size() > 0) {
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("Find By Id: %s", result.get(0)));
        }
        return result;
    }

    @Override
    public List<Role> findAllRoles(Role role) {
        List<Role> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.roles ORDER BY id", schemaName))) {
            result = getRolesFromResultSet(rs);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error("(findAllRole) An error occurred while finding all roles", e);
        }
        // Записываем в LOG
        LoggerApp.getInstance().getLog().info("  Find All Roles.");
        return result;
    }

    /**
     * Метод формирует список ролей из объекта-результата запросов к базе данных ResultSet
     * @param resultSet объект-результата запросов к базе данных
     * @return список ролей из объекта-результата запросов к базе данных ResultSet
     */
    public List<Role> getRolesFromResultSet(ResultSet resultSet) {
        List<Role> result = new ArrayList<>();
        int id;
        String name;
        boolean isAdmin;
        Role role;
        try {
            resultSet.beforeFirst();
            while (resultSet.next()) {
                id = resultSet.getInt(1);
                name = resultSet.getString(2);
                isAdmin = resultSet.getBoolean(3);
                role = new Role(id, name, isAdmin);
                result.add(role);
                //System.out.printf("         Role from ResultSet: <id=%s> <name=%s> <isAdmin=%s>%n", role.getId(), role.getName(), role.getIsAdmin());
            }
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error("(getRolesFromResultSet) An error occurred while get roles from ResultSet", e);
        }
        return result;
    }

    /**
     * Метод для изменения последовательности для первичного ключа таблицы roles
     * @param restartWith начальный элемент последовательности
     */
    private void alterSequenceRolesIdSeq(int restartWith) {
        String stringAlter = String.format("ALTER SEQUENCE %s.roles_id_seq RESTART WITH %s", schemaName, restartWith);
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement()) {
            st.executeUpdate(stringAlter);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error("(alterSequenceRolesIdSeq) An error occurred while alter sequence", e);
        }
    }
}