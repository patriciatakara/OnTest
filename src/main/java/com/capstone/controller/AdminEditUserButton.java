package com.capstone.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capstone.bean.RegisterBean;
/**
 * @author Patricia Takara
 * @implSpec This servlet retrieves the data from the table listing the users and prepares it for editing.
 * The RegisterBean object, user, contains the name, email address, and password of an user(student or faculty member).
 * This content is sent to a jsp page to be displayed in a form that will allow the administrator to edit it.
 */
@WebServlet("/AdminEditUserButton")
public class AdminEditUserButton extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tableName = request.getParameter("tableName");
		String userid = request.getParameter("userid");
		String first = request.getParameter("first");
		String last = request.getParameter("last");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		RegisterBean user = new RegisterBean(first, last, email, password);
				
		request.setAttribute("user", user);
		request.setAttribute("tableName",tableName);
		request.setAttribute("userid",  userid);
		request.getRequestDispatcher("/jsp/Admin/EditUserForm.jsp").forward(request, response);			
	}
}