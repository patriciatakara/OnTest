package com.capstone.serviceClasses;
import java.sql.SQLException;
import com.capstone.model.GetStudentInfoDAO;

/**
 * Handles service operations for retrieving student information.
 */
public class GetStudentInfo {
	
  private final GetStudentInfoDAO getStudentInfoDAO = new GetStudentInfoDAO();

	/**
	 * This method gets the first and last name of a student with the given ID.
	 * @param userid is the student's ID.
	 * @return a string containing the first and last name of a student.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 * @throws SQLException for errors occurring while accessing the database.
	 */
	public String getName(int userid)throws ClassNotFoundException, SQLException  {
		return getStudentInfoDAO.getName(userid);
	}
	
	
	/**
	 * This method gets the student's ID with a given email.
	 * @param email is the student's email.
	 * @return an integer containing the student's ID
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 * @throws SQLException for errors occurring while accessing the database.
	 */
	public int getStudentId(String email) throws ClassNotFoundException, SQLException { 
		if (email == null || email.isBlank()) {
			throw new IllegalArgumentException("Student email cannot be null or blank.");
		}
		return getStudentInfoDAO.getStudentId(email);
	}	   

}