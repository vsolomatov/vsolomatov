package com.solomatoff.mvc.model.repository;

import com.solomatoff.mvc.entities.*;
import com.solomatoff.mvc.model.repository.address.AddressRepositoryDb;
import com.solomatoff.mvc.model.repository.musictype.MusicTypeRepositoryDb;
import com.solomatoff.mvc.model.repository.role.RoleRepositoryDb;
import com.solomatoff.mvc.model.repository.user.UserRepositoryDb;
import com.solomatoff.mvc.model.repository.userroles.UserRolesRepositoryDb;
import com.solomatoff.mvc.model.repository.usersmusictypes.UsersMusicTypesRepositoryDb;

import java.util.List;

public class RepositoryDb implements RepositorySql {
    private static RepositoryDb singletonInstance = new RepositoryDb();

    public RepositoryDb() {
    }

    public static RepositoryDb getInstance() {
        return singletonInstance;
    }

    private final AddressRepositoryDb addressRepositoryDb = new AddressRepositoryDb();
    private final MusicTypeRepositoryDb musicTypeRepositoryDb = new MusicTypeRepositoryDb();
    private final RoleRepositoryDb roleRepositoryDb = new RoleRepositoryDb();
    private final UserRepositoryDb userRepositoryDb = new UserRepositoryDb();
    private final UserRolesRepositoryDb userRolesRepositoryDb = new UserRolesRepositoryDb();
    private final UsersMusicTypesRepositoryDb usersMusicTypesRepositoryDb = new UsersMusicTypesRepositoryDb();
    
    @Override
    public void addAddress(Address address) {
        addressRepositoryDb.addAddress(address);
    }

    @Override
    public void removeAddress(Address address) {
        addressRepositoryDb.removeAddress(address);
    }

    @Override
    public void updateAddress(Address address) {
        addressRepositoryDb.updateAddress(address);
    }

    @Override
    public List queryAddress(SqlSpecification sqlSpecification) {
        return addressRepositoryDb.query(sqlSpecification);
    }

    @Override
    public void addRole(Role role) {
        roleRepositoryDb.addRole(role);
    }

    @Override
    public void removeRole(Role role) {
        roleRepositoryDb.removeRole(role);
    }

    @Override
    public void updateRole(Role role) {
        roleRepositoryDb.updateRole(role);
    }

    @Override
    public List queryRole(SqlSpecification sqlSpecification) {
        return roleRepositoryDb.query(sqlSpecification);
    }

    @Override
    public void addMusicType(MusicType musicType) {
        musicTypeRepositoryDb.addMusicType(musicType);
    }

    @Override
    public void removeMusicType(MusicType musicType) {
        musicTypeRepositoryDb.removeMusicType(musicType);
    }

    @Override
    public void updateMusicType(MusicType musicType) {
        musicTypeRepositoryDb.updateMusicType(musicType);
    }

    @Override
    public List queryMusicType(SqlSpecification sqlSpecification) {
        return musicTypeRepositoryDb.query(sqlSpecification);
    }

    @Override
    public void addUser(User user) {
        userRepositoryDb.addUser(user);
    }

    @Override
    public void removeUser(User user) {
        userRepositoryDb.removeUser(user);
    }

    @Override
    public void updateUser(User user) {
        userRepositoryDb.updateUser(user);
    }

    @Override
    public List queryUser(SqlSpecification sqlSpecification) {
        return userRepositoryDb.query(sqlSpecification);
    }

    @Override
    public void addUserRoles(UserRoles userRoles) {
        userRolesRepositoryDb.addUserRoles(userRoles);
    }

    @Override
    public void removeUserRoles(UserRoles userRoles) {
        userRolesRepositoryDb.removeUserRoles(userRoles);
    }

    @Override
    public void updateUserRoles(UserRoles userRoles) {
        userRolesRepositoryDb.updateUserRoles(userRoles);
    }

    @Override
    public List queryUserRoles(SqlSpecification sqlSpecification) {
        return userRolesRepositoryDb.query(sqlSpecification);
    }

    @Override
    public void addUsersMusicTypes(UsersMusicTypes usersMusicTypes) {
        usersMusicTypesRepositoryDb.addUsersMusicTypes(usersMusicTypes);
    }

    @Override
    public void removeUsersMusicTypes(UsersMusicTypes usersMusicTypes) {
        usersMusicTypesRepositoryDb.removeUsersMusicTypes(usersMusicTypes);
    }

    @Override
    public void updateUsersMusicTypes(UsersMusicTypes usersMusicTypes) {
        usersMusicTypesRepositoryDb.updateUsersMusicTypes(usersMusicTypes);
    }

    @Override
    public List queryUsersMusicTypes(SqlSpecification sqlSpecification) {
        return usersMusicTypesRepositoryDb.query(sqlSpecification);
    }
}
