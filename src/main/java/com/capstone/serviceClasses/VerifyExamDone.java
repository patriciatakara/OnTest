package com.capstone.serviceClasses;
import java.sql.SQLException;

import com.capstone.model.VerifyExamDoneDAO;
/**
 * Handles service operations for verifying exam completion.
 * If a record exists, the student has already taken the exam, so it returns false.
 * If no record exists, the student has not taken the exam, so it returns true.
 */
public class VerifyExamDone {

  private final VerifyExamDoneDAO verifyExamDoneDAO = new VerifyExamDoneDAO();

	/**
	 * This method verifies if a student has already done a specified exam. 
	 * @param userid is the student's ID.
	 * @param exam is the name of the exam.
	 * @return a boolean value. False if the student has already completed the exam; true otherwise.
	 * @throws SQLException for errors occurring while accessing the database.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database. 
	 */
	public boolean Verify(int userid, String exam) throws SQLException, ClassNotFoundException {
   
		// If a record exists, the student has already completed the exam, so verify() returns true.
		// Negating that result makes the method return false, meaning the exam should not be allowed again.
		// If no record exists, verify() returns false, and the negation returns true, meaning the exam is still available.
		return !verifyExamDoneDAO.verify(userid, exam); 
	}
	
}