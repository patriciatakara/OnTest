<!-- ADMINISTRATOR PAGE
This page displays a form to add a new user into the database.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
	<title>Adding New User</title>
</head>

<body class="entirePageAfterDropDown">

<!-- Menu bar -->
<div style="display: flex; width: 100%; ">
  <ul class="ulLogout">
    <li><a style="color:white; font-size:20px; padding-left:25px; padding-right:150px"href="${pageContext.request.contextPath}/jsp/Admin/AdmMainHub.jsp">Home</a></li>
    <li style="float:right; margin-right:15px"><p style="color:white;">${email}</p></li>
  </ul>
  <form action="${pageContext.request.contextPath}/Logout" style="display: inline-block;">
    <input type="submit" value="Logout" style=" float:right; color:white; background-color: dimgray; border: none; text-align: center; padding: 15px 16px; text-decoration: none; font-size: 17px;">
  </form>
</div>

<!-- Go back button -->
<div class="GoBackButton">
  	<a href="${pageContext.request.contextPath}/AdminUpdateUsers"><button >Go Back</button></a>
</div>

<!-- Get the name of the list in which the user is being added to (STUDENTA/FACULTY) -->
<c:set var="userTable" value="${param.type}" />


<h2 class="centerJustify" style="color:black;">Create a New user</h2>

<!-- Form to add a new user -->
<h5  class="centerJustify" style="color:black;" >${userTable}</h5>
<div class="editForm">
<form id=editForm  method = "post" action="${pageContext.request.contextPath}/AdminAddUserButton">	
	<div class="formRow">	
		 <label for="first">First Name</label> 
		 <input type="text" id="first" name="first" placeholder="Enter first name" />
	</div>	
	<div class="formRow">
	 	<label for="last">Last Name</label> 		
	 	<input type="text" id="last" name="last" placeholder="Enter last name" />	
	</div>	
	<div class="formRow">	
		<label for="email">Email</label> 	
		<input type="email" id="email" name="email" placeholder="Enter email" />		
	</div>	
	<div class="formRow">	
		<label for="password">Password</label> 	
		<input type="password" id="password" name="password" placeholder="Enter password" />
	</div>	
	<input type="hidden"  name="userTable"  value="${userTable}">		
	<input class="buttonSaveUsers"  type="submit"  value="Add user">
</form>
</div>


<!-- Alert message: in case the email address already exists on the database. -->
<c:if test="${sessionScope.getAlert1 == 'Yes'}">
    <script type="text/javascript">
        alert("Email Address is alredy registered.");
    </script>
    <c:remove var="getAlert1" scope="session" />
</c:if>

<!-- Alert message: when a new user is added to the database. -->
<c:if test="${sessionScope.getAlert2 == 'Yes'}">
    <script type="text/javascript">
        alert("User added successfully.");
    </script>
    <c:remove var="getAlert2" scope="session" />
</c:if>

<!-- Alert message: in case a field is left in blank. -->
<c:if test="${sessionScope.nullForm == 'Yes'}">
    <script type="text/javascript">
        alert("Please, fill out all the fields.");
    </script>
    <c:remove var="nullForm" scope="session" />
</c:if>

<!-- Alert message: in case the password does not meet the requirements. -->
<c:if test="${sessionScope.invalidPassword == 'Yes'}">
	<script type="text/javascript">
		alert("Password must have at least 5 characters and at least 1 special character.");
	</script>
	<c:remove var="invalidPassword" scope="session" />
</c:if>



<!-- To prevent the user to go back to the previous page after logging out of the system. -->
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0); // Proxies.

if (session.getAttribute("email")==null) {
response.sendRedirect("${pageContext.request.contextPath}/jsp/Forms/AdminLoginForm.jsp");
}
%> 

</body>
</html>