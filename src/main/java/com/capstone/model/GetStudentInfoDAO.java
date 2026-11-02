
package com.capstone.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Handles database operations for retrieving student information.
 */
public class GetStudentInfoDAO {

  /**
   * Retrieves a student's first and last name using their ID.
   * @param userid the student's ID.
   * @return the student's full name, or an empty string if not found.
   * @throws SQLException if a database access error occurs.
   * @throws ClassNotFoundException if the JDBC driver cannot be loaded.
   */
  public String getName(int userid) throws SQLException, ClassNotFoundException {

    String fullName = "";
    String sql = "SELECT FIRSTNAME, LASTNAME FROM STUDENTS WHERE USERID = ?";

    try (Connection connection = DatabaseConnection.getConnection();
    PreparedStatement statement = connection.prepareStatement(sql)) {

      statement.setInt(1, userid);

      try (ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
          String first = resultSet.getString("FIRSTNAME");
          String last = resultSet.getString("LASTNAME");
          fullName = first + " " + last;
        }
      }
    }
    return fullName;
  }

  /**
   * Retrieves a student's ID using their email address.
   * @param email the student's email address.
   * @return the student's ID, or 0 if not found.
   * @throws SQLException if a database access error occurs.
   * @throws ClassNotFoundException if the JDBC driver cannot be loaded.
   */
  public int getStudentId(String email) throws SQLException, ClassNotFoundException {

    int userId = 0;

    String sql = "SELECT USERID FROM STUDENTS WHERE EMAIL = ?";

    try (Connection connection = DatabaseConnection.getConnection();
    PreparedStatement statement = connection.prepareStatement(sql)) {

      statement.setString(1, email.toUpperCase());

      try (ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
        userId = resultSet.getInt("USERID");
        }
      }
    }
    return userId;
  }
  
}