package com.virtualacademy.training.controller;


import com.virtualacademy.training.commons.NorthwindException;
import com.virtualacademy.training.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class CategoryViewServlet
 */
@WebServlet("/category-delete")
public class CategoryDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out = response.getWriter();
		 CategoryService service = new CategoryService();
		 try{
			 int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			 service.deleteCategory(categoryId);
 
	 
			 request.setAttribute("message", "Record deleted successfully");
				request.getRequestDispatcher("categories-list").forward(request, response);
			 

		 }catch(NorthwindException ex){
			 request.setAttribute("error", ex.getMessage());
				request.getRequestDispatcher("categories-list").forward(request, response);
		 }
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
		doGet(request, response);
	}

}
