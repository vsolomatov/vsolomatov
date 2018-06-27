package com.solomatoff.mvc.model.repository.role.specificationimplements;

import com.solomatoff.mvc.model.repository.role.specificationimplements.RoleSpecificationByIdRange;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class RoleSpecificationByIdRangeTest {

    @Test
    public void toSqlClauses() {
        RoleSpecificationByIdRange roleSpecificationByIdRange = new RoleSpecificationByIdRange(1, 1);
        assertThat(roleSpecificationByIdRange.toSqlClauses(), is("id between 1 and 1"));
    }

    @Test
    public void testToString() {
        RoleSpecificationByIdRange roleSpecificationByIdRange = new RoleSpecificationByIdRange(1, 1);
        assertThat(roleSpecificationByIdRange.toString(), is("RoleSpecificationByIdRange{minId = 1, maxId = 1}"));
    }

    @Test
    public void equals() {
        RoleSpecificationByIdRange roleSpecificationByIdRange1 = new RoleSpecificationByIdRange(1, 1);
        RoleSpecificationByIdRange roleSpecificationByIdRange2 = new RoleSpecificationByIdRange(1, 1);
        assertThat(roleSpecificationByIdRange1.equals(roleSpecificationByIdRange2), is(true));

        RoleSpecificationByIdRange roleSpecificationByIdRange3 = new RoleSpecificationByIdRange(2, 2);
        assertThat(roleSpecificationByIdRange1.equals(roleSpecificationByIdRange3), is(false));
    }

    @Test
    public void testHashCode() {
        RoleSpecificationByIdRange roleSpecificationByIdRange1 = new RoleSpecificationByIdRange(1, 1);
        RoleSpecificationByIdRange roleSpecificationByIdRange2 = new RoleSpecificationByIdRange(1, 1);
        assertThat(roleSpecificationByIdRange1.hashCode(), is(roleSpecificationByIdRange2.hashCode()));

        RoleSpecificationByIdRange roleSpecificationByIdRange3 = new RoleSpecificationByIdRange(2, 2);
        assertThat(roleSpecificationByIdRange1.hashCode(), not(roleSpecificationByIdRange3.hashCode()));
    }
}