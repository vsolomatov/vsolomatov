package com.solomatoff.mvc.model;

import com.solomatoff.mvc.controller.LoggerApp;
import com.solomatoff.mvc.entities.*;
import com.solomatoff.mvc.model.store.DbStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс представляет собой слой логики, является частью (Model) модели mvc
 */
public class ModelLogic {
    // Переменная для объекта слоя Persistent
    private PersistentDAO persistent = null; // По умолчанию

    public PersistentDAO getPersistent() {
        return persistent;
    }
    public void setPersistent(PersistentDAO persistent) {
        this.persistent = persistent;
    }

    public List<User> addUser(User user) {
        List<User> result = new ArrayList<>();
        if (user.getLogin() == null || user.getPassword() == null || user.getLogin().equals("") || user.getPassword().equals("")) {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic ADD) user with id = %4d: login, password and idRole can not be empty", user.getId()));
        } else {
            List<User> usersById = persistent.findByIdUser(user);
            if (usersById == null || usersById.size() == 0) {
                List<User> usersByLogin = persistent.findByLoginUser(user);
                if (usersByLogin != null && usersByLogin.size() > 0) {
                    User userBylogin = usersByLogin.get(0);
                    if (userBylogin != null && !userBylogin.getId().equals(user.getId())) {
                        LoggerApp.getInstance().getLog().error(String.format("(ModelLogic ADD) user with login = %s already exists. Login must be unique", user.getLogin()));
                    } else {
                        result = persistent.addUser(user);
                    }
                } else {
                    result = persistent.addUser(user);
                }
            } else {
                LoggerApp.getInstance().getLog().error(String.format("(ModelLogic ADD) user with id = %s already exists", user.getId()));
            }
        }
        return result;
    }

    public List<User> updateUser(User user) {
        List<User> result = new ArrayList<>();
        if (user.getLogin() == null || user.getPassword() == null || user.getLogin().equals("") || user.getPassword().equals("")) {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic UPDATE) user with id = %4d: login, password and idRole can not be empty", user.getId()));
        } else {
            List<User> usersById = persistent.findByIdUser(user);
            if (usersById.size() > 0) {
                List<User> usersByLogin = persistent.findByLoginUser(user);
                if (usersByLogin.size() > 0) {
                    User userBylogin = usersByLogin.get(0);
                    if (userBylogin != null && !userBylogin.getId().equals(user.getId())) {
                        LoggerApp.getInstance().getLog().error(String.format("(ModelLogic UPDATE) user with login = %s already exists. Login must be unique", user.getLogin()));
                    } else {
                        result = persistent.updateUser(user);
                    }
                } else {
                    result = persistent.updateUser(user);
                }
            } else {
                LoggerApp.getInstance().getLog().error(String.format("(ModelLogic UPDATE) user with id = %s does not exists", user.getId()));
            }
        }
        return result;
    }

    public List<User> deleteUser(User user) {
        if (persistent.findByIdUser(user).size() > 0) {
            return persistent.deleteUser(user);
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic DELETE) user with id = %4d not exists", user.getId()));
            return new ArrayList<>();
        }
    }

    public List<User> findByIdUser(User user) {
        List<User> users = persistent.findByIdUser(user);
        if (users.size() > 0) {
            return users;
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic findByIdUser) user with id = %4d not exists", user.getId()));
            return new ArrayList<>();
        }
    }

    public List<User> findByLoginUser(User user) {
        List<User> users = persistent.findByLoginUser(user);
        if (users.size() > 0) {
            return users;
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic findByLoginUser) user with login = %s not exists", user.getLogin()));
            return new ArrayList<>();
        }
    }

    public List<User> findByIdRoleUser(Role role) {
        List<User> users = persistent.findByIdRoleUser(role);
        if (users.size() > 0) {
            return users;
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic findByIdRoleUser) user with idRole = %4d not exists", role.getId()));
            return new ArrayList<>();
        }
    }

    public List<User> findAllUsers(User user) {
        return persistent.findAllUsers(user);
    }

    public List<User> deleteUserAll(User user) {
        return persistent.deleteUserAll(user);
    }

    public List<Role> addRole(Role role) {
        if (role.getId() != null && role.getIsAdmin() != null) {
            List<Role> roles = persistent.findByIdRole(role);
            if (roles == null || roles.size() == 0) {
                return persistent.addRole(role);
            } else {
                LoggerApp.getInstance().getLog().error(String.format("(ModelLogic ADD) role with id = %4d: already exists", role.getId()));
                return new ArrayList<>();
            }
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic ADD) role with id = %4d: id and isAdmin can not be empty", role.getId()));
            return new ArrayList<>();
        }
    }

    public List<Role> updateRole(Role role) {
        if (persistent.findByIdRole(role).size() > 0) {
            return persistent.updateRole(role);
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic UPDATE) role with id = %4d not exists", role.getId()));
            return new ArrayList<>();
        }
    }

    public List<Role> deleteRole(Role role) {
        if (persistent.findByIdRole(role).size() > 0) {
            return persistent.deleteRole(role);
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic DELETE) role with id = %4d not exists", role.getId()));
            return new ArrayList<>();
        }
    }

    public List<Role> findByIdRole(Role role) {
        List<Role> roles = persistent.findByIdRole(role);
        if (roles.size() > 0) {
            return roles;
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic findByIdRole) role with id = %4d not exists", role.getId()));
            return new ArrayList<>();
        }
    }

    public List<Role> findAllRoles(Role role) {
        return persistent.findAllRoles(role);
    }

    public List<Role> deleteRoleAll(Role role) {
        return persistent.deleteRoleAll(role);
    }

    public List<Address> addAddress(Address address) {
        if (address.getUserId() != null) {
            List<Address> addresss = persistent.findByIdAddress(address);
            if (addresss == null || addresss.size() == 0) {
                return persistent.addAddress(address);
            } else {
                LoggerApp.getInstance().getLog().error(String.format("(ModelLogic ADD) address with userid = %4d: already exists", address.getUserId()));
                return new ArrayList<>();
            }
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic ADD) address with userid = %4d: userid  can not be empty", address.getUserId()));
            return new ArrayList<>();
        }
    }

    public List<Address> updateAddress(Address address) {
        if (persistent.findByIdAddress(address).size() > 0) {
            return persistent.updateAddress(address);
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic UPDATE) Address with id = %4d not exists", address.getUserId()));
            return new ArrayList<>();
        }
    }

    public List<Address> deleteAddress(Address address) {
        if (persistent.findByIdAddress(address).size() > 0) {
            return persistent.deleteAddress(address);
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic DELETE) Address with id = %4d not exists", address.getUserId()));
            return new ArrayList<>();
        }
    }

    public List<Address> findByIdAddress(Address address) {
        List<Address> addresses = persistent.findByIdAddress(address);
        if (addresses.size() > 0) {
            return addresses;
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic findByIdAddress) Address with id = %4d not exists", address.getUserId()));
            return new ArrayList<>();
        }
    }

    public List<Address> findAllAddress(Address address) {
        return persistent.findAllAddresses(address);
    }

    public List<Address> deleteAllAddresses(Address address) {
        return persistent.deleteAllAddresses(address);
    }

    public List<MusicType> addMusicType(MusicType musicType) {
        List<MusicType> result = new ArrayList<>();
        if (musicType.getId() != null) {
            List<MusicType> list = persistent.findByIdMusicType(musicType);
            if (list == null || list.size() == 0) {
                result.add(persistent.addMusicType(musicType).get(0));
            } else {
                LoggerApp.getInstance().getLog().error(String.format("(ModelLogic ADD) MusicType with id = %4d: already exists", musicType.getId()));
            }
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic ADD) MusicType with id = %4d: id and isAdmin can not be empty", musicType.getId()));
        }
        return result;
    }

    public List<MusicType> updateMusicType(MusicType musicType) {
        if (persistent.findByIdMusicType(musicType).size() > 0) {
            return persistent.updateMusicType(musicType);
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic UPDATE) MusicType with id = %4d not exists", musicType.getId()));
            return new ArrayList<>();
        }
    }

    public List<MusicType> deleteMusicType(MusicType musicType) {
        if (persistent.findByIdMusicType(musicType).size() > 0) {
            return persistent.deleteMusicType(musicType);
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic DELETE) MusicType with id = %4d not exists", musicType.getId()));
            return new ArrayList<>();
        }
    }

    public List<MusicType> findByIdMusicType(MusicType musicType) {
        List<MusicType> musicTypes = persistent.findByIdMusicType(musicType);
        if (musicTypes.size() > 0) {
            return musicTypes;
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic findByIdMusicType) MusicType with id = %4d not exists", musicType.getId()));
            return new ArrayList<>();
        }
    }

    public List<MusicType> findAllMusicTypes(MusicType musicType) {
        return persistent.findAllMusicTypes(musicType);
    }

    public List<MusicType> deleteAllMusicTypes(MusicType musicType) {
        return persistent.deleteAllMusicTypes(musicType);
    }

    public List<UserRoles> addUserRoles(UserRoles userRoles) {
        boolean exists = false;
        List<UserRoles> result = new ArrayList<>();
        if (userRoles.getUserId() != null && userRoles.getRoleId() != null) {
            List<UserRoles> list = persistent.findByIdUserUserRoles(userRoles);
            if (list.size() > 0) {
                for (UserRoles userRolesLoop : list) {
                    if (userRolesLoop.getRoleId() == userRoles.getRoleId()) {
                        exists = true;
                        break;
                    }
                }
            }
            if (!exists) {
                result.add(persistent.addUserRoles(userRoles).get(0));
            } else {
                LoggerApp.getInstance().getLog().error(String.format("(ModelLogic ADD) UserRoles with userid = %4d and roleid = %4d: already exists", userRoles.getUserId(), userRoles.getRoleId()));
            }
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic ADD) UserRoles with id = %4d: userid and roleid can not be empty", userRoles.getUserId()));
        }
        return result;
    }

    public List<UserRoles> updateUserRoles(UserRoles userRoles) {
        if (persistent.findByIdUserUserRoles(userRoles).size() > 0) {
            return persistent.updateUserRoles(userRoles);
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic UPDATE) UserRoles with id = %4d not exists", userRoles.getUserId()));
            return new ArrayList<>();
        }
    }

    public List<UserRoles> deleteUserRoles(UserRoles userRoles) {
        boolean exists = false;
        List<UserRoles> result = new ArrayList<>();
        if (userRoles.getUserId() != null && userRoles.getRoleId() != null) {
            List<UserRoles> list = persistent.findByIdUserUserRoles(userRoles);
            if (list.size() > 0) {
                for (UserRoles userRolesLoop : list) {
                    if (userRolesLoop.getRoleId() == userRoles.getRoleId()) {
                        exists = true;
                        break;
                    }
                }
            }
            if (exists) {
                result.add(persistent.deleteUserRoles(userRoles).get(0));
            } else {
                LoggerApp.getInstance().getLog().error(String.format("(ModelLogic ADD) UserRoles with userid = %4d and roleid = %4d: not exists", userRoles.getUserId(), userRoles.getRoleId()));
            }
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic DELETE) UserRoles with id = %4d. Userid can not be null", userRoles.getUserId()));
        }
        return result;
    }

    public List<UserRoles> findByIdUserUserRoles(UserRoles userRoles) {
        List<UserRoles> roles = persistent.findByIdUserUserRoles(userRoles);
        if (roles.size() > 0) {
            return roles;
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic findByIdUserUserRoles) user with idRole = %4d not exists", userRoles.getUserId()));
            return new ArrayList<>();
        }
    }

    public List<UserRoles> findAllUserRoles(UserRoles userRoles) {
        return persistent.findAllUserRoles(userRoles);
    }

    public List<UserRoles> deleteAllUserRoles(UserRoles userRoles) {
        return persistent.deleteAllUserRoles(userRoles);
    }


    public List<UsersMusicTypes> addUsersMusicTypes(UsersMusicTypes usersMusicTypes) {
        //System.out.println("ModelLogic 1.(addUsersMusicTypes) param method usersMusicType = " + usersMusicTypes);
        boolean exists = false;
        List<UsersMusicTypes> result = new ArrayList<>();
        if (usersMusicTypes.getUserId() != null && usersMusicTypes.getMusicTypeId() != null) {
            List<UsersMusicTypes> list = persistent.findByIdUsersMusicTypes(usersMusicTypes);
            if (list.size() > 0) {
                for (UsersMusicTypes usersMusicTypesLoop : list) {
                    if (usersMusicTypesLoop.getMusicTypeId() == usersMusicTypes.getMusicTypeId()) {
                        exists = true;
                        break;
                    }
                }
            }
            if (!exists) {
                result.add(persistent.addUsersMusicTypes(usersMusicTypes).get(0));
            } else {
                LoggerApp.getInstance().getLog().error(String.format("(ModelLogic ADD) UsersMusicTypes with userid = %4d and nusictypeid = %4d: already exists", usersMusicTypes.getUserId(), usersMusicTypes.getMusicTypeId()));
            }
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic ADD) UsersMusicTypes with userid = %4d: userid and musictypeid can not be empty", usersMusicTypes.getUserId()));
        }
        return result;
    }

    public List<UsersMusicTypes> updateUsersMusicTypes(UsersMusicTypes usersMusicTypes) {
        if (persistent.findByIdUsersMusicTypes(usersMusicTypes).size() > 0) {
            return persistent.updateUsersMusicTypes(usersMusicTypes);
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic UPDATE) UsersMusicTypes with id = %4d not exists", usersMusicTypes.getUserId()));
            return new ArrayList<>();
        }
    }

    public List<UsersMusicTypes> deleteUsersMusicTypes(UsersMusicTypes usersMusicTypes) {
        boolean exists = false;
        List<UsersMusicTypes> result = new ArrayList<>();
        if (usersMusicTypes.getUserId() != null && usersMusicTypes.getMusicTypeId() != null) {
            List<UsersMusicTypes> list = persistent.findByIdUsersMusicTypes(usersMusicTypes);
            if (list.size() > 0) {
                for (UsersMusicTypes usersMusicTypesLoop : list) {
                    if (usersMusicTypesLoop.getMusicTypeId() == usersMusicTypes.getMusicTypeId()) {
                        exists = true;
                        break;
                    }
                }
            }
            if (exists) {
                result.add(persistent.deleteUsersMusicTypes(usersMusicTypes).get(0));
            } else {
                LoggerApp.getInstance().getLog().error(String.format("(ModelLogic ADD) UsersMusicTypes with userid = %4d and musicTypeId = %4d: not exists", usersMusicTypes.getUserId(), usersMusicTypes.getMusicTypeId()));
            }
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic DELETE) UsersMusicTypes with id = %4d. Userid can not be null", usersMusicTypes.getUserId()));
        }
        return result;
    }

    public List<UsersMusicTypes> findByIdUsersMusicTypes(UsersMusicTypes usersMusicTypes) {
        List<UsersMusicTypes> usersMusicTypess = persistent.findByIdUsersMusicTypes(usersMusicTypes);
        if (usersMusicTypess.size() > 0) {
            return usersMusicTypess;
        } else {
            LoggerApp.getInstance().getLog().error(String.format("(ModelLogic findByIdUsersMusicTypes) UsersMusicTypes with id = %4d not exists", usersMusicTypes.getUserId()));
            return new ArrayList<>();
        }
    }

   public List<UsersMusicTypes> findAllUsersMusicTypes(UsersMusicTypes usersMusicTypes) {
        return persistent.findAllUsersMusicTypes(usersMusicTypes);
    }

    public List<UsersMusicTypes> deleteAllUsersMusicTypes(UsersMusicTypes usersMusicTypes) {
        return persistent.deleteAllUsersMusicTypes(usersMusicTypes);
    }

    public boolean isCredentional(String login, String password) {
        return persistent.isCredentional(login, password);
    }
}
