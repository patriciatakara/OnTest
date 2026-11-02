package com.capstone.serviceClasses;
import java.sql.SQLException;
import com.capstone.model.NewExamDAO;

/**
 * Handles service operations related to creating new exams.
 */
public class NewExamService {

  private final NewExamDAO newExamDAO = new NewExamDAO();	

	/**
	 * This method checks if an exam name already exists on the database. If there is a table with the same name as the examName 
	 * parameter, then it will return 1, otherwise, it will return 0.
	 * @param examName is the name of the new exam. 
	 * @return the count of tables with the same name as the examName variable value.
	 * @throws SQLException for errors occurring while accessing the database.
	 */
	public int checkExamName(String examName) throws SQLException, ClassNotFoundException {
		
		return newExamDAO.checkExamName(examName);
	}

	
	/**
	 * This method creates a new exam/table on the database with the given name, by calling the stored procedure sp_newTable using 
	 * a CallableStatement. The CallableStatement sets the input parameter to the given name and executes the stored procedure. 
	 * If the stored procedure does not return any result, the method retrieves the update count, which should be 1 if the table was 
	 * created successfully. The method then closes the CallableStatement and returns the update count.
	 * @param name is the exam name entered by the instructor.
	 * @return an integer number representing if the exam/table was created successfully or not.
	 * @throws SQLException for errors occurring while accessing the database.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 */
	public int createNewTable(String name) throws SQLException, ClassNotFoundException {
		
		return newExamDAO.createNewTable(name);
	}

}