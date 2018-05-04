package com.solomatoff.chaptertrainee003.department;

import java.util.*;

public class Organisation {
    private List<String> deps = new ArrayList<>();

    Organisation(String[] divisions) {
        for (String div : divisions) {
            String code = div;
            while (code != null) {
                if (!existWithCode(code)) {
                    deps.add(code);
                }
                code = getCodeParent(code);
            }
        }
    }

    /**
     * Метод возвращает отсортированный массив кодов подразделений.
     * @param comparator компаратор по которому нужно отсортировать департаменты.
     * @return отсортированный массив кодов подразделений.
     */
    public String[] sort(Comparator<String> comparator) {
        deps.sort(comparator);
        //System.out.println("Массив после сортировки: " + deps);
        String[] resultArray = new String[deps.size()];
        int i = 0;
        for (String dep : deps) {
            resultArray[i] = dep;
            i++;
        }
        return resultArray;
    }

    /**
     *  Метод проверяет существует ли в нашей структуре депратамент с указанным кодом.
     * @param code проверяемый код
     * @return если существует, то - true, иначе - false
     */
    private boolean existWithCode(String code) {
        boolean exists = false;
        for (String depItr : deps) {
            if (depItr.equals(code)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    static String getCodeParent(String code) {
        String codeParent;
        int index = code.lastIndexOf("\\");
        if (index > 0) {
            codeParent = code.substring(0, index);
        } else {
            codeParent = null;
        }
        return codeParent;
    }
}

class DepartmentComparator implements Comparator<String> {
    public int compare(String left, String right) {
        return left.compareTo(right);
    }
}

class DepCompDescendingly implements Comparator<String> {
    public int compare(String left, String right) {
        int result = 0;
        String code;
        ArrayList<String> arrayLeft = new ArrayList<>();
        ArrayList<String> arrayRight = new ArrayList<>();
        code = left;
        while (code != null) {
            arrayLeft.add(code);
            code = Organisation.getCodeParent(code);
        }
        code = right;
        while (code != null) {
            arrayRight.add(code);
            code = Organisation.getCodeParent(code);
        }
        int leftSize = arrayLeft.size();
        int rightSize = arrayRight.size();
        int indexRight = rightSize - 1;
        for (int indexLeft = leftSize - 1; indexLeft >= 0; indexLeft--) {
            if (arrayLeft.get(indexLeft).compareTo(arrayRight.get(indexRight)) != 0) {
                result = (-1) * arrayLeft.get(indexLeft).compareTo(arrayRight.get(indexRight));
                break;
            }
            if ((indexRight != 0) && (indexLeft == 0)) {
                result = -1;
                break;
            }
            if ((indexLeft != 0) && (indexRight == 0)) {
                result = 1;
                break;
            }
            indexRight -= 1;
        }
        return result;
    }
}
