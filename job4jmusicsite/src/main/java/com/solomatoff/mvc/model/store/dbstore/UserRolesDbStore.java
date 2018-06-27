package com.solomatoff.mvc.model.store.dbstore;

import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.UserRoles;
import com.solomatoff.mvc.model.dao.UserRolesDAO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRolesDbStore extends CommonDbStore implements UserRolesDAO {
    private final CommonDbStore commonDbStore = CommonDbStore.getInstance();
    private final BasicDataSource dataSource = commonDbStore.getDataSource();
    private final String schemaName = commonDbStore.getSchemaName();
    
    /**
     * Метод добавляет userRoles в таблицу userRoles
     * @param userRoles добавляемая UserRole
     * @return список List<UserRoles>, состоящий из добавленной userRoles
     */
    @Override
    public List<UserRoles> addUserRoles(UserRoles userRoles) {
        List<UserRoles> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement st;
            if (userRoles.getUserId() != null && userRoles.getRoleId() != null) {
                st = connection.prepareStatement(String.format("INSERT INTO %s.userroles (userid, roleid) VALUES (?, ?)", schemaName));
                try {
                    st.setInt(1, userRoles.getUserId());
                    st.setInt(2, userRoles.getRoleId());
                    st.executeUpdate();
                    // Добавляем UserRoles в результирующий список
                    result.add(userRoles);
                    // Записываем в LOG
                    LoggerApp.getInstance().getLog().info(String.format("    Add: %s", userRoles));
                } catch (SQLException e) {
                    LoggerApp.getInstance().getLog().error(String.format("(ADD UserRoles) An error occurred while adding userRoles with userid = %4d (UserRoles already exists)", userRoles.getUserId()));
                }
                st.close();
            } else {
                LoggerApp.getInstance().getLog().error(String.format("(ADD UserRoles) An error occurred while adding userRoles with userid = %4d (UserId, RoleId can not be null)", userRoles.getUserId()));
            }
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(ADD) An error occurred while adding user with userid = %4d", userRoles.getUserId()), e);
        }
        return result;
    }

    /**
     *  Метод ничего не делает, просто возвращает значение userRoles
     *  Предполагается, что обновление записей в таблице userroles не имеет смысла
     * @param userRoles якобы обновляемая запись userRoles
     * @return список, содержащий исходную userRoles
     */
    @Override
    public List<UserRoles> updateUserRoles(UserRoles userRoles) {
        List<UserRoles> result = new ArrayList<>();
        result.add(userRoles);
        return result;
    }

    @Override
    public List<UserRoles> deleteUserRoles(UserRoles userRoles) {
        List<UserRoles> result = new ArrayList<>();
        // Получим предыдущее значение полей UserRole
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.userroles WHERE userid=%d and roleid=%d", schemaName, userRoles.getUserId(), userRoles.getRoleId()))) {
            result = getUserRolesFromResultSet(rs);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(DeleteUserRoles) An error occurred while deleting userRoles with userId = %2d, roleid = %2d", userRoles.getUserId(), userRoles.getRoleId()), e);
        }
        // Собственно удаляем
        if (result.size() > 0) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement st = connection.prepareStatement(String.format("DELETE FROM %s.userroles WHERE userid=? and roleid=?", schemaName))) {
                st.setInt(1, userRoles.getUserId());
                st.setInt(2, userRoles.getRoleId());
                int recDelete = st.executeUpdate();
                if (recDelete > 0) {
                    result.add(userRoles);
                    // Записываем в LOG
                    LoggerApp.getInstance().getLog().info(String.format("           Delete: %s", result.get(0)));
                }
            } catch (SQLException e) {
                LoggerApp.getInstance().getLog().error(String.format("(DELETE UserRoles) An error occurred while deleting userRoles with userId = %2d, roleid = %2d", userRoles.getUserId(), userRoles.getRoleId()), e);
            }
        }
        return result;
    }

    @Override
    public List<UserRoles> findByIdUserUserRoles(UserRoles userRoles) {
        List<UserRoles> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.userroles WHERE userId=%d ORDER BY roleid", schemaName, userRoles.getUserId()))) {
            result = getUserRolesFromResultSet(rs);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(findByIdUserRoles) An error occurred while finding userRoles with userId = %4d", userRoles.getUserId()), e);
        }
        if (result.size() > 0) {
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("Find By Id: %s", result.get(0)));
        }
        return result;
    }

    @Override
    public List<UserRoles> findAllUserRoles(UserRoles userRoles) {
        List<UserRoles> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.userroles ORDER BY userid, roleid", schemaName))) {
            result = getUserRolesFromResultSet(rs);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error("(findAllUserRoles) An error occurred while finding all userRoless", e);
        }
        // Записываем в LOG
        LoggerApp.getInstance().getLog().info("  Find All UserRoless.");
        return result;
    }

    /**
     * Метод формирует список UserRole из объекта-результата запросов к базе данных ResultSet
     * @param resultSet объект-результата запросов к базе данных
     * @return список ролей из объекта-результата запросов к базе данных ResultSet
     */
    public List<UserRoles> getUserRolesFromResultSet(ResultSet resultSet) {
        List<UserRoles> result = new ArrayList<>();
        int userid;
        int roleid;
        UserRoles userRoles;
        try {
            resultSet.beforeFirst();
            while (resultSet.next()) {
                userid = resultSet.getInt(1);
                roleid = resultSet.getInt(2);
                userRoles = new UserRoles(userid, roleid);
                result.add(userRoles);
            }
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error("(getUserRolesFromResultSet) An error occurred while get userRoless from ResultSet", e);
        }
        return result;
    }
}
