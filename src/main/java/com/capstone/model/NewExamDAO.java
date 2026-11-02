package com.capstone.model;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Handles database operations related to creating new exams.
 */
public class NewExamDAO {

  /**
   * Checks whether an exam table with the given name already exists.
   * @param examName the name of the exam to check.
   * @return the number of tables matching the exam name.
   * @throws SQLException if an error occurs while accessing the database.
   * @throws ClassNotFoundException if the JDBC driver cannot be loaded.
   */
  public int checkExamName(String examName) throws SQLException, ClassNotFoundException {

    String tableName = examName.toUpperCase();
    String sql = "SELECT COUNT(*) FROM ALL_TABLES WHERE TABLE_NAME = ?";

    try (Connection connection = DatabaseConnection.getConnection();

    PreparedStatement statement = connection.prepareStatement(sql)) {
      statement.setString(1, tableName);
      try (ResultSet resultSet = statement.executeQuery()) {
        resultSet.next();
        return resultSet.getInt(1);
      }
    }
  }


  /**
   * Creates a new exam table by calling the SP_NEWTABLE stored procedure.
   * @param name the name of the exam table to create.
   * @return the update count returned by the stored procedure.
   * @throws SQLException if an error occurs while accessing the database.
   * @throws ClassNotFoundException if the JDBC driver cannot be loaded.
   */
  public int createNewTable(String name) throws SQLException, ClassNotFoundException {

    int updateCount = 0;

    try (Connection connection = DatabaseConnection.getConnection();
    CallableStatement statement =
    
    connection.prepareCall("{ call sp_newTable(?) }")) {

      statement.setString(1, name.toUpperCase());
      boolean hasResult = statement.execute();

      if (!hasResult) {
        updateCount = statement.getUpdateCount();
      }
    }
    return updateCount;
  }

}