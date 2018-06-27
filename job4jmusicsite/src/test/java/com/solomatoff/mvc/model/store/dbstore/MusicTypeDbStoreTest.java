package com.solomatoff.mvc.model.store.dbstore;

import com.solomatoff.mvc.controller.CommonTest;
import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.entities.MusicType;
import com.solomatoff.mvc.entities.UsersMusicTypes;
import com.solomatoff.mvc.model.PersistentDAO;
import com.solomatoff.mvc.model.store.DbStore;
import com.solomatoff.mvc.model.store.MemoryStore;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MusicTypeDbStoreTest {
    private static final PersistentDAO STORE = DbStore.getInstance();

    @Before
    public void setUp() {
        CommonTest.clearAndCreateDataInDataBase();
    }

    @Test
    public void addMusicType() {
        MusicType musicType4 = new MusicType(4, "NEW TYPE MUSIC");
        List<MusicType> expected = new ArrayList<>();
        expected.add(musicType4);

        // Добавляем первый раз MusicType4
        List<MusicType> result = STORE.addMusicType(musicType4);
        assertThat(result, is(expected));

        // Добавляем второй раз MusicType4, теперь список должен быть пустой, т.к. MusicType второй раз не добавится
        result = STORE.addMusicType(musicType4);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateMusicType() {
        MusicType musicType = new MusicType();
        musicType.setId(3);
        List<MusicType> expected = STORE.findByIdMusicType(musicType);

        // Обновим MusicType с id=3
        MusicType newMusicType3 = new MusicType(3, "newmusictype3");
        List<MusicType> result = STORE.updateMusicType(newMusicType3);
        assertThat(result.get(0).getMusicTypeName(), is(expected.get(0).getMusicTypeName()));
    }

    @Test
    public void deleteMusicType() {
        MusicType musicType = new MusicType();
        musicType.setId(3);
        List<MusicType> expected = STORE.findByIdMusicType(musicType);

        // Удалим вначале ссылающуюся запись
        STORE.deleteUsersMusicTypes(new UsersMusicTypes(3, 3));
        // Удалим MusicType с id = 3
        List<MusicType> result = STORE.deleteMusicType(musicType);
        assertThat(result.get(0).getMusicTypeName(), is(expected.get(0).getMusicTypeName()));

        // Попытаемся удалить не существующий MusicType
        musicType = new MusicType();
        musicType.setId(8);
        result = STORE.deleteMusicType(musicType);
        assertThat(result.size(), is(0));
    }

    @Test
    public void findByIdMusicType() {
        MusicType musicType1 = new MusicType(1, "musicType1");
        MusicType result = STORE.findByIdMusicType(musicType1).get(0);
        assertThat(result.getMusicTypeName(), is(musicType1.getMusicTypeName()));
    }

    @Test
    public void findAllMusicTypes() {
        List<MusicType> expected = new ArrayList<>();
        MusicType musicType = new MusicType();
        musicType.setId(1);
        MusicType musicType1 = STORE.findByIdMusicType(musicType).get(0);
        expected.add(musicType1);
        musicType = new MusicType();
        musicType.setId(2);
        MusicType musicType2 = STORE.findByIdMusicType(musicType).get(0);
        expected.add(musicType2);
        musicType = new MusicType();
        musicType.setId(3);
        MusicType musicType3 = STORE.findByIdMusicType(musicType).get(0);
        expected.add(musicType3);
        List<MusicType> result = STORE.findAllMusicTypes(musicType);
        int i = 0;
        for (MusicType musicTypeloop : result) {
            assertThat(musicTypeloop.getMusicTypeName(), is(expected.get(i).getMusicTypeName()));
            i++;
        }
    }
}