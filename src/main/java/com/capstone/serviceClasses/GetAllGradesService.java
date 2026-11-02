package com.capstone.serviceClasses;
import java.sql.SQLException;
import java.util.ArrayList;
import com.capstone.bean.ReportBean;
import com.capstone.model.GetAllGradesDAO;

/**
 * Handles service operations for retrieving exam grades.
 */
public class GetAllGradesService {
	
	private final GetAllGradesDAO getAllGradesDAO = new GetAllGradesDAO();
	
	/**
	 * This method retrieves only the names of all tables containing an exam's grades. 
	 * Each table contains the grade of all the students that took the same exam.
	 * @return an ArrayList of Strings that contains the names of all exam's grades tables retrieved from the database.
	 * @throws SQLException for errors occurring while accessing the database.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 */
	public ArrayList<String> getAllTablesGrades() throws SQLException, ClassNotFoundException {
		
		return getAllGradesDAO.getAllTablesGrades();
	}

	
	/**
	 * This method retrieves the grades of all students that took the same exam. 
	 * @param exam is the name of the exam/table in the database.
	 * @return an ArrayList of ReportBean objects. Each object contains information about a student's performance in a specific exam.
	 * @throws SQLException for errors occurring while accessing the database.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 */
	public ArrayList<ReportBean> getGrades(String exam) throws SQLException, ClassNotFoundException {
		
    return getAllGradesDAO.getGrades(exam);
	}
	
	
	/**
	 * This method retrieves the grades of a specified student with the given ID. It fetches grades from all the exams that were taken.
	 * @param allTables is an ArrayList containing all the tables' grades from the exams. A loop will be used to fetch the grade from each
	 * exam the student took.
	 * @param id is the student ID.
	 * @return an ArrayList of ReportBean objects that contain information about the grades of a student.
	 * @throws SQLException for errors occurring while accessing the database.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 */
  public ArrayList<ReportBean> getGradesStudent(ArrayList<String> allTables, int id) throws SQLException, ClassNotFoundException {
		
		if (allTables == null) {
			throw new IllegalArgumentException("Grade table list cannot be null.");
		}
		return getAllGradesDAO.getGradesStudent(allTables, id);
  }

}
