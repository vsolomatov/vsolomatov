package com.solomatoff.mvc.entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class AddressTest {

    @Test
    public void getUserId() {
        Address address = new Address();
        int expected = 1;
        address.setUserId(1);
        int result = address.getUserId();
        assertEquals(result, expected);
    }

    @Test
    public void getStreet() {
        Address address = new Address();
        String expected = "Pinewood Crescent";
        address.setStreet("Pinewood Crescent");
        String result = address.getStreet();
        assertEquals(result, expected);
    }

    @Test
    public void getHouse() {
        Address address = new Address();
        String expected = "343";
        address.setHouse("343");
        String result = address.getHouse();
        assertEquals(result, expected);
    }

    @Test
    public void getApartment() {
        Address address = new Address();
        String expected = "57a";
        address.setApartment("57a");
        String result = address.getApartment();
        assertEquals(result, expected);
    }

    @Test
    public void getCity() {
        Address address = new Address();
        String expected = "West Vancouver";
        address.setCity("West Vancouver");
        String result = address.getCity();
        assertEquals(result, expected);
    }

    @Test
    public void getZipcode() {
        Address address = new Address();
        String expected = "V9G 3M8";
        address.setZipcode("V9G 3M8");
        String result = address.getZipcode();
        assertEquals(result, expected);
    }

    @Test
    public void getCountry() {
        Address address = new Address();
        String expected = "United Kingdom";
        address.setCountry("United Kingdom");
        String result = address.getCountry();
        assertEquals(result, expected);
    }

    @Test
    public void setUserId() {
        Address address = new Address();
        int expected = 2;
        address.setUserId(2);
        int result = address.getUserId();
        assertEquals(result, expected);
    }

    @Test
    public void setStreet() {
        Address address = new Address();
        String expected = "Rue Dupuis";
        address.setStreet("Rue Dupuis");
        String result = address.getStreet();
        assertEquals(result, expected);
    }

    @Test
    public void setHouse() {
        Address address = new Address();
        String expected = "14";
        address.setHouse("14");
        String result = address.getHouse();
        assertEquals(result, expected);
    }

    @Test
    public void setApartment() {
        Address address = new Address();
        String expected = "app-204";
        address.setApartment("app-204");
        String result = address.getApartment();
        assertEquals(result, expected);
    }

    @Test
    public void setCity() {
        Address address = new Address();
        String expected = "Levis";
        address.setCity("Levis");
        String result = address.getCity();
        assertEquals(result, expected);
    }

    @Test
    public void setZipcode() {
        Address address = new Address();
        String expected = "G1P D3K";
        address.setZipcode("G1P D3K");
        String result = address.getZipcode();
        assertEquals(result, expected);
    }

    @Test
    public void setCountry() {
        Address address = new Address();
        String expected = "USA";
        address.setCountry("USA");
        String result = address.getCountry();
        assertEquals(result, expected);
    }

    @Test
    public void equals() {
        Address address = new Address();
        address.setUserId(1);
        address.setStreet("Pinewood Crescent");
        address.setHouse("343");
        address.setApartment("57a");
        address.setCity("West Vancouver");
        address.setZipcode("V9G 3M8");
        address.setCountry("Canada");

        Address expected = new Address();
        expected.setUserId(1);
        expected.setStreet("Pinewood Crescent");
        expected.setHouse("343");
        expected.setApartment("57a");
        expected.setCity("West Vancouver");
        expected.setZipcode("V9G 3M8");
        expected.setCountry("Canada");

        assertEquals(address, expected);

        expected = new Address();
        expected.setUserId(1);
        expected.setStreet("Pinewood Crescent");
        expected.setHouse("3");
        expected.setApartment("57a");
        expected.setCity("West Vancouver");
        expected.setZipcode("V9G 3M8");
        expected.setCountry("Canada");

        assertNotEquals(address, expected);
    }

    @Test
    public void testHashCode() {
        Address address = new Address();
        address.setUserId(1);
        address.setStreet("Pinewood Crescent");
        address.setHouse("343");
        address.setApartment("57a");
        address.setCity("West Vancouver");
        address.setZipcode("V9G 3M8");
        address.setCountry("Canada");

        Address expected = new Address();
        expected.setUserId(1);
        expected.setStreet("Pinewood Crescent");
        expected.setHouse("343");
        expected.setApartment("57a");
        expected.setCity("West Vancouver");
        expected.setZipcode("V9G 3M8");
        expected.setCountry("Canada");
        assertEquals(address.hashCode(), expected.hashCode());
    }

    @Test
    public void testToString() {
        Address address = new Address();
        address.setUserId(1);
        address.setStreet("Pinewood Crescent");
        address.setHouse("343");
        address.setApartment("57a");
        address.setCity("West Vancouver");
        address.setZipcode("V9G 3M8");
        address.setCountry("Canada");
        String expected = "Address{userId=1, street='Pinewood Crescent', house='343', apartment='57a', city='West Vancouver', zipcode='V9G 3M8', country='Canada'}";
        assertEquals(address.toString(), expected);
    }
}