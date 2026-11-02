
package com.capstone.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.capstone.bean.RegisterBean;
import com.capstone.serviceClasses.TableValidationService;

/**
 * Handles database operations for retrieving student and faculty information.
 */
public class GetUsersDAO {

  /**
   * Retrieves information about all students in the database.
   * @return a list of RegisterBean objects containing student information.
   * @throws SQLException if a database access error occurs.
   * @throws ClassNotFoundException if the JDBC driver cannot be loaded.
   */
  public ArrayList<RegisterBean> getStudents() throws SQLException, ClassNotFoundException {

    ArrayList<RegisterBean> students = new ArrayList<>();
    TableValidationService.validateUsersTables("STUDENTS");

    String sql = "SELECT * FROM STUDENTS ORDER BY USERID ASC";

    try (Connection connection = DatabaseConnection.getConnection();
    PreparedStatement statement = connection.prepareStatement(sql);
    
    ResultSet resultSet = statement.executeQuery()) {

      while (resultSet.next()) {
        RegisterBean user = new RegisterBean();
        user.setUserId(resultSet.getInt("USERID"));
        user.setFirst(resultSet.getString("FIRSTNAME"));
        user.setLast(resultSet.getString("LASTNAME"));
        user.setEmail(resultSet.getString("EMAIL"));
        user.setPassword(resultSet.getString("PASSWORD"));
        students.add(user);
      }
    }
    return students;
  }


  /**
   * Retrieves information about all faculty members in the database.
   * @return a list of RegisterBean objects containing faculty information.
   * @throws SQLException if a database access error occurs.
   * @throws ClassNotFoundException if the JDBC driver cannot be loaded.
   */
  public ArrayList<RegisterBean> getFaculty() throws SQLException, ClassNotFoundException {

    ArrayList<RegisterBean> faculty = new ArrayList<>();
    TableValidationService.validateUsersTables("FACULTY");

    String sql = "SELECT * FROM FACULTY ORDER BY FACULTYID ASC";

    try (Connection connection = DatabaseConnection.getConnection();
    PreparedStatement statement = connection.prepareStatement(sql);
    
    ResultSet resultSet = statement.executeQuery()) {

      while (resultSet.next()) {
        RegisterBean user = new RegisterBean();
        user.setUserId(resultSet.getInt("FACULTYID"));
        user.setFirst(resultSet.getString("FIRSTNAME"));
        user.setLast(resultSet.getString("LASTNAME"));
        user.setEmail(resultSet.getString("EMAIL"));
        user.setPassword(resultSet.getString("PASSWORD"));
        faculty.add(user);
      }
    }
    return faculty;
  }

}