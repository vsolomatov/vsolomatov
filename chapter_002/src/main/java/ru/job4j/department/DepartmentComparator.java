package ru.job4j.department;

import java.util.Comparator;

public class DepartmentComparator implements Comparator<Department> {
    public int compare(Department left, Department right) {
        return left.getCode().compareTo(right.getCode());
    }
}
