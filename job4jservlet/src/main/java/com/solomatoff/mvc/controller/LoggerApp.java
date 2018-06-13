package com.solomatoff.mvc.controller;

import com.solomatoff.mvc.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * Класс для ведения лога приложения
 *
 */
public class LoggerApp {
    private static LoggerApp singletonInstance = new LoggerApp();

    public static LoggerApp getInstance() {
        return singletonInstance;
    }

    private LoggerApp() {
    }

    // Логгер
    private final Logger log = LoggerFactory.getLogger(Controller.class);
    public Logger getLog() {
        return log;
    }

    /**
     * Записывает список пользователей в виде xml-структуры в log
     * @param users список пользователей
     */
    public void saveUsersToLog(List<User> users) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        Document document = getUsersAsXmlDocument(users);
        documentSaveToPrintWriter(document, printWriter);
        log.info(stringWriter.toString());
        printWriter.flush();
    }

    /**
     * Формирует xml-структуру из списка пользователей
     * @param users список пользователей
     * @return Документ xml-структуры
     */
    private Document getUsersAsXmlDocument(List<User> users) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document document = null;
        if (users.size() > 0) {
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

                    String sCreateDate;
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    if (user.getCreateDate() == null) {
                        sCreateDate = "Time is not set";
                    } else {
                        sCreateDate = dateFormat.format(user.getCreateDate().getTime());
                    }
                    fieldUserElement = document.createElement("createDate");
                    fieldUserElement.setTextContent(sCreateDate);
                    userElement.appendChild(fieldUserElement);

                    String sIdRole;
                    if (user.getIdRole() == null) {
                        sIdRole = "Role is not set";
                    } else {
                        sIdRole = Integer.toString(user.getIdRole());
                    }
                    fieldUserElement = document.createElement("idRole");
                    fieldUserElement.setTextContent(sIdRole);
                    userElement.appendChild(fieldUserElement);

                    rootElement.appendChild(userElement);
                }
            } catch (ParserConfigurationException e) {
                log.error(e.getMessage(), e);
            }
        }
        return document;
    }

    /**
     * Записывает XML-структуру в printWriter
     * @param document xml-документ
     * @param printWriter куда будем писать
     */
    private void documentSaveToPrintWriter(Document document, PrintWriter printWriter) {
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
            log.error(e.getMessage(), e);
        }
    }
}
