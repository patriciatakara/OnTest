<!-- GENERAL
This is the start point of the web application. The application's homepage where users have the first contact.
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mycss.css">
	<title>OnlineTest | Exam platform</title>
</head>
<body class="indexBody">
	<main class="entirePageIndex">
		<header class="indexHeader">
			<a class="brandMark" href="${pageContext.request.contextPath}/index.jsp" aria-label="OnlineTest home">
				<span class="brandIcon" aria-hidden="true">OT</span>
				<span>OnlineTest</span>
			</a>
			<nav class="indexNav" aria-label="Main navigation">
				<a href="${pageContext.request.contextPath}/index.jsp" class="active">Home</a>
				<a href="${pageContext.request.contextPath}/jsp/Forms/StudentLoginForm.jsp">Student Login</a>
				<a href="${pageContext.request.contextPath}/jsp/Forms/RegistrationForm.jsp">Registration</a>
				<a href="${pageContext.request.contextPath}/jsp/Forms/FacultyLoginForm.jsp">Faculty</a>
				<a href="${pageContext.request.contextPath}/jsp/Forms/AdminLoginForm.jsp">Admin</a>
			</nav>
		</header>

		<section class="indexHero" aria-labelledby="welcome-title">
			<div class="heroCopy">
				<p class="eyebrow">A smarter way to assess learning</p>
				<h1 id="welcome-title" class="titleIndex">Your next test starts here.</h1>
				<p class="textIndex">OnlineTest brings students, faculty, and administrators together in one clear, reliable space for better examinations.</p>
				<div class="heroActions">
					<a class="primaryAction" href="${pageContext.request.contextPath}/jsp/Forms/RegistrationForm.jsp">Create an account <span aria-hidden="true">→</span></a>
					<a class="secondaryAction" href="${pageContext.request.contextPath}/jsp/Forms/StudentLoginForm.jsp">Sign in to continue</a>
				</div>
			</div>
			<div class="heroPanel" aria-label="OnlineTest platform highlights">
				<div class="panelGlow"></div>
				<p class="panelLabel">ONLINE EXAM PLATFORM</p>
				<div class="panelScore">Ready when<br><strong>you are.</strong></div>
				<div class="panelMeta"><span class="statusDot"></span> Secure, simple, focused</div>
			</div>
		</section>

		<footer class="bottom-bar">
			<p class="email">Support: testonlinespp@edu.com</p>
			<p class="year">© 2023 OnlineTest</p>
		</footer>
	</main>
</body>
</html>
