package com.solomatoff.mvc.controller.userservlets;

import com.solomatoff.mvc.controller.CommonTest;
import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.controller.userservlets.CrudUserServlet;
import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.model.ModelLogic;
import com.solomatoff.mvc.model.store.DbStore;
import com.solomatoff.mvc.model.store.MemoryStore;
import com.solomatoff.mvc.views.HtmlJspView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.servlet.RequestDispatcher;
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
        CommonTest.clearAndCreateDataInDataBase();
        CommonTest.clearAndCreateDataInMemory();

        attributes = new HashMap();
        parameters = new HashMap();
        servlet = new CrudUserServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
        session = mock(HttpSession.class);
        //printWriter = new PrintWriter("CrudUser.html");

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

    /**
     * Test method for
     * {@link CrudUserServlet#doPost(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoPostMemory() {
        User expected = new User(6, "newname6", "newlogin6", "newpassword", "newemail6", new Timestamp(System.currentTimeMillis()));
        parameters.put("action", "Update User");
        parameters.put("id", "6");
        parameters.put("name", expected.getName());
        parameters.put("login", expected.getLogin());
        parameters.put("password", expected.getPassword());
        parameters.put("email", expected.getEmail());
        parameters.put("createDate", expected.getCreateDate().toString());

        MODEL_LOGIC.setPersistent(MemoryStore.getInstance());

        servlet.doPost(request, response);

        User result = new User();
        result.setId(6);
        result = CONTROLLER.executeActionUser("Find By Id User", result).get(0);

        assertEquals(result.getName(), expected.getName());
        assertEquals(result.getLogin(), expected.getLogin());
        assertEquals(result.getPassword(), expected.getPassword());
        assertEquals(result.getEmail(), expected.getEmail());
    }

    /**
     * Test method for
     * {@link CrudUserServlet#doPost(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoPostDb() {
        User expected = new User(3, "newname3", "newlogin3", "newpassword", "newemail3", new Timestamp(System.currentTimeMillis()));
        parameters.put("action", "Update User");
        parameters.put("id", "3");
        parameters.put("name", expected.getName());
        parameters.put("login", expected.getLogin());
        parameters.put("password", expected.getPassword());
        parameters.put("email", expected.getEmail());
        parameters.put("createDate", expected.getCreateDate().toString());

        MODEL_LOGIC.setPersistent(DbStore.getInstance());

        servlet.doPost(request, response);

        User result = new User();
        result.setId(3);
        result = CONTROLLER.executeActionUser("Find By Id User", result).get(0);
        assertEquals(result.getName(), expected.getName());
        assertEquals(result.getLogin(), expected.getLogin());
        assertEquals(result.getPassword(), expected.getPassword());
        assertEquals(result.getEmail(), expected.getEmail());
    }

    /**
     * Test method for
     * {@link CrudUserServlet#doGet(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoGetDb() {
        parameters.put("action", "Delete User");
        parameters.put("id", "3");

        CONTROLLER.setPresentation(new HtmlJspView());
        MODEL_LOGIC.setPersistent(DbStore.getInstance());

        servlet.doGet(request, response);

        User user = new User();
        user.setId(3);
        user = CONTROLLER.executeActionUser("Find By Id User", user).get(0);
        assertThat(request.getAttribute("user").toString(), is(user.toString()));

    }

    /**
     * Test method for
     * {@link CrudUserServlet#doGet(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoGetMemory() {
        parameters.put("action", "Delete User");
        parameters.put("id", "6");

        CONTROLLER.setPresentation(new HtmlJspView());
        MODEL_LOGIC.setPersistent(MemoryStore.getInstance()); // MemoryStore

        servlet.doGet(request, response);

        User user = new User();
        user.setId(6);
        user = CONTROLLER.executeActionUser("Find By Id User", user).get(0);
        assertThat(request.getAttribute("user").toString(), is(user.toString()));
    }
}