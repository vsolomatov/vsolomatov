package com.solomatoff.mvc.model.store.dbstore;

import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.Address;
import com.solomatoff.mvc.model.dao.AddressDAO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDbStore extends CommonDbStore implements AddressDAO {
    private final CommonDbStore commonDbStore = CommonDbStore.getInstance();
    private final BasicDataSource dataSource = commonDbStore.getDataSource();
    private final String schemaName = commonDbStore.getSchemaName();

    /**
     * Метод добавляет address в таблицу addresses
     * @param address добавляемая Address
     * @return список List<Address>, состоящий из добавленной Address
     */
    @Override
    public List<Address> addAddress(Address address) {
        List<Address> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement st;
            if (address.getUserId() != null && address.getStreet() != null && address.getCity() != null && address.getCountry() != null) {
                st = connection.prepareStatement(String.format("INSERT INTO %s.addresses (userid, street, house, apartment, city, zipcode, country) VALUES (?, ?, ?, ?, ?, ?, ?)", schemaName));
                try {
                    st = prepareStatForInsOrUpdAddress(st, address, "ins with id");
                    st.executeUpdate();
                    // Добавляем Address в результирующий список
                    result.add(address);
                    // Записываем в LOG
                    LoggerApp.getInstance().getLog().info(String.format("    Add: %s", address));
                } catch (SQLException e) {
                    LoggerApp.getInstance().getLog().error(String.format("(addAddress) An error occurred while adding address with userid = %4d (Address already exists)", address.getUserId()));
                }
                st.close();
            } else {
                LoggerApp.getInstance().getLog().error(String.format("(addAddress) An error occurred while adding address with userid = %4d (UserId, Street, City, Country can not be null)", address.getUserId()));
            }
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(addAddress) An error occurred while adding user with userid = %4d", address.getUserId()), e);
        }
        return result;
    }

    @Override
    public List<Address> updateAddress(Address address) {
        List<Address> result = new ArrayList<>();
        // Получим предыдущее значение полей Address
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.addresses WHERE userid=%d", schemaName, address.getUserId()))) {
            result = getAddressesFromResultSet(rs);
            if (result.size() > 0) {
                // Записываем в LOG
                LoggerApp.getInstance().getLog().info(String.format("    Before Update: %s", result.get(0)));
            }
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(UpdateAddress) An error occurred while updating address with userid = %4d", address.getUserId()), e);
        }
        // Собственно изменяем
        if (result.size() > 0) {
            try (Connection connection = dataSource.getConnection()) {
                PreparedStatement st = connection.prepareStatement(String.format("UPDATE %s.addresses SET street=?, house=?, apartment=?, city=?, zipcode=?, country=? WHERE userid=?", schemaName));
                st = prepareStatForInsOrUpdAddress(st, address, "upd");
                // Изменяем address
                int recInsert = st.executeUpdate();
                if (recInsert > 0) {
                    // Записываем в LOG
                    LoggerApp.getInstance().getLog().info(String.format("   After Update: %s", address));
                }
                st.close();
            } catch (SQLException e) {
                LoggerApp.getInstance().getLog().error(String.format("(updateAddress) An error occurred while updating address with userid = %4d", address.getUserId()), e);
            }
        }
        return result;
    }

    @Override
    public List<Address> deleteAddress(Address address) {
        List<Address> result = new ArrayList<>();
        // Получим предыдущее значение полей Address
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.addresses WHERE userid=%d", schemaName, address.getUserId()))) {
            result = getAddressesFromResultSet(rs);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(deleteAddress) An error occurred while deleting address with userid = %4d", address.getUserId()), e);
        }
        // Собственно удаляем
        if (result.size() > 0) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement st = connection.prepareStatement(String.format("DELETE FROM %s.addresses WHERE userid=?", schemaName))) {
                st.setInt(1, address.getUserId());
                int recDelete = st.executeUpdate();
                if (recDelete > 0) {
                    result.add(address);
                    // Записываем в LOG
                    LoggerApp.getInstance().getLog().info(String.format("           Delete: %s", result.get(0)));
                }
            } catch (SQLException e) {
                LoggerApp.getInstance().getLog().error(String.format("(deleteAddress) An error occurred while deleting address with userid = %4d", address.getUserId()), e);
            }
        }
        return result;
    }

    @Override
    public List<Address> findByIdAddress(Address address) {
        List<Address> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.addresses WHERE userid=%d", schemaName, address.getUserId()))) {
            result = getAddressesFromResultSet(rs);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(findByIdAddress) An error occurred while finding address with userid = %4d", address.getUserId()), e);
        }
        if (result.size() > 0) {
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("Find By Id: %s", result.get(0)));
        }
        return result;
    }

    @Override
    public List<Address> findAllAddresses(Address address) {
        List<Address> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.addresses ORDER BY userid", schemaName))) {
            result = getAddressesFromResultSet(rs);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error("(findAllAddress) An error occurred while finding all addresses", e);
        }
        // Записываем в LOG
        LoggerApp.getInstance().getLog().info("  Find All Addresses.");
        return result;
    }

    /**
     * Вспомогательный метод для формирования запроса к базе данных
     * @param st запрос
     * @param address объект Address для sql-запроса
     * @attrib атрибут указывающий вид формируемого sql-запроса
     * @return видоизмененный запрос к базе данных
     */
    private PreparedStatement prepareStatForInsOrUpdAddress(PreparedStatement st, Address address, String attrib) {
        if (attrib.equals("ins with id")) {
            try {
                st.setInt(1, address.getUserId());
                st.setString(2, address.getStreet());
                st.setString(3, address.getHouse());
                st.setString(4, address.getApartment());
                st.setString(5, address.getCity());
                st.setString(6, address.getZipcode());
                st.setString(7, address.getCountry());

            } catch (SQLException e) {
                LoggerApp.getInstance().getLog().error("(prepareStatForInsOrUpdAddress) An error occurred while prepared statement", e);
            }
        } else {
            try {
                st.setString(1, address.getStreet());
                st.setString(2, address.getHouse());
                st.setString(3, address.getApartment());
                st.setString(4, address.getCity());
                st.setString(5, address.getZipcode());
                st.setString(6, address.getCountry());
                st.setInt(7, address.getUserId());
            } catch (SQLException e) {
                LoggerApp.getInstance().getLog().error("(prepareStatForInsOrUpdAddress) An error occurred while prepared statement", e);
            }
        }
        return st;
    }

    /**
     * Метод формирует список Address из объекта-результата запросов к базе данных ResultSet
     * @param resultSet объект-результата запросов к базе данных
     * @return список Address из объекта-результата запросов к базе данных ResultSet
     */
    public List<Address> getAddressesFromResultSet(ResultSet resultSet) {
        List<Address> result = new ArrayList<>();
        int id;
        String street;
        String house;
        String apartment;
        String city;
        String zipcode;
        String country;
        Address address;
        try {
            resultSet.beforeFirst();
            while (resultSet.next()) {
                id = resultSet.getInt(1);
                street = resultSet.getString(2);
                house = resultSet.getString(3);
                apartment = resultSet.getString(4);
                city = resultSet.getString(5);
                zipcode = resultSet.getString(6);
                country = resultSet.getString(7);
                address = new Address(id, street, house, apartment, city, zipcode, country);
                result.add(address);
            }
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error("(getAddressesFromResultSet) An error occurred while get addresses from ResultSet", e);
        }
        return result;
    }
}
