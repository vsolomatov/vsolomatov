package com.solomatoff.mvc.model.repository.userroles.specificationimplements;

import com.solomatoff.mvc.model.repository.userroles.specificationimplements.UserRolesSpecificationByIdId;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class UserRolesSpecificationByIdIdTest {

    @Test
    public void toSqlClauses() {
        UserRolesSpecificationByIdId userRolesSpecificationByIdId = new UserRolesSpecificationByIdId(1, 1);
        assertThat(userRolesSpecificationByIdId.toSqlClauses(), is("userid = 1 and roleid = 1"));
    }

    @Test
    public void testToString() {
        UserRolesSpecificationByIdId userRolesSpecificationByIdId = new UserRolesSpecificationByIdId(1, 1);
        //System.out.println("userRolesSpecificationByIdId = " + userRolesSpecificationByIdId);
        assertThat(userRolesSpecificationByIdId.toString(), is("UserRolesSpecificationByIdId{userId = 1, roleId = 1}"));
    }

    @Test
    public void equals() {
        UserRolesSpecificationByIdId userRolesSpecificationByIdId1 = new UserRolesSpecificationByIdId(1, 1);
        UserRolesSpecificationByIdId userRolesSpecificationByIdId2 = new UserRolesSpecificationByIdId(1, 1);
        assertThat(userRolesSpecificationByIdId1.equals(userRolesSpecificationByIdId2), is(true));

        UserRolesSpecificationByIdId userRolesSpecificationByIdId3 = new UserRolesSpecificationByIdId(2, 2);
        assertThat(userRolesSpecificationByIdId1.equals(userRolesSpecificationByIdId3), is(false));
    }

    @Test
    public void testHashCode() {
        UserRolesSpecificationByIdId userRolesSpecificationByIdId1 = new UserRolesSpecificationByIdId(1, 1);
        UserRolesSpecificationByIdId userRolesSpecificationByIdId2 = new UserRolesSpecificationByIdId(1, 1);
        assertThat(userRolesSpecificationByIdId1.hashCode(), is(userRolesSpecificationByIdId2.hashCode()));

        UserRolesSpecificationByIdId userRolesSpecificationByIdId3 = new UserRolesSpecificationByIdId(2, 2);
        assertThat(userRolesSpecificationByIdId1.hashCode(), not(userRolesSpecificationByIdId3.hashCode()));
    }
}