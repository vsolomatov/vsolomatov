package com.solomatoff.mvc.model.repository.role;

import com.solomatoff.mvc.controller.CommonTest;
import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.model.PersistentDAO;
import com.solomatoff.mvc.model.repository.SqlSpecification;
import com.solomatoff.mvc.model.repository.role.RoleRepository;
import com.solomatoff.mvc.model.repository.role.RoleRepositoryDb;
import com.solomatoff.mvc.model.repository.role.specificationimplements.RoleSpecificationAllRoles;
import com.solomatoff.mvc.model.repository.role.specificationimplements.RoleSpecificationByIdRange;
import com.solomatoff.mvc.model.store.DbStore;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RoleRepositorySqlDbTest {
    private final RoleRepository roleRepository = new RoleRepositoryDb();
    private final static PersistentDAO DB_STORE = DbStore.getInstance();

    @Before
    public void setUp() {
        CommonTest.clearAndCreateDataInDataBase();
    }

    private List<Role> makeTestRoleAll() {
        return DB_STORE.findAllRoles(new Role());
    }

    @Test
    public void addRole() {
        Role role = new Role();
        role.setId(4);
        role.setName("role4");
        role.setIsAdmin(false);
        roleRepository.addRole(role);

        SqlSpecification specification = new RoleSpecificationByIdRange(4, 4);
        List result = roleRepository.query(specification);
        Role roleResult = (Role) result.get(0);
        assertThat(roleResult.getName(), is("role4"));
    }

    @Test
    public void removeRole() {
        // добавим Role без ссылок на него
        Role role = new Role();
        role.setId(4);
        role.setName("role4");
        role.setIsAdmin(true);
        roleRepository.addRole(role);

        roleRepository.removeRole(role);

        SqlSpecification specification = new RoleSpecificationByIdRange(4, 4);
        List result = roleRepository.query(specification);
        System.out.println("result = " + result);

        assertEquals(result.size(), 0);
    }

    @Test
    public void updateRole() {
        SqlSpecification specification = new RoleSpecificationByIdRange(3, 3);
        List rolelist = roleRepository.query(specification);
        Role role = (Role) rolelist.get(0);
        role.setName("newname3");
        roleRepository.updateRole(role);

        List result = roleRepository.query(specification);
        Role roleResult = (Role) result.get(0);

        assertEquals(roleResult.getName(), "newname3");
    }

    @Test
    public void query() {
        SqlSpecification specification = new RoleSpecificationByIdRange(1, 1);
        List result = roleRepository.query(specification);
        Role roleResult = (Role) result.get(0);
        assertThat(roleResult.getName(), is("role1"));

        List<Role> expected = makeTestRoleAll();
        specification = new RoleSpecificationAllRoles();
        result = roleRepository.query(specification);
        assertThat(result, is(expected));
    }
}