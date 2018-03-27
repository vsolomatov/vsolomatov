package com.solomatoff.generic;

import java.util.NoSuchElementException;

public class UserStoreOld implements Store<User> {
    private SimpleArray<User> users;

    UserStoreOld(int size) {
        this.users = new SimpleArray<>(size);
    }
    public void add(User model) {
        this.users.add(model);
    }

    public boolean replace(String id, User model) {
        boolean result = true;
        try {
            this.users.set(getIndexById(id), model);
        } catch (NoSuchElementException nsee) {
            result = false;
        }
        return result;
    }

    public boolean delete(String id) {
        boolean result = true;
        try {
            this.users.delete(getIndexById(id));
        } catch (NoSuchElementException nsee) {
            result = false;
        }
        return result;
    }

    public User findById(String id) throws NoSuchElementException {
        User result = null;
        for (User user : this.users) {
            if (user.getId().equals(id)) {
                result = user;
                break;
            }
        }
        if (result == null) {
            throw new NoSuchElementException();
        }
        return result;
    }

    private int getIndexById(String id) {
        int result = -1;
        for (User user : this.users) {
            if (user.getId().equals(id)) {
                result = this.users.getCurrentIndex() - 1;
                break;
            }
        }
        return result;
    }
}
