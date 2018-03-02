package ru.job4j.tracker;

public interface UserAction {
    int returnKey();

    void execute(Input input, Tracker tracker);

    String info();
}
