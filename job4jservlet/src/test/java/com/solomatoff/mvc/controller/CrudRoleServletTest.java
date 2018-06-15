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

public class CrudRoleServletTest {
    // Controller
    private static final Controller CONTROLLER = Controller.getInstance();
    private static final ModelLogic MODEL_LOGIC = CONTROLLER.getLogic();

    /** Servlet under test. */
    private CrudRoleServlet servlet;

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
        servlet = new CrudRoleServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
        session = mock(HttpSession.class);
        printWriter = new PrintWriter("CrudRole.html");

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
        ControllerTest.clearAndCreateData();
    }

    /**
     * Test method for
     * {@link CrudRoleServlet#doPost(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoPostHtmlMemory1() {
        Role newRole = new Role(6, "newrole6", false);
        parameters.put("action", "Update Role");
        parameters.put("id", "6");
        parameters.put("name", newRole.getName());
        parameters.put("isAdmin", newRole.getIsAdmin().toString());

        CONTROLLER.setPresentation(new HtmlView());
        MODEL_LOGIC.setPersistent(MemoryStore.getInstance());

        servlet.doPost(request, response);

        Role role = new Role();
        role.setId(6);
        role = CONTROLLER.executeActionRole("Find By Id Role", role).get(0);
        //System.out.println("role = " + role);
        //System.out.println("role = " + newRole);
        assertEquals(role.getName(), newRole.getName());
        assertEquals(role.getIsAdmin(), newRole.getIsAdmin());
    }

    /**
     * Test method for
     * {@link CrudRoleServlet#doPost(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoPostJspDb2() {
        Role newRole = new Role(3, "newrole3", false);
        parameters.put("action", "Update Role");
        parameters.put("id", "3");
        parameters.put("name", newRole.getName());
        parameters.put("isAdmin", newRole.getIsAdmin().toString());

        CONTROLLER.setPresentation(new HtmlJspView());
        MODEL_LOGIC.setPersistent(DbStore.getInstance());

        servlet.doPost(request, response);

        Role role = new Role();
        role.setId(3);
        role = CONTROLLER.executeActionRole("Find By Id Role", role).get(0);
        //System.out.println("role = " + role);
        //System.out.println("role = " + newRole);
        assertEquals(role.getName(), newRole.getName());
        assertEquals(role.getIsAdmin(), newRole.getIsAdmin());
    }

    /**
     * Test method for
     * {@link CrudRoleServlet#doGet(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoGetJspDb3() {
        parameters.put("action", "Find By Id Role");
        parameters.put("id", "3");
        parameters.put("name", "role3");

        CONTROLLER.setPresentation(new HtmlJspView());
        MODEL_LOGIC.setPersistent(DbStore.getInstance());

        servlet.doGet(request, response);

        Role role = new Role();
        role.setId(3);
        role = CONTROLLER.executeActionRole("Find By Id Role", role).get(0);
        //System.out.println("role = " + role);
        //System.out.println("role = " + attributes.get("role"));
        //System.out.println("role = " + request.getAttribute("role"));
        assertThat(request.getAttribute("role").toString(), is(role.toString()));
    }

    /**
     * Test method for
     * {@link CrudRoleServlet#doGet(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoGetHtmlMemory4() {
        parameters.put("action", "Update Role");
        parameters.put("id", "6");
        parameters.put("name", "role6");

        CONTROLLER.setPresentation(new HtmlView());
        MODEL_LOGIC.setPersistent(MemoryStore.getInstance());

        servlet.doGet(request, response);

        Role role = new Role();
        role.setId(6);
        role = CONTROLLER.executeActionRole("Find By Id Role", role).get(0);
        //System.out.println("role = " + role);
        //System.out.println("role = " + attributes.get("role"));
        //System.out.println("role = " + request.getAttribute("role"));
        assertThat(request.getAttribute("role").toString(), is(role.toString()));
    }

}