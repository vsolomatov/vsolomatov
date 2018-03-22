package ru.job4j.department;

import java.util.*;

public class Structure {
    private Set<Department> mStructure;

    public Structure(Comparator depComparator) {
        System.out.println(depComparator.getClass());
        this.mStructure = new TreeSet<Department>(depComparator);
    }

    /**
     * Метод возвращает отсортированный массив кодов подразделений.
     * @param divisions исходный массив кодов подразделений.
     * @return отсортированный массив кодов подразделений.
     */
    public String[] sort(String[] divisions) {
        Department newDep;
        // Заполняем нашу структуру по переданному нам массиву
        for (String div : divisions) {
            String code = div;
            newDep = new Department(div);
            // Добавляем сам отдел и всех его родителей, если они не существуют
            while (code != null) {
                if (!existWithCode(code)) {
                    mStructure.add(newDep);
                }
                code = newDep.getCodeParent();
                newDep = new Department(code);
            }
        }

        String[] resultArray = new String[mStructure.size()];
        int i = 0;
        for (Department dep : mStructure) {
            resultArray[i] = dep.getCode();
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
        for (Department depItr : mStructure) {
            if (depItr.getCode().equals(code)) {
                exists = true;
                break;
            }
        }
        return exists;
    }
}
