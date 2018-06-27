package com.solomatoff.mvc.model.repository.user.specificationimplements;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserSpecificationAllUsersTest {

    @Test
    public void toSqlClauses() {
        UserSpecificationAllUsers userSpecificationAllUsers = new UserSpecificationAllUsers();
        assertThat(userSpecificationAllUsers.toSqlClauses(), is("id = id"));
    }

    @Test
    public void testToString() {
        UserSpecificationAllUsers userSpecificationAllUsers = new UserSpecificationAllUsers();
        assertThat(userSpecificationAllUsers.toString(), is("UserSpecificationAllUsers{}"));
    }
}