package com.solomatoff.generic;

import java.util.NoSuchElementException;

public abstract class AbstractStore<T extends Base> implements Store<T> {
    private SimpleArray<T> abstractSimpleArray;

    AbstractStore(int size) {
        this.abstractSimpleArray = new SimpleArray<>(size);
    }
    public void add(T model) {
        this.abstractSimpleArray.add(model);
    }

    public boolean replace(String id, T model) {
        boolean result = true;
        try {
            this.abstractSimpleArray.set(getIndexById(id), model);
        } catch (NoSuchElementException nsee) {
            result = false;
        }
        return result;
    }

    public boolean delete(String id) {
        boolean result = true;
        try {
            this.abstractSimpleArray.delete(getIndexById(id));
        } catch (NoSuchElementException nsee) {
            result = false;
        }
        return result;
    }

    public T findById(String id) throws NoSuchElementException {
        T result = null;
        for (T value : this.abstractSimpleArray) {
            if (value.getId().equals(id)) {
                result = value;
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
        for (T value : this.abstractSimpleArray) {
            if (value.getId().equals(id)) {
                result = this.abstractSimpleArray.getCurrentIndex() - 1;
                break;
            }
        }
        return result;
    }
}
