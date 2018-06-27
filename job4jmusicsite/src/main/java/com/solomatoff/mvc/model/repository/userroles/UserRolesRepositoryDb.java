package com.solomatoff.mvc.model.repository.userroles;

import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.UserRoles;
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

public class UserRolesRepositoryDb implements UserRolesRepository {
    private final DbStore dbStore = DbStore.getInstance();
    private final CommonDbStore commonDbStore = CommonDbStore.getInstance();
    private final BasicDataSource dataSource = commonDbStore.getDataSource();
    private final String schemaName = commonDbStore.getSchemaName();

    @Override
    public void addUserRoles(UserRoles userRoles) {
        dbStore.addUserRoles(userRoles);
    }

    @Override
    public void removeUserRoles(UserRoles userRoles) {
        dbStore.deleteUserRoles(userRoles);
    }

    @Override
    public void updateUserRoles(UserRoles userRoles) {
        dbStore.updateUserRoles(userRoles);
    }

    @Override
    public List query(SqlSpecification sqlSpecification) {
        List<UserRoles> result = new ArrayList<>();
        //System.out.println("(query) sqlSpecification.toSqlClauses() = " + sqlSpecification.toSqlClauses());
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.userroles WHERE %s ORDER BY userid, roleid", schemaName, sqlSpecification.toSqlClauses()))) {
            result = dbStore.getUserRolesDbStore().getUserRolesFromResultSet(rs);
            //System.out.println("(query) result = " + result);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(UserRoles query) An error occurred while userRoles query with clauses = %s", sqlSpecification.toSqlClauses()), e);
        }
        if (result.size() > 0) {
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("Query UserRoles: %s", result));
        }
        return result;
    }
}
