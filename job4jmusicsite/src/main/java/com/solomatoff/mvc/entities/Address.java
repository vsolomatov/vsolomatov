package com.solomatoff.mvc.entities;

import java.util.Objects;
import java.util.stream.Stream;

public class Address {
    private Integer userId;
    private String street;
    private String house;
    private String apartment;
    private String city;
    private String zipcode;
    private String country;

    public Address() {
    }
    
    public Address(Integer userId, String street, String house, String apartment, String city, String zipcode, String country) {
        this.userId = userId;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.city = city;
        this.zipcode = zipcode;
        this.country = country;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public String getApartment() {
        return apartment;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return userId == address.userId
                && Objects.equals(street, address.street)
                && Objects.equals(house, address.house)
                && Objects.equals(apartment, address.apartment)
                && Objects.equals(city, address.city)
                && Objects.equals(zipcode, address.zipcode)
                && Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, street, house, apartment, city, zipcode, country);
    }

    @Override
    public String toString() {
        return "Address{"
                + "userId=" + userId
                + ", street='" + street + '\''
                + ", house='" + house + '\''
                + ", apartment='" + apartment + '\''
                + ", city='" + city + '\''
                + ", zipcode='" + zipcode + '\''
                + ", country='" + country + '\''
                + '}';
    }
}
