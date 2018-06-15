package com.solomatoff.mvc.controller;

import com.solomatoff.mvc.model.ModelLogic;
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
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogOutServletTest {

    /** Servlet under test. */
    private LogOutServlet servlet;

    /** Mock request. */
    private HttpServletRequest request;

    /** Mock response. */
    private HttpServletResponse response;

    /** Mock session. */
    private HttpSession session;

    // Mock requestDispetcher
    private RequestDispatcher requestDispatcher;

    /** Request's parameter map. */
    private Map parameters;

    /** Result's map. */
    private Map result;

    @Before
    public void setUp() throws IOException {

        parameters = new HashMap();
        result = new HashMap();
        servlet = new LogOutServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        requestDispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/WEB-INF/views/LogoutView.jsp")).thenReturn(requestDispatcher);
        when(request.getContextPath()).thenReturn("/items");
        when(request.getParameter(anyString())).thenAnswer(new Answer() {
            /**
             * @see Answer#answer(InvocationOnMock)
             */
            @Override
            public Object answer(InvocationOnMock aInvocation) {
                String key = (String) aInvocation.getArguments()[0];
                return parameters.get(key);
            }
        });

        Mockito.doAnswer(new Answer() {
            /**
             * @see Answer#answer(InvocationOnMock)
             */
            @Override
            public Object answer(InvocationOnMock aInvocation) {
                String key = "sendRedirect";
                String value = (String) aInvocation.getArguments()[0];
                result.put(key, value);
                return null;
            }
        }).when(response).sendRedirect(anyString());

        Mockito.doAnswer(new Answer() {
            /**
             * @see Answer#answer(InvocationOnMock)
             */
            @Override
            public Object answer(InvocationOnMock aInvocation) {
                String key = "sessionInvalidate";
                String value = "Success";
                result.put(key, value);
                return null;
            }
        }).when(session).invalidate();
        ControllerTest.clearAndCreateData();
    }

    /**
     * Test method for
     * {@link LogInServlet#doPost(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoPostYes() throws IOException {
        parameters.put("return", "Yes");
        servlet.doPost(request, response);
        assertThat(result.get("sessionInvalidate"), is("Success"));
        assertThat(result.get("sendRedirect"), is("/items/"));
    }

    /**
     * Test method for
     * {@link LogInServlet#doPost(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoPostNo() throws IOException {
        parameters.put("return", "No");
        servlet.doPost(request, response);
        assertThat(result.get("sessionInvalidate"), is(nullValue()));
        assertThat(result.get("sendRedirect"), is("/items/protected/list"));
    }

    /**
     * Test method for
     * {@link LogInServlet#doPost(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoGet() throws ServletException, IOException {
        servlet.doGet(request, response);
        assertThat(request.getRequestDispatcher("/WEB-INF/views/LogoutView.jsp"), is(requestDispatcher));
    }
}