package com.solomatoff.mvc.model.dao;

import com.solomatoff.mvc.entities.MusicType;

import java.util.ArrayList;
import java.util.List;

public interface MusicTypeDAO {
    List<MusicType> addMusicType(MusicType musicType);
    List<MusicType> updateMusicType(MusicType musicType);
    List<MusicType> deleteMusicType(MusicType musicType);
    List<MusicType> findByIdMusicType(MusicType musicType);
    List<MusicType> findAllMusicTypes(MusicType musicType);
}