package com.solomatoff.mvc.model.store.dbstore;

import com.solomatoff.mvc.controller.CommonTest;
import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.entities.Address;
import com.solomatoff.mvc.model.PersistentDAO;
import com.solomatoff.mvc.model.store.DbStore;
import com.solomatoff.mvc.model.store.MemoryStore;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AddressDbStoreTest {
    private static final PersistentDAO  STORE = DbStore.getInstance();

    @Before
    public void setUp() {
        CommonTest.clearAndCreateDataInDataBase();
    }

    @Test
    public void addAddress() {
        Address address3 = new Address(3, "street3", "house3", "apartment3", "city3", "zipcode3", "country3");
        List<Address> expected = new ArrayList<>();
        expected.add(address3);

        // Вначале удалим
        STORE.deleteAddress(address3);
        // Добавляем первый раз Address4
        List<Address> result = STORE.addAddress(address3);
        //System.out.println("result = " + result);
        //System.out.println("expected = " + expected);
        assertThat(result, is(expected));

        // Добавляем второй раз Address3, теперь список должен быть пустой, т.к. Address второй раз не добавится
        result = STORE.addAddress(address3);
        assertThat(result.size(), is(0));
    }

    @Test
    public void updateAddress() {
        Address address = new Address();
        address.setUserId(3);
        List<Address> expected = STORE.findByIdAddress(address);

        // Обновим Address с id=3
        Address newAddress3 = new Address(3, "newstreet3", "newhouse3", "newapartment3", "newcity3", "newzipcode3", "newcountry3");
        List<Address> result = STORE.updateAddress(newAddress3);
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
        address.setUserId(3);
        List<Address> expected = STORE.findByIdAddress(address);

        // Удалим Address с id = 3
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
        Address address1 = new Address(1, "street1", "house1", "apartment1", "city1", "zipcode1", "country1");
        Address result = STORE.findByIdAddress(address1).get(0);
        assertThat(result.getStreet(), is(address1.getStreet()));
    }

    @Test
    public void findAllAddresss() {
        List<Address> expected = new ArrayList<>();
        Address address = new Address();
        address.setUserId(1);
        Address address1 = STORE.findByIdAddress(address).get(0);
        expected.add(address1);
        address = new Address();
        address.setUserId(2);
        Address address2 = STORE.findByIdAddress(address).get(0);
        expected.add(address2);
        address = new Address();
        address.setUserId(3);
        Address address3 = STORE.findByIdAddress(address).get(0);
        expected.add(address3);
        List<Address> result = STORE.findAllAddresses(address);
        int i = 0;
        for (Address addressloop : result) {
            assertThat(addressloop.getStreet(), is(expected.get(i).getStreet()));
            i++;
        }
    }
}