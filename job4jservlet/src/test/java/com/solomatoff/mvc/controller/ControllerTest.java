package com.solomatoff.mvc.controller;

import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.model.DbStore;
import com.solomatoff.mvc.model.MemoryStore;
import com.solomatoff.mvc.model.ModelLogic;
import com.solomatoff.mvc.model.ModelStore;
import com.solomatoff.mvc.views.HtmlJspView;
import com.solomatoff.mvc.views.HtmlView;
import com.solomatoff.mvc.views.ViewLyaer;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class ControllerTest {
    private static final Controller CONTROLLER = Controller.getInstance();
    private static final ModelLogic MODEL_LOGIC = CONTROLLER.getLogic();

    @Before
    public void setUp() {
        MODEL_LOGIC.setPersistent(new DbStore());

        // Удаляем всех пользователей
        MODEL_LOGIC.deleteUserAll(new User());
        // Удаляем все роли
        MODEL_LOGIC.deleteRoleAll(new Role());

        // Добавляем новые три роли
        MODEL_LOGIC.addRole(new Role(1, "role1", true));
        MODEL_LOGIC.addRole(new Role(2, "role2", false));
        MODEL_LOGIC.addRole(new Role(3, "role3", false));

        // Добавляем новых трех пользователей
        MODEL_LOGIC.addUser(new User(1, "user1", "login1", "password", "email1", new Timestamp(System.currentTimeMillis()), 1));
        MODEL_LOGIC.addUser(new User(2, "user2", "login2", "password", "email2", new Timestamp(System.currentTimeMillis()), 2));
        MODEL_LOGIC.addUser(new User(3, "user3", "login3", "password", "email3", new Timestamp(System.currentTimeMillis()), 3));
    }

    @Test
    public void getInstance() {
        Controller controller1 = Controller.getInstance();
        Controller controller2 = Controller.getInstance();
        assertThat(controller1, is(controller2));
    }

    @Test
    public void getPresentation() {
        ViewLyaer viewLyaer1 = new HtmlJspView();
        CONTROLLER.setPresentation(viewLyaer1);
        ViewLyaer viewLyaer = CONTROLLER.getPresentation();
        assertThat(viewLyaer, is(viewLyaer1));
    }

    @Test
    public void setPresentation() {
        ViewLyaer viewLyaer1 = new HtmlJspView();
        ViewLyaer viewLyaer2 = new HtmlView();
        CONTROLLER.setPresentation(viewLyaer1);
        ViewLyaer viewLyaer = CONTROLLER.getPresentation();
        assertThat(viewLyaer, is(viewLyaer1));

        CONTROLLER.setPresentation(viewLyaer2);
        viewLyaer = CONTROLLER.getPresentation();
        assertThat(viewLyaer, is(viewLyaer2));
    }

    @Test
    public void getLogic() {
        ModelLogic modelLogic = CONTROLLER.getLogic();
        String result = modelLogic.getClass().toString();
        assertThat(result, is("class com.solomatoff.mvc.model.ModelLogic"));
    }

    @Test
    public void executeActionUser() {
        User user = new User();
        user.setId(3);
        List<User> list = CONTROLLER.executeActionUser("Find By Id User", user);
        assertEquals(list.get(0).getId(), new Integer(3));
        assertEquals(list.get(0).getLogin(), "login3");
        assertEquals(list.get(0).getPassword(), "password");
    }

    @Test
    public void executeActionRole() {
        Role role = new Role(3, null, null);
        List<Role> listRoles = CONTROLLER.executeActionRole("Find By Id Role", role);
        assertThat(listRoles.get(0).getName(), is("role3"));
    }

    @Test
    public void getTypeUser() {
        String typeUser = CONTROLLER.getTypeUser("login2");
        assertThat(typeUser, is("any user"));

        typeUser = CONTROLLER.getTypeUser("login1");
        assertThat(typeUser, is("admin"));

        typeUser = CONTROLLER.getTypeUser("login0");
        assertThat(typeUser, is(nullValue()));
    }
}