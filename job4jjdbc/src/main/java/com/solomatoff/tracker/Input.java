package com.solomatoff.tracker;

import java.util.ArrayList;

public interface Input {

    String ask(String question);

    String ask(String question, ArrayList<String> range);

}