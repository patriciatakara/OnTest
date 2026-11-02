
package com.capstone.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.capstone.serviceClasses.TableValidationService;

/**
 * Handles database operations for verifying whether a student has already completed an exam.
 */
public class VerifyExamDoneDAO {

  /**
   * Checks whether a grade record exists for a specified student and exam.
   * @param userid the student's ID.
   * @param exam the name of the exam.
   * @return true if a matching grade record exists; false otherwise.
   * @throws SQLException if an error occurs while accessing the database.
   * @throws ClassNotFoundException if the JDBC driver cannot be loaded.
   */
  public boolean verify(int userid, String exam) throws SQLException, ClassNotFoundException {

    String tableGrade = exam + "_GRADE";

    // Validate the grade table before using its name in the SQL query.
    TableValidationService.validateGradeTables(tableGrade);

    String sql = "SELECT * FROM " + tableGrade + " WHERE USERID = ? AND EXAM = ?";

    try (Connection connection = DatabaseConnection.getConnection();
    
    PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setInt(1, userid);
      statement.setString(2, exam);
      try (ResultSet resultSet = statement.executeQuery()) {
        return resultSet.next();  // This returns true when a matching record exists and false otherwise.
      }
    }
  }

}