<!-- LOGIN PAGE - FACULTY MEMBERS
This is the login page for faculty members to access the application system.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel ="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
	<title>Faculty Login</title>
</head>

<body>
<div class="entirePageFacultyLogin">

	<!-- Menu bar -->
	<ul>
		<li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
		<li><a href="${pageContext.request.contextPath}/jsp/Forms/StudentLoginForm.jsp">Student Login</a></li>
		<li><a href="${pageContext.request.contextPath}/jsp/Forms/RegistrationForm.jsp">Registration</a></li>
		<li><a href="${pageContext.request.contextPath}/jsp/Forms/FacultyLoginForm.jsp"class="active">Faculty</a></li>
		<li ><a href="${pageContext.request.contextPath}/jsp/Forms/AdminLoginForm.jsp">ADMIN</a></li>
	</ul>

	<!-- Login form -->
	<div class="formCenter3" >
		<h2 style="color:white">FACULTY ONLY</h2>

		<div class="editForm2" style="padding:45px">
		<form method = "post" action="${pageContext.request.contextPath}/FacultyLogin">
			<div class="formRow">	
				<input  type="email" name="facemail" placeholder= "Enter your email" required>
			</div>	
			<div class="formRow"> 		
				<input type="password" name="facpassword" placeholder= "Enter your password" required>
			</div>	
			<input class="signButton" type="submit" value="Sign In">
		</form>
		</div>	
	</div>


	<!-- Alert message: in case login fails. -->
	<c:if test="${sessionScope.getAlert == 'Yes'}">
			<script type="text/javascript">
					alert("Username/password incorrect. Please try again.");
			</script>
			<c:remove var="getAlert" scope="session" />
	</c:if>

</div>
</body>
</html>