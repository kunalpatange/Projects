package WolfPubProject;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static WolfPubProject.Constants.buildTableModel;
import static WolfPubProject.Constants.takeIntegerInput;
import static WolfPubProject.Constants.takeStringInput;

public class Reports {

  private static final Scanner scanner = new Scanner(System.in);

  public static void operations(Connection connection, Statement statement, ResultSet result) {
    while (true) {
      try {
        int choice = takeIntegerInput("Please select your operations for Report Task: \n" +
                "1. Get number of copies and total price of copies of each publication bought per distributor. \n" +
                "2. Get number of copies and total price of copies of each publication bought per month. \n" +
                "3. Total revenue of the publishing house. \n" +
                "4. Total expenses of the publishing house. \n" +
                "5. Total current number of distributors. \n" +
                "6. Total revenue (since inception) per city. \n" +
                "7. Total revenue (since inception) per distributor. \n" +
                "8. Total revenue (since inception) per location. \n" +
                "9. Total payments to the editors and authors, per time period. \n" +
                "10. Total payments to the editors and authors, per work type. \n" +
                "11. Exit the operation. \n", scanner);
        switch (choice) {
          case 1:
            getNumberOfCopiesAndTotalPricePerDistributor(connection, statement, result);
            break;
          case 2:
            getNumberOfCopiesAndTotalPricePerMonth(connection, statement, result);
            break;
          case 3:
            getTotalRevenue(connection, statement, result);
            break;
          case 4:
            getTotalExpenses(connection, statement, result);
            break;
          case 5:
            getCurrentNumberOfDistributors(connection, statement, result);
            break;
          case 6:
            getRevenuePerCity(connection, statement, result);
            break;
          case 7:
            getRevenuePerDistributor(connection, statement, result);
            break;
          case 8:
            getRevenuePerLocation(connection, statement, result);
            break;
          case 9:
            getTotalPaymentPerTimePeriod(connection, statement, result);
            break;
          case 10:
            getTotalPaymentPerWorkType(connection, statement, result);
            break;
          case 11:
            System.out.println("Exiting this operation\n");
            return;
          default:
            System.out.println("Invalid input, Please try again !\n");
            break;
        }
      } catch (Exception exception) {
        System.out.println("Exception occurred while generating reports: " + exception.getMessage());
      }
    }
  }

  /*
   * getNumberOfCopiesAndTotalPricePerDistributor function returns the report with
   * number of copies
   * and total price per distributor
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void getNumberOfCopiesAndTotalPricePerDistributor(Connection connection, Statement statement,
                                                                   ResultSet result) {
    try {
      result = statement.executeQuery("SELECT distributor_id, publication_id, SUM(number_of_copies) " +
              "AS 'total copies ordered', SUM(price*number_of_copies) AS 'total price' FROM order_details " +
              "GROUP BY distributor_id, publication_id;");

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while generating reports: " + exception.getCause().getMessage());
    }

  }

  /*
   * getNumberOfCopiesAndTotalPricePerMonth function returns the report with
   * number of copies
   * and total price per month
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void getNumberOfCopiesAndTotalPricePerMonth(Connection connection, Statement statement,
                                                             ResultSet result) {

    try {
      result = statement.executeQuery("SELECT DATE_FORMAT(ordered_date, '%b') AS 'Month', publication_id, " +
              "SUM(number_of_copies)  AS 'total copies ordered', SUM(price*number_of_copies) AS 'total price' " +
              "FROM order_details " +
              "GROUP BY month(ordered_date), publication_id;");

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while generating reports: " + exception.getCause().getMessage());
    }

  }

  /**
   * getTotalRevenue get the total revenue of the publishing house,
   * getTotalRevenue includes (number_of_copies * price) + shipping_cost
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void getTotalRevenue(Connection connection, Statement statement, ResultSet result) {
    try {

      result = statement.executeQuery("SELECT sum((number_of_copies * price) + shipping_cost) as " +
              "'Total Revenue' FROM order_details;");

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while generating reports: " + exception.getCause().getMessage());
    }

  }

  /**
   * getTotalExpenses function returns total expense of the publishing house
   * totalExpenses include payments done + shipping cost
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void getTotalExpenses(Connection connection, Statement statement, ResultSet result) {
    try {

      result = statement.executeQuery("SELECT sum(amount) AS 'Total Expenses' FROM" +
              " (SELECT amount FROM payment_details UNION ALL SELECT shipping_cost FROM order_details) AS result;");

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {

      System.out.println("Exception occurred while generating reports: " + exception.getCause().getMessage());
    }
  }

  /**
   * getCurrentNumberOfDistributors functions returns all the current distributors
   * affiliated
   * with the pubhouse
   * Distributors whose orders are due are considered as current distributors
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void getCurrentNumberOfDistributors(Connection connection, Statement statement, ResultSet result) {
    try {

      result = statement.executeQuery("SELECT COUNT(distinct distributor_id) as 'Current No. of Distributors' " +
              "FROM order_details " +
              "WHERE due_date >= CURDATE() OR due_date IS NULL;");

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while generating reports: " + exception.getCause().getMessage());
    }

  }

  /**
   * getRevenuePerCity get the total revenue of the publishing house per city,
   * getTotalRevenue includes (number_of_copies * price) + shipping_cost
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void getRevenuePerCity(Connection connection, Statement statement, ResultSet result) {
    try {

      result = statement.executeQuery("SELECT address_city.city, " +
              "SUM(order_details.price * order_details.number_of_copies + order_details.shipping_cost) " +
              "as 'Total Revenue' FROM distributor_address_details " +
              "NATURAL JOIN address_city " +
              "NATURAL JOIN distributor_details " +
              "NATURAL JOIN order_details GROUP BY address_city.city;");

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while generating reports: " + exception.getCause().getMessage());
    }

  }

  /**
   * getRevenuePerDistributor get the total revenue of the publishing house per
   * distributor,
   * getTotalRevenue includes (number_of_copies * price) + shipping_cost
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void getRevenuePerDistributor(Connection connection, Statement statement, ResultSet result) {
    try {

      result = statement.executeQuery("SELECT distributor_details.distributor_id, distributor_details.name, " +
              "sum(order_details.price * order_details.number_of_copies + order_details.shipping_cost) as 'Total Revenue'" +
              " FROM distributor_details NATURAL JOIN order_details GROUP BY distributor_details.distributor_id;");

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while generating reports: " + exception.getCause().getMessage());
    }

  }

  /**
   * getRevenuePerLocation get the total revenue of the publishing house per
   * location,
   * getTotalRevenue includes (number_of_copies * price) + shipping_cost
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void getRevenuePerLocation(Connection connection, Statement statement, ResultSet result) {
    try {

      result = statement.executeQuery("SELECT address_city.street_address, " +
              "sum(order_details.price * order_details.number_of_copies + order_details.shipping_cost) as 'Total Revenue' " +
              "FROM distributor_address_details  " +
              "NATURAL JOIN address_city " +
              "NATURAL JOIN distributor_details " +
              "NATURAL JOIN order_details GROUP BY address_city.street_address;");

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {

      System.out.println("Exception occurred while generating reports: " + exception.getCause().getMessage());
    }

  }

  /**
   * getTotalPaymentPerTimePeriod function return total payment done to all
   * the employees within the time frame
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void getTotalPaymentPerTimePeriod(Connection connection, Statement statement, ResultSet result) {

    String startDate = takeStringInput("Enter the start date( YYYY-MM-DD )", scanner);
    String endDate = takeStringInput("Enter the end date( YYYY-MM-DD )", scanner);

    try {
      String query = String.format(
              "SELECT SUM(amount) as 'Total Payment' FROM payment_details WHERE payment_date BETWEEN  '%s' AND '%s';",
              startDate, endDate);
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while generating reports: " + exception.getCause().getMessage());
    }

  }

  /**
   * getTotalPaymentPerWorkType function return total payment done to all
   * the employees per their work type
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void getTotalPaymentPerWorkType(Connection connection, Statement statement, ResultSet result) {
    try {

      result = statement.executeQuery("SELECT type, SUM(amount) as 'Total Payment' FROM payment_details " +
              "NATURAL JOIN writer_details GROUP BY type;");

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while generating reports: " + exception.getCause().getMessage());
    }
  }
}
