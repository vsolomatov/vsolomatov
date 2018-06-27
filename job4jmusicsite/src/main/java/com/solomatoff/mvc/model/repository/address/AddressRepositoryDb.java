package com.solomatoff.mvc.model.repository.address;

import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.Address;
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

public class AddressRepositoryDb implements AddressRepository {
    private final DbStore dbStore = DbStore.getInstance();
    private final CommonDbStore commonDbStore = CommonDbStore.getInstance();
    private final BasicDataSource dataSource = commonDbStore.getDataSource();
    private final String schemaName = commonDbStore.getSchemaName();

    @Override
    public void addAddress(Address musicType) {
        dbStore.addAddress(musicType);
    }

    @Override
    public void removeAddress(Address musicType) {
        dbStore.deleteAddress(musicType);
    }

    @Override
    public void updateAddress(Address musicType) {
        dbStore.updateAddress(musicType);
    }

    @Override
    public List query(SqlSpecification sqlSpecification) {
        List<Address> result = new ArrayList<>();
        //System.out.println("(query) specification.toSqlClauses() = " + specification.toSqlClauses());
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.addresses WHERE %s ORDER BY userid", schemaName, sqlSpecification.toSqlClauses()))) {
            result = dbStore.getAddressDbStore().getAddressesFromResultSet(rs);
            //System.out.println("(query) result = " + result);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(Address query) An error occurred while address query with clauses = %s", sqlSpecification.toSqlClauses()), e);
        }
        if (result.size() > 0) {
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("Query Address: %s", result));
        }
        return result;
    }
}
