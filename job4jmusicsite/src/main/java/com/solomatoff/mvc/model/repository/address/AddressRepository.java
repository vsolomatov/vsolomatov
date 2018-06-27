package com.solomatoff.mvc.model.repository.address;


import com.solomatoff.mvc.entities.Address;
import com.solomatoff.mvc.model.repository.SqlSpecification;

import java.util.List;

public interface AddressRepository {
    void addAddress(Address address);
    void removeAddress(Address address);
    void updateAddress(Address address); // Think it as replace for set

    List query(SqlSpecification sqlSpecification);
}
