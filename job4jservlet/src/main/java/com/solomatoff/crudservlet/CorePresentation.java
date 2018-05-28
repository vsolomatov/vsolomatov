package com.solomatoff.crudservlet;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Класс представляет собой ядро слоя Presentation
 */
public class CorePresentation {
    // Instance
    private static CorePresentation ourInstance = null;
    public static CorePresentation getInstance() {
        if (ourInstance == null) {
            synchronized (CorePresentation.class) {
                if (ourInstance == null) {
                    ourInstance = new CorePresentation();
                    ourInstance.init();
                }
            }
        }
        return ourInstance;
    }
    // Логгер
    private static final Logger LOG = LoggerFactory.getLogger(CorePresentation.class);
    public static Logger getLOG() {
        return LOG;
    }
    // Слой логики
    private final ValidateService logic = ValidateService.getInstance();
    // Перечень поддерживаемых действий
    enum TypeAction { CREATE, UPDATE, DELETE, FINDBYID, FINDALL }

     /**
     * Получает параметр-действия в виде значения типа TypeAction
     * @param paramAction параметр-действие в виде строки
     * @return параметр-действия в виде значения типа TypeAction
     */
    public TypeAction getTypeAction(String paramAction) {
        TypeAction typeAction = null;
        switch (paramAction) {
            case "Create User":
                typeAction = TypeAction.CREATE;
                break;
            case "Update User":
                typeAction = TypeAction.UPDATE;
                break;
            case "Delete User":
                typeAction = TypeAction.DELETE;
                break;
            case "Find By Id":
                typeAction = TypeAction.FINDBYID;
                break;
            case "Find All":
                typeAction = TypeAction.FINDALL;
                break;
            default:
        }
        return typeAction;
    }
    /**
     * Содержит список функций, реализующих поддерживаемые действия
     */
    private final Map<TypeAction, Function<User, List<User>>> actions = new HashMap<>();

    /**
     * Init's
     */
    private void init() {
        if (actions.isEmpty()) {
            this.load(TypeAction.CREATE, this.userAdd());
            this.load(TypeAction.UPDATE, this.userUpdate());
            this.load(TypeAction.DELETE, this.userDelete());
            this.load(TypeAction.FINDBYID, this.userFindById());
            this.load(TypeAction.FINDALL, this.userFindAll());
        }
    }

    /**
     * Load handlers for actions
     * @param typeAction action type.
     * @param handle Handler.
     */
    private void load(TypeAction typeAction, Function<User, List<User>> handle) {
        this.actions.put(typeAction, handle);
    }

    /**
     * Execute action
     * @param typeAction type action
     * @return User List
     */
    public List<User> executeAction(String typeAction, User user) {
        return this.actions.get(getTypeAction(typeAction)).apply(user);
    }

    /**
     * Формирует xml-структуру из списка пользователей
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

                rootElement.appendChild(userElement);
            }
        } catch (ParserConfigurationException e) {
            LOG.error(e.getMessage(), e);
        }
        return document;
    }

    /**
     * Записывает XML-структуру в printWriter
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
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Записывает список пользователей в виде xml-структуры в printWriter
     * @param users список пользователей
     * @param printWriter куда выводить
     */
    public void saveUsersAsXmlToPrintWriter(List<User> users, PrintWriter printWriter) {
        Document document = getUsersAsXmlDocument(users);
        printWriter.append("<p><b>A list of users</b></p>");
        documentSaveToPrintWriter(document, printWriter);
    }

    /**
     * Записывает список пользователей в виде xml-структуры в Logger
     * @param users список пользователей
     * @param log логгер в который нужно выводить информацию
     */
    public void saveUsersAsXmlToLog(List<User> users, Logger log) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        Document document = getUsersAsXmlDocument(users);
        documentSaveToPrintWriter(document, printWriter);
        log.info(stringWriter.toString());
        printWriter.flush();
    }

    /**
     * Создает страницу со списком пользователей в виде таблицы
     * @param users список пользователей
     * @param printWriter куда выводить страницу
     */
    public void saveUsersAsHtmlToPrintWriter(List<User> users, PrintWriter printWriter, String contextPath) {
        StringBuilder sb = new StringBuilder();
        for (User user : users) {
            sb.append("\t\t\t\t<tr>\n\t\t\t\t\t<td>")
                    .append(user.getId())
                    .append("\t\t\t\t\t</td>\n\t\t\t\t\t<td>")
                    .append(user.getName())
                    .append("\t\t\t\t\t</td>\n\t\t\t\t\t<td>")
                    .append(user.getLogin())
                    .append("\t\t\t\t\t</td>\n\t\t\t\t\t<td>")
                    .append(user.getEmail())
                    .append("\t\t\t\t\t</td>\n\t\t\t\t\t<td>")
                    .append(user.getCreateDate())
                    .append("\t\t\t\t\t</td>\n")
                    .append("\t\t\t\t\t<td>\n\t\t\t\t\t\t<form action='")
                    .append(contextPath)
                    .append("/edit'>\n")
                    .append("\t\t\t\t\t\t\t<input type='hidden' name='action' value='Update User'>\n")
                    .append("\t\t\t\t\t\t\t<input type='hidden' name='id' value='")
                    .append(user.getId())
                    .append("'>\n")
                    .append("\t\t\t\t\t\t\t<input type='submit' value='Edit'>\n")
                    .append("\t\t\t\t\t\t</form>\n\t\t\t\t\t</td>\n")
                    .append("\t\t\t\t\t<td>\n\t\t\t\t\t\t<form action='")
                    .append(contextPath)
                    .append("/delete'>\n")
                    .append("\t\t\t\t\t\t\t<input type='hidden' name='action' value='Delete User'>\n")
                    .append("\t\t\t\t\t\t\t<input type='hidden' name='id' value='")
                    .append(user.getId())
                    .append("'>\n")
                    .append("\t\t\t\t\t\t\t<input type='submit' value='Delete'>\n")
                    .append("\t\t\t\t\t\t</form>\n\t\t\t\t\t</td>\n\t\t\t\t</tr>\n");
        }
        printWriter.append("<!DOCTYPE HTML>\n")
                .append("<html>\n")
                .append("\t<head>\n")
                .append("\t\t<meta charset='utf-8'>\n")
                .append("\t\t<title>Users</title>\n")
                .append("\t</head>\n")
                .append("\t<body>\n")
                .append("\t\t<p><b>A list of users</b></p>\n")
                .append("\t\t<table>\n")
                .append("\t\t\t<th>id</th>\n")
                .append("\t\t\t<th>name</th>\n")
                .append("\t\t\t<th>login</th>\n")
                .append("\t\t\t<th>e-mail</th>\n")
                .append("\t\t\t<th>createDate</th>\n")
                .append("\t\t\t<tbody>\n")
                .append(sb.toString())
                .append("\t\t\t</tbody>\n")
                .append("\t\t</table>\n")
                .append("\t\t<form action='")
                .append(contextPath)
                .append("/create'>\n")
                .append("\t\t\t<input type='submit' value='New'>\n")
                .append("\t\t</form>\n")
                .append("\t</body>\n")
                .append("</html>");
    }

    /**
     * Создает страницу с формой для создания, редактирования или удаления пользователя
     * @param user пользователь
     * @param printWriter куда выводить страницу
     */
    public void saveFormForActionToPrintWriter(User user, PrintWriter printWriter, String typeForm, String contextPath) {
        printWriter.append("<!DOCTYPE HTML>\n" + "<html>\n" + "\t<head>\n" + "\t\t<meta charset='utf-8'>\n" + "\t<title>")
                .append(typeForm)
                .append("</title>\n")
                .append("\t</head>\n")
                .append("\t<body>\n")
                .append("\t\t<p><b>")
                .append(typeForm)
                .append("</b></p>\n")
                .append("\t\t<form action='")
                .append(contextPath)
                .append("/userservlet' method='post'>\n")
                .append("\t\t\t<input type='hidden' name='action' value='")
                .append(typeForm).append("'><Br>\n")
                .append("\t\t\t<b>user id:</b> <input type='' name='id' value='")
                .append(String.valueOf(user.getId())).append("'><Br>\n")
                .append("\t\t\t<b>user name:</b> <input type='text' name='name' value='")
                .append(user.getName()).append("'><Br>\n")
                .append("\t\t\t<b>user login:</b> <input type='text' name='login' value='")
                .append(user.getLogin()).append("'><Br>\n")
                .append("\t\t\t<b>user e-mail:</b> <input type='text' name='email' value='")
                .append(user.getEmail())
                .append("'><Br>\n")
                .append("\t\t\t<p><input type='submit' value='Accept'></p>\n")
                .append("\t\t</form>\n")
                .append("\t</body>\n")
                .append("</html>");
    }

    /**
     * Add User
     * @return null
     */
    private Function<User, List<User>> userAdd() {
        return logic::add;
    }

    /**
     * Update User
     * @return user updated
     */
    private Function<User, List<User>> userUpdate() {
        return logic::update;
    }

    /**
     * Delete User
     * @return user updated
     */
    private Function<User, List<User>> userDelete() {
        return logic::delete;
    }

    /**
     * Find User
     * @return user updated
     */
    private Function<User, List<User>> userFindById() {
        return logic::findById;
    }

    /**
     * Receives All Users
     * @return User List
     */
    private Function<User, List<User>> userFindAll() {
        return logic::findAll;
    }
}
