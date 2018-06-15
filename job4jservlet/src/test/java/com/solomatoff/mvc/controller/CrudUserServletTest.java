package com.solomatoff.mvc.controller;

import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.model.DbStore;
import com.solomatoff.mvc.model.MemoryStore;
import com.solomatoff.mvc.model.ModelLogic;
import com.solomatoff.mvc.model.ModelStore;
import com.solomatoff.mvc.views.HtmlJspView;
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

public class CrudUserServletTest {
    // Controller
    private static final Controller CONTROLLER = Controller.getInstance();
    private static final ModelLogic MODEL_LOGIC = CONTROLLER.getLogic();

    /** Servlet under test. */
    private CrudUserServlet servlet;

    /** Mock request. */
    private HttpServletRequest request;

    /** Mock response. */
    private HttpServletResponse response;

    // Mock requestDispetcher
    private RequestDispatcher requestDispatcher;

    /** Mock session. */
    private HttpSession session;

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
        servlet = new CrudUserServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
        session = mock(HttpSession.class);
        printWriter = new PrintWriter("CrudUser.html");

        when(request.getContextPath()).thenReturn("/items");
        when(response.getWriter()).thenReturn(printWriter);

        Mockito.doAnswer(new Answer() {
            /**
             * @see Answer#answer(InvocationOnMock
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
             * @see Answer#answer(InvocationOnMock
             */
            @Override
            public Object answer(InvocationOnMock aInvocation) {
                return requestDispatcher;
            }
        }).when(request).getRequestDispatcher(anyString());

        when(request.getParameter(anyString())).thenAnswer(new Answer() {
            /**
             * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock
             */
            @Override
            public Object answer(InvocationOnMock aInvocation) {
                String key = (String) aInvocation.getArguments()[0];
                return parameters.get(key);
            }
        });

        when(request.getAttribute(anyString())).thenAnswer(new Answer() {
            /**
             * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock
             */
            @Override
            public Object answer(InvocationOnMock aInvocation) {
                String key = (String) aInvocation.getArguments()[0];
                return attributes.get(key);
            }
        });

        Mockito.doAnswer(new Answer() {
            /**
             * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock
             */
            @Override
            public Object answer(InvocationOnMock aInvocation) {
                String key = (String) aInvocation.getArguments()[0];
                Object value = aInvocation.getArguments()[1];
                attributes.put(key, value);
                return null;
            }
        }).when(request).setAttribute(anyString(), anyObject());

        when(session.getAttribute(anyString())).thenAnswer(new Answer() {
            /**
             * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock
             */
            @Override
            public Object answer(InvocationOnMock aInvocation) {
                String key = (String) aInvocation.getArguments()[0];
                return attributes.get(key);
            }
        });
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
     * {@link CrudUserServlet#doPost(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoPostHtmlMemory1() {
        User newUser = new User(6, "newname6", "newlogin6", "newpassword", "newemail6", new Timestamp(System.currentTimeMillis()), 6);
        parameters.put("action", "Update User");
        parameters.put("id", "6");
        parameters.put("name", newUser.getName());
        parameters.put("login", newUser.getLogin());
        parameters.put("password", newUser.getPassword());
        parameters.put("email", newUser.getEmail());
        parameters.put("createDate", newUser.getCreateDate().toString());
        parameters.put("idRole", newUser.getIdRole().toString());

        CONTROLLER.setPresentation(new HtmlView());
        MODEL_LOGIC.setPersistent(new MemoryStore());

        MODEL_LOGIC.addRole(new Role(4, "role4", true));
        MODEL_LOGIC.addRole(new Role(5, "role5", false));
        MODEL_LOGIC.addRole(new Role(6, "role6", false));
        MODEL_LOGIC.addUser(new User(4, "name4", "login4", "password", "email4", new Timestamp(System.currentTimeMillis()), 4));
        MODEL_LOGIC.addUser(new User(5, "name5", "login5", "password", "email5", new Timestamp(System.currentTimeMillis()), 5));
        MODEL_LOGIC.addUser(new User(6, "name6", "login6", "password", "email6", new Timestamp(System.currentTimeMillis()), 6));

        servlet.doPost(request, response);

        User user = new User();
        user.setId(6);
        user = CONTROLLER.executeActionUser("Find By Id User", user).get(0);
        //System.out.println("user = " + user);
        //System.out.println("user = " + newUser);
        assertEquals(user.getName(), newUser.getName());
        assertEquals(user.getLogin(), newUser.getLogin());
        assertEquals(user.getPassword(), newUser.getPassword());
        assertEquals(user.getEmail(), newUser.getEmail());
        assertEquals(user.getIdRole(), newUser.getIdRole());
    }

    /**
     * Test method for
     * {@link CrudUserServlet#doPost(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoPostJspDb2() {
        User newUser = new User(3, "newname3", "newlogin3", "newpassword", "newemail3", new Timestamp(System.currentTimeMillis()), 3);
        parameters.put("action", "Update User");
        parameters.put("id", "3");
        parameters.put("name", newUser.getName());
        parameters.put("login", newUser.getLogin());
        parameters.put("password", newUser.getPassword());
        parameters.put("email", newUser.getEmail());
        parameters.put("createDate", newUser.getCreateDate().toString());
        parameters.put("idRole", newUser.getIdRole().toString());

        CONTROLLER.setPresentation(new HtmlJspView());
        MODEL_LOGIC.setPersistent(new DbStore());

        servlet.doPost(request, response);

        User user = new User();
        user.setId(3);
        user = CONTROLLER.executeActionUser("Find By Id User", user).get(0);
        //System.out.println("user = " + user);
        //System.out.println("user = " + newUser);
        assertEquals(user.getName(), newUser.getName());
        assertEquals(user.getLogin(), newUser.getLogin());
        assertEquals(user.getPassword(), newUser.getPassword());
        assertEquals(user.getEmail(), newUser.getEmail());
        assertEquals(user.getIdRole(), newUser.getIdRole());
    }

    /**
     * Test method for
     * {@link CrudUserServlet#doGet(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoGetJspDb3() {
        parameters.put("action", "Find By Id User");
        parameters.put("id", "3");

        CONTROLLER.setPresentation(new HtmlJspView());
        MODEL_LOGIC.setPersistent(new DbStore());

        servlet.doGet(request, response);

        List<Role> roles = CONTROLLER.executeActionRole("Find All Roles", new Role());
        //System.out.println("roles = " + roles);
        //System.out.println("roles = " + attributes.get("roles"));
        //System.out.println("roles = " + request.getAttribute("roles"));
        assertThat(request.getAttribute("roles").toString(), is(roles.toString()));

        User user = new User();
        user.setId(3);
        user = CONTROLLER.executeActionUser("Find By Id User", user).get(0);
        //System.out.println("user = " + user);
        //System.out.println("user = " + attributes.get("user"));
        //System.out.println("user = " + request.getAttribute("user"));
        assertThat(request.getAttribute("user").toString(), is(user.toString()));

    }

    /**
     * Test method for
     * {@link CrudUserServlet#doGet(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoGetHtmlMemory4() {
        parameters.put("action", "Update User");
        parameters.put("id", "6");

        CONTROLLER.setPresentation(new HtmlView());
        MODEL_LOGIC.setPersistent(new MemoryStore());

        MODEL_LOGIC.addRole(new Role(4, "role4", true));
        MODEL_LOGIC.addRole(new Role(5, "role5", false));
        MODEL_LOGIC.addRole(new Role(6, "role6", false));
        MODEL_LOGIC.addUser(new User(4, "name4", "login4", "password", "email4", new Timestamp(System.currentTimeMillis()), 4));
        MODEL_LOGIC.addUser(new User(5, "name5", "login5", "password", "email5", new Timestamp(System.currentTimeMillis()), 5));
        MODEL_LOGIC.addUser(new User(6, "name6", "login6", "password", "email6", new Timestamp(System.currentTimeMillis()), 6));

        servlet.doGet(request, response);

        List<Role> roles = CONTROLLER.executeActionRole("Find All Roles", new Role());
        //System.out.println("roles = " + roles);
        //System.out.println("roles = " + attributes.get("roles"));
        //System.out.println("roles = " + request.getAttribute("roles"));
        assertThat(request.getAttribute("roles").toString(), is(roles.toString()));

        User user = new User();
        user.setId(6);
        user = CONTROLLER.executeActionUser("Find By Id User", user).get(0);
        //System.out.println("user = " + user);
        //System.out.println("user = " + attributes.get("user"));
        //System.out.println("user = " + request.getAttribute("user"));
        assertThat(request.getAttribute("user").toString(), is(user.toString()));
    }
}