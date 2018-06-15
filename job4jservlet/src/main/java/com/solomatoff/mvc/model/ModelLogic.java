package com.solomatoff.mvc.model;

import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс представляет собой слой логики, является частью Model модели mvc
 */
public class ModelLogic {
    // Переменная для объекта слоя Persistent
    private ModelStore persistent = new DbStore(); // По умолчанию
    public ModelStore getPersistent() {
        return persistent;
    }
    public void setPersistent(ModelStore persistent) {
        this.persistent = persistent;
    }

    public List<User> addUser(User user) {
        List<User> result = new ArrayList<>();
        if (user.getLogin() == null || user.getPassword() == null || user.getIdRole() == null || user.getLogin().equals("") || user.getPassword().equals("")) {
            LoggerApp.getInstance().getLog().error(String.format("(ADD) user with id = %4d: login, password and idRole can not be empty", user.getId()));
        } else {
            List<User> usersById = persistent.findByIdUser(user);
            if (usersById.size() == 0) {
                List<User> usersByLogin = persistent.findByLoginUser(user);
                if (usersByLogin.size() > 0) {
                    User userBylogin = usersByLogin.get(0);
                    if (userBylogin != null && !userBylogin.getId().equals(user.getId())) {
                        LoggerApp.getInstance().getLog().error(String.format("(ADD) user with login = %s already exists. Login must be unique", user.getLogin()));
                    } else {
                        result = persistent.addUser(user);
                    }
                } else {
                    result = persistent.addUser(user);
                }
            } else {
                LoggerApp.getInstance().getLog().error(String.format("(ADD) user with id = %s already exists", user.getId()));
            }
        }
        return result;
    }

    public List<User> updateUser(User user) {
        List<User> result = new ArrayList<>();
        if (user.getLogin() == null || user.getPassword() == null || user.getIdRole() == null || user.getLogin().equals("") || user.getPassword().equals("")) {
            LoggerApp.getInstance().getLog().error(String.format("(UPDATE) user with id = %4d: login, password and idRole can not be empty", user.getId()));
        } else {
            List<User> usersById = persistent.findByIdUser(user);
            if (usersById.size() > 0) {
                List<User> usersByLogin = persistent.findByLoginUser(user);
                if (usersByLogin.size() > 0) {
                    User userBylogin = usersByLogin.get(0);
                    if (userBylogin != null && !userBylogin.getId().equals(user.getId())) {
                        LoggerApp.getInstance().getLog().error(String.format("(UPDATE) user with login = %s already exists. Login must be unique", user.getLogin()));
                    } else {
                        result = persistent.updateUser(user);
                    }
                } else {
                    result = persistent.updateUser(user);
                }
            } else {
                LoggerApp.getInstance().getLog().error(String.format("(UPDATE) user with id = %s does not exists", user.getId()));
            }
        }
        return result;
    }

    public List<User> deleteUser(User user) {
        if (persistent.findByIdUser(user).size() > 0) {
            return persistent.deleteUser(user);
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(DELETE) user with id = %4d not exists", user.getId()));
            return new ArrayList<>();
        }
    }

    public List<User> deleteUserAll(User user) {
        return persistent.deleteUserAll(user);
    }

    public List<User> findByIdUser(User user) {
        List<User> users = persistent.findByIdUser(user);
        if (users.size() > 0) {
            return users;
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(FINDBYID) user with id = %4d not exists", user.getId()));
            return new ArrayList<>();
        }
    }

    public List<User> findByLoginUser(User user) {
        List<User> users = persistent.findByLoginUser(user);
        if (users.size() > 0) {
            return users;
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(FINDBYLOGIN) user with login = %s not exists", user.getLogin()));
            return new ArrayList<>();
        }
    }

    public List<User> findByIdRoleUser(User user) {
        List<User> users = persistent.findByIdRoleUser(user);
        if (users.size() > 0) {
            return users;
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(FINDBYIDROLEUSER) user with idRole = %4d not exists", user.getIdRole()));
            return new ArrayList<>();
        }
    }

    public List<User> findAllUsers(User user) {
        return persistent.findAllUsers(user);
    }

    public boolean isCredentional(String login, String password) {
        return persistent.isCredentional(login, password);
    }

    public List<Role> addRole(Role role) {
        if (role.getId() != null && role.getIsAdmin() != null) {
            if (persistent.findByIdRole(role).size() == 0) {
                return persistent.addRole(role);
            } else {
                LoggerApp.getInstance().getLog().error(String.format("(ADD) role with id = %4d: already exists", role.getId()));
                return new ArrayList<>();
            }
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(ADD) role with id = %4d: id and isAdmin can not be empty", role.getId()));
            return new ArrayList<>();
        }
    }

    public List<Role> updateRole(Role role) {
        if (persistent.findByIdRole(role).size() > 0) {
            return persistent.updateRole(role);
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(UPDATE) role with id = %4d not exists", role.getId()));
            return new ArrayList<>();
        }
    }

    public List<Role> deleteRole(Role role) {
        if (persistent.findByIdRole(role).size() > 0) {
            return persistent.deleteRole(role);
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(DELETE) role with id = %4d not exists", role.getId()));
            return new ArrayList<>();
        }
    }

    public List<Role> deleteRoleAll(Role role) {
        return persistent.deleteRoleAll(role);
    }

    public List<Role> findByIdRole(Role role) {
        List<Role> roles = persistent.findByIdRole(role);
        if (roles.size() > 0) {
            return roles;
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(FINDBYIDROLE) role with id = %4d not exists", role.getId()));
            return new ArrayList<>();
        }
    }

    public List<Role> findAllRoles(Role role) {
        return persistent.findAllRoles(role);
    }

}
