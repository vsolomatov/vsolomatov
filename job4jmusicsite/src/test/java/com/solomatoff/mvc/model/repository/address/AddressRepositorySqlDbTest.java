package com.solomatoff.mvc.model.repository.address;

import com.solomatoff.mvc.controller.CommonTest;
import com.solomatoff.mvc.entities.Address;
import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.model.PersistentDAO;
import com.solomatoff.mvc.model.repository.SqlSpecification;
import com.solomatoff.mvc.model.repository.address.AddressRepository;
import com.solomatoff.mvc.model.repository.address.AddressRepositoryDb;
import com.solomatoff.mvc.model.repository.address.specificationimplements.AddressSpecificationAllAddress;
import com.solomatoff.mvc.model.repository.address.specificationimplements.AddressSpecificationById;
import com.solomatoff.mvc.model.repository.user.UserRepository;
import com.solomatoff.mvc.model.repository.user.UserRepositoryDb;
import com.solomatoff.mvc.model.store.DbStore;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AddressRepositorySqlDbTest {
    private final AddressRepository addressRepository = new AddressRepositoryDb();
    private final UserRepository userRepository = new UserRepositoryDb();
    private final static PersistentDAO DB_STORE = DbStore.getInstance();

    @Before
    public void setUp() {
        CommonTest.clearAndCreateDataInDataBase();
    }

    private List<Address> makeTestAddressAll() {
        return DB_STORE.findAllAddresses(new Address());
    }

    @Test
    public void addAddress() {
        // добавим вначале новый User
        User user = new User();
        user.setId(4);
        user.setLogin("login4");
        user.setPassword("password4");
        userRepository.addUser(user);

        Address address = new Address();
        address.setUserId(4);
        address.setStreet("street4");
        address.setCity("Monreal");
        address.setCountry("Canada");
        addressRepository.addAddress(address);

        SqlSpecification specification = new AddressSpecificationById(4);
        List result = addressRepository.query(specification);
        Address addressResult = (Address) result.get(0);
        assertThat(addressResult.getStreet(), is("street4"));
    }

    @Test
    public void removeAddress() {
        Address address = new Address();
        address.setUserId(3);
        addressRepository.removeAddress(address);

        SqlSpecification specification = new AddressSpecificationById(3);
        List result = addressRepository.query(specification);
        //System.out.println("result = " + result);

        assertEquals(result.size(), 0);
    }

    @Test
    public void updateAddress() {
        SqlSpecification specification = new AddressSpecificationById(3);
        List addresslist = addressRepository.query(specification);
        Address address = (Address) addresslist.get(0);
        address.setStreet("newstreet3");
        addressRepository.updateAddress(address);

        List result = addressRepository.query(specification);
        Address addressResult = (Address) result.get(0);

        assertEquals(addressResult.getStreet(), "newstreet3");
    }

    @Test
    public void query() {
        SqlSpecification specification = new AddressSpecificationById(1);
        List result = addressRepository.query(specification);
        Address addressResult = (Address) result.get(0);
        assertThat(addressResult.getStreet(), is("street1"));
        assertThat(addressResult.getHouse(), is("house1"));
        assertThat(addressResult.getApartment(), is("apartment1"));
        assertThat(addressResult.getCity(), is("city1"));
        assertThat(addressResult.getCountry(), is("country1"));

        List<Address> expected = makeTestAddressAll();
        specification = new AddressSpecificationAllAddress();
        result = addressRepository.query(specification);
        assertThat(result, is(expected));
    }
}