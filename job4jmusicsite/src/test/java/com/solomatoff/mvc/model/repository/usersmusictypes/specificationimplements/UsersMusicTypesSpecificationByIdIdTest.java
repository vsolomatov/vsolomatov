package com.solomatoff.mvc.model.repository.usersmusictypes.specificationimplements;

import com.solomatoff.mvc.model.repository.usersmusictypes.specificationimplements.UsersMusicTypesSpecificationByIdId;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class UsersMusicTypesSpecificationByIdIdTest {

    @Test
    public void toSqlClauses() {
        UsersMusicTypesSpecificationByIdId usersMusicTypesSpecificationByIdId = new UsersMusicTypesSpecificationByIdId(1, 1);
        assertThat(usersMusicTypesSpecificationByIdId.toSqlClauses(), is("userid = 1 and musictypeid = 1"));
    }

    @Test
    public void testToString() {
        UsersMusicTypesSpecificationByIdId usersMusicTypesSpecificationByIdId = new UsersMusicTypesSpecificationByIdId(1, 1);
        System.out.println("usersMusicTypesSpecificationByIdId = " + usersMusicTypesSpecificationByIdId);
        assertThat(usersMusicTypesSpecificationByIdId.toString(), is("UsersMusicTypesSpecificationByIdId{userId = 1, musicTypeId = 1}"));
    }

    @Test
    public void equals() {
        UsersMusicTypesSpecificationByIdId usersMusicTypesSpecificationByIdId1 = new UsersMusicTypesSpecificationByIdId(1, 1);
        UsersMusicTypesSpecificationByIdId usersMusicTypesSpecificationByIdId2 = new UsersMusicTypesSpecificationByIdId(1, 1);
        assertThat(usersMusicTypesSpecificationByIdId1.equals(usersMusicTypesSpecificationByIdId2), is(true));

        UsersMusicTypesSpecificationByIdId usersMusicTypesSpecificationByIdId3 = new UsersMusicTypesSpecificationByIdId(2, 2);
        assertThat(usersMusicTypesSpecificationByIdId1.equals(usersMusicTypesSpecificationByIdId3), is(false));
    }

    @Test
    public void testHashCode() {
        UsersMusicTypesSpecificationByIdId usersMusicTypesSpecificationByIdId1 = new UsersMusicTypesSpecificationByIdId(1, 1);
        UsersMusicTypesSpecificationByIdId usersMusicTypesSpecificationByIdId2 = new UsersMusicTypesSpecificationByIdId(1, 1);
        assertThat(usersMusicTypesSpecificationByIdId1.hashCode(), is(usersMusicTypesSpecificationByIdId2.hashCode()));

        UsersMusicTypesSpecificationByIdId usersMusicTypesSpecificationByIdId3 = new UsersMusicTypesSpecificationByIdId(2, 2);
        assertThat(usersMusicTypesSpecificationByIdId1.hashCode(), not(usersMusicTypesSpecificationByIdId3.hashCode()));
    }
}