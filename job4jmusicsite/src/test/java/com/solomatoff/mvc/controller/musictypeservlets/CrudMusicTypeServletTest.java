package com.solomatoff.mvc.controller.musictypeservlets;

import com.solomatoff.mvc.controller.CommonTest;
import com.solomatoff.mvc.controller.Controller;
import com.solomatoff.mvc.controller.musictypeservlets.CrudMusicTypeServlet;
import com.solomatoff.mvc.entities.MusicType;
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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CrudMusicTypeServletTest {
    // Controller
    private static final Controller CONTROLLER = Controller.getInstance();
    private static final ModelLogic MODEL_LOGIC = CONTROLLER.getLogic();

    /** Servlet under test. */
    private CrudMusicTypeServlet servlet;

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
        servlet = new CrudMusicTypeServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
        session = mock(HttpSession.class);
        //printWriter = new PrintWriter("CrudMusicType.html");

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
        CommonTest.clearAndCreateDataInDataBase();
        CommonTest.clearAndCreateDataInMemory();
    }

    /**
     * Test method for
     * {@link CrudMusicTypeServlet#doPost(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoPostHtmlMemory1() {
        MusicType newMusicType = new MusicType(6, "newmusicType6");
        parameters.put("action", "Update MusicType");
        parameters.put("id", "6");
        parameters.put("musictypename", newMusicType.getMusicTypeName());

        //CONTROLLER.setPresentation(new HtmlView());
        MODEL_LOGIC.setPersistent(MemoryStore.getInstance());

        servlet.doPost(request, response);

        MusicType musicType = new MusicType();
        musicType.setId(6);
        musicType = CONTROLLER.getLogic().findByIdMusicType(musicType).get(0);
        assertEquals(musicType.getMusicTypeName(), newMusicType.getMusicTypeName());
    }

    /**
     * Test method for
     * {@link CrudMusicTypeServlet#doPost(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoPostJspDb2() {
        MusicType newMusicType = new MusicType(3, "newmusicType3");
        parameters.put("action", "Update MusicType");
        parameters.put("id", "3");
        parameters.put("musictypename", newMusicType.getMusicTypeName());

        CONTROLLER.setPresentation(new HtmlJspView());
        MODEL_LOGIC.setPersistent(DbStore.getInstance());

        servlet.doPost(request, response);

        MusicType musicType = new MusicType();
        musicType.setId(3);
        musicType = CONTROLLER.getLogic().findByIdMusicType(musicType).get(0);
        assertEquals(musicType.getMusicTypeName(), newMusicType.getMusicTypeName());
    }

    /**
     * Test method for
     * {@link CrudMusicTypeServlet#doGet(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoGetJspDb3() {
        parameters.put("action", "Find By Id MusicType");
        parameters.put("id", "3");
        parameters.put("musictypename", "musicType3");

        CONTROLLER.setPresentation(new HtmlJspView());
        MODEL_LOGIC.setPersistent(DbStore.getInstance());

        servlet.doGet(request, response);

        MusicType musicType = new MusicType();
        musicType.setId(3);
        musicType = CONTROLLER.getLogic().findByIdMusicType(musicType).get(0);
        assertThat(request.getAttribute("musictype").toString(), is(musicType.toString()));
    }

    /**
     * Test method for
     * {@link CrudMusicTypeServlet#doGet(HttpServletRequest, HttpServletResponse)} .
     *
     */
    @Test
    public void testDoGetHtmlMemory4() {
        parameters.put("action", "Update MusicType");
        parameters.put("id", "6");
        parameters.put("musictypename", "musicType6");

        //CONTROLLER.setPresentation(new HtmlView());
        MODEL_LOGIC.setPersistent(MemoryStore.getInstance());

        servlet.doGet(request, response);

        MusicType musicType = new MusicType();
        musicType.setId(6);
        musicType = CONTROLLER.getLogic().findByIdMusicType(musicType).get(0);
        assertThat(request.getAttribute("musictype").toString(), is(musicType.toString()));
    }
}