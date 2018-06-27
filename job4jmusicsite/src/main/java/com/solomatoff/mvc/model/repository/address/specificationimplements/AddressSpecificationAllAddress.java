package com.solomatoff.mvc.model.repository.address.specificationimplements;

import com.solomatoff.mvc.model.repository.SqlSpecification;

public class AddressSpecificationAllAddress implements SqlSpecification {

    public AddressSpecificationAllAddress() {
    }

    @Override
    public String toSqlClauses() {
        return "userid = userid";
    }

    @Override
    public String toString() {
        return "AddressSpecificationAllAddress{}";
    }
}
