package com.capstone.controller;
import java.io.IOException;
import com.capstone.bean.RegisterBean;
import com.capstone.serviceClasses.PasswordValidationService;
import com.capstone.serviceClasses.RegistrationService;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * @author Patricia Takara
 * @implSpec This servlet registers a new user(student) into the system.
 * It first checks if the email entered by the user is already registered in the system. If it is, a message box will be displayed.
 * The values from the parameters first, last, email, and password are stored in a RegisterBean object().
 * Then, the object is passed to the addUser method from the RegistrationService class to process the data and store the new student's
 * information on the database.
 * If registration is successful, the new user is redirected to a welcome page.
 */
@WebServlet(name = "registration", urlPatterns = { "/registration" })
public class Registration extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	PasswordValidationService passwordService = new PasswordValidationService();
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userTable = "STUDENTS";
		String first = request.getParameter("first");
		String last = request.getParameter("last");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
			
		// Check if fields are empty
		if(first ==null || first.trim().isEmpty()
		|| last ==null || last.trim().isEmpty()
		|| email ==null || email.trim().isEmpty()
		|| password ==null || password.trim().isEmpty()){
		
			HttpSession session = request.getSession();
    	session.setAttribute("nullForm", "Yes");

			response.sendRedirect(request.getContextPath() + "/jsp/Forms/RegistrationForm.jsp");
			return;
		}

		if (!passwordService.isValidPassword(password)) {

			HttpSession session = request.getSession();
			session.setAttribute("invalidPassword", "Yes");

			response.sendRedirect(request.getContextPath() + "/jsp/Forms/RegistrationForm.jsp");
			return;
		}

		RegisterBean newStudent = new RegisterBean(first, last, email, password);

		try {
			RegistrationService registrationService = new RegistrationService();

			if (registrationService.checkExistUser(email, userTable)) {
				HttpSession session = request.getSession();
				session.setAttribute("getAlert", "Yes");
				response.sendRedirect(request.getContextPath() + "/jsp/Forms/RegistrationForm.jsp");
				return;
			}
			
			if (RegistrationService.addUser(newStudent, userTable)) {
				response.sendRedirect(request.getContextPath() + "/jsp/Students/RegisteredSuccess.jsp");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("nullForm", "Yes");
				response.sendRedirect(request.getContextPath() + "/jsp/Forms/RegistrationForm.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred during registration");
		}
	}
}