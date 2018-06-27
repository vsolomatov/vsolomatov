package com.solomatoff.mvc.model.repository.musictype;

import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.MusicType;
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

public class MusicTypeRepositoryDb implements MusicTypeRepository {
    private final DbStore dbStore = DbStore.getInstance();
    private final CommonDbStore commonDbStore = CommonDbStore.getInstance();
    private final BasicDataSource dataSource = commonDbStore.getDataSource();
    private final String schemaName = commonDbStore.getSchemaName();

    @Override
    public void addMusicType(MusicType musicType) {
        dbStore.addMusicType(musicType);
    }

    @Override
    public void removeMusicType(MusicType musicType) {
        dbStore.deleteMusicType(musicType);
    }

    @Override
    public void updateMusicType(MusicType musicType) {
        dbStore.updateMusicType(musicType);
    }

    @Override
    public List query(SqlSpecification sqlSpecification) {
        List<MusicType> result = new ArrayList<>();
        //System.out.println("(query) sqlSpecification.toSqlClauses() = " + sqlSpecification.toSqlClauses());
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.musictypes WHERE %s ORDER BY id", schemaName, sqlSpecification.toSqlClauses()))) {
            result = dbStore.getMusicTypeDbStore().getMusicTypesFromResultSet(rs);
            //System.out.println("(query) result = " + result);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(MusicType query) An error occurred while musicType query with clauses = %s", sqlSpecification.toSqlClauses()), e);
        }
        if (result.size() > 0) {
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("Query MusicType: %s", result));
        }
        return result;
    }
}
