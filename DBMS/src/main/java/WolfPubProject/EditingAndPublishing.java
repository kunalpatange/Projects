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

/**
 * EditingAndPublishing class performs editing and publishing operations for WolfPub House.
 */
public class EditingAndPublishing {

  private static final Scanner scanner = new Scanner(System.in);

  public static void operations(Connection connection, Statement statement, ResultSet result) {
    while (true) {
      try {
        int choice = takeIntegerInput("Please select your operations: \n" +
                "1. Enter basic information on a new publication. \n" +
                "2. Update publication information. \n" +
                "3. Assign editor(s) to publication. \n" +
                "4. Let each editor view the information on the publications he/she is responsible for. \n" +
                "5. Edit publication, by adding/deleting articles (for periodic publications) or chapters/sections (for books).\n" +
                "6. Exit this operation", scanner);
        switch (choice) {
          case 1:
            addPublication(connection, statement, result);
            break;
          case 2:
            updatePublication(connection, statement, result);
            break;
          case 3:
            assignEditor(connection, statement, result);
            break;
          case 4:
            viewEditorInformation(connection, statement, result);
            break;
          case 5:
            editPublication(connection, statement, result);
            break;
          case 6:
            System.out.println("Exiting this operation\n");
            return;
          default:
            System.out.println("Invalid input, Please try again!");
            break;
        }
      } catch (Exception exception) {
        System.out.println("Exception occurred while operating on Editing and Publication task: " + exception.getMessage());
      }
    }
  }

  private static void editPublication(Connection connection, Statement statement, ResultSet result) {
    int choice;
    choice = takeIntegerInput("Please select your operations: \n" +
            "1. Edit Chapters/Books.\n" +
            "2. Edit Articles/Magazines. \n" +
            "3. Exit the operation. \n", scanner);
    switch (choice) {
      case 1:
        editChapter(connection, statement, result);
        break;
      case 2:
        editArticle(connection, statement, result);
        break;
      case 3:
        System.out.println("Exiting the operation\n");
        break;
      default:
        System.out.println("Invalid input, Please try again!");
    }
  }

  private static void editArticle(Connection connection, Statement statement, ResultSet result) {
    int choice;
    choice = takeIntegerInput("Please select your operations: \n" +
            "1. Insert a new article.\n" +
            "2. Delete an article.\n" +
            "3. Exit the operation. \n", scanner);
    switch (choice) {
      case 1:
        addArticle(connection, statement, result);
        break;
      case 2:
        deleteArticle(connection, statement, result);
        break;
      case 3:
        System.out.println("Exiting this operation\n");
        break;
      default:
        System.out.println("Invalid input, Please try again!");
    }
  }

  private static void editChapter(Connection connection, Statement statement, ResultSet result) {
    int choice;
    choice = takeIntegerInput("Please select your operations: \n" +
            "1. Insert a new chapter.\n" +
            "2. Delete a chapter.\n" +
            "3. Exit the operation. \n", scanner);
    switch (choice) {
      case 1:
        addChapter(connection, statement, result);
        break;
      case 2:
        deleteChapter(connection, statement, result);
        break;
      case 3:
        System.out.println("Exiting this operation\n");
        break;
      default:
        System.out.println("Invalid input, Please try again!");
    }
  }

  private static void assignEditor(Connection connection, Statement statement, ResultSet result) {
    int choice;
    choice = takeIntegerInput("Please select your operations: \n" +
            "1. Assign Editor to Chapter \n" +
            "2. Assign Editor to Articles. \n" +
            "3. Exit this operation.\n", scanner);
    switch (choice) {
      case 1:
        assignEditorToChapters(connection, statement, result);
        break;
      case 2:
        assignEditorToArticles(connection, statement, result);
        break;
      case 3:
        System.out.println("Exiting this operation\n");
        break;
      default:
        System.out.println("Invalid input, Please try again!");
    }
  }

  private static void assignEditorToArticles(Connection connection, Statement statement, ResultSet result) {
    int choice = takeIntegerInput("Please select your operations: \n" +
            "1. Insert new article and Assign an editor \n" +
            "2. Assign to existing article. \n" +
            "3. Exit this operation. \n", scanner);
    switch (choice) {
      case 1:
        assignEditorToNewArticle(connection, statement, result);
        break;
      case 2:
        assignEditorToExistingArticle(connection, statement, result);
        break;
      case 3:
        System.out.println("Exiting this operation\n");
        break;
      default:
        System.out.println("Invalid input, Please try again!");
    }
  }

  private static void assignEditorToChapters(Connection connection, Statement statement, ResultSet result) {
    int choice = takeIntegerInput("Please select your operations: \n" +
            "1. Insert new chapter and Assign an editor.\n" +
            "2. Assign to existing chapter. \n" +
            "3. Exit this operation", scanner);
    switch (choice) {
      case 1:
        assignEditorToNewChapter(connection, statement, result);
        break;
      case 2:
        assignEditorToExistingChapter(connection, statement, result);
        break;
      case 3:
        System.out.println("Exiting this operation\n");
        break;
      default:
        System.out.println("Invalid input, Please try again!");
    }
  }

  /**
   * addPublication function adds a new publication record.
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void addPublication(Connection connection, Statement statement, ResultSet result) {
    try {
      String title = takeStringInput("Enter title of publication:", scanner);
      String topic = takeStringInput("Enter topic of publication:", scanner);
      String type = takeStringInput("Select type of publication: \n1. Book \n2. Magazine/Journal", scanner);
      if (type.equals("1") || type.equals("2")) {
        type = type.equals("1") ? "book" : "magazine";
      } else {
        System.out.println("Invalid Input. Please enter valid input for the publication type");
        return;
      }

      String periodicity = "0";
      if (type.equals("magazine")) {
        periodicity = takeStringInput("Enter the periodicity of the magazine/journal: ", scanner);
      }

      String numberOfCopies = takeStringInput("Enter number of copies:", scanner);

      System.out.println("Adding a new publication..");
      String query = String.format("INSERT INTO publication_details(title, topic, publication_type, number_of_copies) " +
              "VALUES( '%s',  '%s',  '%s',  %s);", title, topic, type, numberOfCopies);
      statement.executeUpdate(query);
      System.out.println("Successfully added a new publication.");
      query = "SELECT LAST_INSERT_ID();";
      result = statement.executeQuery(query);
      result.next();
      int publicationId = result.getInt("LAST_INSERT_ID()");

      if (type.equals("magazine")) {
        System.out.println("Adding a magazine/journal..");
        query = String.format("INSERT INTO magazine_details(publication_id, periodicity) VALUES('%s', '%s');",
                publicationId, periodicity);
        statement.executeUpdate(query);
        System.out.println("Successfully added the magazine/journal");
      }

      query = "SELECT * from publication_details;";
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while inserting a new publication: " + exception.getCause().getMessage());
    }
  }

  /**
   * updatePublication function will update an existing publication record for this list of attributes(title, topic,
   * publication_type and number_of_copies and returns whether the record has been updated successfully.
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void updatePublication(Connection connection, Statement statement, ResultSet result) {

    try {
      String attribute = takeStringInput("Enter the attribute name to be " +
              "updated(title, topic, publication_type, number_of_copies): ", scanner);
      String attributeValue = takeStringInput("Enter the attribute value:", scanner);
      if (attributeValue.equals("publication_id")) {
        System.out.println("Invalid input, Please try again");
        return;
      }
      String baseAttribute = "publication_id";
      String baseAttributeValue = takeStringInput("Enter publication id:", scanner);

      System.out.println("Updating the publication..");
      String query = String.format("UPDATE publication_details SET %s = '%s' WHERE %s = '%s';", attribute,
              attributeValue, baseAttribute, baseAttributeValue);
      statement.executeUpdate(query);
      System.out.println("Successfully updated the publication.");

      query = String.format("select * from publication_details where %s = '%s'", baseAttribute, baseAttributeValue);
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while updating the publication: " + exception.getCause().getMessage());
    }
  }

  /**
   * assignEditorToNewChapter function will assign an editor to a new chapter
   * and returns whether the assignment is successful or not.
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void assignEditorToNewChapter(Connection connection, Statement statement, ResultSet result) {

    try {
      String chapter_number = takeStringInput("Enter chapter number of a publication:", scanner);
      String isbn = takeStringInput("Enter isbn of a publication:", scanner);
      String title = takeStringInput("Enter title of publication:", scanner);
      String text = takeStringInput("Enter text of publication:", scanner);
      String date = takeStringInput("Enter date (YYYY-MM-DD) of publication:", scanner);
      String writer_id = takeStringInput("Enter writer id of the editor:", scanner);

      System.out.println("Assigning the editor to the new chapter..");
      String query = String.format("INSERT INTO chapter_details(chapter_number, isbn, title, text, date, writer_id) " +
              "VALUES ('%s', '%s', '%s', '%s', '%s', '%s');", chapter_number, isbn, title, text, date, writer_id);
      statement.executeUpdate(query);
      System.out.println("Assigned the editor to the new chapter.");

      query = "select * from chapter_details";
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while assigning editor to the chapter: " + exception.getCause().getMessage());
    }
  }

  /**
   * assignEditorToExistingChapter function will assign an editor to an existing chapter
   * and returns whether the assignment is successful.
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void assignEditorToExistingChapter(Connection connection, Statement statement, ResultSet result) {

    try {
      String chapter_number = takeStringInput("Enter chapter number of a publication:", scanner);
      String isbn = takeStringInput("Enter isbn of a publication:", scanner);
      String writer_id = takeStringInput("Enter writer id of the editor to be assigned:", scanner);

      System.out.println("Assigning the editor to existing chapter..");
      String query = String.format("UPDATE chapter_details SET writer_id = '%s' where chapter_number = '%s' " +
              "and isbn= '%s';", writer_id, chapter_number, isbn);
      statement.executeUpdate(query);
      System.out.println("Assigned the editor to the existing chapter.");

      query = "select * from chapter_details";
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while assigning editor to the chapter: " + exception.getCause().getMessage());
    }
  }

  /**
   * assignEditorToNewArticle function will assign an editor to a new article
   * and returns whether the assignment is successful or not.
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void assignEditorToNewArticle(Connection connection, Statement statement, ResultSet result) {

    try {
      String article_number = takeStringInput("Enter article number of a publication:", scanner);
      String issue_id = takeStringInput("Enter issue id of a publication:", scanner);
      String title = takeStringInput("Enter title of publication:", scanner);
      String text = takeStringInput("Enter text of publication:", scanner);
      String date = takeStringInput("Enter date (YYYY-MM-DD) of publication:", scanner);
      String writer_id = takeStringInput("Enter writer_id of the editor:", scanner);

      System.out.println("Assigning the editor to the new article..");
      String query = String.format("INSERT INTO article_details(article_number, issue_id, title, text, date, writer_id) " +
              "VALUES ('%s', '%s', '%s', '%s', '%s', '%s');", article_number, issue_id, title, text, date, writer_id);
      statement.executeUpdate(query);
      System.out.println("Assigned the editor to the new article.");

      query = "select * from article_details;";
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while assigning editor to the article: " + exception.getCause().getMessage());
    }
  }

  /**
   * assignEditorToExistingArticle function will assign an editor to an existing article
   * and returns whether the assignment is successful or not.
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void assignEditorToExistingArticle(Connection connection, Statement statement, ResultSet result) {

    try {
      String article_number = takeStringInput("Enter article number of a publication:", scanner);
      String issue_id = takeStringInput("Enter issue id of a publication:", scanner);
      String writer_id = takeStringInput("Enter writer id of the editor to be assigned:", scanner);

      System.out.println("Assigning the editor to existing article..");
      String query = String.format("UPDATE article_details SET writer_id = '%s' where article_number = '%s' " +
              "and issue_id= '%s';", writer_id, article_number, issue_id);
      statement.executeUpdate(query);
      System.out.println("Assigned the editor to the existing article.");

      query = "select * from article_details";
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while assigning editor to the article: " + exception.getCause().getMessage());
    }
  }

  /**
   * viewEditorInformation function will provide information about the publications he/she is responsible for.
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void viewEditorInformation(Connection connection, Statement statement, ResultSet result) {

    try {
      String writer_id = takeStringInput("Enter writer id of the editor:", scanner);
      System.out.println("Displaying editor view..");
      String query = String.format(
              "SELECT `Chapter/Article Number`, `ISBN/Issue ID`, title, text, name, writer_details.writer_id\n" +
                      "FROM (SELECT writer_id, title, text, chapter_number as \"Chapter/Article Number\", isbn as \"ISBN/Issue ID\"\n" +
                      "      FROM chapter_details\n" +
                      "      UNION ALL\n" +
                      "      SELECT writer_id, title, text, article_number as \"Chapter/Article Number\", issue_id as \"ISBN/Issue ID\"\n" +
                      "      FROM article_details\n" +
                      "     ) as temporary,\n" +
                      "     writer_details\n" +
                      "WHERE temporary.writer_id = writer_details.writer_id\n" +
                      "  AND writer_details.type = 'editor'\n" +
                      "  AND temporary.writer_id = '%s';", writer_id);
      statement.executeUpdate(query);
      System.out.println("Successfully displayed editor view.");

      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while displaying editor view: " + exception.getCause().getMessage());
    }
  }

  /**
   * addChapter function will add a new Chapter to the publication.
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void addChapter(Connection connection, Statement statement, ResultSet result) {

    try {
      String chapter_number = takeStringInput("Enter chapter number of publication:", scanner);
      String isbn = takeStringInput("Enter isbn of publication:", scanner);
      String title = takeStringInput("Enter title of publication:", scanner);
      String text = takeStringInput("Enter text of chapter:", scanner);
      String date = takeStringInput("Enter date (YYYY-MM-DD) for the chapter:", scanner);
      String writer_id = takeStringInput("Enter writer id of the author:", scanner);

      System.out.println("Adding a new chapter.");
      String query = String.format("INSERT INTO chapter_details (chapter_number, isbn, title, text, date, writer_id) " +
              "VALUES ('%s', '%s', '%s','%s', '%s', '%s');", chapter_number, isbn, title, text, date, writer_id);
      statement.executeUpdate(query);
      System.out.println("Successfully added a new chapter.");

      query = String.format("select * from chapter_details where chapter_number = '%s' and isbn = '%s'", chapter_number, isbn);
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred adding a new chapter: " + exception.getCause().getMessage());
    }
  }

  /**
   * deleteChapter function will delete a Chapter from the publication.
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void deleteChapter(Connection connection, Statement statement, ResultSet result) {

    try {
      String chapter_number = takeStringInput("Enter chapter number:", scanner);
      String isbn = takeStringInput("Enter isbn of publication:", scanner);

      System.out.println("Deleting the chapter..");
      String query = String.format("DELETE FROM chapter_details WHERE isbn = '%s' and chapter_number = '%s';", isbn,
              chapter_number);
      statement.executeUpdate(query);
      System.out.println("Successfully deleted the chapter.");

      query = "select * from chapter_details;";
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while deleting a chapter: " + exception.getCause().getMessage());
    }
  }

  /**
   * addArticle function will add a new Article to the publication.
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void addArticle(Connection connection, Statement statement, ResultSet result) {

    try {
      String article_number = takeStringInput("Enter article number of publication:", scanner);
      String issue_id = takeStringInput("Enter issue id of publication:", scanner);
      String title = takeStringInput("Enter title of publication:", scanner);
      String text = takeStringInput("Enter text of chapter:", scanner);
      String date = takeStringInput("Enter date (YYYY-MM-DD) of article updation:", scanner);
      String writer_id = takeStringInput("Enter writer id of the author:", scanner);

      System.out.println("Adding a new article..");
      String query = String.format("INSERT INTO article_details (article_number, issue_id, title, text, date, writer_id)" +
              " VALUES ('%s', '%s', '%s', '%s', '%s', '%s');", article_number, issue_id, title, text, date, writer_id);
      statement.executeUpdate(query);
      System.out.println("Successfully added an article.");

      query = String.format("select * from article_details where article_number = '%s' and issue_id = '%s'",
              article_number, issue_id);
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while adding an article: " + exception.getCause().getMessage());
    }
  }

  /**
   * deleteArticle function will add delete an article from the publication
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void deleteArticle(Connection connection, Statement statement, ResultSet result) {

    try {
      String article_number = takeStringInput("Enter article number of article:", scanner);
      String issue_id = takeStringInput("Enter issue id of article:", scanner);

      System.out.println("Deleting an article..");
      String query = String.format("DELETE FROM article_details WHERE article_number = '%s' and issue_id = '%s';",
              article_number, issue_id);
      statement.executeUpdate(query);
      System.out.println("Successfully deleted the article");

      query = "select * from article_details;";
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while deleting an article" + exception.getCause().getMessage());
    }
  }
}
