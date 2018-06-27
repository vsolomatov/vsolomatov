package com.solomatoff.mvc.model.repository.user;

import com.solomatoff.mvc.controller.CommonTest;
import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.model.PersistentDAO;
import com.solomatoff.mvc.model.repository.SqlSpecification;
import com.solomatoff.mvc.model.repository.user.specificationimplements.UserSpecificationAllUsers;
import com.solomatoff.mvc.model.repository.user.specificationimplements.UserSpecificationByIdRange;
import com.solomatoff.mvc.model.repository.user.specificationimplements.UserSpecificationByUserLogin;
import com.solomatoff.mvc.model.store.DbStore;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserRepositorySqlDbTest {
    private final UserRepository userRepository = new UserRepositoryDb();
    private final static PersistentDAO DB_STORE = DbStore.getInstance();

    @Before
    public void setUp() {
        CommonTest.clearAndCreateDataInDataBase();
    }

    private List<User> makeTestUserAll() {
        return DB_STORE.findAllUsers(new User());
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setId(4);
        user.setName("user4");
        user.setLogin("login4");
        user.setPassword("password4");
        userRepository.addUser(user);

        SqlSpecification specification = new UserSpecificationByIdRange(4, 4);
        List result = userRepository.query(specification);
        User userResult = (User) result.get(0);
        assertThat(userResult.getName(), is("user4"));
    }

    @Test
    public void removeUser() {
        // добавим User без ссылок на него
        User user = new User();
        user.setId(4);
        user.setName("user4");
        user.setLogin("login4");
        user.setPassword("password4");
        userRepository.addUser(user);

        userRepository.removeUser(user);

        SqlSpecification specification = new UserSpecificationByIdRange(4, 4);
        List result = userRepository.query(specification);
        System.out.println("result = " + result);

        assertEquals(result.size(), 0);
    }

    @Test
    public void updateUser() {
        SqlSpecification specification = new UserSpecificationByIdRange(3, 3);
        List userlist = userRepository.query(specification);
        User user = (User) userlist.get(0);
        user.setName("newname3");
        userRepository.updateUser(user);

        List result = userRepository.query(specification);
        User userResult = (User) result.get(0);

        assertEquals(userResult.getName(), "newname3");
    }

    @Test
    public void query() {
        SqlSpecification specification = new UserSpecificationByIdRange(1, 1);
        List result = userRepository.query(specification);
        User userResult = (User) result.get(0);
        assertThat(userResult.getName(), is("user1"));

        specification = new UserSpecificationByUserLogin("login2");
        result = userRepository.query(specification);
        userResult = (User) result.get(0);
        assertThat(userResult.getName(), is("user2"));

        List<User> expected = makeTestUserAll();
        specification = new UserSpecificationAllUsers();
        result = userRepository.query(specification);
        assertThat(result, is(expected));
    }
}