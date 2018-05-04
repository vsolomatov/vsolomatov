package com.solomatoff.chaptertrainee003.listmap;

import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {
    @Test
    public void whenUserConvert() {
        UserConvert aUC = new UserConvert();

        List<User> aList = new ArrayList<>();
        aList.add(new User("User1", "Ivan Ivanov", "Moscow"));
        aList.add(new User("User2", "Vyacheslav Solomatov", "Ekaterinburg"));
        aList.add(new User("User3", "Iliya Yashin", "Bransk"));

        HashMap<Integer, User> resultHashMap;
        resultHashMap = aUC.process(aList);

        assertThat(resultHashMap.get(0).getId(), is("User1"));
        assertThat(resultHashMap.get(1).getId(), is("User2"));
        assertThat(resultHashMap.get(2).getId(), is("User3"));
    }
}
