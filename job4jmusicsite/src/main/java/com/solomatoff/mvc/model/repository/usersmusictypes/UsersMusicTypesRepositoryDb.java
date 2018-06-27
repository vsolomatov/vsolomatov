package com.solomatoff.mvc.model.repository.usersmusictypes;

import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.UsersMusicTypes;
import com.solomatoff.mvc.model.repository.SqlSpecification;
import com.solomatoff.mvc.model.store.DbStore;
import com.solomatoff.mvc.model.store.dbstore.CommonDbStore;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersMusicTypesRepositoryDb implements UsersMusicTypesRepository {
    private final DbStore dbStore = DbStore.getInstance();
    private final CommonDbStore commonDbStore = CommonDbStore.getInstance();
    private final BasicDataSource dataSource = commonDbStore.getDataSource();
    private final String schemaName = commonDbStore.getSchemaName();

    @Override
    public void addUsersMusicTypes(UsersMusicTypes userRoles) {
        dbStore.addUsersMusicTypes(userRoles);
    }

    @Override
    public void removeUsersMusicTypes(UsersMusicTypes userRoles) {
        dbStore.deleteUsersMusicTypes(userRoles);
    }

    @Override
    public void updateUsersMusicTypes(UsersMusicTypes userRoles) {
        dbStore.updateUsersMusicTypes(userRoles);
    }

    @Override
    public List query(SqlSpecification sqlSpecification) {
        List<UsersMusicTypes> result = new ArrayList<>();
        //System.out.println("(query) sqlSpecification.toSqlClauses() = " + sqlSpecification.toSqlClauses());
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.usersmusictypes WHERE %s ORDER BY userid, musictypeid", schemaName, sqlSpecification.toSqlClauses()))) {
            result = dbStore.getUsersMusicTypesDbStore().getUsersMusicTypesFromResultSet(rs);
            //System.out.println("(query) result = " + result);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(UsersMusicTypes query) An error occurred while usersMusicTypes query with clauses = %s", sqlSpecification.toSqlClauses()), e);
        }
        if (result.size() > 0) {
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("Query UsersMusicTypes: %s", result));
        }
        return result;
    }
}
