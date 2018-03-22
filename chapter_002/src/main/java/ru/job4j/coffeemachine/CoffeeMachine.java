package ru.job4j.coffeemachine;

import java.util.*;

public class CoffeeMachine {
    private TreeSet<Integer> money = new TreeSet<>(Arrays.asList(5, 1, 2, 10, 50, 500, 100, 1000, 5000));

    /**
     * Метод выдачи сдачи для автомата.
     * @param value купюра вставленная в автомат
     * @param price цена товара
     * @return целочисленный массив представляющий собой перечень купюр и монет для сдачи
     */
     public int[] changes(int value, int price) {
        int changeSum = value - price;
        Integer smallBankNote;
        int numBankNote;
        ArrayList<Integer> changeList = new ArrayList<>();
        while (changeSum > 0) {
            smallBankNote = money.floor(changeSum); // В нашем случае исключение не должно возникать
            numBankNote = changeSum / smallBankNote;
            for (int i = 0; i < numBankNote; i++) {
                changeList.add(smallBankNote);
            }
            changeSum -= smallBankNote * numBankNote;
        }

        Integer[] iArray = new Integer[changeList.size()];
        return toint(changeList.toArray(iArray));
    }

    /**
    * Метод преобразует массив типа Integer в массив типа int.
     * @param wrapperArray исходный массив типа Integer.
     * @return массив преобразованный в int.
     */
    private static int[] toint(Integer[] wrapperArray) {
        int[] newArray = new int[wrapperArray.length];
        for (int i = 0; i < wrapperArray.length; i++) {
            newArray[i] = wrapperArray[i];
        }
        return newArray;
    }
}
