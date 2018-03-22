package ru.job4j.department;

import java.util.Comparator;

public class Department {
    private String code;

    Department(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public String getCodeParent() {
        String codeParent = this.getCode();
        int index = codeParent.lastIndexOf("\\");
        if (index > 0) {
            codeParent = codeParent.substring(0, index);
        } else {
            codeParent = null;
        }
        return codeParent;
    }
}
