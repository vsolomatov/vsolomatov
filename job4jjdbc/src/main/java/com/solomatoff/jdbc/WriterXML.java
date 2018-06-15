package com.solomatoff.jdbc;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.FileOutputStream;
import java.io.IOException;

public class WriterXML {
     /**
     * Метод сохранения DOM в файл
     */
    public static void writeDocument(String stylesheet, Document document, String path) throws TransformerFactoryConfigurationError {
        Transformer trf;
        Source src;
        FileOutputStream fos;
        try {
            if (stylesheet == null || stylesheet.equals("")) {
                trf = TransformerFactory.newInstance().newTransformer();
            } else {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                dbf.setNamespaceAware(true);
                DocumentBuilder db;
                db = dbf.newDocumentBuilder();
                Document xslt = db.parse(stylesheet);
                Source xsltSource = new DOMSource(xslt);
                trf = TransformerFactory.newInstance().newTransformer(xsltSource);
            }
            src = new DOMSource(document);
            fos = new FileOutputStream(path);
            StreamResult result = new StreamResult(fos);
            trf.setOutputProperty(OutputKeys.INDENT, "yes"); // для того, чтобы каждый узел начинался с новой строки
            //trf.setOutputProperty(OutputKeys.ENCODING, "WINDOWS-1251");
            trf.transform(src, result);
           //System.out.println("    Записали файл " + path);
        } catch (TransformerException | ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}