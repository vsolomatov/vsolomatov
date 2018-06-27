package com.solomatoff.mvc.controller;

import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.entities.UserRoles;
import com.solomatoff.mvc.model.ModelLogic;
import com.solomatoff.mvc.views.ViewLyaer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Controller {
    private static Controller singletonInstance = new Controller();

    public static Controller getInstance() {
        return singletonInstance;
    }

    private Controller() {
        init();
    }

    // Переменная для объекта слоя Presentation
    private ViewLyaer presentation;
    public ViewLyaer getPresentation() {
        return presentation;
    }
    public void setPresentation(ViewLyaer presentation) {
        this.presentation = presentation;
    }

    // Переменная для объекта слоя Logic
    // Слой логики (контроллер обращается к нему, а он уже обращается к Persistent)
    // Т.е. это прокладка между Controller и ModelStore
    private final ModelLogic logic = new ModelLogic();
    public ModelLogic getLogic() {
        return logic;
    }

    /**
     * Содержит список функций, реализующих поддерживаемые действия с user
     */
    private Map<TypeAction, Function<User, List<User>>> actions = new HashMap<>();

    /**
     * Содержит список функций, реализующих поддерживаемые действия с role
     */
    private Map<TypeAction, Function<Role, List<Role>>> actionsrole = new HashMap<>();


    /**
     * Execute action
     * @param typeAction type action
     * @return User List
     */
    public List<User> executeActionUser(String typeAction, User user) {
        return this.actions.get(getTypeAction(typeAction)).apply(user);
    }

    /**
     * Execute action
     * @param typeAction type action
     * @return User List
     */
    public List<Role> executeActionRole(String typeAction, Role role) {
        return this.actionsrole.get(getTypeAction(typeAction)).apply(role);
    }

    // Перечень поддерживаемых действий
    enum TypeAction { CREATE_USER, UPDATE_USER, DELETE_USER, DELETEALL_USER, FINDBYID_USER, FINDBYLOGIN_USER, FINDBYIDROLE_USER, FINDALL_USERS, CREATE_ROLE, UPDATE_ROLE, DELETE_ROLE, DELETEALL_ROLE, FINDBYID_ROLE, FINDALL_ROLES }

    /**
     * Получает параметр-действие в виде значения типа TypeAction
     * @param paramAction параметр-действие в виде строки
     * @return параметр-действие в виде значения типа TypeAction
     */
    private TypeAction getTypeAction(String paramAction) {
        TypeAction typeAction = null;
        switch (paramAction) {
            case "Create User":
                typeAction = TypeAction.CREATE_USER;
                break;
            case "Update User":
                typeAction = TypeAction.UPDATE_USER;
                break;
            case "Delete User":
                typeAction = TypeAction.DELETE_USER;
                break;
            case "Delete All User":
                typeAction = TypeAction.DELETEALL_USER;
                break;
            case "Find By Id User":
                typeAction = TypeAction.FINDBYID_USER;
                break;
            case "Find By Login User":
                typeAction = TypeAction.FINDBYLOGIN_USER;
                break;
            case "Find By IdRole User":
                typeAction = TypeAction.FINDBYIDROLE_USER;
                break;
            case "Find All Users":
                typeAction = TypeAction.FINDALL_USERS;
                break;
            case "Create Role":
                typeAction = TypeAction.CREATE_ROLE;
                break;
            case "Update Role":
                typeAction = TypeAction.UPDATE_ROLE;
                break;
            case "Delete Role":
                typeAction = TypeAction.DELETE_ROLE;
                break;
            case "Delete All Role":
                typeAction = TypeAction.DELETEALL_ROLE;
                break;
            case "Find By Id Role":
                typeAction = TypeAction.FINDBYID_ROLE;
                break;
            case "Find All Roles":
                typeAction = TypeAction.FINDALL_ROLES;
                break;
            default:
        }
        return typeAction;
    }

    /**
     * Init's
     */
    private void init() {
        if (actions.isEmpty()) {
            this.loadactionsuser(TypeAction.CREATE_USER, this.userAdd());
            this.loadactionsuser(TypeAction.UPDATE_USER, this.userUpdate());
            this.loadactionsuser(TypeAction.DELETE_USER, this.userDelete());
            this.loadactionsuser(TypeAction.DELETEALL_USER, this.userDeleteAll());
            this.loadactionsuser(TypeAction.FINDBYID_USER, this.userFindById());
            this.loadactionsuser(TypeAction.FINDBYLOGIN_USER, this.userFindByLogin());
            this.loadactionsuser(TypeAction.FINDALL_USERS, this.userFindAll());
        }
        if (actionsrole.isEmpty()) {
            this.loadactionsrole(TypeAction.CREATE_ROLE, this.roleAdd());
            this.loadactionsrole(TypeAction.UPDATE_ROLE, this.roleUpdate());
            this.loadactionsrole(TypeAction.DELETE_ROLE, this.roleDelete());
            this.loadactionsrole(TypeAction.DELETEALL_ROLE, this.roleDeleteAll());
            this.loadactionsrole(TypeAction.FINDBYID_ROLE, this.roleFindById());
            this.loadactionsrole(TypeAction.FINDALL_ROLES, this.roleFindAll());
        }
    }

    /**
     * Load handlers for actions
     * @param typeAction action type.
     * @param handle Handler.
     */
    private void loadactionsuser(TypeAction typeAction, Function<User, List<User>> handle) {
        this.actions.put(typeAction, handle);
    }

    /**
     * Load handlers for actions
     * @param typeAction action type.
     * @param handle Handler.
     */
    private void loadactionsrole(TypeAction typeAction, Function<Role, List<Role>> handle) {
        this.actionsrole.put(typeAction, handle);
    }

    /**
     * Add User
     * @return null
     */
    private Function<User, List<User>> userAdd() {
        return logic::addUser;
    }

    /**
     * Update User
     * @return user updated
     */
    private Function<User, List<User>> userUpdate() {
        return logic::updateUser;
    }

    /**
     * Delete User
     * @return user deleted
     */
    private Function<User, List<User>> userDelete() {
        return logic::deleteUser;
    }

    /**
     * Delete All Users
     * @return users deleted
     */
    private Function<User, List<User>> userDeleteAll() {
        return logic::deleteUserAll;
    }


    /**
     * Find User By Id
     * @return user finded
     */
    private Function<User, List<User>> userFindById() {
        return logic::findByIdUser;
    }

    /**
     * Find User By Login
     * @return user finded
     */
    private Function<User, List<User>> userFindByLogin() {
        return logic::findByLoginUser;
    }

    /**
     * Find User By IdRole
     * @return users finded
     */
    //private Function<User, List<User>> userFindByIdRole() {
    //    return logic::findByIdRoleUser;
    //}

    /**
     * Receives All Users
     * @return Users List
     */
    private Function<User, List<User>> userFindAll() {
        return logic::findAllUsers;
    }

    /**
     * Add Role
     * @return null
     */
    private Function<Role, List<Role>> roleAdd() {
        return logic::addRole;
    }

    /**
     * Update Role
     * @return role updated
     */
    private Function<Role, List<Role>> roleUpdate() {
        return logic::updateRole;
    }

    /**
     * Delete Role
     * @return role deleted
     */
    private Function<Role, List<Role>> roleDelete() {
        return logic::deleteRole;
    }

    /**
     * Delete Roles
     * @return roles deleted
     */
    private Function<Role, List<Role>> roleDeleteAll() {
        return logic::deleteRoleAll;
    }

    /**
     * Find Role
     * @return role finded
     */
    private Function<Role, List<Role>> roleFindById() {
        return logic::findByIdRole;
    }

    /**
     * Receives All Roles
     * @return Roles List
     */
    private Function<Role, List<Role>> roleFindAll() {
        return logic::findAllRoles;
    }

    /**
     * Получает тип пользователя по логину
     * @param login логин пользователя
     * @return тип пользователя
     */
    public String getTypeUser(String login) {
        String result = "any user";
        User user = new User();
        user.setLogin(login);
        List<User> usersByLogin = getLogic().findByLoginUser(user);
        if (usersByLogin.size() == 1) {
            List<UserRoles> userRoles = logic.findByIdUserUserRoles(new UserRoles(usersByLogin.get(0).getId(), null));
            List<Role> list;
            Role role;
            for (UserRoles userRolesLoop : userRoles) {
                list = logic.findByIdRole(new Role(userRolesLoop.getRoleId(), null, null));
                if (list.size() > 0) {
                    role = list.get(0);
                    if (role.getIsAdmin()) {
                        result = "admin";
                    }
                }
            }
        } else {
            result = "unknown user";
        }
        return result;
    }
}
