package com.solomatoff.mvc.model.repository.user;

import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.model.repository.SqlSpecification;
import com.solomatoff.mvc.model.repository.user.UserRepository;
import com.solomatoff.mvc.model.store.DbStore;
import com.solomatoff.mvc.model.store.dbstore.CommonDbStore;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryDb implements UserRepository {
    private final DbStore dbStore = DbStore.getInstance();
    private final CommonDbStore commonDbStore = CommonDbStore.getInstance();
    private final BasicDataSource dataSource = commonDbStore.getDataSource();
    private final String schemaName = commonDbStore.getSchemaName();

    @Override
    public void addUser(User user) {
        dbStore.addUser(user);
    }

    @Override
    public void removeUser(User user) {
        dbStore.deleteUser(user);
    }

    @Override
    public void updateUser(User user) {
        dbStore.updateUser(user);
    }

    @Override
    public List query(SqlSpecification sqlSpecification) {
        List<User> result = new ArrayList<>();
        //System.out.println("(query) sqlSpecification.toSqlClauses() = " + sqlSpecification.toSqlClauses());
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.users WHERE %s ORDER BY id", schemaName, sqlSpecification.toSqlClauses()))) {
            result = dbStore.getUserDbStore().getUsersFromResultSet(rs);
            //System.out.println("(query) result = " + result);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(User query) An error occurred while user query with clauses = %s", sqlSpecification.toSqlClauses()), e);
        }
        if (result.size() > 0) {
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("Query User: %s", result));
        }
        return result;
    }
}
