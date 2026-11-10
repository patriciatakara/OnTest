package com.capstone.controller;
import java.io.IOException;
import java.sql.SQLException;
import com.capstone.bean.RegisterBean;
import com.capstone.serviceClasses.EditService;
import com.capstone.serviceClasses.PasswordValidationService;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
/**
 * @author Patricia Takara
 * @implSpec This servlet is responsible for updating a user's information stored on the database.
 * The updateUser method from the EditService class, passes the user's name, ID number, email address, and password to process 
 * the editing, and update the user on the database. 
 */
@WebServlet(name = "AdminEditUserProcess", urlPatterns = { "/AdminEditUserProcess" })
public class AdminEditUserProcess extends HttpServlet {

	private static final long serialVersionUID = 1L;

  private final PasswordValidationService passwordService = new PasswordValidationService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String tableName = request.getParameter("tableName");
		String userid = request.getParameter("userid");
		String first = request.getParameter("first");
		String last = request.getParameter("last");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		RegisterBean user = new RegisterBean(first, last, email, password);
	
		// Check whether any required field is empty.
		if (first == null || first.trim().isEmpty()
		|| last == null || last.trim().isEmpty()
		|| email == null || email.trim().isEmpty()
		|| password == null || password.trim().isEmpty()) {

			HttpSession session = request.getSession();
			session.setAttribute("nullForm", "Yes");
			session.setAttribute("user", user);
			session.setAttribute("tableName", tableName);
			session.setAttribute("userid", userid);
			response.sendRedirect(request.getContextPath() + "/jsp/Admin/EditUserForm.jsp");
			return;
		}

		// Check whether the password meets the requirements.
		if (!passwordService.isValidPassword(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("invalidPassword", "Yes");
			session.setAttribute("user", user);
			session.setAttribute("tableName", tableName);
			session.setAttribute("userid", userid);
			response.sendRedirect(request.getContextPath() + "/jsp/Admin/EditUserForm.jsp");
				return;
		}

		// Create an instance of the EditService to handle user updates.
		EditService editService = new EditService();
		int rowsUpdated;
		
		try {
			// Call the updateUser method to update the user's information in the database.
			rowsUpdated = editService.updateUser(user, tableName, userid);
			// Check if the update was successful and redirect accordingly.
			if(rowsUpdated > 0) {  
				response.sendRedirect(request.getContextPath() + "/AdminUpdateUsers");  
		    } else {
		    	HttpSession session = request.getSession();
		    	session.setAttribute("userNotAdded","Yes"); 
		    	session.setAttribute("user", user);
		    	session.setAttribute("tableName",tableName); 
		    	session.setAttribute("userid",userid); 
		    	response.sendRedirect(request.getContextPath() + "/jsp/Admin/EditUserForm.jsp"); }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
	