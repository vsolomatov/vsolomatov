package com.solomatoff.chapterjunior001.generic;

import java.util.NoSuchElementException;

public class RoleStoreOld implements Store<Role> {
    private SimpleArray<Role> roles;

    RoleStoreOld(int size) {
        this.roles = new SimpleArray<>(size);
    }
    public void add(Role model) {
        this.roles.add(model);
    }

    public boolean replace(String id, Role model) {
        boolean result = true;
        try {
            this.roles.set(getIndexById(id), model);
        } catch (NoSuchElementException nsee) {
            result = false;
        }
        return result;
    }

    public boolean delete(String id) {
        boolean result = true;
        try {
            this.roles.delete(getIndexById(id));
        } catch (NoSuchElementException nsee) {
            result = false;
        }
        return result;
    }

    public Role findById(String id) throws NoSuchElementException {
        Role result = null;
        for (Role role : this.roles) {
            if (role.getId().equals(id)) {
                result = role;
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
        for (Role role : this.roles) {
            if (role.getId().equals(id)) {
                result = this.roles.getCurrentIndex() - 1;
                break;
            }
        }
        return result;
    }
}
