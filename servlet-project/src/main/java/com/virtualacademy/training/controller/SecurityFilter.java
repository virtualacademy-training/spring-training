package com.virtualacademy.training.controller;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class FailServlet
 */
@WebFilter(filterName = "SecurityFilter", urlPatterns = "/*")
public class SecurityFilter implements Filter {
	public void init(FilterConfig filterConfig)
			throws ServletException
	{
	}

	@Override
	public void doFilter(ServletRequest req,
						 ServletResponse resp,
						 FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		if (request.getRequestURI().contains("login.jsp")
				|| request.getRequestURI().contains("login")
		|| request.getRequestURI().contains("logout")) {
			chain.doFilter(req, resp);
		} else {
			HttpSession session = request.getSession();

			if (session != null && session.getAttribute("userName") != null) {
				chain.doFilter(req, resp);
			} else {
				response.sendRedirect("login.jsp");
			}

		}
	}

	public void destroy() {}
}
