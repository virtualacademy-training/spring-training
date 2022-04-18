package com.virtualacademy.training.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtualacademy.training.commons.NorthwindException;
import com.virtualacademy.training.model.Category;
import com.virtualacademy.training.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Servlet implementation class CategoryViewServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		String userName = request.getParameter("userName");
		 String password = request.getParameter("password");
		HttpSession session = request.getSession(true);
		 if (userName.equals("admin") && password.equals("admin")) {

			 session.setAttribute("userName", userName);
			 response.sendRedirect("index.jsp");
		 } else {
			 session.invalidate();
			 request.setAttribute("LOGIN_STATUS", "FAIL");
			 request.getRequestDispatcher("login.jsp").forward(request, response);
		 }







	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
