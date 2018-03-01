package ru.job4j.tracker;

public interface UserAction {
    int setKey();

    void execute(Input input, Tracker tracker);

    String info();
}
