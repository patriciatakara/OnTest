package com.capstone.serviceClasses;
import java.sql.SQLException;
import java.util.ArrayList;
import com.capstone.bean.RegisterBean;
import com.capstone.model.GetUsersDAO;

/**
 * Handles service operations for retrieving student and faculty information
 */
public class GetUsersService {
	
	private final GetUsersDAO getUsersDAO = new GetUsersDAO();

	/**
	 * This method retrieves information about all the students in the database.
	 * @return an ArrayList of RegisterBean type containing all the students' names, userID, email, and password.
	 * @throws SQLException for errors occurring while accessing the database.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 */
	 public ArrayList<RegisterBean> getStudents() throws SQLException, ClassNotFoundException {

    return getUsersDAO.getStudents();
	 }
	 
	 
	 /**
	  * This method retrieves information about all the faculty members in the database.
	  * @return an ArrayList of type RegisterBean containing all the faculty member's ID, name, email, and password.
	  * @throws SQLException for errors occurring while accessing the database.
	  * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	  */
	 public ArrayList<RegisterBean> getFaculty() throws SQLException, ClassNotFoundException {
		
		return getUsersDAO.getFaculty();
	 }	 	 
	 
}