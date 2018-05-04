package com.solomatoff.jdbc;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.*;
import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;

import org.w3c.dom.Node;

public class WorkWithDB {
    private static final Logger LOG = LoggerFactory.getLogger(WorkWithDB.class);
    private static String url;
    private static String username;
    private static String password;
    private static Integer numberN;

    private static void runWork() {
        BasicConfigurator.configure(); // Для Logger
        Connection conn = null;
        Statement st;
        try {
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false);
            st = conn.createStatement();
            // создаем таблицу TEST
            st.execute("CREATE TABLE IF NOT EXISTS test(field integer)");
            // очищаем таблицу TEST
            st.executeUpdate("DELETE FROM test");
            conn.commit();
            // вставляем numberN записей в таблицу
            PreparedStatement stInsert;
            for (int index = 0; index < numberN; index++) {
                stInsert = conn.prepareStatement("INSERT INTO test VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                stInsert.setInt(1, index);
                stInsert.executeUpdate();
                ResultSet generatedKeys = stInsert.getGeneratedKeys();
                /*if (generatedKeys.next()) {
                    System.out.println(generatedKeys.getInt(1));
                }*/
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
                try {
                    conn.close();
                } catch (SQLException e1) {
                    LOG.error(e1.getMessage(), e1);
                }
            } catch (NullPointerException | SQLException e2) {
                LOG.error(e2.getMessage(), e2);
            }
            LOG.error(e.getMessage(), e);
            System.exit(0);
        }
        // формируем путь к файлу и имя файла 1.xml
        Path path = null;
        try {
            path = Paths.get(WorkWithDB.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch (URISyntaxException e) {
            LOG.error(e.getMessage(), e);
        }
        String dir;
        try {
            dir = path.toString();
            dir = dir.substring(0, dir.lastIndexOf(File.separator) + 1);
        } catch (NullPointerException e) {
            dir = "";
            LOG.error(e.getMessage(), e);
        }
        String outputFile = dir + "1.xml";
        System.out.println("outputFile = " + outputFile);

        // делаем выборку из базы данных
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM test ORDER BY field");
        } catch (NullPointerException | SQLException e) {
            e.printStackTrace();
            System.exit(0);
        } finally {
                try {
                    conn.close();
                } catch (NullPointerException | SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
        }

        // Для формирования файла 1.xml
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        String stylesheet = ""; // первый вызов WriterXML должен быть без xsl-файла
        try {
            Document document = dbf.newDocumentBuilder().newDocument();
            // создаем корневой элемент <entries>
            Element rootElement;
            rootElement = document.createElement("entries");
            document.appendChild(rootElement);
            while (rs.next()) {
                Element fieldElement = document.createElement("field");
                fieldElement.setTextContent(Integer.toString(rs.getInt(1)));
                Element entryElement = document.createElement("entry");
                entryElement.appendChild(fieldElement);
                rootElement.appendChild(entryElement);
            }
            rs.close();
            // Сохраняем document в XML-файл
            WriterXML.writeDocument(stylesheet, document, outputFile);
        } catch (NullPointerException | SQLException | ParserConfigurationException e) {
            LOG.error(e.getMessage(), e);
        }
        stylesheet = dir + "1.xsl";
        if (!new File(stylesheet).exists()) {
            System.out.println("Файл таблицы стилей (1.xsl) не существует в указанном каталоге!");
            System.exit(0);
        }
        String dataFile = dir + "1.xml";
        // делаем трансформацию файла 1.xml в 2.xml при помощи 1.xsl
        Stylizer.stylizerRun(stylesheet, dataFile);

        // Делаем разбор 2.xml и считаем сумму
        JAXBXMLtoEntry jaxbxmLtoEntry = new JAXBXMLtoEntry();
        Entries entries;
        entries = jaxbxmLtoEntry.xmlToEntry(dir + "2.xml");
        ArrayList<Entry> list = entries.getList();
        long sumEntries = 0;
        for (Entry entry : list) {
            sumEntries += entry.getId();
        }
        System.out.println("Сумма полей FIELD = " + sumEntries);
    }

    private static void setUrl(String url) {
        WorkWithDB.url = url;
    }

    private static void setUsername(String username) {
        WorkWithDB.username = username;
    }

    private static void setPassword(String password) {
        WorkWithDB.password = password;
    }

    private static void setNumberN(Integer numberN) {
        WorkWithDB.numberN = numberN;
        System.out.println("numberN = " + numberN);
    }

    public static void main(String[] args) {
        if (args.length != 4) {
            System.err.println("Usage: java -jar job4jjdbc.jar url username password N");
            System.exit(1);
        }
        setUrl(args[0]);
        setUsername(args[1]);
        setPassword(args[2]);
        setNumberN(Integer.parseInt(args[3]));
        runWork();
    }
}
