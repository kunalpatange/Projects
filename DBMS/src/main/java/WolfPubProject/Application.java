package WolfPubProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static WolfPubProject.Constants.JDBC_URL;
import static WolfPubProject.Constants.MENU;
import static WolfPubProject.Constants.PASSWORD;
import static WolfPubProject.Constants.USER_ID;
import static WolfPubProject.Constants.takeIntegerInput;

/**
 * Main application for the WolfPub House. This is the entry point of the application.
 */
public class Application {
  private static final Scanner scanner = new Scanner(System.in);
  private final ResultSet result = null;
  private Connection connection = null;
  private Statement statement = null;

  public static void main(String[] args) {
    Application app = new Application();
    try {
      connectToDatabase(app);
      boolean flag = true;
      while (flag) {
        try {
          System.out.println("Welcome To WolfPub House.\n");
          int choice = takeIntegerInput(MENU, scanner);
          switch (choice) {
            case 1:
              // Operation involving editing and publication
              EditingAndPublishing.operations(app.connection, app.statement, app.result);
              break;
            case 2:
              // Operation involving publications
              Publication.operations(app.connection, app.statement, app.result);
              break;
            case 3:
              // Operation involving Distributors
              Distributions.operations(app.connection, app.statement, app.result);
              break;
            case 4:
              // Operation involving Report generation
              Reports.operations(app.connection, app.statement, app.result);
              break;
            case 5:
              // Exit condition for the application
              System.out.println("Exiting the main application..! Arigato\n");
              flag = false;
              break;
            default:
              // Error Handling - Condition for invalid input
              System.out.println("Invalid input. Please try again\n");
              break;
          }
        } catch (Exception exception) {
          System.out.println("Exception occurred with error: " + exception.getMessage());
        }
      }
    } catch (Exception exception) {
      System.out.println("Exception occurred with error: " + exception.getMessage());
    } finally {
      closeConnection(app);
    }
  }

  /**
   * Closing connections, statements and result when the application ends.
   */
  private static void closeConnection(Application app) {
    if (app.connection != null) {
      try {
        app.connection.close();
        System.out.println("Closed connection.");
      } catch (SQLException exception) {
        exception.printStackTrace();
      }
    }
    if (app.statement != null) {
      try {
        app.statement.close();
        System.out.println("Closed statement.");
      } catch (SQLException exception) {
        exception.printStackTrace();
      }
    }
    if (app.result != null) {
      try {
        app.result.close();
        System.out.println("Closed result.");
      } catch (SQLException exception) {
        exception.printStackTrace();
      }
    }
  }

  /**
   * Logic to connect to the database using the URL, USER_ID and PASSWORD. The details are provided in Constants.java file
   */
  private static void connectToDatabase(Application app) throws ClassNotFoundException, SQLException {
    Class.forName("org.mariadb.jdbc.Driver");
    app.connection = DriverManager.getConnection(JDBC_URL, USER_ID, PASSWORD);
    app.statement = app.connection.createStatement();
  }
}
