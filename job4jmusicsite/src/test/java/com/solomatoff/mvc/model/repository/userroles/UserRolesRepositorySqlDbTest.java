package com.solomatoff.mvc.model.repository.userroles;

import com.solomatoff.mvc.controller.CommonTest;
import com.solomatoff.mvc.entities.UserRoles;
import com.solomatoff.mvc.model.PersistentDAO;
import com.solomatoff.mvc.model.repository.SqlSpecification;
import com.solomatoff.mvc.model.repository.userroles.UserRolesRepository;
import com.solomatoff.mvc.model.repository.userroles.UserRolesRepositoryDb;
import com.solomatoff.mvc.model.repository.userroles.specificationimplements.UserRolesSpecificationAllUserRoles;
import com.solomatoff.mvc.model.repository.userroles.specificationimplements.UserRolesSpecificationByIdId;
import com.solomatoff.mvc.model.store.DbStore;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserRolesRepositorySqlDbTest {
    private final UserRolesRepository userRolesRepository = new UserRolesRepositoryDb();
    private final static PersistentDAO DB_STORE = DbStore.getInstance();

    @Before
    public void setUp() {
        CommonTest.clearAndCreateDataInDataBase();
    }

    private List<UserRoles> makeTestUserRolesAll() {
        return DB_STORE.findAllUserRoles(new UserRoles());
    }

    @Test
    public void addUserRoles() {
        UserRoles userRoles = new UserRoles();
        userRoles.setUserId(3);
        userRoles.setRoleId(1);
        userRolesRepository.addUserRoles(userRoles);

        SqlSpecification specification = new UserRolesSpecificationByIdId(3, 1);
        List result = userRolesRepository.query(specification);
        UserRoles userRolesResult = (UserRoles) result.get(0);
        assertThat(userRolesResult.getRoleId(), is(1));
    }

    @Test
    public void removeUserRoles() {
        UserRoles userRoles = new UserRoles();
        userRoles.setUserId(3);
        userRoles.setRoleId(3);

        userRolesRepository.removeUserRoles(userRoles);

        SqlSpecification specification = new UserRolesSpecificationByIdId(3, 3);
        List result = userRolesRepository.query(specification);
        //System.out.println("result = " + result);

        assertEquals(result.size(), 0);
    }

    @Test
    public void updateUserRoles() {
        SqlSpecification specification = new UserRolesSpecificationByIdId(3, 3);
        List userRoleslist = userRolesRepository.query(specification);
        UserRoles userRoles = (UserRoles) userRoleslist.get(0);
        userRoles.setUserId(3);
        userRoles.setRoleId(3);
        userRolesRepository.updateUserRoles(userRoles);

        List result = userRolesRepository.query(specification);
        UserRoles userRolesResult = (UserRoles) result.get(0);

        assertThat(userRolesResult.getRoleId(), is(3));
    }

    @Test
    public void query() {
        SqlSpecification specification = new UserRolesSpecificationByIdId(1, 1);
        List result = userRolesRepository.query(specification);
        //System.out.println("result = " + result);
        UserRoles userRolesResult = (UserRoles) result.get(0);
        assertThat(userRolesResult.getRoleId(), is(1));

        List<UserRoles> expected = makeTestUserRolesAll();
        //System.out.println("expected = " + expected);
        specification = new UserRolesSpecificationAllUserRoles();
        result = userRolesRepository.query(specification);
        assertThat(result, is(expected));
    }
}