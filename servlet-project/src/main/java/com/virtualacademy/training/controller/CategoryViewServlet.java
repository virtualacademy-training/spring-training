package com.virtualacademy.training.controller;


import com.virtualacademy.training.commons.NorthwindException;
import com.virtualacademy.training.model.Category;
import com.virtualacademy.training.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Servlet implementation class CategoryViewServlet
 */
@WebServlet("/categories-list")
public class CategoryViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
	 	 CategoryService service = new CategoryService();
		 try{
			 List<Category> list = service.getAllCategories();
			/* out.print("<table border='1'>");
			 for(Category c : list){
				 out.print("<tr>");
				 out.print("<td> "+c.getCategoryId()+"</td>");
				 out.print("<td> "+c.getCategoryName()+"</td>");
				 out.print("<td> "+c.getDescription()+"</td>");
				 out.print("</tr>");
			 }*/
			 //out.print("</table>");
			 	 ObjectMapper objectMapper = new ObjectMapper();
			 String val =  objectMapper.writeValueAsString(list);
			request.setAttribute("categories", list);
			List<String> courses = new ArrayList<>();
			courses.add("C++");
			courses.add("JAVA");
			courses.add("DBMS");
			request.setAttribute("courses", courses);
			request.setAttribute("listPage", "list.jsp");

			 request.getRequestDispatcher("index.jsp").forward(request, response);
			 
			 
		 }catch(NorthwindException ex){
			 ex.printStackTrace();
		 }
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
		doGet(request, response);
	}

	public static void issueCookieHttpOnly(HttpServletResponse response,
										   String cookieName,
										   String cookieValue,

										   long maxAgeInSeconds) {

		Date expireDate= new Date();
		expireDate.setTime (expireDate.getTime() + (1000 * maxAgeInSeconds));
		// The following pattern does not work for IE.
		// DateFormat df = new SimpleDateFormat("dd MMM yyyy kk:mm:ss z");

		// This pattern works for Firefox, Chrome, Safari and Opera, as well as IE.
		DateFormat df = new SimpleDateFormat("EEE, dd-MMM-yyyy kk:mm:ss z");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		String cookieExpire = df.format(expireDate);

		StringBuilder sb = new StringBuilder(cookieName);
		sb.append("=");
		sb.append(cookieValue);
		sb.append(";expires=");
		sb.append(cookieExpire);
		sb.append(";path=");

		sb.append(";HttpOnly");

		response.setHeader("SET-COOKIE", sb.toString());
	}
}
