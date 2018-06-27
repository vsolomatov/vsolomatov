package com.solomatoff.mvc.model.repository.user.specificationimplements;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

public class UserSpecificationByIdRangeTest {

    @Test
    public void toSqlClauses() {
        UserSpecificationByIdRange userSpecificationByIdRange = new UserSpecificationByIdRange(1, 1);
        assertThat(userSpecificationByIdRange.toSqlClauses(), is("id between 1 and 1"));
    }

    @Test
    public void testToString() {
        UserSpecificationByIdRange userSpecificationByIdRange = new UserSpecificationByIdRange(1, 1);
        assertThat(userSpecificationByIdRange.toString(), is("UserSpecificationByIdRange{minId = 1, maxId = 1}"));
    }

    @Test
    public void equals() {
        UserSpecificationByIdRange userSpecificationByIdRange1 = new UserSpecificationByIdRange(1, 1);
        UserSpecificationByIdRange userSpecificationByIdRange2 = new UserSpecificationByIdRange(1, 1);
        assertThat(userSpecificationByIdRange1.equals(userSpecificationByIdRange2), is(true));

        UserSpecificationByIdRange userSpecificationByIdRange3 = new UserSpecificationByIdRange(2, 2);
        assertThat(userSpecificationByIdRange1.equals(userSpecificationByIdRange3), is(false));
    }

    @Test
    public void testHashCode() {
        UserSpecificationByIdRange userSpecificationByIdRange1 = new UserSpecificationByIdRange(1, 1);
        UserSpecificationByIdRange userSpecificationByIdRange2 = new UserSpecificationByIdRange(1, 1);
        assertThat(userSpecificationByIdRange1.hashCode(), is(userSpecificationByIdRange2.hashCode()));

        UserSpecificationByIdRange userSpecificationByIdRange3 = new UserSpecificationByIdRange(2, 2);
        assertThat(userSpecificationByIdRange1.hashCode(), not(userSpecificationByIdRange3.hashCode()));
    }
}