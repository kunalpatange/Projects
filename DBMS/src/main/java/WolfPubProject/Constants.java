package WolfPubProject;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

/**
 * Constants class keeps track of static variables required for the publishing house such as JDBC URL, USER_ID,
 * PASSWORD etc. It also handles logic to show the output for each operation using the buildTableModel function
 */
public class Constants {
  public static final String MENU = "Main Menu\nPlease select your task from below: \n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
          "1. Editing and publishing. \n" +
          "2. Production of a book edition or of an issue of a publication. \n" +
          "3. Distribution. \n" +
          "4. Reports. \n" +
          "5. Exit the main application. \n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n";
  static final String JDBC_URL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/tpalla";
  static final String USER_ID = "tpalla";
  static final String PASSWORD = "csc_540_dbms";

  public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

    ResultSetMetaData metaData = rs.getMetaData();
    // names of columns
    Vector<String> columnNames = new Vector<String>();
    int columnCount = metaData.getColumnCount();
    for (int column = 1; column <= columnCount; column++) {
      columnNames.add(metaData.getColumnName(column));
    }
    // data of the table
    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    while (rs.next()) {
      Vector<Object> vector = new Vector<Object>();
      for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
        vector.add(rs.getObject(columnIndex));
      }
      data.add(vector);
    }
    return new DefaultTableModel(data, columnNames);
  }

  /**
   * @param inputLabel - The label to be shown for the user while asking for the input
   * @param scanner    - Scanner model to take the input
   * @return - The value taken as input
   */
  public static String takeStringInput(String inputLabel, Scanner scanner) {
    System.out.println(inputLabel);
    return scanner.nextLine();
  }


  /**
   * @param inputLabel - The label to be shown for the user while asking for the input
   * @param scanner    - Scanner model to take the input
   * @return - The value taken as input
   */
  public static int takeIntegerInput(String inputLabel, Scanner scanner) {
    System.out.println(inputLabel);
    String inputValue = scanner.nextLine();
    return Integer.parseInt(inputValue);
  }
}
