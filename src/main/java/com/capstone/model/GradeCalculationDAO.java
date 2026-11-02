package com.capstone.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.capstone.serviceClasses.TableValidationService;

/**
 * Handles database operations for saving student exam grades. It saves the student's exam score in the database.
 */
public class GradeCalculationDAO {

  /**
   * Saves a student's score, letter grade, and submission date.
   * @param userId the student's ID.
   * @param score the student's exam score
   * @param exam the name of the exam.
   * @param letterGrade the student's letter grade.
   * @param date the exam submission date.
   * @throws SQLException if a database access error occurs.
   * @throws ClassNotFoundException if the JDBC driver cannot be loaded.
   */
  public void saveScore(int userId, int score, String exam, String letterGrade, String date) throws SQLException, ClassNotFoundException {

    String examGrade = exam + "_GRADE";

    // Validate the grade table before using its name in SQL.
    TableValidationService.validateGradeTables(examGrade);

    String sql = "INSERT INTO " + examGrade + " (USERID, GRADE, EXAM, LETTER, SUBMISSION)"
    + " VALUES (?, ?, ?, ?,  TO_TIMESTAMP(?, 'YYYY-MM-DD HH24:MI:SS'))";

    try (Connection connection = DatabaseConnection.getConnection();
    
    PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, userId);
      statement.setInt(2, score);
      statement.setString(3, exam);
      statement.setString(4, letterGrade);
      statement.setString(5, date);
      statement.executeUpdate();
    }
  }

}