package com.virtualacademy.training.controller;


import com.virtualacademy.training.commons.NorthwindException;
import com.virtualacademy.training.model.Category;
import com.virtualacademy.training.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class CategoryViewServlet
 */
@WebServlet("/category-form")
public class CategoryEditServlet extends HttpServlet {
	CategoryService service = new CategoryService();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void handleForward(HttpServletRequest request, HttpServletResponse response,int categoryId) throws Exception{
    	 CategoryService service = new CategoryService();
	 
			Category cat = new Category();
			if (categoryId > 0) {
				cat = service.findCategory(categoryId);
			}
			request.setAttribute("category", cat);
			request.setAttribute("listPage", "editcat.jsp");
 
			request.getRequestDispatcher("index.jsp").forward(request, response);
	 

			 
		 
    } 
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 if(request.getParameter("action") == null){
			 int categoryId = 0;
			 if (request.getParameter("categoryId") != null) {
				   categoryId = Integer.parseInt(request.getParameter("categoryId"));	 
			 }
			try {
			 handleForward(request, response, categoryId);
			}catch(Exception ex) {
				
			}
		 }else{
			 String catId = request.getParameter("categoryId");
			 String catName =request.getParameter("categoryName");
			 String desc = request.getParameter("description");
			 Category cat = new Category();
			 cat.setCategoryId(Integer.parseInt(catId));
			 cat.setCategoryName(catName);
			 cat.setDescription(desc);
			 if (request.getParameter("action").equalsIgnoreCase("edit") ) {
				
				 try {
					 	service.updateCategory(cat);
					  
						request.setAttribute("message", "Record Updated successfully");
						request.getRequestDispatcher("categories-list").forward(request, response);
					} catch (NorthwindException e) {
						request.setAttribute("category", cat);
						request.setAttribute("listPage", "editcat.jsp");
						request.setAttribute("error", e.getMessage());
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
 
				
		 
			 } else {
				 try {
						service.saveCategory(cat);
					 
						request.setAttribute("message", "Record Saved successfully");
						request.getRequestDispatcher("categories-list").forward(request, response);
					} catch (NorthwindException e) {
						request.setAttribute("category", cat);
						 
						request.setAttribute("error", e.getMessage());
						request.getRequestDispatcher("categories-list").forward(request, response);
					}
			 }
			
 
		 }
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
		doGet(request, response);
	}

}
