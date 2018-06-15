package com.solomatoff.jdbc;

import org.junit.Test;

import java.util.ArrayList;

public class JAXBXMLtoEntryTest {

    @Test
    public void whenXMLtoEntry() {
        JAXBXMLtoEntry jaxbxmLtoEntry = new JAXBXMLtoEntry();
        Entries entries;
        entries = jaxbxmLtoEntry.xmlToEntry("2.xml");
        ArrayList<Entry> list = entries.getList();
        for (Entry entry : list) {
           //System.out.println("entry.getId() = " + entry.getId());
        }
    }
}