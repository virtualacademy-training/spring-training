<%@page import="com.virtualacademy.training.model.Category"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
	Category category = (Category)request.getAttribute("category");
%>
 

 <div class="card">
    <form  action="category-form?action=edit" method="post" class="col s12">
          <div class="card-content">

            <span class="card-title">Product Category</span>

        <div class="row">
       
            <div class="row">
              <div class="input-field col s6">
                <input id="categoryId" name = "categoryId" type="text" class="validate" value="<%=category.getCategoryId() %>">
                <label for="categoryId">Category ID</label>
              </div>
              <div class="input-field col s6">
                <input  id="categoryName" name="categoryName" type="text" class="validate" value="<%=category.getCategoryName() %>">
                <label for="categoryName">Category Name</label>
              </div>
            </div>

            <div class="row">
              <div class="input-field col s12">
                <input  id="description" name="description" type="text" class="validate" value="<%=category.getDescription() %>">
                <label for="description">Description</label>
              </div>
            </div>



        </div>
          </div>
          <div class="card-action">
          
 <% if (category.getCategoryId() == 0) { %>            
 <button type="submit" formaction="category-form?action=new"  class="add-btn btn blue darken-3"><i class="fa fa-save	"></i> Save Category</button>
 
 <% } %>
 <% if (category.getCategoryId() > 0) { %>
            <button type="submit" class="update-btn btn orange"><i class="fa fa-pencil-square-o"></i> Update Category</button>
             
<% } %>
            <button  type=submit  formaction="categories-list" class="back-btn btn grey pull-right"><i class="fa fa-chevron-circle-left"></i> Back</button>
          </div>
                    </form>
        </div>
        
</body>
</html>