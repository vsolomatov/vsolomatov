package ru.job4j.department;

import java.util.Comparator;

public class DepCompDescendingly implements Comparator<Department> {
    public int compare(Department left, Department right) {
        return (-1) * left.getCode().compareTo(right.getCode());
    }
}
