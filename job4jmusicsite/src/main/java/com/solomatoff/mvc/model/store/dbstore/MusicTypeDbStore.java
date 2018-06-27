package com.solomatoff.mvc.model.store.dbstore;

import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.MusicType;
import com.solomatoff.mvc.model.dao.MusicTypeDAO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicTypeDbStore extends CommonDbStore implements MusicTypeDAO {
    private final CommonDbStore commonDbStore = CommonDbStore.getInstance();
    private final BasicDataSource dataSource = commonDbStore.getDataSource();
    private final String schemaName = commonDbStore.getSchemaName();
    
    /**
     * Метод добавляет musicType в таблицу musicTypes
     * @param musicType добавляемая MusicType
     * @return список List<MusicType>, состоящий из добавленной musicType
     */
    @Override
    public List<MusicType> addMusicType(MusicType musicType) {
        List<MusicType> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement st;
            if (musicType.getId() != null) {
                st = connection.prepareStatement(String.format("INSERT INTO %s.musictypes (id, musictypename) VALUES (?, ?)", schemaName));
                try {
                    st.setInt(1, musicType.getId());
                    st.setString(2, musicType.getMusicTypeName());
                    st.executeUpdate();
                    // Добавляем MusicType в результирующий список
                    result.add(musicType);
                    // Записываем в LOG
                    LoggerApp.getInstance().getLog().info(String.format("    Add: %s", musicType));
                } catch (SQLException e) {
                    LoggerApp.getInstance().getLog().error(String.format("(ADD MusicType) An error occurred while adding musicType with userid = %4d (MusicType already exists)", musicType.getId()));
                }
                st.close();
            } else {
                LoggerApp.getInstance().getLog().error(String.format("(ADD MusicType) An error occurred while adding musicType with userid = %4d (Id can not be null)", musicType.getId()));
            }
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(ADD) An error occurred while adding user with userid = %4d", musicType.getId()), e);
        }
        return result;
    }

    @Override
    public List<MusicType> updateMusicType(MusicType musicType) {
        List<MusicType> result = new ArrayList<>();
        // Получим предыдущее значение полей роли
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.musictypes WHERE id=%d", schemaName, musicType.getId()))) {
            result = getMusicTypesFromResultSet(rs);
            if (result.size() > 0) {
                // Записываем в LOG
                LoggerApp.getInstance().getLog().info(String.format("    Before Update: %s", result.get(0)));
            }
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(UpdateMusicType) An error occurred while updating musicType with id = %4d", musicType.getId()), e);
        }
        // Собственно изменяем
        if (result.size() > 0) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement st = connection.prepareStatement(String.format("UPDATE %s.musicTypes SET musictypename=?  WHERE id=?", schemaName))) {
                st.setString(1, musicType.getMusicTypeName());
                st.setInt(2, musicType.getId());
                // Изменяем MusicType
                int recInsert = st.executeUpdate();
                if (recInsert > 0) {
                    // Записываем в LOG
                    LoggerApp.getInstance().getLog().info(String.format("   After Update: %s", musicType));
                }
            } catch (SQLException e) {
                LoggerApp.getInstance().getLog().error(String.format("(UPDATE MusicType) An error occurred while updating musicType with id = %4d", musicType.getId()), e);
            }
        }
        return result;
    }

    @Override
    public List<MusicType> deleteMusicType(MusicType musicType) {
        List<MusicType> result = new ArrayList<>();
        // Получим предыдущее значение полей роли
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.musictypes WHERE id=%d", schemaName, musicType.getId()))) {
            result = getMusicTypesFromResultSet(rs);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(DeleteMusicType) An error occurred while deleting musicType with id = %4d", musicType.getId()), e);
        }
        // Собственно удаляем
        if (result.size() > 0) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement st = connection.prepareStatement(String.format("DELETE FROM %s.musictypes WHERE id=?", schemaName))) {
                st.setInt(1, musicType.getId());
                int recDelete = st.executeUpdate();
                if (recDelete > 0) {
                    result.add(musicType);
                    // Записываем в LOG
                    LoggerApp.getInstance().getLog().info(String.format("           Delete: %s", result.get(0)));
                }
            } catch (SQLException e) {
                LoggerApp.getInstance().getLog().error(String.format("(DELETE MusicType) An error occurred while deleting musicType with id = %4d", musicType.getId()), e);
            }
        }
        return result;
    }

    @Override
    public List<MusicType> findByIdMusicType(MusicType musicType) {
        List<MusicType> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.musictypes WHERE id=%d", schemaName, musicType.getId()))) {
            result = getMusicTypesFromResultSet(rs);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error(String.format("(findByIdMusicType) An error occurred while finding musicType with id = %4d", musicType.getId()), e);
        }
        if (result.size() > 0) {
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("Find By Id: %s", result.get(0)));
        }
        return result;
    }

    @Override
    public List<MusicType> findAllMusicTypes(MusicType musicType) {
        List<MusicType> result = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = st.executeQuery(String.format("SELECT * FROM %s.musictypes ORDER BY id", schemaName))) {
            result = getMusicTypesFromResultSet(rs);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error("(findAllMusicType) An error occurred while finding all musicTypes", e);
        }
        // Записываем в LOG
        LoggerApp.getInstance().getLog().info("  Find All MusicTypes.");
        return result;
    }

    /**
     * Метод формирует список ролей из объекта-результата запросов к базе данных ResultSet
     * @param resultSet объект-результата запросов к базе данных
     * @return список ролей из объекта-результата запросов к базе данных ResultSet
     */
    public List<MusicType> getMusicTypesFromResultSet(ResultSet resultSet) {
        List<MusicType> result = new ArrayList<>();
        int id;
        String name;
        MusicType musicType;
        try {
            resultSet.beforeFirst();
            while (resultSet.next()) {
                id = resultSet.getInt(1);
                name = resultSet.getString(2);
                musicType = new MusicType(id, name);
                result.add(musicType);
            }
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error("(getMusicTypesFromResultSet) An error occurred while get musicTypes from ResultSet", e);
        }
        return result;
    }

    /**
     * Метод для изменения последовательности для первичного ключа таблицы musicTypes
     * @param restartWith начальный элемент последовательности
     */
    private void alterSequenceMusicTypesIdSeq(int restartWith) {
        String stringAlter = String.format("ALTER SEQUENCE %s.musictypes_id_seq RESTART WITH %s", schemaName, restartWith);
        try (Connection connection = dataSource.getConnection();
             Statement st = connection.createStatement()) {
            st.executeUpdate(stringAlter);
        } catch (SQLException e) {
            LoggerApp.getInstance().getLog().error("(alterSequenceMusicTypesIdSeq) An error occurred while alter sequence", e);
        }
    }
}
