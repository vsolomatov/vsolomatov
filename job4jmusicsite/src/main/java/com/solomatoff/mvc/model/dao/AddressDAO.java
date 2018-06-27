package com.solomatoff.mvc.model.dao;

import com.solomatoff.mvc.entities.Address;

import java.util.ArrayList;
import java.util.List;

public interface AddressDAO {
    List<Address> addAddress(Address address);
    List<Address> updateAddress(Address address);
    List<Address> deleteAddress(Address address);
    List<Address> findByIdAddress(Address address);
    List<Address> findAllAddresses(Address address);
}
