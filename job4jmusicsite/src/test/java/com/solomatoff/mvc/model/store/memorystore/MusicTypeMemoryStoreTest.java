package com.solomatoff.mvc.model.store.memorystore;

import com.solomatoff.mvc.controller.CommonTest;
import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.entities.MusicType;
import com.solomatoff.mvc.model.PersistentDAO;
import com.solomatoff.mvc.model.store.MemoryStore;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class MusicTypeMemoryStoreTest {
    private static final PersistentDAO STORE = MemoryStore.getInstance();

    @Before
    public void setUp() {
        CommonTest.clearAndCreateDataInMemory();
    }

    @Test
    public void addMusicType() {
        MusicType musicType7 = new MusicType(7, "NEW TYPE MUSIC");
        List<MusicType> expected = new ArrayList<>();
        expected.add(musicType7);

        // Добавляем первый раз MusicType7
        List<MusicType> result = STORE.addMusicType(musicType7);
        assertThat(result, is(expected));

        // Добавляем второй раз MusicType7, теперь список должен быть пустой, т.к. MusicType второй раз не добавится
        result = STORE.addMusicType(musicType7);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateMusicType() {
        MusicType musicType = new MusicType();
        musicType.setId(6);
        List<MusicType> expected = STORE.findByIdMusicType(musicType);

        // Обновим MusicType с id=6
        MusicType newMusicType6 = new MusicType(6, "newmusictype6");
        List<MusicType> result = STORE.updateMusicType(newMusicType6);
        assertThat(result.get(0).getMusicTypeName(), is(expected.get(0).getMusicTypeName()));
    }

    @Test
    public void deleteMusicType() {
        MusicType musicType = new MusicType();
        musicType.setId(6);
        List<MusicType> expected = STORE.findByIdMusicType(musicType);

        // Удалим MusicType с id = 6
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
        MusicType musicType4 = new MusicType(4, "musicType4");
        MusicType result = STORE.findByIdMusicType(musicType4).get(0);
        assertThat(result.getMusicTypeName(), is(musicType4.getMusicTypeName()));
    }

    @Test
    public void findAllMusicTypes() {
        List<MusicType> expected = new ArrayList<>();
        MusicType musicType = new MusicType();
        musicType.setId(4);
        MusicType musicType4 = STORE.findByIdMusicType(musicType).get(0);
        expected.add(musicType4);
        musicType = new MusicType();
        musicType.setId(5);
        MusicType musicType5 = STORE.findByIdMusicType(musicType).get(0);
        expected.add(musicType5);
        musicType = new MusicType();
        musicType.setId(6);
        MusicType musicType6 = STORE.findByIdMusicType(musicType).get(0);
        expected.add(musicType6);
        List<MusicType> result = STORE.findAllMusicTypes(musicType);
        int i = 0;
        for (MusicType musicTypeloop : result) {
            assertThat(musicTypeloop.getMusicTypeName(), is(expected.get(i).getMusicTypeName()));
            i++;
        }
    }
}