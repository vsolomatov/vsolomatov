package com.solomatoff.mvc.controller;

import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.model.ModelLogic;
import com.solomatoff.mvc.views.ViewLyaer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    // Логгер
    private final Logger log = LoggerFactory.getLogger(Controller.class);
    public Logger getLog() {
        return log;
    }

    // Переменная для объекта слоя Presentation
    private ViewLyaer presentation;
    public ViewLyaer getPresentation() {
        return presentation;
    }
    public void setPresentation(ViewLyaer presentation) {
        this.presentation = presentation;
    }

    // Слой логики (контроллер обращается к нему, а он уже обращается к Persistent)
    // Т.е. это прокладка между Controller и ModelStore
    private final ModelLogic logic = new ModelLogic();

    /**
     * Execute action
     * @param typeAction type action
     * @return User List
     */
    public List<User> executeAction(String typeAction, User user) {
        return this.actions.get(getTypeAction(typeAction)).apply(user);
    }

    // Перечень поддерживаемых действий
    enum TypeAction { CREATE, UPDATE, DELETE, FINDBYID, FINDALL }

    /**
     * Получает параметр-действие в виде значения типа TypeAction
     * @param paramAction параметр-действие в виде строки
     * @return параметр-действие в виде значения типа TypeAction
     */
    private TypeAction getTypeAction(String paramAction) {
        TypeAction typeAction = null;
        switch (paramAction) {
            case "Create User":
                typeAction = TypeAction.CREATE;
                break;
            case "Update User":
                typeAction = TypeAction.UPDATE;
                break;
            case "Delete User":
                typeAction = TypeAction.DELETE;
                break;
            case "Find By Id":
                typeAction = TypeAction.FINDBYID;
                break;
            case "Find All":
                typeAction = TypeAction.FINDALL;
                break;
            default:
        }
        return typeAction;
    }

    /**
     * Содержит список функций, реализующих поддерживаемые действия
     */
    private Map<TypeAction, Function<User, List<User>>> actions = new HashMap<>();

    /**
     * Init's
     */
    private void init() {
        if (actions.isEmpty()) {
            this.load(TypeAction.CREATE, this.userAdd());
            this.load(TypeAction.UPDATE, this.userUpdate());
            this.load(TypeAction.DELETE, this.userDelete());
            this.load(TypeAction.FINDBYID, this.userFindById());
            this.load(TypeAction.FINDALL, this.userFindAll());
        }
    }

    /**
     * Load handlers for actions
     * @param typeAction action type.
     * @param handle Handler.
     */
    private void load(TypeAction typeAction, Function<User, List<User>> handle) {
        this.actions.put(typeAction, handle);
    }

    /**
     * Add User
     * @return null
     */
    private Function<User, List<User>> userAdd() {
        return logic::add;
    }

    /**
     * Update User
     * @return user updated
     */
    private Function<User, List<User>> userUpdate() {
        return logic::update;
    }

    /**
     * Delete User
     * @return user updated
     */
    private Function<User, List<User>> userDelete() {
        return logic::delete;
    }

    /**
     * Find User
     * @return user updated
     */
    private Function<User, List<User>> userFindById() {
        return logic::findById;
    }

    /**
     * Receives All Users
     * @return User List
     */
    private Function<User, List<User>> userFindAll() {
        return logic::findAll;
    }
}
