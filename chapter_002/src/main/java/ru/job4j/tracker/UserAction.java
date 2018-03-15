package ru.job4j.tracker;

public interface UserAction {
    String returnKey();

    void execute(Input input, Tracker tracker);

    String info();
}
