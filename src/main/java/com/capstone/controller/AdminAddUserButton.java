package com.capstone.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.capstone.bean.RegisterBean;
import com.capstone.serviceClasses.PasswordValidationService;
import com.capstone.serviceClasses.RegistrationService;
/**
 * @author Patricia Takara
 * @implSpec This servlet is responsible for adding a new user to the database. It will first check if the user exists by using the email. 
 * If the email entered already exists, the system will let the administrator know, otherwise the user is registered on the system 
 * sucessfully and a confirmation is displayed.
 */
@WebServlet("/AdminAddUserButton")
public class AdminAddUserButton extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final PasswordValidationService passwordService = new PasswordValidationService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userTable = request.getParameter("userTable");
		String first = request.getParameter("first");
		String last = request.getParameter("last");
		String email = request.getParameter("email");
		String password = request.getParameter("password");


		// Check whether any required field is empty.
		if (first == null || first.trim().isEmpty()
		|| last == null || last.trim().isEmpty()
		|| email == null || email.trim().isEmpty()
		|| password == null || password.trim().isEmpty()) {

			HttpSession session = request.getSession();session.setAttribute("nullForm", "Yes");

			response.sendRedirect(request.getContextPath() + "/jsp/Admin/AddUserForm.jsp?type=" + userTable);
			return;
		}

		// Check whether the password meets the requirements.
		if (!passwordService.isValidPassword(password)) {

			HttpSession session = request.getSession();session.setAttribute("invalidPassword", "Yes");

			response.sendRedirect(request.getContextPath() + "/jsp/Admin/AddUserForm.jsp?type=" + userTable);
			return;
		}

		// Create a new RegisterBean object with the provided user information.
		RegisterBean newStudent = new RegisterBean(first, last, email, password);
		
		try {
			// Create an instance of the RegistrationService to handle user registration.
			RegistrationService registrationService = new RegistrationService();
			
			// Check if the user already exists in the database.
			if (registrationService.checkExistUser(email, userTable)) {
				HttpSession session = request.getSession();
				session.setAttribute("getAlert1", "Yes");
				response.sendRedirect(request.getContextPath() + "/jsp/Admin/AddUserForm.jsp");
			} else {
				// Register the new user in the selected table and notify the admin on success.
				if (RegistrationService.addUser(newStudent, userTable)) {
					HttpSession session = request.getSession();
					session.setAttribute("getAlert2", "Yes");
					response.sendRedirect(request.getContextPath() + "/jsp/Admin/AddUserForm.jsp");
				} else {
					// If the form is incomplete or the registration fails, notify the admin.
					HttpSession session = request.getSession();
					session.setAttribute("nullForm", "Yes");
					response.sendRedirect(request.getContextPath() + "/jsp/Admin/AddUserForm.jsp");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
}