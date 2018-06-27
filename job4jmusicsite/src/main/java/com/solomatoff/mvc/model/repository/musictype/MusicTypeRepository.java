package com.solomatoff.mvc.model.repository.musictype;


import com.solomatoff.mvc.entities.MusicType;
import com.solomatoff.mvc.model.repository.SqlSpecification;

import java.util.List;

public interface MusicTypeRepository {
    void addMusicType(MusicType musicType);
    void removeMusicType(MusicType musicType);
    void updateMusicType(MusicType musicType); // Think it as replace for set

    List query(SqlSpecification sqlSpecification);
}
