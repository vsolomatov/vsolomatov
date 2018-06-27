package com.solomatoff.mvc.model.store.dbstore;

import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.entities.User;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommonDbStore {
    private static CommonDbStore singletonInstance = new CommonDbStore();
    public static CommonDbStore getInstance() {
        if (dataSource == null) {
            initDataSource();
        }
        return singletonInstance;
    }

    private static BasicDataSource dataSource = null;
    public BasicDataSource getDataSource() {
        return dataSource;
    }

    private static final String SCHEMA_NAME = "solomatov";
    public String getSchemaName() {
        return SCHEMA_NAME;
    }

    public CommonDbStore() {
    }

    private static void initDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost:5432/db_solomatov");
        ds.setUsername("solomatov");
        ds.setPassword("123");
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
        dataSource = ds;
        // Если таблицы не существуют, то создать их и заполнить
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement()) {
            // Table musictypes
            st.execute(String.format("CREATE TABLE IF NOT EXISTS %s.musictypes(id serial PRIMARY KEY, musictypename character varying)", SCHEMA_NAME));
            st.execute(String.format("GRANT ALL ON TABLE %s.musictypes TO public", SCHEMA_NAME));
            // Table roles
            st.execute(String.format("CREATE TABLE IF NOT EXISTS %s.roles(id serial PRIMARY KEY, name character varying, is_admin boolean NOT NULL)", SCHEMA_NAME));
            st.execute(String.format("GRANT ALL ON TABLE %s.roles TO public", SCHEMA_NAME));
            // Table users
            st.execute(String.format("CREATE TABLE IF NOT EXISTS %s.users(id serial PRIMARY KEY, name character varying, login character varying NOT NULL, password character varying NOT NULL, email character varying, createDate timestamp without time zone)", SCHEMA_NAME));
            st.execute(String.format("GRANT ALL ON TABLE %s.users TO public", SCHEMA_NAME));
            // Table addresses
            st.execute(String.format("CREATE TABLE IF NOT EXISTS %s.addresses(userid integer PRIMARY KEY REFERENCES %s.users, street character varying NOT NULL, house character varying, apartment character varying, city character varying NOT NULL, zipcode character varying, country character varying NOT NULL)", SCHEMA_NAME, SCHEMA_NAME));
            st.execute(String.format("GRANT ALL ON TABLE %s.addresses TO public", SCHEMA_NAME));
            // Table userroles
            st.execute(String.format("CREATE TABLE IF NOT EXISTS %s.userroles(userid integer REFERENCES %s.users, roleid integer REFERENCES %s.roles, CONSTRAINT userroles_pkey PRIMARY KEY (userid, roleid))", SCHEMA_NAME, SCHEMA_NAME, SCHEMA_NAME));
            st.execute(String.format("GRANT ALL ON TABLE %s.userroles TO public", SCHEMA_NAME));
            // Table usersmusictypes
            st.execute(String.format("CREATE TABLE IF NOT EXISTS %s.usersmusictypes(userid integer REFERENCES %s.users, musictypeid integer REFERENCES %s.musictypes, CONSTRAINT usersmusictypes_pkey PRIMARY KEY (userid, musictypeid))", SCHEMA_NAME, SCHEMA_NAME, SCHEMA_NAME));
            st.execute(String.format("GRANT ALL ON TABLE %s.usersmusictypes TO public", SCHEMA_NAME));
            // Filling in
            try {
                st.executeUpdate(String.format("INSERT INTO %s.roles VALUES (0, 'Administrator', true)", SCHEMA_NAME));
            } catch (SQLException e) {
                LoggerApp.getInstance().getLog().info("(CommonDbStore) Role Administrator already exists");
            }
            try {
                st.executeUpdate(String.format("INSERT INTO %s.users VALUES (0, 'Solomatov Vyacheslav', 'root', 'root', 'solomatoff.vyacheslav@yandex.ru', now())", SCHEMA_NAME));
            } catch (SQLException e) {
                LoggerApp.getInstance().getLog().info("(CommonDbStore) User root already exists");
            }
            try {
                st.executeUpdate(String.format("INSERT INTO %s.userroles VALUES (0, 0)", SCHEMA_NAME));
            } catch (SQLException e) {
                LoggerApp.getInstance().getLog().info("(CommonDbStore) Role Administrator for root already exists");
            }
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error("(CommonDbStore) Сan not create tables", e);
            System.exit(1);
        }
    }
}
