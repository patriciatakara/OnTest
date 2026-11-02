package com.capstone.serviceClasses;
import java.sql.SQLException;
import com.capstone.model.GradeCalculationDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DecimalFormat;

/**
 *  Handles grade calculations and related service operations.
 *  It calculates percentages, assigns letter grades, generates timestamps, and calls the DAO to save scores.
 */
public class GradeCalculationService {

	private final GradeCalculationDAO gradeCalculationDAO = new GradeCalculationDAO();

	/**
	 * This method saves a student's score and the exam's submission date on the database.
	 * @param userId is the student's ID.
	 * @param score is the student's grade on an exam.
	 * @param exam is the name of the exam.
	 * @param letterGrade is the student's letter grade (A, B, C, D, E, F).
	 * @param date is the exam's submission date.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 * @throws SQLException for errors occurring while accessing the database.
	 */
	public void saveScore(int userId, int score, String exam, String letterGrade, String date) throws ClassNotFoundException, SQLException {
		
    gradeCalculationDAO.saveScore(userId, score, exam, letterGrade, date);
	}
	
	
	/**
	 * This method calculates the grade in a percentage format.
	 * @param questionCount is the number of questions of an exam.
	 * @param score is the grade from the exam.
	 * @return a double containing the grade in a percentage format.
	 */
	public double percentage(int questionCount, int score) {
		double percentage = ((double)score/questionCount)*100 ;
		DecimalFormat df = new DecimalFormat("#.##");
	  String formattedPercentage = df.format(percentage);
	  percentage = Double.parseDouble(formattedPercentage);
	  return percentage;
	}
	
	
	/**
	 * This method assign letter grades to the student's grade.
	 * @param grade is the student's grade in numeric form. 
	 * @return a letter grade representing the grade value.
	 */
	public String getLetterGrade(double grade) {
		
	    String letterGrade = "";
	    
	    if (grade >= 89.0) {
	        letterGrade = "A";
	    } else if (grade >= 79.0) {
	        letterGrade = "B";
	    } else if (grade >= 69.0) {
	        letterGrade = "C";
	    } else if (grade >= 59.0) {
	        letterGrade = "D";
	    } else {
	        letterGrade = "F";
	    }
	    return letterGrade;
	}

	
	/**
	 * This method returns a timestamp representing the current data and time.
	 * It will be generated when the student submits the exam. 
	 * @return a formatted timestamp string displaying the current time and date.
	 */
	public String timeStamp() {
	    Date now = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			formatter.setTimeZone(java.util.TimeZone.getTimeZone("America/New_York"));
	    return formatter.format(now);
	}
	
}