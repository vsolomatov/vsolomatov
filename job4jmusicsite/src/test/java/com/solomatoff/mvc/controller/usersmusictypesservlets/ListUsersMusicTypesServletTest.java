package com.solomatoff.mvc.controller.usersmusictypesservlets;

import com.solomatoff.mvc.controller.CommonTest;
import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.controller.usersmusictypesservlets.ListUsersMusicTypesServlet;
import com.solomatoff.mvc.entities.UsersMusicTypes;
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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListUsersMusicTypesServletTest {
    // Controller
    private static final Controller CONTROLLER = Controller.getInstance();
    private static final ModelLogic MODEL_LOGIC = CONTROLLER.getLogic();

    /** Servlet under test. */
    private ListUsersMusicTypesServlet servlet;

    /** Mock request. */
    private HttpServletRequest request;

    /** Mock response. */
    private HttpServletResponse response;

    // Mock requestDispetcher
    private RequestDispatcher requestDispatcher;

    /** Request's parameter map. */
    private Map parameters;

    /** Session's and Request's attribute map. */
    private Map attributes;

    /** Responce's printWriter. */
    private PrintWriter printWriter;

    @Before
    public void setUp() throws IOException {

        attributes = new HashMap();
        parameters = new HashMap();
        servlet = new ListUsersMusicTypesServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
        //printWriter = new PrintWriter("ListUsersMusicTypes.html");

        when(request.getContextPath()).thenReturn("/items");
        when(response.getWriter()).thenReturn(printWriter);

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

        Mockito.doAnswer(new Answer() {
            /**
             * @see Answer#answer(InvocationOnMock)
             */
            @Override
            public Object answer(InvocationOnMock aInvocation) {
                return requestDispatcher;
            }
        }).when(request).getRequestDispatcher(anyString());

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
        CommonTest.clearAndCreateDataInDataBase();
        CommonTest.clearAndCreateDataInMemory();
    }

    /**
     * Test method for
     * {@link ListUsersMusicTypesServlet#doPost(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoGet1() {
        CONTROLLER.setPresentation(new HtmlJspView());
        MODEL_LOGIC.setPersistent(DbStore.getInstance());
        parameters.put("userid", "3");
        servlet.doGet(request, response);
        List<UsersMusicTypes> usersMusicTypes = CONTROLLER.getLogic().findByIdUsersMusicTypes(new UsersMusicTypes(3, null));

        //System.out.println("usersMusicTypes = " + usersMusicTypes);
        //System.out.println("request.getAttribute(usersmusictypes) = " + request.getAttribute("usersmusictypes"));
        assertThat(request.getAttribute("usersmusictypes").toString(), is(usersMusicTypes.toString()));
    }

    /**
     * Test method for
     * {@link ListUsersMusicTypesServlet#doPost(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoGet2() {
        CONTROLLER.setPresentation(new HtmlJspView());
        MODEL_LOGIC.setPersistent(MemoryStore.getInstance());
        parameters.put("userid", "6");
        servlet.doGet(request, response);
        List<UsersMusicTypes> usersMusicTypes = CONTROLLER.getLogic().findByIdUsersMusicTypes(new UsersMusicTypes(6, null));

        //System.out.println("usersMusicTypes = " + usersMusicTypes);
        //System.out.println("request.getAttribute(usersmusictypes) = " + request.getAttribute("usersmusictypes"));
        assertThat(request.getAttribute("usersmusictypes").toString(), is(usersMusicTypes.toString()));
    }
}