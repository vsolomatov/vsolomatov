package com.solomatoff.tracker;

import java.util.*;

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String ask(String question) {
       //System.out.print(question);
        return scanner.nextLine();
    }

    @Override
    public String ask(String question, ArrayList<String> range) {
        String key = this.ask(question);

        boolean exist = false;
        for (String value : range) {
            //System.out.println(value);
            if (key.equals(value)) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Out of menu range");
        }
    }
}
