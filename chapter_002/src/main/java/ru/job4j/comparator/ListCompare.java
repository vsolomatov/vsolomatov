package ru.job4j.comparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        ArrayList<String> leftList = new ArrayList(Arrays.asList(left.toCharArray()));
        ArrayList<String> rightList = new ArrayList(Arrays.asList(right.toCharArray()));
        //System.out.println(leftList);
        //System.out.println(rightList);
        boolean result = true;
        int i = 0;

        for (String leftitr : leftList) {
            if (!rightList.get(i).equals(leftitr)) {
                result = false;
                break;
            }
            i++;
        }

        return 0;
    }
}

