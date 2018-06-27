package com.solomatoff.mvc.model.repository.address.specificationimplements;

import com.solomatoff.mvc.model.repository.address.specificationimplements.AddressSpecificationAllAddress;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AddressSpecificationAllAddressTest {

    @Test
    public void toSqlClauses() {
        AddressSpecificationAllAddress addressSpecificationAllAddresss = new AddressSpecificationAllAddress();
        assertThat(addressSpecificationAllAddresss.toSqlClauses(), is("userid = userid"));
    }

    @Test
    public void testToString() {
        AddressSpecificationAllAddress addressSpecificationAllAddresss = new AddressSpecificationAllAddress();
        assertThat(addressSpecificationAllAddresss.toString(), is("AddressSpecificationAllAddress{}"));
    }
}