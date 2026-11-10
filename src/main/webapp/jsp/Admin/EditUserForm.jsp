<!-- ADMINISTRATOR PAGE
This page displays a form to edit a user's information on the database.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
	<title>Editing User</title>
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


<h2 class="centerJustify" style="padding-top:15px; color:black ">Alterations User Form</h2>

<!-- Form to edit a user's information -->
<div class="editForm">
  <form id=editForm method="post" action="${pageContext.request.contextPath}/AdminEditUserProcess">
	  <div class="formRow">
	    <label for="first-name">First Name</label> 
	    <input type="text" id="first-name" name="first" value="${user.first}" placeholder="${user.first}" />
	  </div> 
	  <div class="formRow"> 
	    <label for="last-name">Last Name</label>
	    <input type="text" id="last-name" name="last" value="${user.last}" placeholder="${user.last}" />
	  </div>
	  <div class="formRow">
	    <label for="email">Email</label>
	    <input type="email" id="email" name="email" value="${user.email}" placeholder="${user.email}" />
	  </div>
	  <div class="formRow">
	    <label for="password">Password</label>
	    <input type="text" id="password" name="password" value="${user.password}" placeholder="${user.password}" />
	 </div> 
	    <input type="hidden" name="tableName" value="${tableName}" />
	    <input type="hidden" name="userid" value="${userid}" />
	    <input class="buttonSaveUsers" type="submit" value="Save" />
  </form>
</div>

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


<!-- To prevent the user to go back to the previous page after logging out of the system -->
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