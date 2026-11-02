
package com.capstone.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.capstone.bean.NewExamBean;
import com.capstone.serviceClasses.TableValidationService;

/**
 * Handles database operations related to retrieving exam questions.
 */
public class TakeTestDAO {

  /**
   * Retrieves all questions and their corresponding information from a specified exam table.
   * @param exam the name of the exam table.
   * @return an ArrayList of NewExamBean containing the exam questions, IDs, multiple-choice options, and correct answers.
   * @throws SQLException if an error occurs while accessing the database.
   * @throws ClassNotFoundException if the JDBC driver cannot be loaded.
   */
  public ArrayList<NewExamBean> getExam(String exam) throws SQLException, ClassNotFoundException {

    ArrayList<NewExamBean> questions = new ArrayList<>();

    // Validate the exam table before using its name in the SQL query.
    TableValidationService.validateExamTables(exam);

    String sql = "SELECT * FROM " + exam;

    try (Connection connection = DatabaseConnection.getConnection();
    PreparedStatement statement = connection.prepareStatement(sql);
    
    ResultSet resultSet = statement.executeQuery()) {

      while (resultSet.next()) {
        NewExamBean question = new NewExamBean();
        question.setTableName(exam);
        question.setQid(resultSet.getString("QID"));
        question.setQtext(resultSet.getString("QUESTION"));
        question.setOpt1(resultSet.getString("OP1"));
        question.setOpt2(resultSet.getString("OP2"));
        question.setOpt3(resultSet.getString("OP3"));
        question.setOpt4(resultSet.getString("OP4"));
        question.setCorrectAnswer(resultSet.getString("CORRECTANS"));
        questions.add(question);
      }
    }
    return questions;
  }

}