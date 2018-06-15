package com.solomatoff.mvc.controller;

import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.model.DbStore;
import com.solomatoff.mvc.model.MemoryStore;
import com.solomatoff.mvc.model.ModelLogic;
import com.solomatoff.mvc.model.ModelStore;
import com.solomatoff.mvc.views.HtmlView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListUsersServletTest {
    // Controller
    private static final Controller CONTROLLER = Controller.getInstance();
    private static final ModelLogic MODEL_LOGIC = CONTROLLER.getLogic();

    /** Servlet under test. */
    private ListUsersServlet servlet;

    /** Mock request. */
    private HttpServletRequest request;

    /** Mock response. */
    private HttpServletResponse response;

    /** Mock session. */
    private HttpSession session;

    // Mock requestDispetcher
    private RequestDispatcher requestDispatcher;

    /** Session's and Request's attribute map. */
    private Map attributes;

    /** Request's parameter map. */
    private Map parameters;

    /** Responce's printWriter. */
    private PrintWriter printWriter;

    @Before
    public void setUp() throws IOException {

        attributes = new HashMap();
        parameters = new HashMap();
        servlet = new ListUsersServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        requestDispatcher = mock(RequestDispatcher.class);
        printWriter = new PrintWriter("ListUsers.html");

        when(request.getContextPath()).thenReturn("/items");
        when(response.getWriter()).thenReturn(printWriter);

        Mockito.doAnswer(new Answer() {
            /**
             * @see Answer#answer(InvocationOnMock)
             */
            @Override
            public Object answer(InvocationOnMock aInvocation) {
                String key = "login";
                String value = request.getParameter(key);
                session.setAttribute(key, value);
                return session;
            }
        }).when(request).getSession(false);

        Mockito.doAnswer(new Answer() {
            /**
             * @see Answer#answer(InvocationOnMock)
             */
            @Override
            public Object answer(InvocationOnMock aInvocation) {
                return requestDispatcher;
            }
        }).when(request).getRequestDispatcher(anyString());

        when(request.getParameter(anyString())).thenAnswer(new Answer() {
            /**
             * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock)
             */
            @Override
            public Object answer(InvocationOnMock aInvocation) {
                String key = (String) aInvocation.getArguments()[0];
                return parameters.get(key);
            }
        });

        when(request.getAttribute(anyString())).thenAnswer(new Answer() {
            /**
             * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock)
             */
            @Override
            public Object answer(InvocationOnMock aInvocation) {
                String key = (String) aInvocation.getArguments()[0];
                return attributes.get(key);
            }
        });

        when(session.getAttribute(anyString())).thenAnswer(new Answer() {
            /**
             * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock)
             */
            @Override
            public Object answer(InvocationOnMock aInvocation) {
                String key = (String) aInvocation.getArguments()[0];
                return attributes.get(key);
            }
        });

        Mockito.doAnswer(new Answer() {
            /**
             * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock)
             */
            @Override
            public Object answer(InvocationOnMock aInvocation) {
                String key = (String) aInvocation.getArguments()[0];
                Object value = aInvocation.getArguments()[1];
                attributes.put(key, value);
                return null;
            }
        }).when(session).setAttribute(anyString(), anyObject());

        Mockito.doAnswer(new Answer() {
            /**
             * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock)
             */
            @Override
            public Object answer(InvocationOnMock aInvocation) {
                String key = (String) aInvocation.getArguments()[0];
                Object value = aInvocation.getArguments()[1];
                attributes.put(key, value);
                return null;
            }
        }).when(request).setAttribute(anyString(), anyObject());
    }

    @After
    public void tearDown() {
        ModelStore persistent = MODEL_LOGIC.getPersistent();
        //System.out.println("After test: persistent.getClass().getName() = " + persistent.getClass().getName());
        Timestamp createDate = new Timestamp(System.currentTimeMillis());
        if (persistent.getClass().getName().equals("com.solomatoff.mvc.model.DbStore")) {
            MODEL_LOGIC.updateRole(new Role(1, "role1", true));
            MODEL_LOGIC.updateRole(new Role(2, "role2", false));
            MODEL_LOGIC.updateRole(new Role(3, "role3", false));
            MODEL_LOGIC.updateUser(new User(1, "name1", "login1", "password", "email1", createDate, 1));
            MODEL_LOGIC.updateUser(new User(2, "name2", "login2", "password", "email2", createDate, 2));
            MODEL_LOGIC.updateUser(new User(3, "name3", "login3", "password", "email3", createDate, 3));

        } else {
            MODEL_LOGIC.updateRole(new Role(4, "role4", true));
            MODEL_LOGIC.updateRole(new Role(5, "role5", false));
            MODEL_LOGIC.updateRole(new Role(6, "role6", false));
            MODEL_LOGIC.updateUser(new User(4, "name4", "login4", "password", "email4", createDate, 4));
            MODEL_LOGIC.updateUser(new User(5, "name5", "login5", "password", "email5", createDate, 5));
            MODEL_LOGIC.updateUser(new User(6, "name6", "login6", "password", "email6", createDate, 6));
        }
    }

    /**
     * Test method for
     * {@link ListUsersServlet#doPost(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoGetAdmin() {
        MODEL_LOGIC.setPersistent(new DbStore());

        parameters.put("typeview", "jsp");
        parameters.put("typestorage", "db");
        String login = "login1";
        parameters.put("login", login);

        servlet.doGet(request, response);

        List<User> users;
        String typeUser = CONTROLLER.getTypeUser(login);
        if (typeUser != null && typeUser.equals("admin")) {
            users = CONTROLLER.executeActionUser("Find All Users", new User());
        } else {
            User user = new User();
            user.setLogin(login);
            users = CONTROLLER.executeActionUser("Find By Login User", user);
        }
        assertThat(request.getAttribute("users").toString(), is(users.toString()));
    }

    /**
     * Test method for
     * {@link ListUsersServlet#doPost(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoGetAnyUser() {
        parameters.put("typeview", "html");
        parameters.put("typestorage", "memory");
        String login = "login5";
        parameters.put("login", login);

        MODEL_LOGIC.setPersistent(new MemoryStore());
        MODEL_LOGIC.addRole(new Role(4, "role4", true));
        MODEL_LOGIC.addRole(new Role(5, "role5", false));
        MODEL_LOGIC.addRole(new Role(6, "role6", false));
        MODEL_LOGIC.addUser(new User(4, "name4", "login4", "password", "email4", new Timestamp(System.currentTimeMillis()), 4));
        MODEL_LOGIC.addUser(new User(5, "name5", "login5", "password", "email5", new Timestamp(System.currentTimeMillis()), 5));
        MODEL_LOGIC.addUser(new User(6, "name6", "login6", "password", "email6", new Timestamp(System.currentTimeMillis()), 6));

        servlet.doGet(request, response);

        List<User> users;
        String typeUser = CONTROLLER.getTypeUser(login);
        if (typeUser != null && typeUser.equals("admin")) {
            users = CONTROLLER.executeActionUser("Find All Users", new User());
        } else {
            User user = new User();
            user.setLogin(login);
            users = CONTROLLER.executeActionUser("Find By Login User", user);
        }
        assertThat(request.getAttribute("users").toString(), is(users.toString()));
    }
}