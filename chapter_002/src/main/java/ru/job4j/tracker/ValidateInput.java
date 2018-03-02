package ru.job4j.tracker;

public class ValidateInput extends ConsoleInput {
    public int ask(String question, int[] range) {
       boolean invalid = true;
       int key = -1;
       do {
           try {
               key = super.ask(question, range);
               invalid = false;
           } catch (MenuOutException moe) {
               //moe.printStackTrace();
               System.out.println("Please, select key from menu!");
           } catch (NumberFormatException nfe) {
               System.out.println("Please, enter validate data again!");
           }
       }
       while (invalid);
       return key;
    }
}
