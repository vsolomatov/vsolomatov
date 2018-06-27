package com.solomatoff.mvc.model.store.memorystore;

import com.solomatoff.mvc.controller.CommonTest;
import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.entities.Address;
import com.solomatoff.mvc.model.PersistentDAO;
import com.solomatoff.mvc.model.store.MemoryStore;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AddressMemoryStoreTest {
    private static final PersistentDAO STORE = MemoryStore.getInstance();

    @Before
    public void setUp() {
        CommonTest.clearAndCreateDataInMemory();
    }

    @Test
    public void addAddress() {
        Address address7 = new Address(7, "street7", "house7", "apartment7", "city7", "zipcode7", "country7");
        List<Address> expected = new ArrayList<>();
        expected.add(address7);

        // Добавляем первый раз Address7
        List<Address> result = STORE.addAddress(address7);
        assertThat(result, is(expected));

        // Добавляем второй раз Address7, теперь список должен быть пустой, т.к. адрес второй раз не добавится
        result = STORE.addAddress(address7);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateAddress() {
        Address address = new Address();
        address.setUserId(6);
        List<Address> expected = STORE.findByIdAddress(address);

        // Обновим Address с id=6
        Address newAddress6 = new Address(6, "newstreet6", "newhouse6", "newapartment6", "newcity6", "newzipcode6", "newcountry6");
        List<Address> result = STORE.updateAddress(newAddress6);
        assertThat(result.get(0).getStreet(), is(expected.get(0).getStreet()));
        assertThat(result.get(0).getHouse(), is(expected.get(0).getHouse()));
        assertThat(result.get(0).getApartment(), is(expected.get(0).getApartment()));
        assertThat(result.get(0).getCity(), is(expected.get(0).getCity()));
        assertThat(result.get(0).getZipcode(), is(expected.get(0).getZipcode()));
        assertThat(result.get(0).getCountry(), is(expected.get(0).getCountry()));
    }

    @Test
    public void deleteAddress() {
        Address address = new Address();
        address.setUserId(6);
        List<Address> expected = STORE.findByIdAddress(address);

        // Удалим Address с id = 6
        List<Address> result = STORE.deleteAddress(address);
        assertThat(result.get(0).getStreet(), is(expected.get(0).getStreet()));

        // Попытаемся удалить не существующий Address
        address = new Address();
        address.setUserId(8);
        result = STORE.deleteAddress(address);
        assertThat(result.size(), is(0));
    }

    @Test
    public void findByIdAddress() {
        Address address4 = new Address(4, "street4", "house4", "apartment4", "city4", "zipcode4", "country4");
        Address result = STORE.findByIdAddress(address4).get(0);
        assertThat(result.getStreet(), is(address4.getStreet()));
    }

    @Test
    public void findAllAddresss() {
        List<Address> expected = new ArrayList<>();
        Address address = new Address();
        address.setUserId(4);
        Address address4 = STORE.findByIdAddress(address).get(0);
        expected.add(address4);
        address = new Address();
        address.setUserId(5);
        Address address5 = STORE.findByIdAddress(address).get(0);
        expected.add(address5);
        address = new Address();
        address.setUserId(6);
        Address address6 = STORE.findByIdAddress(address).get(0);
        expected.add(address6);
        List<Address> result = STORE.findAllAddresses(address);
        int i = 0;
        for (Address addressloop : result) {
            assertThat(addressloop.getStreet(), is(expected.get(i).getStreet()));
            i++;
        }
    }
}