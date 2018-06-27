package com.solomatoff.mvc.model.repository;

import com.solomatoff.mvc.entities.*;
import com.solomatoff.mvc.model.repository.address.specificationimplements.AddressSpecificationAllAddress;
import com.solomatoff.mvc.model.repository.address.specificationimplements.AddressSpecificationById;
import com.solomatoff.mvc.model.repository.role.specificationimplements.RoleSpecificationAllRoles;
import com.solomatoff.mvc.model.repository.user.specificationimplements.UserSpecificationByIdRange;
import com.solomatoff.mvc.model.repository.userroles.specificationimplements.UserRolesSpecificationAllUserRoles;
import com.solomatoff.mvc.model.repository.userroles.specificationimplements.UserRolesSpecificationByIdId;
import com.solomatoff.mvc.model.repository.userroles.specificationimplements.UserRolesSpecificationByRole;
import com.solomatoff.mvc.model.repository.userroles.specificationimplements.UserRolesSpecificationByUser;
import com.solomatoff.mvc.model.repository.usersmusictypes.specificationimplements.UsersMusicTypesSpecificationAllUsersMusicTypes;
import com.solomatoff.mvc.model.repository.usersmusictypes.specificationimplements.UsersMusicTypesSpecificationByIdId;
import com.solomatoff.mvc.model.repository.usersmusictypes.specificationimplements.UsersMusicTypesSpecificationByUser;

import java.util.ArrayList;
import java.util.List;

public interface RepositorySql {
    void addAddress(Address address);
    void removeAddress(Address address);
    void updateAddress(Address address);
    List queryAddress(SqlSpecification sqlSpecification);

    void addRole(Role role);
    void removeRole(Role role);
    void updateRole(Role role);
    List queryRole(SqlSpecification sqlSpecification);

    void addMusicType(MusicType musicType);
    void removeMusicType(MusicType musicType);
    void updateMusicType(MusicType musicType);
    List queryMusicType(SqlSpecification sqlSpecification);

    void addUser(User user);
    void removeUser(User user);
    void updateUser(User user);
    List queryUser(SqlSpecification sqlSpecification);

    void addUserRoles(UserRoles userRoles);
    void removeUserRoles(UserRoles userRoles);
    void updateUserRoles(UserRoles userRoles);
    List queryUserRoles(SqlSpecification sqlSpecification);

    void addUsersMusicTypes(UsersMusicTypes usersMusicTypes);
    void removeUsersMusicTypes(UsersMusicTypes usersMusicTypes);
    void updateUsersMusicTypes(UsersMusicTypes usersMusicTypes);
    List queryUsersMusicTypes(SqlSpecification sqlSpecification);

    /**
     * Метод реализует операцию получения всех связанных с User сущностей;
     * @param sqlSpecification спецификация пользователя (может быть и несколько пользователей)
     * @return список всех связанных с User сущностей
     */
    default List queryUserAllRelatedEntities(SqlSpecification sqlSpecification) {
        List result = new ArrayList();
        List<Address> addressList;
        List<UserRoles> userRolesList;
        List<UsersMusicTypes> usersMusicTypesList;

        List<User> users = queryUser(sqlSpecification);
        for (User user : users) {
            addressList = queryAddress(new AddressSpecificationById(user.getId()));
            result.add(addressList);
            userRolesList = queryUserRoles(new UserRolesSpecificationByUser(user.getId()));
            result.add(userRolesList);
            usersMusicTypesList = queryUsersMusicTypes(new UsersMusicTypesSpecificationByUser(user.getId()));
            result.add(usersMusicTypesList);
        }
        return result;
    }

    /**
     * Метод реализует операцию добавления нового User и всех связанных с ним сущностей;
     * @param user пользователь
     * @param address адрес
     * @param userRoles роли пользователя
     * @param usersMusicTypes музыкальные типы пользователя
     */
    default void addUserWithAllRelatedEntities(User user, Address address, List<UserRoles> userRoles, List<UsersMusicTypes> usersMusicTypes) {
        addUser(user);
        addAddress(address);
        for (UserRoles userRolesLoop : userRoles) {
            addUserRoles(userRolesLoop);
        }
        for (UsersMusicTypes usersMusicTypesLoop : usersMusicTypes) {
            addUsersMusicTypes(usersMusicTypesLoop);
        }
    }

    /**
     * Метод реализует операцию получения всех связанных с Role сущностей;
     * @param sqlSpecification спецификация роли (может быть и несколько ролей)
     * @return список всех связанных с Role сущностей
     */
    default List queryRoleAllRelatedEntities(SqlSpecification sqlSpecification) {
        List result = new ArrayList();
        List<User> users;

        List<Role> roleList = queryRole(sqlSpecification);
        //System.out.println("roleList = " + roleList);
        for (Role roleLoop : roleList) {
            SqlSpecification specificationByRole = new UserRolesSpecificationByRole(roleLoop.getId());
            //System.out.println("specificationByRole = " + specificationByRole.toSqlClauses());
            List<UserRoles> userRolesListByRole = queryUserRoles(specificationByRole);
            for (UserRoles userRolesLoop: userRolesListByRole) {
                SqlSpecification specificationByUser = new UserSpecificationByIdRange(userRolesLoop.getUserId(), userRolesLoop.getUserId());
                //System.out.println("specificationByUser = " + specificationByUser.toSqlClauses());
                users = queryUser(specificationByUser);
                for (User user : users) {
                    result.add(user);
                }
            }
        }
        return result;
    }

    /**
     * Метод реализует операцию поиска Users по заданным параметрам типа Address
     * @param addresses список параметров для поиска
     * @return список Users
     */
    default List queryUsersByAddresses(List<Address> addresses) {
        List result = new ArrayList();
        List<User> users;
        AddressSpecificationAllAddress addressSpecificationAllAddress = new AddressSpecificationAllAddress();
        List<Address> addressList = queryAddress(addressSpecificationAllAddress);

        for (Address addressLoop : addresses) {
            for (Address addressListLoop : addressList) {
                if (addressLoop.getStreet().equals(addressListLoop.getStreet())
                        && addressLoop.getHouse().equals(addressListLoop.getHouse())
                        && addressLoop.getApartment().equals(addressListLoop.getApartment())
                        && addressLoop.getCity().equals(addressListLoop.getCity())
                        && addressLoop.getZipcode().equals(addressListLoop.getZipcode())
                        && addressLoop.getCountry().equals(addressListLoop.getCountry())) {
                    SqlSpecification specification = new UserSpecificationByIdRange(addressListLoop.getUserId(), addressListLoop.getUserId());
                    users = queryUser(specification);
                    for (User userLoop : users) {
                        result.add(userLoop);
                    }
                }
            }

        }
        return result;
    }

    /**
     * Метод реализует операцию поиска Users по заданным параметрам типа Role
     * @param roles список параметров для поиска
     * @return список Users
     */
    default List queryUsersByRoles(List<Role> roles) {
        List result = new ArrayList();
        List<User> users;
        UserRolesSpecificationAllUserRoles userRolesSpecificationAllUserRoles = new UserRolesSpecificationAllUserRoles();
        List<UserRoles> userRolesList = queryUserRoles(userRolesSpecificationAllUserRoles);

        for (Role roleLoop : roles) {
            for (UserRoles userRolesListLoop : userRolesList) {
                if (roleLoop.getId().equals(userRolesListLoop.getRoleId())) {
                    SqlSpecification specification = new UserSpecificationByIdRange(userRolesListLoop.getUserId(), userRolesListLoop.getUserId());
                    users = queryUser(specification);
                    for (User userLoop : users) {
                        result.add(userLoop);
                    }
                }
            }

        }
        return result;
    }

    /**
     * Метод реализует операцию поиска Users по заданным параметрам типа MusicType
     * @param musicTypes список параметров для поиска
     * @return список Users
     */
    default List queryUsersByMusicTypes(List<MusicType> musicTypes) {
        List result = new ArrayList();
        List<User> users;
        UsersMusicTypesSpecificationAllUsersMusicTypes usersMusicTypesSpecificationAllUsersMusicTypes = new UsersMusicTypesSpecificationAllUsersMusicTypes();
        List<UsersMusicTypes> userMusicTypesList = queryUsersMusicTypes(usersMusicTypesSpecificationAllUsersMusicTypes);

        for (MusicType musicTypeLoop : musicTypes) {
            for (UsersMusicTypes userMusicTypesListLoop : userMusicTypesList) {
                if (musicTypeLoop.getId().equals(userMusicTypesListLoop.getMusicTypeId())) {
                    SqlSpecification specification = new UserSpecificationByIdRange(userMusicTypesListLoop.getUserId(), userMusicTypesListLoop.getUserId());
                    users = queryUser(specification);
                    for (User userLoop : users) {
                        result.add(userLoop);
                    }
                }
            }

        }
        return result;
    }
}
