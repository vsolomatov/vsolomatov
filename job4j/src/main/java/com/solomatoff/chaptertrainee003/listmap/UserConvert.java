package com.solomatoff.chaptertrainee003.listmap;

import java.util.List;
import java.util.HashMap;

public class UserConvert {
    /**
     * Метод принимает в себя список пользователей и конвертирует его в Map с ключом Integer id
     * и соответствующим ему User.
     * @param list список пользователей
     * @return Ссылку на объект HashMap, содержащий список ключей и пользователей
     */
    HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> aHM = new HashMap<>();
        int i = 0;
        for (User useritr : list) {
            aHM.put(i, useritr);
            i++;
        }
        return aHM;
    }
}
