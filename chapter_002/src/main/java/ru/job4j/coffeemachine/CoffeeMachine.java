package ru.job4j.coffeemachine;

import java.util.*;

public class CoffeeMachine {
    private ArrayList<Integer> money = new ArrayList<>(Arrays.asList(1, 2, 5, 10, 50, 100, 200, 500, 1000, 2000, 5000));

    /**
     * Метод выдачи сдачи для автомата.
     * @param value купюра вставленная в автомат
     * @param price цена товара
     * @return целочисленный массив представляющий собой перечень купюр и монет для сдачи
     */
     public int[] changes(int value, int price) {
        int changeSum = value - price;
        Integer smallBankNote = 0;
        int numBankNote;
        ArrayList<Integer> changeList = new ArrayList<>();
        while (changeSum > 0) {
            // Находим наибольшую банкноту, меньшую по номиналу, чем сумма сдачи
            for (Integer iMoney : money) {
                if (iMoney <= changeSum) {
                    smallBankNote = iMoney;
                } else {
                    break;
                }
            }
            numBankNote = changeSum / smallBankNote;
            for (int i = 0; i < numBankNote; i++) {
                changeList.add(smallBankNote);
            }
            changeSum -= smallBankNote * numBankNote;
        }
        int[] iArray = new int[changeList.size()];
        for (int i = 0; i < changeList.size(); i++) {
             iArray[i] = changeList.get(i);
        }
        return iArray;
    }
}
