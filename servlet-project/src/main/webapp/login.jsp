<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Application Login</title>
    <link rel="stylesheet" href="assets/materialize.min.css">
    <link href="assets/css/font-awesome.min.css" rel="stylesheet" >
</head>
<body>


 <div class="card" style="width: 50%">
    <form  action="login" method="post" class="col s12">
          <div class="card-content">

            <span class="card-title">Login</span>

        <div class="row">

            <c:if test="${LOGIN_STATUS == 'FAIL'}">
                Invalid Credentials
            </c:if>
            <div class="row">
              <div class="input-field col s6">
                <input id="userName" name = "userName" type="text" class="validate">
                <label for="userName">User Name</label>
              </div>
            </div>
            <div class="row">
              <div class="input-field col s6">
                <input  id="password" name="password" type="password"  >
                <label for="password">Password</label>
              </div>
            </div>




        </div>
          </div>
          <div class="card-action">
          

 <button type="submit"   class="add-btn btn blue darken-3"><i class="fa fa-save	"></i> Sign In</button>
       </div>
                    </form>
        </div>
 <script
         src="assets/jquery-3.2.1.min.js"></script>
 <script src="assets/materialize.min.js"></script>
</body>
</html>