package com.solomatoff.mvc.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    public void destroy() {
        System.out.println("Уничтожение фильтра AuthFilter");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String pTypeView = request.getParameter("typeview");
        String pTypeStorage = request.getParameter("typestorage");
        HttpSession session = request.getSession(false);
        String loginURI = String.format("%s/login", request.getContextPath());

        boolean loggedIn = session != null && session.getAttribute("login") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);

        if (loggedIn || loginRequest) {
            chain.doFilter(request, response); // User is logged in, just continue request.
        } else {
            //System.out.println(String.format("    Call login: %s?typeview=%s&typestorage=%s", loginURI, pTypeView, pTypeStorage));
            response.sendRedirect(String.format("%s?typeview=%s&typestorage=%s", loginURI, pTypeView, pTypeStorage)); // Not logged in, show login page.
        }
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("Инициализация фильтра AuthFilter");
    }

}
