package com.solomatoff.mvc.entities;

import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.model.ModelLogic;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class User {
    private Integer id;
    private String name;
    private String login;
    private String password;
    private String email;
    private Timestamp createDate;
    private String nameRoles;
    private String nameMusicTypes;
    private String nameAddress;

    public User() {
    }

    public User(Integer id, String name, String login, String password, String email, Timestamp createDate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameRoles() {
        StringBuilder result = new StringBuilder();
        ModelLogic modelLogic = Controller.getInstance().getLogic();
        List<Role> roles;
        List<UserRoles> userRoles = modelLogic.findByIdUserUserRoles(new UserRoles(this.id, null));
        if (userRoles.size() > 0) {
            int i = 0;
            for (UserRoles userRolesLoop : userRoles) {
                roles = modelLogic.findByIdRole(new Role(userRolesLoop.getRoleId(), null, null));
                if (roles.size() > 0) {
                    if (i != 0) {
                        result.append(", ");
                    }
                    result.append(roles.get(0).getName());
                }
                i++;
            }
        }
        return result.toString();
    }

    public String getNameMusicTypes() {
        StringBuilder result = new StringBuilder();
        ModelLogic modelLogic = Controller.getInstance().getLogic();
        List<MusicType> musicTypes;
        List<UsersMusicTypes> usersMusicTypes = modelLogic.findByIdUsersMusicTypes(new UsersMusicTypes(this.id, null));
        if (usersMusicTypes.size() > 0) {
            int i = 0;
            for (UsersMusicTypes usersMusicTypesLoop : usersMusicTypes) {
                musicTypes = modelLogic.findByIdMusicType(new MusicType(usersMusicTypesLoop.getMusicTypeId(), null));
                if (musicTypes.size() > 0) {
                    if (i != 0) {
                        result.append(", ");
                    }
                    result.append(musicTypes.get(0).getMusicTypeName());
                }
                i++;
            }
        }
        return result.toString();
    }

    public String getNameAddress() {
        StringBuilder result = new StringBuilder();
        ModelLogic modelLogic = Controller.getInstance().getLogic();
        Address address = new Address();
        address.setUserId(this.id);
        List<Address> addresses = modelLogic.findByIdAddress(address);
        if (addresses.size() == 1) {
            address = addresses.get(0);
            result.append(address.getStreet());
            result.append(", ");
            result.append(address.getHouse());
            result.append(", ");
            result.append(address.getApartment());
            result.append(", ");
            result.append(address.getCity());
            result.append(", ");
            result.append(address.getZipcode());
            result.append(", ");
            result.append(address.getCountry());
            }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id)
                && Objects.equals(name, user.name)
                && Objects.equals(login, user.login)
                && Objects.equals(password, user.password)
                && Objects.equals(email, user.email)
                && Objects.equals(createDate, user.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, password, email, createDate);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", login='" + login + '\'' + ", password='" + password + '\'' + ", email='" + email + '\'' + ", createDate=" + createDate + '}';
    }
}
