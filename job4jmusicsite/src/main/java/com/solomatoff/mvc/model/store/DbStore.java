package com.solomatoff.mvc.model.store;

import com.solomatoff.mvc.entities.*;
import com.solomatoff.mvc.model.PersistentDAO;
import com.solomatoff.mvc.model.store.dbstore.*;

import java.util.List;

public class DbStore implements PersistentDAO {
    private static DbStore singletonInstance = new DbStore();

    public DbStore() {
    }

    public static DbStore getInstance() {
        return singletonInstance;
    }

    private final AddressDbStore addressDbStore = new AddressDbStore();
    private final MusicTypeDbStore musicTypeDbStore = new MusicTypeDbStore();
    private final RoleDbStore roleDbStore = new RoleDbStore();
    private final UserDbStore userDbStore = new UserDbStore();
    private final UserRolesDbStore userRolesDbStore = new UserRolesDbStore();
    private final UsersMusicTypesDbStore usersMusicTypesDbStore = new UsersMusicTypesDbStore();
    public AddressDbStore getAddressDbStore() {
        return addressDbStore;
    }

    public MusicTypeDbStore getMusicTypeDbStore() {
        return musicTypeDbStore;
    }

    public RoleDbStore getRoleDbStore() {
        return roleDbStore;
    }

    public UserDbStore getUserDbStore() {
        return userDbStore;
    }

    public UserRolesDbStore getUserRolesDbStore() {
        return userRolesDbStore;
    }

    public UsersMusicTypesDbStore getUsersMusicTypesDbStore() {
        return usersMusicTypesDbStore;
    }

    @Override
    public List<Address> addAddress(Address address) {
        return addressDbStore.addAddress(address);
    }

    @Override
    public List<Address> updateAddress(Address address) {
        return addressDbStore.updateAddress(address);
    }

    @Override
    public List<Address> deleteAddress(Address address) {
        return addressDbStore.deleteAddress(address);
    }

    @Override
    public List<Address> findByIdAddress(Address address) {
        return addressDbStore.findByIdAddress(address);
    }

    @Override
    public List<Address> findAllAddresses(Address address) {
        return addressDbStore.findAllAddresses(address);
    }

    @Override
    public List<MusicType> addMusicType(MusicType musicType) {
        return musicTypeDbStore.addMusicType(musicType);
    }

    @Override
    public List<MusicType> updateMusicType(MusicType musicType) {
        return musicTypeDbStore.updateMusicType(musicType);
    }

    @Override
    public List<MusicType> deleteMusicType(MusicType musicType) {
        return musicTypeDbStore.deleteMusicType(musicType);
    }

    @Override
    public List<MusicType> findByIdMusicType(MusicType musicType) {
        return musicTypeDbStore.findByIdMusicType(musicType);
    }

    @Override
    public List<MusicType> findAllMusicTypes(MusicType musicType) {
        return musicTypeDbStore.findAllMusicTypes(musicType);
    }

    @Override
    public List<Role> addRole(Role role) {
        return roleDbStore.addRole(role);
    }

    @Override
    public List<Role> updateRole(Role role) {
        return roleDbStore.updateRole(role);
    }

    @Override
    public List<Role> deleteRole(Role role) {
        return roleDbStore.deleteRole(role);
    }

    @Override
    public List<Role> findByIdRole(Role role) {
        return roleDbStore.findByIdRole(role);
    }

    @Override
    public List<Role> findAllRoles(Role role) {
        return roleDbStore.findAllRoles(role);
    }

    @Override
    public List<User> addUser(User user) {
        return userDbStore.addUser(user);
    }

    @Override
    public List<User> updateUser(User user) {
        return userDbStore.updateUser(user);
    }

    @Override
    public List<User> deleteUser(User user) {
        return userDbStore.deleteUser(user);
    }

    @Override
    public List<User> findByIdUser(User user) {
        return userDbStore.findByIdUser(user);
    }

    @Override
    public List<User> findAllUsers(User user) {
        return userDbStore.findAllUsers(user);
    }

    @Override
    public List<UserRoles> addUserRoles(UserRoles userRoles) {
        return userRolesDbStore.addUserRoles(userRoles);
    }

    @Override
    public List<UserRoles> updateUserRoles(UserRoles userRoles) {
        return userRolesDbStore.updateUserRoles(userRoles);
    }

    @Override
    public List<UserRoles> deleteUserRoles(UserRoles userRoles) {
        return userRolesDbStore.deleteUserRoles(userRoles);
    }

    @Override
    public List<UserRoles> findByIdUserUserRoles(UserRoles userRoles) {
        return userRolesDbStore.findByIdUserUserRoles(userRoles);
    }

    @Override
    public List<UserRoles> findAllUserRoles(UserRoles userRoles) {
        return userRolesDbStore.findAllUserRoles(userRoles);
    }

    @Override
    public List<UsersMusicTypes> addUsersMusicTypes(UsersMusicTypes musicType) {
        return usersMusicTypesDbStore.addUsersMusicTypes(musicType);
    }

    @Override
    public List<UsersMusicTypes> updateUsersMusicTypes(UsersMusicTypes musicType) {
        return usersMusicTypesDbStore.updateUsersMusicTypes(musicType);
    }

    @Override
    public List<UsersMusicTypes> deleteUsersMusicTypes(UsersMusicTypes musicType) {
        return usersMusicTypesDbStore.deleteUsersMusicTypes(musicType);
    }

    @Override
    public List<UsersMusicTypes> findByIdUsersMusicTypes(UsersMusicTypes musicType) {
        return usersMusicTypesDbStore.findByIdUsersMusicTypes(musicType);
    }

    @Override
    public List<UsersMusicTypes> findAllUsersMusicTypes(UsersMusicTypes musicType) {
        return usersMusicTypesDbStore.findAllUsersMusicTypes(musicType);
    }


}
