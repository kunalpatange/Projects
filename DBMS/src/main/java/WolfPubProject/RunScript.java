package WolfPubProject;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Using apache library to run the SQL script using Script Runner. Add the jar to your classpath before running.
// Find the apache jar - https://jar-download.com/artifacts/org.mybatis/mybatis/3.4.1/source-code/org/apache/ibatis/jdbc/ScriptRunner.java
public class RunScript {

  static final String JDBC_URL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/$USER";
  static final String USER_ID = "$USER";
  static final String PASSWORD = "$PASSWORD";
  static final String PATH_TO_SCRIPT = "src/main/resources/old_table_structure.sql";
  private static final ResultSet result = null;
  private static Connection connection = null;
  private static Statement statement = null;

  public static void main(String[] args) {
    try {
      connectToDatabase();
      System.out.println("Connection established......");
      ScriptRunner sr = new ScriptRunner(connection);
      System.out.println(new File(PATH_TO_SCRIPT).getAbsolutePath());
      Reader reader = new BufferedReader(new FileReader(new File(PATH_TO_SCRIPT).getAbsolutePath()));
      sr.runScript(reader);
    } catch (Exception e) {
      System.out.println("Exception occurred with " + e);
    } finally {
      closeConnection();
    }
  }

  private static void closeConnection() {
    if (connection != null) {
      try {
        connection.close();
        System.out.println("Closed connection");
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    if (statement != null) {
      try {
        statement.close();
        System.out.println("Closed statement");
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    if (result != null) {
      try {
        result.close();
        System.out.println("Closed result");
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  private static void connectToDatabase() throws ClassNotFoundException, SQLException {
    Class.forName("org.mariadb.jdbc.Driver");
    connection = DriverManager.getConnection(JDBC_URL, USER_ID, PASSWORD);
    statement = connection.createStatement();
  }
}

