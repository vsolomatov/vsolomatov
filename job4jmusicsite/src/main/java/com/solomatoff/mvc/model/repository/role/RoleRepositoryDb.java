package com.solomatoff.mvc.model.repository.role;

import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.Role;
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

public class RoleRepositoryDb implements RoleRepository {
    private final DbStore dbStore = DbStore.getInstance();
    private final CommonDbStore commonDbStore = CommonDbStore.getInstance();
    private final BasicDataSource dataSource = commonDbStore.getDataSource();
    private final String schemaName = commonDbStore.getSchemaName();

    @Override
    public void addRole(Role role) {
        dbStore.addRole(role);
    }

    @Override
    public void removeRole(Role role) {
        dbStore.deleteRole(role);
    }

    @Override
    public void updateRole(Role role) {
        dbStore.updateRole(role);
    }

    @Override
    public List query(SqlSpecification sqlSpecification) {
        List<Role> result = new ArrayList<>();
        //System.out.println("(query) sqlSpecification.toSqlClauses() = " + sqlSpecification.toSqlClauses());
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.roles WHERE %s ORDER BY id", schemaName, sqlSpecification.toSqlClauses()))) {
            result = dbStore.getRoleDbStore().getRolesFromResultSet(rs);
            //System.out.println("(query) result = " + result);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(Role query) An error occurred while role query with clauses = %s", sqlSpecification.toSqlClauses()), e);
        }
        if (result.size() > 0) {
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("Query Role: %s", result));
        }
        return result;
    }
}
