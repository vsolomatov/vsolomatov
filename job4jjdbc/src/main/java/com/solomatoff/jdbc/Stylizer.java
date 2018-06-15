package com.solomatoff.jdbc;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Stylizer {

    public static void stylizerRun(String stylesheet, String datafile) {
        Document document;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        int separatorLastIndex = datafile.lastIndexOf("/");
        if (separatorLastIndex == -1) {
            separatorLastIndex = datafile.lastIndexOf(File.separator);
        }
        String dir = datafile.substring(0, separatorLastIndex + 1);
        String outputFile = dir + "2.xml";
       //System.out.println("outputFile - " + outputFile);
        try {
            DocumentBuilder builder = dbf.newDocumentBuilder();
            File datafileAsFile = new File(datafile);
            document = builder.parse(datafileAsFile);
            WriterXML.writeDocument(stylesheet, document, outputFile);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}