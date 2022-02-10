package com.solomatoff.mvc.model.store.dbstore;

import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.model.dao.UserDAO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDbStore extends CommonDbStore implements UserDAO {
    private final CommonDbStore commonDbStore = CommonDbStore.getInstance();
    private final BasicDataSource dataSource = commonDbStore.getDataSource();
    private final String schemaName = commonDbStore.getSchemaName();
    
    /**
     *  Метод добавляет user в таблицу users
     * @param user добавляемый User
     * @return список List<User>, состоящий из добавленного user
     */
    @Override
    public List<User> addUser(User user) {
        List<User> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement st;
            if (user.getId() == null) { // Если id не задан
                String stringInsert = String.format("INSERT INTO %s.users(name, login, password, email, createDate) VALUES (?, ?, ?, ?, ?)", schemaName);
                st = connection.prepareStatement(stringInsert, Statement.RETURN_GENERATED_KEYS);
                st = prepareStatForInsOrUpdUser(st, user);
                // Определим максимальный id User в таблице
                Statement st2 = connection.createStatement();
                st2.executeQuery(String.format("SELECT max(id) FROM %s.users", schemaName));
                ResultSet resultSet = st2.getResultSet();
                if (resultSet.next()) {
                    int nextIdUser = resultSet.getInt(1) + 1;
                    alterSequenceUsersIdSeq(nextIdUser);
                }
                st2.close();
            } else { // Если id задан
                st = connection.prepareStatement(String.format("INSERT INTO %s.users(id, name, login, password, email, createDate) VALUES (?, ?, ?, ?, ?, ?)", schemaName));
                st.setInt(1, user.getId());
                st.setString(2, user.getName());
                st.setString(3, user.getLogin());
                st.setString(4, user.getPassword());
                st.setString(5, user.getEmail());
                st.setTimestamp(6, user.getCreateDate());
            }
            try {
                // Собственно добавление User в таблицу
                //System.out.println("st = " + st);
                int recInsert = st.executeUpdate();
                if (recInsert > 0) {
                    ResultSet autoGenerated = st.getGeneratedKeys();
                    Integer userId;
                    if (autoGenerated.next()) {
                        userId = autoGenerated.getInt(1);
                    } else {
                        userId = user.getId();
                    }
                    user.setId(userId);
                    // Добавление нового User в результат (список)
                    result.add(user);
                    // Записываем в LOG
                    LoggerApp.getInstance().getLog().info(String.format("    Add: %s", user));
                }
            } catch (SQLException e) {
                LoggerApp.getInstance().getLog().error(String.format("(ADD) An error occurred while adding user with id = %4d (User already exists)", user.getId()));
            }
            st.close();
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(ADD) An error occurred while adding user with id = %4d", user.getId()), e);
        }
        return result;
    }

    @Override
    public List<User> updateUser(User user) {
        List<User> result = new ArrayList<>();
        // Вначале выберем старые значения полей User, чтобы вернуть их
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.users WHERE id=%d", schemaName, user.getId()))) {
            result = getUsersFromResultSet(rs);
            if (result.size() > 0) {
                // Записываем в LOG
                LoggerApp.getInstance().getLog().info(String.format("    Before Update: %s", result.get(0)));
            }
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(UPDATE User) An error occurred while updating user with id = %4d", user.getId()), e);
        }
        // Теперь выполним собственно Update
        if (result.size() > 0) {
            try (Connection connection = dataSource.getConnection()) {
                PreparedStatement st = connection.prepareStatement(String.format("UPDATE %s.users SET name=?, login=?, password=?, email=?, createDate=? WHERE id=?", schemaName));
                st = prepareStatForInsOrUpdUser(st, user);
                st.setInt(6, user.getId());
                st.executeUpdate();
                // Записываем в LOG
                LoggerApp.getInstance().getLog().info(String.format("    After Update: %s", user));
                st.close();
            } catch (SQLException e) {
                LoggerApp.getInstance().getLog().error(String.format("(UPDATE User) An error occurred while updating user with id = %4d", user.getId()), e);
            }
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(UPDATE User) An error occurred while updating user with id = %4d (User not exists)", user.getId()));
        }
        return result;
    }

    @Override
    public List<User> deleteUser(User user) {
        List<User> result = new ArrayList<>();
        // Вначале выберем старые значения полей User, чтобы вернуть их
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.users WHERE id=%d", schemaName, user.getId()))) {
            result = getUsersFromResultSet(rs);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(DELETE User) An error occurred while deleting user with id = %4d", user.getId()), e);
        }
        // Теперь выполним собственно Delete
        if (result.size() > 0) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement st = connection.prepareStatement(String.format("DELETE FROM %s.users WHERE id=?", schemaName))) {
                st.setInt(1, user.getId());
                st.executeUpdate();
                // Записываем в LOG
                LoggerApp.getInstance().getLog().info(String.format("           Delete: %s", result.get(0)));
            } catch (SQLException e) {
                LoggerApp.getInstance().getLog().error(String.format("(DELETE User) An error occurred while deleting user with id = %4d", user.getId()), e);
            }
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(DELETE User) An error occurred while deleting user with id = %4d (User not exists)", user.getId()));
        }
        return result;
    }

    @Override
    public List<User> findByIdUser(User user) {
        List<User> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.users WHERE id=%d", schemaName, user.getId()))) {
            result = getUsersFromResultSet(rs);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(findByIdUser) An error occurred while finding by id user with id = %4d", user.getId()), e);
        }
        if (result.size() > 0) {
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("Find By Id: %s", result.get(0)));
        }
        return result;
    }

    @Override
    public List<User> findAllUsers(User user) {
        List<User> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.users ORDER BY id", schemaName))) {
            result = getUsersFromResultSet(rs);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error("(findAllUsers) An error occurred while finding all users", e);
        }
        // Записываем в LOG
        LoggerApp.getInstance().getLog().info("  Find All Users.");
        return result;
    }

    /**
     * Метод формирует список User из объекта-результата запросов к базе данных ResultSet
     * @param resultSet объект-результата запросов к базе данных
     * @return список User из объекта-результата запросов к базе данных ResultSet
     */
    public List<User> getUsersFromResultSet(ResultSet resultSet) {
        List<User> result = new ArrayList<>();
        int id;
        String name;
        String login;
        String password;
        String email;
        Timestamp createDate;
        User user;
        try {
            resultSet.beforeFirst();
            while (resultSet.next()) {
                id = resultSet.getInt(1);
                name = resultSet.getString(2);
                login = resultSet.getString(3);
                password = resultSet.getString(4);
                email = resultSet.getString(5);
                createDate = resultSet.getTimestamp(6);
                user = new User(id, name, login, password, email, createDate);
                result.add(user);
            }
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error("(getUsersFromResultSet) An error occurred while get users from ResultSet", e);
        }
        return result;
    }

    /**
     * Вспомогательный метод для формирования запроса к базе данных
     * @param st запрос
     * @param user User для запроса
     * @return видоизмененный запрос к базе данных
     */
    private PreparedStatement prepareStatForInsOrUpdUser(PreparedStatement st, User user) {
        try {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getPassword());
            st.setString(4, user.getEmail());
            st.setTimestamp(5, user.getCreateDate());
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error("(prepareStatForInsOrUpdUser) An error occurred while prepared statement", e);
        }
        return st;
    }

    /**
     * Метод для изменения последовательности для первичного ключа таблицы users
     * @param restartWith начальный элемент последовательности
     */
    private void alterSequenceUsersIdSeq(int restartWith) {
        String stringAlter = String.format("ALTER SEQUENCE %s.users_id_seq RESTART WITH %s", schemaName, restartWith);
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement()) {
            st.executeUpdate(stringAlter);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error("(alterSequenceUsersIdSeq) An error occurred while alter sequence", e);
        }
    }
}


