package com.solomatoff.chaptertrainee003.comparator;

import java.util.Comparator;

public class ListCompare implements Comparator<String> {
        @Override
        public int compare(String left, String right) {
            int result = 0;
            for (int i = 0; i < Math.min(left.length(), right.length()); i++) {
                    if (left.charAt(i) < right.charAt(i)) {
                        result = -1;
                        break;
                    } else if (left.charAt(i) > right.charAt(i)) {
                        result = 1;
                        break;
                    }
            }
            if (result == 0) {
                if (left.length() < right.length()) {
                    result = -1;
                } else if (left.length() > right.length()) {
                    result = 1;
                }
            }
            return result;
        }
}


