package com.solomatoff.mvc.model.repository.musictype.specificationimplements;

import com.solomatoff.mvc.model.repository.musictype.specificationimplements.MusicTypeSpecificationAllMusicTypes;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MusicTypeSpecificationAllMusicTypesTest {

    @Test
    public void toSqlClauses() {
        MusicTypeSpecificationAllMusicTypes musicTypeSpecificationAllMusicTypes = new MusicTypeSpecificationAllMusicTypes();
        assertThat(musicTypeSpecificationAllMusicTypes.toSqlClauses(), is("id = id"));
    }

    @Test
    public void testToString() {
        MusicTypeSpecificationAllMusicTypes musicTypeSpecificationAllMusicTypes = new MusicTypeSpecificationAllMusicTypes();
        assertThat(musicTypeSpecificationAllMusicTypes.toString(), is("MusicTypeSpecificationAllMusicTypes{}"));
    }
}