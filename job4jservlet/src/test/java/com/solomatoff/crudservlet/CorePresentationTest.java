package com.solomatoff.crudservlet;

import org.junit.Test;
import org.slf4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CorePresentationTest {

    @Test
    public void getInstance() {
        CorePresentation corePresentation1 = CorePresentation.getInstance();
        CorePresentation corePresentation2 = CorePresentation.getInstance();
        assertThat(corePresentation1, is(corePresentation2));
    }

    @Test
    public void getLOG() {
        Logger logger = CorePresentation.getLOG();
        assertThat(logger.getName(), is("com.solomatoff.crudservlet.CorePresentation"));
    }

    @Test
    public void getTypeAction() {
        CorePresentation corePresentation = CorePresentation.getInstance();
        CorePresentation.TypeAction typeAction = corePresentation.getTypeAction("Create User");
        assertThat(typeAction, is(CorePresentation.TypeAction.CREATE));
    }

    @Test
    public void executeAction() {
        CorePresentation corePresentation = CorePresentation.getInstance();
        User user = new User(666, "name", "login", "email", null);
        corePresentation.executeAction("Create User", user);
        List<User> list = corePresentation.executeAction("Find By Id", user);
        assertThat(list.get(0).getId(), is(user.getId()));
    }

    @Test
    public void getUsersAsXmlDocument() {
        CorePresentation corePresentation = CorePresentation.getInstance();
        User user1 = new User(4, "name4", "login4", "email4", new Timestamp(System.currentTimeMillis()));
        User user2 = new User(5, "name5", "login5", "email5", new Timestamp(System.currentTimeMillis()));
        User user3 = new User(6, "name6", "login6", "email6", null);
        corePresentation.executeAction("Create User", user1);
        corePresentation.executeAction("Create User", user2);
        corePresentation.executeAction("Create User", user3);
        List<User> list = corePresentation.executeAction("Find All", new User());
        Document document = corePresentation.getUsersAsXmlDocument(list);
        NodeList nList = document.getElementsByTagName("*");
        Node item;
        Element eElement;
        for (int i = 0; i < nList.getLength(); i++) {
            item = nList.item(i);
            System.out.print("Current Element " + item.getNodeName() + " : " + item.getTextContent());
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                eElement = (Element) item.getChildNodes();
                if (eElement.getNodeName().equals("user")) {
                    String id = eElement.getElementsByTagName("id").item(0).getTextContent();
                    String name = eElement.getElementsByTagName("name").item(0).getTextContent();
                    String login = eElement.getElementsByTagName("login").item(0).getTextContent();
                    String email = eElement.getElementsByTagName("email").item(0).getTextContent();
                    String createDate = eElement.getElementsByTagName("createDate").item(0).getTextContent();
                    System.out.printf("     <id=%s> <name=%s> <login=%s> <email=%s> <createDate=%s>", id, name, login, email, createDate);
                }
            }
            System.out.println("");
            item.getNextSibling();
        }
    }

    @Test
    public void documentSaveToPrintWriter() {
        CorePresentation corePresentation = CorePresentation.getInstance();
        User user1 = new User(1, "name1", "login1", "email1", new Timestamp(System.currentTimeMillis()));
        User user2 = new User(2, "name2", "login2", "email2", new Timestamp(System.currentTimeMillis()));
        User user3 = new User(3, "name3", "login3", "email3", new Timestamp(System.currentTimeMillis()));
        corePresentation.executeAction("Create User", user1);
        corePresentation.executeAction("Create User", user2);
        corePresentation.executeAction("Create User", user3);
        List<User> list = corePresentation.executeAction("Find All", new User());
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter);
        Document document = corePresentation.getUsersAsXmlDocument(list);
        corePresentation.documentSaveToPrintWriter(document, pw);
        assertThat(stringWriter.toString().substring(0, 54), is("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>"));
    }

    @Test
    public void saveUsersAsXmlToPrintWriter() {
        CorePresentation corePresentation = CorePresentation.getInstance();
        User user1 = new User(7, "name7", "login7", "email7", new Timestamp(System.currentTimeMillis()));
        User user2 = new User(8, "name8", "login8", "email8", new Timestamp(System.currentTimeMillis()));
        User user3 = new User(9, "name9", "login9", "email9", new Timestamp(System.currentTimeMillis()));
        corePresentation.executeAction("Create User", user1);
        corePresentation.executeAction("Create User", user2);
        corePresentation.executeAction("Create User", user3);
        List<User> list = corePresentation.executeAction("Find All", new User());
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter);
        corePresentation.saveUsersAsXmlToPrintWriter(list, pw);
        assertThat(stringWriter.toString().substring(0, 29), is("<p><b>A list of users</b></p>"));
    }

    @Test
    public void saveUsersAsXmlToLog() {
        CorePresentation corePresentation = CorePresentation.getInstance();
        User user1 = new User(10, "name10", "login10", "email10", new Timestamp(System.currentTimeMillis()));
        User user2 = new User(11, "name11", "login11", "email11", new Timestamp(System.currentTimeMillis()));
        User user3 = new User(12, "name12", "login12", "email12", new Timestamp(System.currentTimeMillis()));
        corePresentation.executeAction("Create User", user1);
        corePresentation.executeAction("Create User", user2);
        corePresentation.executeAction("Create User", user3);
        List<User> list = corePresentation.executeAction("Find All", new User());
        Logger log = CorePresentation.getLOG();
        corePresentation.saveUsersAsXmlToLog(list, log);
        assertThat(log.getName(), is("com.solomatoff.crudservlet.CorePresentation"));
    }

    @Test
    public void saveUsersAsHtmlToPrintWriter() {
        CorePresentation corePresentation = CorePresentation.getInstance();
        User user1 = new User(13, "name13", "login13", "email13", new Timestamp(System.currentTimeMillis()));
        User user2 = new User(14, "name14", "login14", "email14", new Timestamp(System.currentTimeMillis()));
        User user3 = new User(15, "name15", "login15", "email15", new Timestamp(System.currentTimeMillis()));
        corePresentation.executeAction("Create User", user1);
        corePresentation.executeAction("Create User", user2);
        corePresentation.executeAction("Create User", user3);
        List<User> list = corePresentation.executeAction("Find All", new User());
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter);
        corePresentation.saveUsersAsHtmlToPrintWriter(list, pw, "/items");
        System.out.println(stringWriter.toString());
        assertThat(stringWriter.toString().substring(0, 15), is("<!DOCTYPE HTML>"));
    }

    @Test
    public void saveFormForActionToPrintWriter() {
        CorePresentation corePresentation = CorePresentation.getInstance();
        User user = new User(666, "name", "login", "email", null);
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter);
        corePresentation.saveFormForActionToPrintWriter(user, pw, "Update User", "/items");
        System.out.println(stringWriter.toString());
        assertThat(stringWriter.toString().substring(57, 83), is("<title>Update User</title>"));
    }
}