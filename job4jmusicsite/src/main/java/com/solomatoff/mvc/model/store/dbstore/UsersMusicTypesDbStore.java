package com.solomatoff.mvc.model.store.dbstore;

import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.UsersMusicTypes;
import com.solomatoff.mvc.model.dao.UsersMusicTypesDAO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersMusicTypesDbStore extends CommonDbStore implements UsersMusicTypesDAO {
    private final CommonDbStore commonDbStore = CommonDbStore.getInstance();
    private final BasicDataSource dataSource = commonDbStore.getDataSource();
    private final String schemaName = commonDbStore.getSchemaName();
    
    /**
     * Метод добавляет usersMusicTypes в таблицу usersMusicTypes
     * @param usersMusicTypes добавляемая UserRole
     * @return список List<UsersMusicTypes>, состоящий из добавленной usersMusicTypes
     */
    @Override
    public List<UsersMusicTypes> addUsersMusicTypes(UsersMusicTypes usersMusicTypes) {
        List<UsersMusicTypes> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement st;
            if (usersMusicTypes.getUserId() != null && usersMusicTypes.getMusicTypeId() != null) {
                st = connection.prepareStatement(String.format("INSERT INTO %s.usersmusictypes (userid, musictypeid) VALUES (?, ?)", schemaName));
                try {
                    st.setInt(1, usersMusicTypes.getUserId());
                    st.setInt(2, usersMusicTypes.getMusicTypeId());
                    st.executeUpdate();
                    // Добавляем UsersMusicTypes в результирующий список
                    result.add(usersMusicTypes);
                    // Записываем в LOG
                    LoggerApp.getInstance().getLog().info(String.format("    Add: %s", usersMusicTypes));
                } catch (SQLException e) {
                    LoggerApp.getInstance().getLog().error(String.format("(ADD UsersMusicTypes) An error occurred while adding usersMusicTypes with userid = %4d (UsersMusicTypes already exists)", usersMusicTypes.getUserId()));
                }
                st.close();
            } else {
                LoggerApp.getInstance().getLog().error(String.format("(ADD UsersMusicTypes) An error occurred while adding usersMusicTypes with userid = %4d (UserId, MusicTypeId can not be null)", usersMusicTypes.getUserId()));
            }
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(ADD) An error occurred while adding user with userid = %4d", usersMusicTypes.getUserId()), e);
        }
        return result;
    }

    /**
     *  Метод ничего не делает, просто возвращает значение usersMusicTypes
     *  Предполагается, что обновление записей в таблице usersmusictypes не имеет смысла
     * @param usersMusicTypes якобы обновляемая запись usersMusicTypes
     * @return список, содержащий исходную usersMusicTypes
     */
    @Override
    public List<UsersMusicTypes> updateUsersMusicTypes(UsersMusicTypes usersMusicTypes) {
        List<UsersMusicTypes> result = new ArrayList<>();
        result.add(usersMusicTypes);
        return result;
    }

    @Override
    public List<UsersMusicTypes> deleteUsersMusicTypes(UsersMusicTypes usersMusicTypes) {
        List<UsersMusicTypes> result = new ArrayList<>();
        // Получим предыдущее значение полей UserRole
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.usersMusicTypes WHERE userId=%d and musictypeid=%d", schemaName, usersMusicTypes.getUserId(), usersMusicTypes.getMusicTypeId()))) {
            result = getUsersMusicTypesFromResultSet(rs);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(DeleteUsersMusicTypes) An error occurred while deleting usersMusicTypes with userId = %2d, musictypeid = %2d", usersMusicTypes.getUserId(), usersMusicTypes.getMusicTypeId()), e);
        }
        // Собственно удаляем
        if (result.size() > 0) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement st = connection.prepareStatement(String.format("DELETE FROM %s.usersMusicTypes WHERE userId=? and musictypeid=?", schemaName))) {
                st.setInt(1, usersMusicTypes.getUserId());
                st.setInt(2, usersMusicTypes.getMusicTypeId());
                int recDelete = st.executeUpdate();
                if (recDelete > 0) {
                    result.add(usersMusicTypes);
                    // Записываем в LOG
                    LoggerApp.getInstance().getLog().info(String.format("           Delete: %s", result.get(0)));
                }
            } catch (SQLException e) {
                LoggerApp.getInstance().getLog().error(String.format("(DELETE UsersMusicTypes) An error occurred while deleting usersMusicTypes with userId = %2d, musictypeid = %2d", usersMusicTypes.getUserId(), usersMusicTypes.getMusicTypeId()), e);
            }
        }
        return result;
    }

    /**
     * Метод находит все записи в таблице UsersMusicType c userId таким как у параметра метода
     * @param usersMusicTypes входящий usersMusicTypes
     * @return список usersMusicTypes
     */
    @Override
    public List<UsersMusicTypes> findByIdUsersMusicTypes(UsersMusicTypes usersMusicTypes) {
        List<UsersMusicTypes> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.usersMusicTypes WHERE userId=%d ORDER BY musictypeid", schemaName, usersMusicTypes.getUserId()))) {
            result = getUsersMusicTypesFromResultSet(rs);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(findByIdUsersMusicTypes) An error occurred while finding usersMusicTypes with userId = %4d", usersMusicTypes.getUserId()), e);
        }
        if (result.size() > 0) {
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("Find By Id: %s", result.get(0)));
        }
        return result;
    }

    @Override
    public List<UsersMusicTypes> findAllUsersMusicTypes(UsersMusicTypes usersMusicTypes) {
        List<UsersMusicTypes> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.usersMusicTypes ORDER BY userId, musictypeid", schemaName))) {
            result = getUsersMusicTypesFromResultSet(rs);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error("(findAllUsersMusicTypes) An error occurred while finding all usersMusicTypess", e);
        }
        // Записываем в LOG
        LoggerApp.getInstance().getLog().info("  Find All UsersMusicTypess.");
        return result;
    }

    /**
     * Метод формирует список UserRole из объекта-результата запросов к базе данных ResultSet
     * @param resultSet объект-результата запросов к базе данных
     * @return список ролей из объекта-результата запросов к базе данных ResultSet
     */
    public List<UsersMusicTypes> getUsersMusicTypesFromResultSet(ResultSet resultSet) {
        List<UsersMusicTypes> result = new ArrayList<>();
        int userid;
        int musicTypeId;
        UsersMusicTypes usersMusicTypes;
        try {
            resultSet.beforeFirst();
            while (resultSet.next()) {
                userid = resultSet.getInt(1);
                musicTypeId = resultSet.getInt(2);
                usersMusicTypes = new UsersMusicTypes(userid, musicTypeId);
                result.add(usersMusicTypes);
            }
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error("(getUsersMusicTypesFromResultSet) An error occurred while get usersMusicTypess from ResultSet", e);
        }
        return result;
    }
}
