package com.capstone.controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import com.capstone.bean.RegisterBean;
import com.capstone.serviceClasses.GetUsersService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author Patricia Takara
 * @implSpec This servlet retrieves all the information from all students and faculty members. They are stored in two separated ArrayLists,
 * table1 and table2. 
 * table1 contains students' information and table2 contains faculty information. Both lists are dispatched to the same jsp page to be 
 * displayed to the administrator.
 */
@WebServlet(name = "AdminUpdateUsers", urlPatterns = { "/AdminUpdateUsers" })
public class AdminUpdateUsers extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<RegisterBean> table1 = new ArrayList<>();
		ArrayList<RegisterBean> table2 = new ArrayList<>();
		GetUsersService usersService = new GetUsersService();
		 
		try {
			table1 = usersService.getStudents();
			table2 = usersService.getFaculty();

			request.setAttribute("resultSet", table1); 
			request.setAttribute("resultSet2", table2); 
			 
			// Forward both user lists to the administrator update page.
			RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/Admin/UpdateUsers.jsp");
			dispatcher.forward(request, response);
			
		 } catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		 }
	}   
}