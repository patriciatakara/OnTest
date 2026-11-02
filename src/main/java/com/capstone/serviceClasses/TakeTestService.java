package com.capstone.serviceClasses;
import java.sql.SQLException;
import java.util.ArrayList;
import com.capstone.bean.NewExamBean;
import com.capstone.model.TakeTestDAO;

/**
 * Handles service operations related to taking exams.
 */
public class TakeTestService {

	private final TakeTestDAO takeTestDAO = new TakeTestDAO();

	/**
	 * This method retrieves a specified exam and its contents.
	 * @param exam is the name of the specified exam.
	 * @return an ArrayList of NewExamBean type containing all the questions and their IDs, multiple choices, and correct answers from an exam. 
	 * @throws SQLException for errors occurring while accessing the database.
	 * @throws ClassNotFoundException for when DatabaseConnection.getConnection() method is unable to load the JDBC driver for the database.
	 */
	public ArrayList <NewExamBean> getExam(String exam) throws SQLException, ClassNotFoundException {
	       
		return takeTestDAO.getExam(exam);
	} 
	  
	  
	/**
	 * This method gets the number of questions of a specified exam. 
	 * @param exam is the exam name.
	 * @return an integer returning the total number of questions from an exam.
	 */
	public int questionsCount(ArrayList<NewExamBean> exam) {
	
		return exam.size();
	}
	
	 
	 /**
	  * This method calculates the duration of a quiz based on the number of question.
	  * @param questionCount is the number of questions of an exam.
	  * @return an integer representing the duration of an exam in seconds.
	  */
	public int duration(int questionCount) {
		
		return questionCount * 60;  
	}	  

}