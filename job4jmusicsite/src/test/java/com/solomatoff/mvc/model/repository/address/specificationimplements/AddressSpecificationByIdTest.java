package com.solomatoff.mvc.model.repository.address.specificationimplements;

import com.solomatoff.mvc.model.repository.address.specificationimplements.AddressSpecificationById;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class AddressSpecificationByIdTest {

    @Test
    public void toSqlClauses() {
        AddressSpecificationById addressSpecificationById = new AddressSpecificationById(1);
        assertThat(addressSpecificationById.toSqlClauses(), is("userid = 1"));
    }

    @Test
    public void testToString() {
        AddressSpecificationById addressSpecificationById = new AddressSpecificationById(1);
        System.out.println("addressSpecificationById = " + addressSpecificationById);
        assertThat(addressSpecificationById.toString(), is("AddressSpecificationById{userId = 1}"));
    }

    @Test
    public void equals() {
        AddressSpecificationById addressSpecificationById1 = new AddressSpecificationById(1);
        AddressSpecificationById addressSpecificationById2 = new AddressSpecificationById(1);
        assertThat(addressSpecificationById1.equals(addressSpecificationById2), is(true));

        AddressSpecificationById addressSpecificationById3 = new AddressSpecificationById(2);
        assertThat(addressSpecificationById1.equals(addressSpecificationById3), is(false));
    }

    @Test
    public void testHashCode() {
        AddressSpecificationById addressSpecificationById1 = new AddressSpecificationById(1);
        AddressSpecificationById addressSpecificationById2 = new AddressSpecificationById(1);
        assertThat(addressSpecificationById1.hashCode(), is(addressSpecificationById2.hashCode()));

        AddressSpecificationById addressSpecificationById3 = new AddressSpecificationById(2);
        assertThat(addressSpecificationById1.hashCode(), not(addressSpecificationById3.hashCode()));
    }
}