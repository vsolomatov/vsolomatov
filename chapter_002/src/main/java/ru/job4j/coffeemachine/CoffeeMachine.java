package ru.job4j.coffeemachine;

import java.util.*;

public class CoffeeMachine {
    private ArrayList<Integer> money = new ArrayList<>(Arrays.asList(5000, 2000, 1000, 500, 200, 100, 50, 10, 5, 2, 1));

    /**
     * Метод выдачи сдачи для автомата.
     * @param value купюра вставленная в автомат
     * @param price цена товара
     * @return целочисленный массив представляющий собой перечень купюр и монет для сдачи
     */
     public int[] changes(int value, int price) {
        int changeSum = value - price;
        int numBankNote, currBankNote, indexMoney = 0;
        ArrayList<Integer> changeList = new ArrayList<>();
        while (changeSum > 0) {
            currBankNote = money.get(indexMoney);
            numBankNote = changeSum / currBankNote;
            if (numBankNote > 0) {
                for (int i = 0; i < numBankNote; i++) {
                    changeList.add(currBankNote);
                }
                changeSum = changeSum % currBankNote;
            }
            indexMoney++;
        }
        int[] iArray = new int[changeList.size()];
        for (int i = 0; i < changeList.size(); i++) {
             iArray[i] = changeList.get(i);
        }
        return iArray;
    }
}
