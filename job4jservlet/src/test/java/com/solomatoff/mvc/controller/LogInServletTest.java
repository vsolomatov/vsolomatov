package com.solomatoff.mvc.controller;

import com.solomatoff.mvc.entities.Role;
import com.solomatoff.mvc.entities.User;
import com.solomatoff.mvc.model.DbStore;
import com.solomatoff.mvc.model.MemoryStore;
import com.solomatoff.mvc.model.ModelLogic;
import com.solomatoff.mvc.views.HtmlJspView;
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
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogInServletTest {
    // Controller
    private static final Controller CONTROLLER = Controller.getInstance();
    private static final ModelLogic MODEL_LOGIC = CONTROLLER.getLogic();

    /** Servlet under test. */
    private LogInServlet servlet;

    /** Mock request. */
    private HttpServletRequest request;

    /** Mock response. */
    private HttpServletResponse response;

    /** Mock session. */
    private HttpSession session;

    // Mock requestDispetcher
    private RequestDispatcher requestDispatcher;

    /** Session's attribute map. */
    private Map attributes;

    /** Request's parameter map. */
    private Map parameters;

    @Before
    public void setUp() {

        attributes = new HashMap();
        parameters = new HashMap();
        servlet = new LogInServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        requestDispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher("/WEB-INF/views/LoginView.jsp")).thenReturn(requestDispatcher);
        when(request.getContextPath()).thenReturn("/items");
        when(request.getSession()).thenReturn(session);

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
        }).when(session).setAttribute(anyString(), anyObject());
    }

    /**
     * Test method for
     * {@link LogInServlet#doPost(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoPostLoginSuccess() throws ServletException, IOException {
        parameters.put("login", "login1");
        parameters.put("password", "password");
        parameters.put("typeview", "jsp");
        parameters.put("typestorage", "db");

        MODEL_LOGIC.setPersistent(new DbStore());

        servlet.doPost(request, response);

        assertThat(session.getAttribute("login"), is("login1"));
        assertThat(session.getAttribute("typeuser"), is("admin"));
    }

    /**
     * Test method for
     * {@link LogInServlet#doPost(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoPostLoginFailure() throws ServletException, IOException {
        parameters.put("login", "login4");
        parameters.put("password", "pass");
        parameters.put("typeview", "html");
        parameters.put("typestorage", "memory");

        MODEL_LOGIC.setPersistent(new MemoryStore());
        MODEL_LOGIC.addRole(new Role(4, "role4", true));
        MODEL_LOGIC.addRole(new Role(5, "role5", false));
        MODEL_LOGIC.addRole(new Role(6, "role6", false));
        MODEL_LOGIC.addUser(new User(4, "name4", "login4", "password", "email4", new Timestamp(System.currentTimeMillis()), 4));
        MODEL_LOGIC.addUser(new User(5, "name5", "login5", "password", "email5", new Timestamp(System.currentTimeMillis()), 5));
        MODEL_LOGIC.addUser(new User(6, "name6", "login6", "password", "email6", new Timestamp(System.currentTimeMillis()), 6));

        servlet.doPost(request, response);

        assertThat(session.getAttribute("login"), is(nullValue()));
        assertThat(session.getAttribute("typeuser"), is(nullValue()));
    }

    /**
     * Test method for
     * {@link LogInServlet#doPost(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoGet() throws ServletException, IOException {
        servlet.doGet(request, response);
        assertThat(request.getRequestDispatcher("/WEB-INF/views/LoginView.jsp"), is(requestDispatcher));
    }
}