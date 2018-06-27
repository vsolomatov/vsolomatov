package com.solomatoff.mvc.model.store;

import com.solomatoff.mvc.entities.*;
import com.solomatoff.mvc.model.PersistentDAO;
import com.solomatoff.mvc.model.store.memorystore.*;

import java.util.List;

public class MemoryStore implements PersistentDAO {
    private static MemoryStore singletonInstance = new MemoryStore();

    public MemoryStore() {
    }

    public static MemoryStore getInstance() {
        return singletonInstance;
    }

    private final AddressMemoryStore addressMemoryStore = new AddressMemoryStore();
    private final MusicTypeMemoryStore musicTypeMemoryStore = new MusicTypeMemoryStore();
    private final RoleMemoryStore roleMemoryStore = new RoleMemoryStore();
    private final UserMemoryStore userMemoryStore = new UserMemoryStore();
    private final UserRolesMemoryStore userRolesMemoryStore = new UserRolesMemoryStore();
    private final UsersMusicTypesMemoryStore usersMusicTypesMemoryStore = new UsersMusicTypesMemoryStore();

    @Override
    public List<Address> addAddress(Address address) {
        return addressMemoryStore.addAddress(address);
    }

    @Override
    public List<Address> updateAddress(Address address) {
        return addressMemoryStore.updateAddress(address);
    }

    @Override
    public List<Address> deleteAddress(Address address) {
        return addressMemoryStore.deleteAddress(address);
    }

    @Override
    public List<Address> findByIdAddress(Address address) {
        return addressMemoryStore.findByIdAddress(address);
    }

    @Override
    public List<Address> findAllAddresses(Address address) {
        return addressMemoryStore.findAllAddresses(address);
    }

    @Override
    public List<MusicType> addMusicType(MusicType musicType) {
        return musicTypeMemoryStore.addMusicType(musicType);
    }

    @Override
    public List<MusicType> updateMusicType(MusicType musicType) {
        return musicTypeMemoryStore.updateMusicType(musicType);
    }

    @Override
    public List<MusicType> deleteMusicType(MusicType musicType) {
        return musicTypeMemoryStore.deleteMusicType(musicType);
    }

    @Override
    public List<MusicType> findByIdMusicType(MusicType musicType) {
        return musicTypeMemoryStore.findByIdMusicType(musicType);
    }

    @Override
    public List<MusicType> findAllMusicTypes(MusicType musicType) {
        return musicTypeMemoryStore.findAllMusicTypes(musicType);
    }

    @Override
    public List<Role> addRole(Role role) {
        return roleMemoryStore.addRole(role);
    }

    @Override
    public List<Role> updateRole(Role role) {
        return roleMemoryStore.updateRole(role);
    }

    @Override
    public List<Role> deleteRole(Role role) {
        return roleMemoryStore.deleteRole(role);
    }

    @Override
    public List<Role> findByIdRole(Role role) {
        return roleMemoryStore.findByIdRole(role);
    }

    @Override
    public List<Role> findAllRoles(Role role) {
        return roleMemoryStore.findAllRoles(role);
    }

    @Override
    public List<User> addUser(User user) {
        return userMemoryStore.addUser(user);
    }

    @Override
    public List<User> updateUser(User user) {
        return userMemoryStore.updateUser(user);
    }

    @Override
    public List<User> deleteUser(User user) {
        return userMemoryStore.deleteUser(user);
    }

    @Override
    public List<User> findByIdUser(User user) {
        return userMemoryStore.findByIdUser(user);
    }

    @Override
    public List<User> findAllUsers(User user) {
        return userMemoryStore.findAllUsers(user);
    }

    @Override
    public List<UserRoles> addUserRoles(UserRoles userRoles) {
        return userRolesMemoryStore.addUserRoles(userRoles);
    }

    @Override
    public List<UserRoles> updateUserRoles(UserRoles userRoles) {
        return userRolesMemoryStore.updateUserRoles(userRoles);
    }

    @Override
    public List<UserRoles> deleteUserRoles(UserRoles userRoles) {
        return userRolesMemoryStore.deleteUserRoles(userRoles);
    }

    @Override
    public List<UserRoles> findByIdUserUserRoles(UserRoles userRoles) {
        return userRolesMemoryStore.findByIdUserUserRoles(userRoles);
    }

    @Override
    public List<UserRoles> findAllUserRoles(UserRoles userRoles) {
        return userRolesMemoryStore.findAllUserRoles(userRoles);
    }

    @Override
    public List<UsersMusicTypes> addUsersMusicTypes(UsersMusicTypes userRoles) {
        return usersMusicTypesMemoryStore.addUsersMusicTypes(userRoles);
    }

    @Override
    public List<UsersMusicTypes> updateUsersMusicTypes(UsersMusicTypes userRoles) {
        return usersMusicTypesMemoryStore.updateUsersMusicTypes(userRoles);
    }

    @Override
    public List<UsersMusicTypes> deleteUsersMusicTypes(UsersMusicTypes userRoles) {
        return usersMusicTypesMemoryStore.deleteUsersMusicTypes(userRoles);
    }

    @Override
    public List<UsersMusicTypes> findByIdUsersMusicTypes(UsersMusicTypes userRoles) {
        return usersMusicTypesMemoryStore.findByIdUsersMusicTypes(userRoles);
    }

    @Override
    public List<UsersMusicTypes> findAllUsersMusicTypes(UsersMusicTypes userRoles) {
        return usersMusicTypesMemoryStore.findAllUsersMusicTypes(userRoles);
    }
}
