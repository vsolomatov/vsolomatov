package com.solomatoff.mvc.model.store.memorystore;

import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.Address;
import com.solomatoff.mvc.entities.Address;
import com.solomatoff.mvc.model.dao.AddressDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class AddressMemoryStore implements AddressDAO {
    /**
     * Contains Adresses
     */
    private static final Map<Integer, Address> ADDRESS_MAP = new ConcurrentSkipListMap<>();

    @Override
    public List<Address> addAddress(Address address) {
        List<Address> result = new ArrayList<>();
        Address addressResult = ADDRESS_MAP.get(address.getUserId());
        // Добавляем только, если еще не существует
        if (addressResult == null) {
            ADDRESS_MAP.put(address.getUserId(), address);
            result.add(address); // добавляем новый адрес в результирующий список
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Add in Memory: %s", address));
        }
        return result;
    }

    @Override
    public List<Address> updateAddress(Address address) {
        List<Address> result = new ArrayList<>();
        Address addressResult = ADDRESS_MAP.put(address.getUserId(), address);
        if (addressResult != null) {
            result.add(addressResult);
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Before Update in Memory: %s", addressResult));
            LoggerApp.getInstance().getLog().info(String.format("    After Update in Memory: %s", address));
        }
        return result;
    }

    @Override
    public List<Address> deleteAddress(Address address) {
        List<Address> result = new ArrayList<>();
        Address addressResult = ADDRESS_MAP.remove(address.getUserId());
        if (addressResult != null) {
            result.add(addressResult);
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Delete from Memory: %s", addressResult));
        }
        return result;
    }

    @Override
    public List<Address> findByIdAddress(Address address) {
        List<Address> result = new ArrayList<>();
        Address addressResult = ADDRESS_MAP.get(address.getUserId());
        if (addressResult != null) {
            result.add(addressResult);
            // Записываем в LOG
            LoggerApp.getInstance().getLog().info(String.format("    Find By Id in Memory: %s", addressResult));
        }
        return result;
    }

    @Override
    public List<Address> findAllAddresses(Address address) {
        List<Address> result = new ArrayList<>(ADDRESS_MAP.values());
        // Записываем в LOG
        LoggerApp.getInstance().getLog().info("  Find All Addresss in Memory.");
        return result;
    }
}
