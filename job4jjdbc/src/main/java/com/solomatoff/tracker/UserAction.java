package com.solomatoff.tracker;

import com.solomatoff.tracker.store.Store;

public interface UserAction {
    String returnKey();

    void execute(Input input, Store tracker);

    String info();
}
