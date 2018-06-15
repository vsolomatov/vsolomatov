package com.solomatoff.chaptertrainee002.tracker;

import java.util.ArrayList;

public class ValidateInput implements Input {

    private final Input input;

    public ValidateInput(final Input input) {
        this.input = input;
    }

    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    public String ask(String question, ArrayList<String> range) {
        boolean invalid = true;
        String value = "";
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
               System.out.println("Please select key from menu.");
            }
        } while (invalid);
        return  value;
    }
}
