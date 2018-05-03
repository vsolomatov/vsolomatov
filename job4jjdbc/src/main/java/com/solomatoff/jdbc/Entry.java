package com.solomatoff.jdbc;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class Entry {
    private int id;

    @XmlAttribute(name = "field")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}