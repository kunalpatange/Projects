package WolfPubProject;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import static WolfPubProject.Constants.buildTableModel;
import static WolfPubProject.Constants.takeIntegerInput;
import static WolfPubProject.Constants.takeStringInput;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;

public class Distributions {
  public static void operations(Connection connection, Statement statement, ResultSet result) {
    while (true) {
      try {
        Scanner scanner = new Scanner(System.in);
        int choice = takeIntegerInput("Please select your operations: \n" +
                "1. Add new distributor. \n" +
                "2. Update distributor information. \n" +
                "3. Delete a distributor. \n" +
                "4. Add new order. \n" +
                "5. Generate bill for the distributor. \n" +
                "6. Update the balance on receipt of payment. \n" +
                "7. Add new staff. \n" +
                "8. Exit this operation. \n", scanner);
        switch (choice) {
          case 1:
            addDistributor(connection, statement, result);
            break;
          case 2:
            updateDistributor(connection, statement, result);
            break;
          case 3:
            deleteDistributor(connection, statement, result);
            break;
          case 4:
            addOrder(connection, statement, result);
            break;
          case 5:
            billOrder(connection, statement, result);
            break;
          case 6:
            updateBalance(connection, statement, result);
            break;
          case 7:
            addNewStaff(connection, statement, result);
            break;
          case 8:
            System.out.println("Exiting this operation\n");
            return;
          default:
            System.out.println("Invalid input, Please try again!\n");
            break;
        }
      } catch (Exception exception) {
        System.out.println("Exception occurred while operating on Distributors: " + exception.getMessage());
      }
    }
  }

  /**
   * addDistributor function adds a new row in the distributor_details table by
   * taking inputs for
   * contact_person, phone_number, type and name from the user.
   * distributor_id is the primary key which has auto-increment property.
   * account_Id is the foreign key which is referenced from the account_details table.
   * Since a new distributor is being added, we first need to create an account_Id
   * for the new distributor.
   * Hence we first insert a new entry in account_details table. Its primary key
   * has auto-increment
   * property and we retrieve the newly inserted account_Id by using
   * last_insert_id() and use it for the new distributor.
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void addDistributor(Connection connection, Statement statement, ResultSet result) {
    try {
      // The will group the transactions and that are terminated by a call to either
      // rollback method or commit method in our case
      connection.setAutoCommit(false);

      Scanner scanner = new Scanner(System.in);
      String distName = takeStringInput("Enter the name of the distributor's contact person:", scanner);
      String streetAddress = takeStringInput("Enter Street address of the distributor", scanner);
      String city = takeStringInput("Enter City of the distributor", scanner);
      String ph_no = takeStringInput("Enter 10-digit phone number of the distributor:", scanner);

      if (ph_no.length() != 10) {
        System.out.println("Invalid input! Please enter 10 digit phone number.");
        return;
      }
      String type = takeStringInput("Enter the type of distributor(wholesale, bookstore, library): ", scanner);
      String name = takeStringInput("Enter the name of distributor’s book store:", scanner);

      int accountID = addNewAccount(connection, statement, result);

      if (accountID != -1) {
        System.out.println("Adding a new distributor.");
        String query = format("INSERT INTO distributor_details (contact_person, phone_number, type, name, acccount_Id)" +
                "VALUES('%s', '%s', '%s', '%s', '%s');", distName, ph_no, type, name, accountID);
        statement.executeUpdate(query);
        System.out.println("Successfully added a new distributor.");

        query = "SELECT LAST_INSERT_ID();";
        result = statement.executeQuery(query);
        result.next();
        int distributorId = result.getInt("LAST_INSERT_ID()");

        System.out.println("Adding address city details.");
        query = format("INSERT INTO address_city (street_address, city)" +
                "VALUES('%s', '%s');", streetAddress, city);
        statement.executeUpdate(query);
        System.out.println("Successfully address city details.");

        System.out.println("Adding distributor address details.");
        query = format("INSERT INTO distributor_address_details (distributor_id, street_address)" +
                "VALUES('%s', '%s');", distributorId, streetAddress);
        statement.executeUpdate(query);
        System.out.println("Successfully added distributor address details.");

        // Committing the transaction when adding an account and distributor is successful.
        connection.commit();
        query = format("SELECT * FROM distributor_details WHERE distributor_id = '%s';", distributorId);
        result = statement.executeQuery(query);

        JTable table = new JTable(buildTableModel(result));
        JOptionPane.showMessageDialog(null, new JScrollPane(table));
      } else {
        //Rolling the transaction when there is an issue while adding an account and distributor.
        connection.rollback();
        System.out.println("Failed to create account of the new distributor, Please place the order again!");
      }
    } catch (Exception exception) {
      System.out.println("Exception occurred while adding a new distributor: " + exception.getCause().getMessage());
      try {
        connection.rollback();
      } catch (Exception e) {
        System.out.println("Exception occurred while rolling back." + e.getCause().getMessage());
      }
    } finally {
      try {
        // statements will be executed and committed as individual transactions.
        connection.setAutoCommit(true);
      } catch (Exception e) {
        System.err.println("Error with the connection: " + e.toString());
      }
    }
  }

  /**
   * updateDistributor function updates a row in distributor_details table by
   * taking an attribute name and its value to be updated
   * from the user. It also asks the distributor_id (primary key) from the user
   * for which the value is to
   * be updated.
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void updateDistributor(Connection connection, Statement statement, ResultSet result) {
    try {
      Scanner scanner = new Scanner(System.in);
      String attribute = takeStringInput("Enter attribute to update of a distributor " +
              "(contact_person, phone_number, type, name):", scanner);
      String attrValue = takeStringInput("Enter attribute’s value to be updated:", scanner);
      if (attribute.equals("acccount_Id")) {
        System.out.println("Invalid input. Cannot update account Id. Please try again.");
        return;
      }
      String baseAttribute = "distributor_id";
      String baseAttrValue = takeStringInput("Enter distributor ID:", scanner);

      System.out.println("Updating the distributor information.");
      String query = format("UPDATE distributor_details SET %s = '%s' WHERE %s = '%s';", attribute, attrValue,
              baseAttribute, baseAttrValue);
      statement.executeUpdate(query);
      System.out.println("Successfully updated the distributor information.");

      query = format("SELECT * FROM distributor_details WHERE %s = '%s';", baseAttribute, baseAttrValue);
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));
    } catch (SQLException exception) {
      System.out.println("Exception occurred while updating a distributor: " + exception.getCause().getMessage());
    }
  }

  /**
   * deleteDistributor function deletes a row from the distributor_details table
   * by taking the
   * distributor_id from the user.
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void deleteDistributor(Connection connection, Statement statement, ResultSet result) {
    try {
      Scanner scanner = new Scanner(System.in);
      String distributorId = "distributor_id";
      String attrValue = takeStringInput("Enter the distributor ID:", scanner);

      System.out.println("Deleting the distributor.");
      String query = format("DELETE FROM distributor_details WHERE %s = '%s';", distributorId, attrValue);
      statement.executeUpdate(query);
      System.out.println("Successfully deleted the distributor.");

      query = "SELECT * FROM distributor_details;";
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));
    } catch (SQLException exception) {
      System.out.println("Exception occurred while deleting a distributor: " + exception.getCause().getMessage());
    }
  }

  /**
   * addOrder function adds a new row in the order_details table by taking price, version,
   * number_of_copies, shipping_cost, due_date, publication_id and distributor_id from the user.
   * order_id is the primary key and it has auto-increment property.
   * ordered_date is taken as the current date by default and not asked by the
   * user. There is a check to
   * ensure that due_date is after the ordered_date.
   * addBalance function is called to add the amount for this new order to the
   * balance in the account_details table
   * for the distributor who has placed the order.
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void addOrder(Connection connection, Statement statement, ResultSet result) {
    try {

      // The will group the transactions and that are terminated by a call to either
      // rollback method or commit method in our case
      connection.setAutoCommit(false);

      Scanner scanner = new Scanner(System.in);
      String distID = takeStringInput("Enter the distributor ID", scanner);
      String pubID = takeStringInput("Enter the publication ID", scanner);
      String price = takeStringInput("Enter the price of the publication", scanner);
      String noOfCopies = takeStringInput("Enter the number of copies", scanner);
      String version = takeStringInput("Enter the version of the publication", scanner);
      String shipCost = takeStringInput("Enter the shipping cost", scanner);
      Calendar cal = Calendar.getInstance();
      Date date = cal.getTime();
      SimpleDateFormat format1 = new SimpleDateFormat("YYYY-MM-dd");
      String orderDate = format1.format(date);
      String dueDate = takeStringInput("Enter the due date (YYYY-MM-DD) for the publication", scanner);
      if (orderDate.compareTo(dueDate) > 0) {
        System.out.println("Please enter the correct due date");
        return;
      }

      System.out.println("Adding a new order..");
      String query = format("INSERT INTO order_details (price, number_of_copies, version, shipping_cost," +
                      " due_date, ordered_date, distributor_id, publication_id) " +
                      "VALUES('%s','%s','%s','%s','%s','%s','%s','%s');", price, noOfCopies, version, shipCost, dueDate,
              orderDate, distID, pubID);
      statement.executeUpdate(query);

      int amount = parseInt(noOfCopies) * parseInt(price) + parseInt(shipCost);
      boolean isSuccess = addBalance(distID, String.valueOf(amount), connection, statement, result);

      if (isSuccess) {
        // If its a success then commit
        connection.commit();
        System.out.println("Successfully added a new order.");
        System.out.println("Successfully updated the balance for new order");
        query = "SELECT * FROM order_details;";
        result = statement.executeQuery(query);

        JTable table = new JTable(buildTableModel(result));
        JOptionPane.showMessageDialog(null, new JScrollPane(table));
      } else {
        connection.rollback();
        System.out.println("Failed to update the balance for new order. Please place the order again!");
      }
    } catch (Exception exception) {
      System.out.println("Exception occurred while inserting a new order: " + exception.getCause().getMessage());
      try {
        connection.rollback();
      } catch (Exception e) {
        System.out.println("Exception occurred while rolling back." + e.getCause().getMessage());
      }

    } finally {
      try {
        // statements will be executed and committed as individual transactions.
        connection.setAutoCommit(true);
      } catch (Exception e) {
        System.err.println("Error with the connection.  " + e.toString());
      }
    }
  }

  /**
   * billOrder function gives a row with details of distributor_id and Bill Amount
   * by taking the order_id from the user. Bill Amount is calculated by taking the
   * sum
   * of (number_of_copies * price) and shipping_cost.
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void billOrder(Connection connection, Statement statement, ResultSet result) {
    try {
      Scanner scanner = new Scanner(System.in);
      String orderID = takeStringInput("Enter the order ID for bill", scanner);

      System.out.println("Billing an order.");
      String query = format("SELECT order_id,distributor_id, number_of_copies, price, shipping_cost, " +
              "SUM(number_of_copies * price + shipping_cost) AS 'Bill Amount' FROM order_details " +
              "WHERE order_id = %s;", orderID);
      result = statement.executeQuery(query);
      System.out.println("Successfully billed for an order.");

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));
    } catch (SQLException exception) {
      System.out.println("Exception occurred while billing the orders: " + exception.getCause().getMessage());
    }
  }

  /**
   * updateBalance function updates the balance in account_details table by taking
   * the account_id and the amount paid from the user. It subtracts the amount paid from the value of balance
   * attribute.
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void updateBalance(Connection connection, Statement statement, ResultSet result) {
    try {

      Scanner scanner = new Scanner(System.in);
      String amt = takeStringInput("Enter the amount paid", scanner);
      String orderID = takeStringInput("Enter the order ID", scanner);

      System.out.println("Updating the balance.");
      String query = format("UPDATE account_details set account_details.balance = account_details.balance - %s WHERE " +
              "account_details.account_id IN " +
              "(SELECT  distributor_details.acccount_Id  from distributor_details ,order_details where order_details.order_id =%s and " +
              "order_details.distributor_id= distributor_details.distributor_id);", amt, orderID);
      statement.executeUpdate(query);
      System.out.println("Successfully updated the balance.");

      query = format("SELECT * FROM account_details WHERE account_id IN " +
              "(SELECT  distributor_details.acccount_Id  from distributor_details ,order_details where order_details.order_id =%s and " +
              "order_details.distributor_id= distributor_details.distributor_id);", orderID);
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));
    } catch (SQLException exception) {
      System.out.println("Exception occurred while updating the balance: " + exception.getCause().getMessage());
    }
  }

  /**
   * addBalance function is called to add the amount for the new order to the
   * balance in the account_details table
   * for the distributor who has placed the order.
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static boolean addBalance(String distributor_id, String amountPaid, Connection connection,
                                    Statement statement, ResultSet result) {
    try {

      String query = format("UPDATE account_details set account_details.balance = account_details.balance + %s " +
              "WHERE account_details.account_id IN " +
              "(SELECT distributor_details.acccount_Id from distributor_details " +
              "where distributor_details.distributor_id=%s);", amountPaid, distributor_id);
      System.out.println("Updating the balance for new order");
      statement.executeUpdate(query);

    } catch (SQLException exception) {
      System.out.println("Exception occurred while updating adding balance: " + exception.getCause().getMessage());
      return false;
    }
    return true;
  }

  /**
   * addNewAccount is a helper function which adds a new account while adding a new distributor
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static int addNewAccount(Connection connection, Statement statement, ResultSet result) {
    try {
      String query = "INSERT INTO account_details (balance) VALUES(0);";
      statement.executeUpdate(query);
      query = "SELECT LAST_INSERT_ID();";
      result = statement.executeQuery(query);
      result.next();
      return result.getInt("LAST_INSERT_ID()");

    } catch (SQLException exception) {
      System.out.println("Exception occurred while adding new account: " + exception.getCause().getMessage());
      return -1;
    }
  }

  /**
   * addNewStaff function adds a new writer to writer_details table
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void addNewStaff(Connection connection, Statement statement, ResultSet result) {
    try {
      Scanner scanner = new Scanner(System.in);
      String writerName = takeStringInput("Enter the Staff Name: ", scanner);
      String writerType = takeStringInput("Enter the Staff Type (author, editor, journalist): ", scanner);
      String is_invited = takeStringInput("Is the staff invited? (YES(Enter '1')/NO(Enter '0'):", scanner);

      System.out.println("Adding the staff.");
      String query = format("INSERT INTO writer_details(is_invited, name, type) VALUES('%s', '%s', '%s');", is_invited,
              writerName, writerType);
      statement.executeUpdate(query);
      System.out.println("Successfully added new staff");

      query = "SELECT * FROM writer_details;";
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));
    } catch (SQLException exception) {
      System.out.println("Exception occurred while adding a new staff: " + exception.getCause().getMessage());
    }
  }
}
