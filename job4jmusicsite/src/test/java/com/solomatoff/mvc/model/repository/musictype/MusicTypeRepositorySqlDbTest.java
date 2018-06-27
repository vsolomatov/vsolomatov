package com.solomatoff.mvc.model.repository.musictype;

import com.solomatoff.mvc.controller.CommonTest;
import com.solomatoff.mvc.entities.MusicType;
import com.solomatoff.mvc.model.PersistentDAO;
import com.solomatoff.mvc.model.repository.SqlSpecification;
import com.solomatoff.mvc.model.repository.musictype.specificationimplements.MusicTypeSpecificationAllMusicTypes;
import com.solomatoff.mvc.model.repository.musictype.specificationimplements.MusicTypeSpecificationByIdRange;
import com.solomatoff.mvc.model.store.DbStore;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MusicTypeRepositorySqlDbTest {
    private final MusicTypeRepository musicTypeRepository = new MusicTypeRepositoryDb();
    private final static PersistentDAO DB_STORE = DbStore.getInstance();

    @Before
    public void setUp() {
        CommonTest.clearAndCreateDataInDataBase();
    }

    private List<MusicType> makeTestMusicTypeAll() {
        return DB_STORE.findAllMusicTypes(new MusicType());
    }

    @Test
    public void addMusicType() {
        MusicType musicType = new MusicType();
        musicType.setId(4);
        musicType.setMusicTypeName("musicType4");
        musicTypeRepository.addMusicType(musicType);

        SqlSpecification specification = new MusicTypeSpecificationByIdRange(4, 4);
        List result = musicTypeRepository.query(specification);
        MusicType musicTypeResult = (MusicType) result.get(0);
        assertThat(musicTypeResult.getMusicTypeName(), is("musicType4"));
    }

    @Test
    public void removeMusicType() {
        // добавим MusicType без ссылок на него
        MusicType musicType = new MusicType();
        musicType.setId(4);
        musicType.setMusicTypeName("musicType4");
        musicTypeRepository.addMusicType(musicType);

        musicTypeRepository.removeMusicType(musicType);

        SqlSpecification specification = new MusicTypeSpecificationByIdRange(4, 4);
        List result = musicTypeRepository.query(specification);
        System.out.println("result = " + result);

        assertEquals(result.size(), 0);
    }

    @Test
    public void updateMusicType() {
        SqlSpecification specification = new MusicTypeSpecificationByIdRange(3, 3);
        List musicTypelist = musicTypeRepository.query(specification);
        MusicType musicType = (MusicType) musicTypelist.get(0);
        musicType.setMusicTypeName("newname3");
        musicTypeRepository.updateMusicType(musicType);

        List result = musicTypeRepository.query(specification);
        MusicType musicTypeResult = (MusicType) result.get(0);

        assertEquals(musicTypeResult.getMusicTypeName(), "newname3");
    }

    @Test
    public void query() {
        SqlSpecification specification = new MusicTypeSpecificationByIdRange(1, 1);
        List result = musicTypeRepository.query(specification);
        MusicType musicTypeResult = (MusicType) result.get(0);
        assertThat(musicTypeResult.getMusicTypeName(), is("musicType1"));

        List<MusicType> expected = makeTestMusicTypeAll();
        specification = new MusicTypeSpecificationAllMusicTypes();
        result = musicTypeRepository.query(specification);
        assertThat(result, is(expected));
    }
}