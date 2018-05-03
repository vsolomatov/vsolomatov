package com.solomatoff.jdbc;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Entries {
    @XmlElement(name = "entry")
    private ArrayList<Entry> entryList;

    public ArrayList<Entry> getList() { // у меня остался вопрос, почему этот метод нельзя назвать getEntryList
        return this.entryList;
    }
}