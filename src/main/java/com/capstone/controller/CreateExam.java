package com.capstone.controller;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.capstone.serviceClasses.NewExamService;
/**
 * @author Patricia Takara
 * @implSpec This servlet is reponsible for creating a new exam on the database. 
 * It first checks if the the exam name entered already exists on the database. If it exist, the exam is not created and the 
 * instructor is informed. If the exam name is available, a SQL procedure containing a pre-structured table with all the proper 
 * columns and properties, is used to create the table for the exam to store all the questions.
 * The variable newExamName is the exam name that will be used to create the table.
 */
@WebServlet("/createExam")
public class CreateExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String newExamName = request.getParameter("examName");
		NewExamService newExamService = new NewExamService();
		int updateCount = 0;
		
		try {
			if (newExamService.checkExamName(newExamName) >0) {
				HttpSession session = request.getSession(); 
				session.setAttribute("getAlert1","Yes");
				response.sendRedirect(request.getContextPath() + "/jsp/Faculty/CreateNewExam.jsp");
			}else {
				 try {
					updateCount = newExamService.createNewTable(newExamName);
					
					if (updateCount == 0) {
			            HttpSession session = request.getSession();
			            session.setAttribute("getAlert2", "Yes");
			            response.sendRedirect(request.getContextPath() + "/jsp/Faculty/CreateNewExam.jsp");
			        } else {
			            response.sendRedirect(request.getContextPath() + "/jsp/Faculty/ExameCreated.jsp");
			        }	
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}		 
			}		
		} catch (SQLException | ClassNotFoundException e ) {
			e.printStackTrace();
		}
	}
}