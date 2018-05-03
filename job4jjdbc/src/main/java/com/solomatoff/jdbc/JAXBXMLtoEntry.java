package com.solomatoff.jdbc;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBXMLtoEntry {
    public Entries xmlToEntry(String inputFile) {
        Entries entries = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Entries.class);
            Unmarshaller um = context.createUnmarshaller();
            entries = (Entries) um.unmarshal(new FileReader(inputFile));
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return entries;
    }
}