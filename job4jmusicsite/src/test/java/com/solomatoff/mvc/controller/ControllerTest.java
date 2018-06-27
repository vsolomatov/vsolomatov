package com.solomatoff.mvc.controller;

import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.model.ModelLogic;
import com.solomatoff.mvc.model.store.DbStore;
import com.solomatoff.mvc.model.store.MemoryStore;
import com.solomatoff.mvc.views.HtmlJspView;
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
        CommonTest.clearAndCreateDataInDataBase();
        CommonTest.clearAndCreateDataInMemory();
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
    public void getLogic() {
        ModelLogic modelLogic = CONTROLLER.getLogic();
        String result = modelLogic.getClass().toString();
        assertThat(result, is("class com.solomatoff.mvc.model.ModelLogic"));
    }

    @Test
    public void executeActionUser() {
        User user = new User();
        user.setId(3);
        MODEL_LOGIC.setPersistent(DbStore.getInstance());
        List<User> list = CONTROLLER.executeActionUser("Find By Id User", user);
        assertEquals(list.get(0).getId(), new Integer(3));
        assertEquals(list.get(0).getLogin(), "login3");
        assertEquals(list.get(0).getPassword(), "password");
    }

    @Test
    public void executeActionRole() {
        Role role = new Role(3, null, null);
        MODEL_LOGIC.setPersistent(DbStore.getInstance());
        List<Role> listRoles = CONTROLLER.executeActionRole("Find By Id Role", role);
        assertThat(listRoles.get(0).getName(), is("role3"));
    }

    @Test
    public void getTypeUser() {
        MODEL_LOGIC.setPersistent(DbStore.getInstance());
        String typeUser = CONTROLLER.getTypeUser("login3");
        assertThat(typeUser, is("any user"));

        typeUser = CONTROLLER.getTypeUser("login2");
        assertThat(typeUser, is("any user"));

        typeUser = CONTROLLER.getTypeUser("login1");
        assertThat(typeUser, is("admin"));

        typeUser = CONTROLLER.getTypeUser("login0");
        assertThat(typeUser, is("unknown user"));
    }
}