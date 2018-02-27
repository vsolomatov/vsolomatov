package ru.job4j.tracker;

import ru.job4j.tracker.start.StartUI;

public class StartUITest {

    public static void main(String[] args) {
        String[] itemNewArray = {"create stub item1", "create stub item2", "create stub item3"};
        Input input = new StubInput(itemNewArray);
        Tracker tracker = new Tracker();

        new StartUI(input, tracker).init();
    }
}
