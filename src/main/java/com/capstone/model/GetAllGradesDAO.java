
package com.capstone.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.capstone.bean.ReportBean;
import com.capstone.serviceClasses.TableValidationService;

/**
 * Handles database operations for retrieving exam grades.
 */
public class GetAllGradesDAO {

  /**
   * Retrieves the names of all tables containing exam grades.
   * @return a list of grade table names.
   * @throws SQLException if a database access error occurs.
   * @throws ClassNotFoundException if the JDBC driver cannot be loaded.
   */
  public ArrayList<String> getAllTablesGrades() throws SQLException, ClassNotFoundException {

    ArrayList<String> tableGrades = new ArrayList<>();
    String sql = "SELECT TABLE_NAME FROM ALL_TABLESGRADES ORDER BY TABLE_NAME ASC";

    try (Connection connection = DatabaseConnection.getConnection();
    PreparedStatement statement = connection.prepareStatement(sql);
    
    ResultSet resultSet = statement.executeQuery()) {
      while (resultSet.next()) {
        tableGrades.add(resultSet.getString("TABLE_NAME"));
      }
    }
    return tableGrades;
  }


  /**
   * Retrieves the grades of all students who took a specified exam.
   * @param exam the name of the exam grade table.
   * @return a list of ReportBean objects containing student grade information.
   * @throws SQLException if a database access error occurs.
   * @throws ClassNotFoundException if the JDBC driver cannot be loaded.
   */
  public ArrayList<ReportBean> getGrades(String exam) throws SQLException, ClassNotFoundException {

    ArrayList<ReportBean> reports = new ArrayList<>();

    // Validate the grade table before using its name in the SQL query.
    TableValidationService.validateGradeTables(exam);

    String sql = "SELECT S.USERID, S.FIRSTNAME, S.LASTNAME, G.GRADE, G.LETTER, G.SUBMISSION " +
    "FROM STUDENTS S JOIN " + exam + " G ON S.USERID = G.USERID  ORDER BY S.USERID ASC";

    try (Connection connection = DatabaseConnection.getConnection();
    PreparedStatement statement = connection.prepareStatement(sql);

    ResultSet resultSet = statement.executeQuery()) {

      while (resultSet.next()) {
        ReportBean report = new ReportBean();
        report.setExamName(exam);
        report.setUserId(resultSet.getInt("USERID"));
        report.setFirst(resultSet.getString("FIRSTNAME"));
        report.setLast(resultSet.getString("LASTNAME"));
        report.setGrade(resultSet.getInt("GRADE"));
        report.setLetterGrade(resultSet.getString("LETTER"));
        report.setSubmissionDate(resultSet.getString("SUBMISSION"));
        reports.add(report);
      }
    }
    return reports;
  }


  /**
   * Retrieves the grades of a specified student from all supplied exam grade tables.
   * @param allTables the names of the exam grade tables.
   * @param id the student's ID.
   * @return a list of ReportBean objects containing the student's grades.
   * @throws SQLException if a database access error occurs.
   * @throws ClassNotFoundException if the JDBC driver cannot be loaded.
   */
  public ArrayList<ReportBean> getGradesStudent(ArrayList<String> allTables, int id) throws SQLException, ClassNotFoundException {

    ArrayList<ReportBean> grades = new ArrayList<>();

    try (Connection connection = DatabaseConnection.getConnection()) {

      for (String table : allTables) {

        // Validate each grade table before using its name in SQL.
        TableValidationService.validateGradeTables(table);

        String sql ="SELECT S.USERID, S.FIRSTNAME, S.LASTNAME, G.GRADE, G.LETTER, G.EXAM, G.SUBMISSION " +
        "FROM STUDENTS S JOIN " + table + " G ON S.USERID = G.USERID WHERE S.USERID = ? ORDER BY S.USERID ASC";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

          // Pass the student ID as a parameter.
          statement.setInt(1, id);

          try (ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
              ReportBean grade = new ReportBean();
              grade.setUserId(resultSet.getInt("USERID"));
              grade.setFirst(resultSet.getString("FIRSTNAME"));
              grade.setLast(resultSet.getString("LASTNAME"));
              grade.setGrade(resultSet.getInt("GRADE"));
              grade.setLetterGrade(resultSet.getString("LETTER"));
              grade.setSubmissionDate(resultSet.getString("SUBMISSION"));
              grade.setExamName(resultSet.getString("EXAM"));
              grades.add(grade);
            }
          }
        }
      }
    }
    return grades;
  }
  
}
