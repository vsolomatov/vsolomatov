package com.solomatoff.mvc.model.repository.musictype.specificationimplements;

import com.solomatoff.mvc.model.repository.SqlSpecification;

public class MusicTypeSpecificationAllMusicTypes implements SqlSpecification {

    public MusicTypeSpecificationAllMusicTypes() {
    }

    @Override
    public String toSqlClauses() {
        return "id = id";
    }

    @Override
    public String toString() {
        return "MusicTypeSpecificationAllMusicTypes{}";
    }
}
