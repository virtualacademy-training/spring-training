package com.virtualacademy.training.controller;


import com.virtualacademy.training.model.Category;
import com.virtualacademy.training.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * Servlet implementation class CategoryViewServlet
 */
@WebServlet("/api/categories/*")
public class CategoryApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
		 CategoryService service = new CategoryService();
		 response.setHeader("Content-Type", "application/json");
		 ObjectMapper objectMapper = new ObjectMapper();
		 try{
			 if (request.getPathInfo() != null && request.getPathInfo().length() > 1) {
				 List<String> paths =  Arrays.asList(request.getPathInfo().substring(1).split("/"));
				 if (paths.size() > 1) {
					 
					 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					 out.println("Invalid path");
				 } else {
					 Category category = service.findCategory(Integer.parseInt(paths.get(0)));
					 out.print(objectMapper.writeValueAsString(category));
				 }
			
		 
			 } else {
				 List<Category> list = service.getAllCategories();
				  
				 
				 out.print(objectMapper.writeValueAsString(list));
			 }
		
			 
			 
		 }catch(Exception e){
			 out.write("{\"error\": \""+e.getMessage()+"\"}");
			 
			 
		 }
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
	 StringBuffer jb = new StringBuffer();
	  String line = null;
	  try {
	    BufferedReader reader = request.getReader();
	    while ((line = reader.readLine()) != null)
	      jb.append(line);
	  
	  ObjectMapper objectMapper = new ObjectMapper();
	  Category category = objectMapper.readValue(jb.toString(), Category.class);
	  CategoryService service = new CategoryService();
	  	service.saveCategory(category);
	  	 out.write(objectMapper.writeValueAsString(category));
	  	
	  	
	  } catch (Exception e) { 
		  out.write("{\"error\": \""+e.getMessage()+"\"}");
	  }
	 
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		PrintWriter out = response.getWriter();
		 StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  
		  ObjectMapper objectMapper = new ObjectMapper();
		  Category category = objectMapper.readValue(jb.toString(), Category.class);
		  CategoryService service = new CategoryService();
		  	service.updateCategory(category);
		  out.write(objectMapper.writeValueAsString(category));
		  	
		  	
		  } catch (Exception e) { 
			  out.write("{\"error\": \""+e.getMessage()+"\"}");
		  }
		 
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
		 CategoryService service = new CategoryService();
		 response.setHeader("Content-Type", "application/json");
		 ObjectMapper objectMapper = new ObjectMapper();
		 try{
			 if (request.getPathInfo() != null && request.getPathInfo().length() > 1) {
				 List<String> paths =  Arrays.asList(request.getPathInfo().substring(1).split("/"));
				 if (paths.size() > 1) {
					 
					 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					 out.write("{\"error\": \"Invalid path\"}");
				 } else {
					   service.deleteCategory(Integer.parseInt(paths.get(0)));
					   out.write("{\"message\": \"Record deleted\"}");
				 }
			
		 
			 }
		
			 
			 
		 }catch(Exception e){
			 e.printStackTrace();
			 out.write("{\"error\": \""+e.getMessage()+"\"}");
			 
			 
		 }
		 
	}

}
