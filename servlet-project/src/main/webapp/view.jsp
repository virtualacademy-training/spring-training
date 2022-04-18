<%@page import="com.virtualacademy.training.model.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>List of Categories</h1>
<table border="1">
 <%
	List<Category> list =  (List)request.getAttribute("categories");
 for(Category cat : list){
%>
	
		<tr>
			<td><%=cat.getCategoryId() %></td>
			<td><%=cat.getCategoryName() %></td>
			<td><%=cat.getDescription() %></td>
			<td><a href="category-form?catid=<%=cat.getCategoryId()%>">Edit</a>&nbsp;&nbsp;
			<a href="category-form?catid=<%=cat.getCategoryId()%>">Delete</a>
			</td> 
		</tr>
	
<% 	 
 }
 %>
 </table>
</body>
</html>