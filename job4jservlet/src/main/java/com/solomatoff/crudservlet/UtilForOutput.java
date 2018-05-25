package com.solomatoff.crudservlet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.List;

public class UtilForOutput {
    /**
     * Формирует xml-структуры из списка пользователей
     * @param users список пользователей
     * @return Документ xml-структуры
     */
    public Document getUsersAsXmlDocument(List<User> users) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document document = null;
        try {
            document = dbf.newDocumentBuilder().newDocument();
            // создаем корневой элемент <users>
            Element rootElement;
            rootElement = document.createElement("users");
            document.appendChild(rootElement);
            for (User user : users) {
                Element userElement = document.createElement("user");

                Element fieldUserElement = document.createElement("id");
                fieldUserElement.setTextContent(Integer.toString(user.getId()));
                userElement.appendChild(fieldUserElement);

                fieldUserElement = document.createElement("name");
                fieldUserElement.setTextContent(user.getName());
                userElement.appendChild(fieldUserElement);

                fieldUserElement = document.createElement("login");
                fieldUserElement.setTextContent(user.getLogin());
                userElement.appendChild(fieldUserElement);

                fieldUserElement = document.createElement("email");
                fieldUserElement.setTextContent(user.getEmail());
                userElement.appendChild(fieldUserElement);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String sCreateDate = dateFormat.format(user.getCreateDate().getTime());

                fieldUserElement = document.createElement("createDate");
                fieldUserElement.setTextContent(sCreateDate);
                userElement.appendChild(fieldUserElement);

                rootElement.appendChild(userElement);
            }
        } catch (ParserConfigurationException e) {
            UserServlet.LOG.error(e.getMessage(), e);
        }
        return document;
    }

    /**
     *  Записывает XML-структуру в printWriter
     * @param document xml-документ
     * @param printWriter куда будем писать
     */
    public void documentSaveToPrintWriter(Document document, PrintWriter printWriter) {
        Transformer trf;
        Source src;
        try {
            trf = TransformerFactory.newInstance().newTransformer();
            src = new DOMSource(document);
            trf.setOutputProperty(OutputKeys.INDENT, "yes"); // для того, чтобы каждый узел начинался с новой строки
            //trf.setOutputProperty(OutputKeys.ENCODING, "WINDOWS-1251");
            StreamResult result = new StreamResult(printWriter);
            trf.transform(src, result);
        } catch (TransformerException e) {
            UserServlet.LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Записывает XML-структуру в LOG
     * @param document xml-документ
     */
    public void documentSaveToLog(Document document) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        documentSaveToPrintWriter(document, printWriter);
        UserServlet.LOG.info(stringWriter.toString());
        printWriter.flush();
    }

}
