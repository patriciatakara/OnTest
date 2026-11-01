
package com.capstone.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.capstone.bean.NewExamBean;
import com.capstone.serviceClasses.TableValidationService;

/**
 * Handles database operations for retrieving exam names and exam questions.
 */
public class GetAllExamsDAO {

  /**
   * Retrieves the names of all exams (Exam tables).
   * @return a list of exam table names.
   * @throws SQLException if a database access error occurs.
   * @throws ClassNotFoundException if the JDBC driver cannot be loaded.
   */
  public ArrayList<String> getAllExams() throws SQLException, ClassNotFoundException {


    // Stores the names of the exam tables returned by the query.
    ArrayList<String> exams = new ArrayList<>();

    String sql = "SELECT TABLE_NAME FROM USER_TAB_COLUMNS " +
    "GROUP BY TABLE_NAME " +
    "HAVING COUNT(DISTINCT CASE " +
    "WHEN COLUMN_NAME IN " +
    "('QID', 'QUESTION', 'OP1', 'OP2', 'OP3', 'OP4', 'CORRECTANS') " +
    "THEN COLUMN_NAME END) = 7 " +
    "ORDER BY TABLE_NAME ASC";

    try (Connection connection = DatabaseConnection.getConnection();
    PreparedStatement statement = connection.prepareStatement(sql);

    // The query returns each exam table name in alphabetical order.
    ResultSet resultSet = statement.executeQuery()) {
      while (resultSet.next()) {
        // Add the current table name from the result set to the returned list.
        exams.add(resultSet.getString("TABLE_NAME"));
      }
    }
    return exams;
  }


  /**
   * Retrieves all questions from a single exam table.
   * @param examSelected the name of the selected exam table.
   * @return a list of questions from the selected exam.
   * @throws SQLException if a database access error occurs.
   * @throws ClassNotFoundException if the JDBC driver cannot be loaded.
   */
  public ArrayList<NewExamBean> getQuestionsFromAnExam(String examSelected) throws SQLException, ClassNotFoundException {

    ArrayList<NewExamBean> questions = new ArrayList<>();

    // Validate the table before using its name in the SQL query.
    TableValidationService.validateExamTables(examSelected);

    String sql = "SELECT * FROM " + examSelected + " ORDER BY QID ASC";

    try (Connection connection = DatabaseConnection.getConnection();
    PreparedStatement statement = connection.prepareStatement(sql);

    ResultSet resultSet = statement.executeQuery()) {

      while (resultSet.next()) {
        NewExamBean question = new NewExamBean();
        question.setTableName(examSelected);
        question.setQid(resultSet.getString("QID"));
        question.setQtext(resultSet.getString("QUESTION"));
        question.setOpt1(resultSet.getString("OP1"));
        question.setOpt2(resultSet.getString("OP2"));
        question.setOpt3(resultSet.getString("OP3"));
        question.setOpt4(resultSet.getString("OP4"));
        question.setCorrectAnswer(
        resultSet.getString("CORRECTANS"));
        questions.add(question);
      }
    }
    return questions;
  }

}