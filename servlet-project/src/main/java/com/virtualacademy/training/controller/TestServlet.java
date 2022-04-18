package com.virtualacademy.training.controller;


import com.virtualacademy.training.model.Category;
import com.virtualacademy.training.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

 
@WebServlet("/CategoryController")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		 String catId = request.getParameter("catid");
		 String catName =request.getParameter("catnam");
		 String desc = request.getParameter("desc");
		 Category cat = new Category();
		 cat.setCategoryId(Integer.parseInt(catId));
		 cat.setCategoryName(catName);
		 cat.setDescription(desc);
		 CategoryService service = new CategoryService();
		 try{
			 service.saveCategory(cat);
			 out.print("<div style='color:green'>Category saved successfully</div>");
		 }catch(Exception ex){
			  out.print("<div style='color:red'>Unable to Save Category</div>");
		 }
		
	 
	}
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		processRequest(request, response);
   	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
