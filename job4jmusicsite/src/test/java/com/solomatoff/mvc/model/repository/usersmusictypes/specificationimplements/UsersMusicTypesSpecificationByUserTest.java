package com.solomatoff.mvc.model.repository.usersmusictypes.specificationimplements;

import com.solomatoff.mvc.model.repository.usersmusictypes.specificationimplements.UsersMusicTypesSpecificationByUser;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

public class UsersMusicTypesSpecificationByUserTest {

    @Test
    public void toSqlClauses() {
        UsersMusicTypesSpecificationByUser usersMusicTypesSpecificationByUser = new UsersMusicTypesSpecificationByUser(1);
        assertThat(usersMusicTypesSpecificationByUser.toSqlClauses(), is("userid = 1"));
    }

    @Test
    public void testToString() {
        UsersMusicTypesSpecificationByUser usersMusicTypesSpecificationByUser = new UsersMusicTypesSpecificationByUser(1);
        //System.out.println("usersMusicTypesSpecificationByUser = " + usersMusicTypesSpecificationByUser);
        assertThat(usersMusicTypesSpecificationByUser.toString(), is("UsersMusicTypesSpecificationByUser{userId = 1}"));
    }

    @Test
    public void equals() {
        UsersMusicTypesSpecificationByUser usersMusicTypesSpecificationByUser1 = new UsersMusicTypesSpecificationByUser(1);
        UsersMusicTypesSpecificationByUser usersMusicTypesSpecificationByUser2 = new UsersMusicTypesSpecificationByUser(1);
        assertThat(usersMusicTypesSpecificationByUser1.equals(usersMusicTypesSpecificationByUser2), is(true));

        UsersMusicTypesSpecificationByUser usersMusicTypesSpecificationByUser3 = new UsersMusicTypesSpecificationByUser(2);
        assertThat(usersMusicTypesSpecificationByUser1.equals(usersMusicTypesSpecificationByUser3), is(false));
    }

    @Test
    public void testHashCode() {
        UsersMusicTypesSpecificationByUser usersMusicTypesSpecificationByUser1 = new UsersMusicTypesSpecificationByUser(1);
        UsersMusicTypesSpecificationByUser usersMusicTypesSpecificationByUser2 = new UsersMusicTypesSpecificationByUser(1);
        assertThat(usersMusicTypesSpecificationByUser1.hashCode(), is(usersMusicTypesSpecificationByUser2.hashCode()));

        UsersMusicTypesSpecificationByUser usersMusicTypesSpecificationByUser3 = new UsersMusicTypesSpecificationByUser(2);
        assertThat(usersMusicTypesSpecificationByUser1.hashCode(), not(usersMusicTypesSpecificationByUser3.hashCode()));
    }
}