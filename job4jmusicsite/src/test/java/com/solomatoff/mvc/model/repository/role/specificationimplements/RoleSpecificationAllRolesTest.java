package com.solomatoff.mvc.model.repository.role.specificationimplements;

import com.solomatoff.mvc.model.repository.role.specificationimplements.RoleSpecificationAllRoles;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class RoleSpecificationAllRolesTest {

    @Test
    public void toSqlClauses() {
        RoleSpecificationAllRoles roleSpecificationAllRoles = new RoleSpecificationAllRoles();
        assertThat(roleSpecificationAllRoles.toSqlClauses(), is("id = id"));
    }

    @Test
    public void testToString() {
        RoleSpecificationAllRoles roleSpecificationAllRoles = new RoleSpecificationAllRoles();
        assertThat(roleSpecificationAllRoles.toString(), is("RoleSpecificationAllRoles{}"));
    }
}