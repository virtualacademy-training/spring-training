<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="assets/materialize.min.css">
  <link href="assets/css/font-awesome.min.css" rel="stylesheet" >
  <title>Inventory Management</title>
</head>
<body>

  <!-- Navbar -->
  <nav>
    <div class="nav-wrapper blue">
      <div class="container">
        <a href="#" class="brand-logo left">Inventory Management</a>

        <c:if test="${userName != null}">
           <span style="float: right; padding-left: 20px">
               <a href="logout">Logout</a>
           </span>
            <span style="float: right"> Welcome ${userName} - ${SESSION_ID}</span>

        </c:if>
        <c:if test="${userName == null}">
           <span style="float: right; padding-left: 20px">
               <a href="login.jsp">Login</a>
           </span>
        </c:if>

      </div>
    </div>
  </nav>

  <br>
<div>
  <div class="row">
    <div class="col s2">


      <div class="collection">
        <a href="#!" class="collection-item">Product </a>
        <% 
        String active = "active";
        if (request.getAttribute("javax.servlet.forward.request_uri") != null ) {
	        if (request.getAttribute("javax.servlet.forward.request_uri").toString().contains("categories-list")) {
	        	  active = "active";
	        }
        }
        %>
        <a href="categories-list"  class="collection-item  ${active}">Categories</a>
        <a href="#!" class="collection-item">Users</a>
        <a href="#!" class="collection-item">Customer</a>
        <a href="#!" class="collection-item">Orders</a>
      </div>


    </div>
    <div class="col s10">

    <%
    	if (request.getAttribute("message") != null) {
    %>
	<div class="chip green darken-2 white-text">
    	${requestScope["message"]}
  	</div>
        <%} %>
         <%
    	if (request.getAttribute("error") != null) {
    %>
	<div class="chip red darken-2 white-text">
    	${requestScope["error"]}
  	</div>
        <%} %>
    <%
		String listPage = (String)request.getAttribute("listPage");
    if (listPage != null) {
	%>

       <jsp:include page="${listPage}"/>


      <% } %>
    </div>
  </div>
</div>


  <div id="modal1" class="modal" style="width: 400px">
    <div class="modal-content">
      <h4>Delete Confirmation</h4>
      <p>Are you sure to delete?</p>
    </div>
    <div class="modal-footer">
      <a href="#!" class="yes-btn modal-close waves-effect waves-green btn-flat" >Yes</a>
      <a href="#!" class="modal-close waves-effect waves-green btn-flat">No</a>
    </div>
  </div>
  <script
  src="assets/jquery-3.2.1.min.js"></script>
  <script src="assets/materialize.min.js"></script>
 

</body>
</html>