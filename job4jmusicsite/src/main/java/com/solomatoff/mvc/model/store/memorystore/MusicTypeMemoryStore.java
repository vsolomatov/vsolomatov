package com.solomatoff.mvc.model.store.memorystore;

import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.MusicType;
import com.solomatoff.mvc.model.dao.MusicTypeDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class MusicTypeMemoryStore implements MusicTypeDAO {
    /**
     * Contains MusicTypes
     */
    private static final Map<Integer, MusicType> MUSIC_TYPE_MAP = new ConcurrentSkipListMap<>();

    @Override
    public List<MusicType> addMusicType(MusicType musicType) {
        List<MusicType> result = new ArrayList<>();
        MusicType musicTypeResult = MUSIC_TYPE_MAP.get(musicType.getId());
        // Добавляем только, если еще не существует
        if (musicTypeResult == null) {
            MUSIC_TYPE_MAP.put(musicType.getId(), musicType);
            result.add(musicType); // добавляем новый MusicType в результирующий список
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Add in Memory: %s", musicType));
        }
        return result;
    }

    @Override
    public List<MusicType> updateMusicType(MusicType musicType) {
        List<MusicType> result = new ArrayList<>();
        MusicType musicTypeResult = MUSIC_TYPE_MAP.put(musicType.getId(), musicType);
        if (musicTypeResult != null) {
            result.add(musicTypeResult);
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Before Update in Memory: %s", musicTypeResult));
            LoggerApp.getInstance().getLog().info(String.format("    After Update in Memory: %s", musicType));
        }
        return result;
    }

    @Override
    public List<MusicType> deleteMusicType(MusicType musicType) {
        List<MusicType> result = new ArrayList<>();
        MusicType musicTypeResult = MUSIC_TYPE_MAP.remove(musicType.getId());
        if (musicTypeResult != null) {
            result.add(musicTypeResult);
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Delete from Memory: %s", musicTypeResult));
        }
        return result;
    }

    @Override
    public List<MusicType> findByIdMusicType(MusicType musicType) {
        List<MusicType> result = new ArrayList<>();
        MusicType musicTypeResult = MUSIC_TYPE_MAP.get(musicType.getId());
        if (musicTypeResult != null) {
            result.add(musicTypeResult);
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Find By Id in Memory: %s", musicTypeResult));
        }
        return result;
    }

    @Override
    public List<MusicType> findAllMusicTypes(MusicType musicType) {
        List<MusicType> result = new ArrayList<>(MUSIC_TYPE_MAP.values());
        // Записываем в LOG
        LoggerApp.getInstance().getLog().info("  Find All MusicTypes in Memory.");
        return result;
    }
}
