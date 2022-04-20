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

public class Publication {
  public static void operations(Connection connection, Statement statement, ResultSet result) {
    while (true) {
      try {
        Scanner scanner = new Scanner(System.in);
        int choice = takeIntegerInput("Please select your operations: \n" +
                "1. Enter a new book edition/new issue of a publication. \n" +
                "2. Update book edition or publication issue. \n" +
                "3. Delete book edition or publication issue. \n" +
                "4. Enter/update article/chapter. \n" +
                "5. Update text of an article/chapter. \n" +
                "6. Find books and articles by topic, date, author's name. \n" +
                "7. Enter payment for author/editor. \n" +
                "8. Check payment history. \n" +
                "9. Exit this operation. \n", scanner);
        switch (choice) {
          case 1:
            addNewEditionOrAnIssue(connection, statement, result, scanner);
            break;
          case 2:
            updateEditionOrIssue(connection, statement, result, scanner);
            break;
          case 3:
            deleteEditionOrIssue(connection, statement, result, scanner);
            break;
          case 4:
            addOrUpdateChapterOrArticle(connection, statement, result, scanner);
            break;
          case 5:
            updateArticleTextOrChapterText(connection, statement, result, scanner);
            break;
          case 6:
            findBookOrArticle(connection, statement, result, scanner);
            break;
          case 7:
            addPaymentForWriter(connection, statement, result);
            break;
          case 8:
            checkPaymentHistory(connection, statement, result);
            break;
          case 9:
            System.out.println("Exiting this operation\n");
            return;
          default:
            System.out.println("Invalid input, Please try again!");
            break;
        }
      } catch (Exception exception) {
        System.out.println("Exception occurred while operating on publications: " + exception.getMessage());
      }
    }
  }

  private static void findBookOrArticle(Connection connection, Statement statement, ResultSet result, Scanner scanner) {
    int choice;
    choice = takeIntegerInput("Please select your task:\n" +
            "1. Find chapters for a Book. \n" +
            "2. Find articles for an Issue. \n" +
            "3. Exit the operation. \n", scanner);
    switch (choice) {
      case 1:
        findChapters(connection, statement, result);
        break;
      case 2:
        findArticles(connection, statement, result);
        break;
      case 3:
        System.out.println("Exiting this operation\n");
        break;
      default:
        System.out.println("Invalid input, Please try again!");
    }
  }

  // TODO: FIX THIS
  private static void updateArticleTextOrChapterText(Connection connection, Statement statement, ResultSet result,
                                                     Scanner scanner) {
    int choice;
    choice = takeIntegerInput("Please select your task:\n" +
            "1. Update article text. \n" +
            "2. Update chapter text. \n" +
            "3. Exit the operation. \n", scanner);
    switch (choice) {
      case 1:
        updateArticleText(connection, statement, result);
        break;
      case 2:
        updateChapterText(connection, statement, result);
        break;
      case 3:
        System.out.println("Exiting this operation\n");
        break;
      default:
        System.out.println("Invalid input, Please try again!");
    }
  }

  private static void addOrUpdateChapterOrArticle(Connection connection, Statement statement, ResultSet result,
                                                  Scanner scanner) {
    int choice;
    choice = takeIntegerInput("Please select your task:\n" +
            "1. Enter article/chapter. \n" +
            "2. Update article/chapter. \n" +
            "3. Exit the operation. \n", scanner);
    switch (choice) {
      case 1:
        choice = takeIntegerInput("1. Enter article. \n" +
                "2. Enter chapter. \n" +
                "3. Exit the operation. \n", scanner);
        switch (choice) {
          case 1:
            addNewArticle(connection, statement, result);
            break;
          case 2:
            addNewChapter(connection, statement, result);
            break;
          case 3:
            System.out.println("Exiting this operation\n");
            break;
          default:
            System.out.println("Invalid input, Please try again!");
        }
        break;
      case 2:
        choice = takeIntegerInput("1. Update article. \n" +
                "2. Update chapter. \n" +
                "3. Exit the operation. \n", scanner);
        switch (choice) {
          case 1:
            updateArticle(connection, statement, result);
            break;
          case 2:
            updateChapter(connection, statement, result);
            break;
          case 3:
            System.out.println("Exiting this operation\n");
            break;
          default:
            System.out.println("Invalid input, Please try again!");
        }
        break;
    }
  }

  private static void deleteEditionOrIssue(Connection connection, Statement statement, ResultSet result,
                                           Scanner scanner) {
    int choice;
    choice = takeIntegerInput("Please select your task:\n" +
            "1. Delete book edition. \n" +
            "2. Delete publication issue. \n" +
            "3. Exit the operation. \n", scanner);
    switch (choice) {
      case 1:
        deleteEdition(connection, statement, result);
        break;
      case 2:
        deleteIssue(connection, statement, result);
        break;
      case 3:
        System.out.println("Exiting this operation\n");
        break;
      default:
        System.out.println("Invalid input, Please try again!");
    }
  }

  private static void addNewEditionOrAnIssue(Connection connection, Statement statement, ResultSet result,
                                             Scanner scanner) {
    int choice;
    choice = takeIntegerInput("Please select your task:\n" +
            "1. Enter a new book edition. \n" +
            "2. Enter a new publication issue. \n" +
            "3. Exit the operation. \n", scanner);
    switch (choice) {
      case 1:
        addNewEdition(connection, statement, result);
        break;
      case 2:
        addNewIssue(connection, statement, result);
        break;
      case 3:
        System.out.println("Exiting this operation\n");
        break;
      default:
        System.out.println("Invalid input, Please try again!");
    }
  }

  private static void updateEditionOrIssue(Connection connection, Statement statement, ResultSet result,
                                           Scanner scanner) {
    int choice;
    choice = takeIntegerInput("Please select your task:\n" +
            "1. Update book edition. \n" +
            "2. Update publication issue. \n" +
            "3. Exit the operation. \n", scanner);
    switch (choice) {
      case 1:
        updateEdition(connection, statement, result);
        break;
      case 2:
        updateIssue(connection, statement, result);
        break;
      case 3:
        System.out.println("Exiting this operation\n");
        break;
      default:
        System.out.println("Invalid input, Please try again!");
    }
  }

  /**
   * addNewEdition function is responsible for adding a new edition of an already existing book.
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void addNewEdition(Connection connection, Statement statement, ResultSet result) {

    try {
      Scanner scanner = new Scanner(System.in);
      String publicationId = takeStringInput("Enter the Publication ID: ", scanner);
      String edition_number = takeStringInput("Enter the Edition Number: ", scanner);
      String date = takeStringInput("Enter the date (YYYY-MM-DD): ", scanner);

      String output = String.format("Adding new edition with publicationId: %s, Edition number: %s and date: %s",
              publicationId, edition_number, date);
      System.out.println(output);
      String query = String.format("INSERT INTO edition_details(edition_number, date, publication_id)" +
              " VALUES('%s', '%s', '%s');", edition_number, date, publicationId);
      statement.executeQuery(query);
      System.out.println("Successfully added a new edition");

      query = String.format("select * from edition_details where edition_number = '%s' and publication_id = '%s'",
              edition_number, publicationId);
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));
    } catch (SQLException exception) {
      System.out.println("Exception occurred while adding a new book edition: " + exception.getCause().getMessage());
    }
  }

  /**
   * addNewIssue function is responsible for adding a new publication of an already existing magazine/journal
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void addNewIssue(Connection connection, Statement statement, ResultSet result) {
    try {
      Scanner scanner = new Scanner(System.in);
      String publicationId = takeStringInput("Enter the Publication ID: ", scanner);
      String date = takeStringInput("Enter the date (YYYY-MM-DD): ", scanner);

      System.out.println("Adding new issue..");
      String query = String.format("INSERT INTO issue_details(date, publication_id) VALUES('%s', '%s');", date,
              publicationId);
      statement.executeUpdate(query);
      System.out.println("Successfully added a new issue");

      query = String.format("SELECT * FROM issue_details WHERE publication_id = '%s';", publicationId);
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));
    } catch (SQLException exception) {
      System.out.println("Exception occurred while adding a new publication issue: " + exception.getCause().getMessage());
    }
  }

  /**
   * updateEdition function is responsible for updating an existing book edition in the edition_details table.
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void updateEdition(Connection connection, Statement statement, ResultSet result) {

    try {
      Scanner scanner = new Scanner(System.in);
      String attribute = takeStringInput("Enter attribute to be updated (edition_number, date, publication_id): ",
              scanner);
      if (attribute.equals("isbn")) {
        System.out.println("Invalid attribute, please enter a valid one");
        return;
      }
      String attrValue = takeStringInput("Enter new value of the attribute: ", scanner);

      String baseAttribute = "isbn";
      String baseAttrValue = takeStringInput("Enter isbn of the edition: ", scanner);

      System.out.println("Updating the edition");
      String query = String.format("UPDATE edition_details SET %s = '%s' WHERE %s = '%s';", attribute, attrValue,
              baseAttribute, baseAttrValue);
      statement.executeUpdate(query);
      System.out.println("Successfully updated the edition");

      query = String.format("select * from edition_details where %s = '%s'", baseAttribute, baseAttrValue);
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while updating existing edition: " + exception.getCause().getMessage());
    }
  }

  /**
   * updateIssue function is responsible for updating an existing issue of publication in the issue_details table.
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void updateIssue(Connection connection, Statement statement, ResultSet result) {

    try {
      Scanner scanner = new Scanner(System.in);
      String attribute = takeStringInput("Enter attribute to be updated (date, publication_id): ", scanner);
      if (attribute.equals("issue_id")) {
        System.out.println("Invalid attribute, please enter a valid one");
        return;
      }
      String attrValue = takeStringInput("Enter new value of the attribute: ", scanner);
      String baseAttribute = "issue_id";
      String baseAttrValue = takeStringInput("Enter issue id to be updated: ", scanner);

      System.out.println("Updating the issue");
      String query = String.format("UPDATE issue_details SET %s = '%s' WHERE %s = '%s';", attribute, attrValue,
              baseAttribute, baseAttrValue);
      statement.executeUpdate(query);
      System.out.println("Successfully updated the issue");

      query = String.format("select * from issue_details where %s = '%s'", baseAttribute, baseAttrValue);
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while updating issue: " + exception.getCause().getMessage());
    }
  }

  /**
   * deleteEdition function is responsible for deleting a book edition from the edition_details table
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void deleteEdition(Connection connection, Statement statement, ResultSet result) {

    try {
      Scanner scanner = new Scanner(System.in);
      String isbn = takeStringInput("Enter isbn of the publication: ", scanner);

      System.out.println("Trying to delete the edition...");
      String query = String.format("DELETE FROM edition_details WHERE isbn = '%s';", isbn);
      int deleted_editions = statement.executeUpdate(query);
      if (deleted_editions == 0) {
        System.out.println("There is no record to delete");
        return;
      } else
        System.out.println("Successfully deleted the edition");

      query = "select * from edition_details;";
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while deleting a book edition: " + exception.getCause().getMessage());
    }
  }

  /**
   * This function is responsible for deleting a publication issue from the issue_details table
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void deleteIssue(Connection connection, Statement statement, ResultSet result) {

    try {
      Scanner scanner = new Scanner(System.in);
      String issueId = takeStringInput("Enter Issue ID of the publication: ", scanner);

      System.out.println("Deleting the issue");
      String query = String.format("DELETE FROM issue_details WHERE issue_id = '%s';", issueId);
      int deleted_issues = statement.executeUpdate(query);
      if (deleted_issues == 0) {
        System.out.println("There is no record to delete");
        return;
      } else
        System.out.println("Successfully deleted the issue");

      query = "select * from issue_details;";
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while deleting a publication issue: " + exception.getCause().getMessage());
    }
  }

  /**
   * This function is responsible for adding a new article for a publication in the article_details table
   * (title, journalist's name, topic, date)
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void addNewArticle(Connection connection, Statement statement, ResultSet result) {

    try {
      Scanner scanner = new Scanner(System.in);
      String articleNumber = takeStringInput("Enter the Article Number: ", scanner);
      String issueId = takeStringInput("Enter the Issue ID: ", scanner);
      String title = takeStringInput("Enter the title: ", scanner);
      String date = takeStringInput("Enter the date (YYYY-MM-DD)", scanner);
      String writerId = takeStringInput("Enter the Writer ID: ", scanner);

      String query = String
              .format("INSERT INTO article_details(article_number, issue_id, title, text, date, writer_id)" +
                      " VALUES('%s', '%s', '%s', '%s', '%s', '%s');", articleNumber, issueId, title, "", date, writerId);
      statement.executeUpdate(query);
      System.out.println("Article Entered Successfully");

      query = String.format("select * from article_details where article_number = '%s' and issue_id = '%s'",
              articleNumber,
              issueId);
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));
    } catch (SQLException exception) {
      System.out.println("Exception occurred while adding new article: " + exception.getCause().getMessage());
    }
  }

  /**
   * This function is responsible for adding a new chapter for a book in the chapter_details table
   * (title, author's name, topic, date)
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */

  private static void addNewChapter(Connection connection, Statement statement, ResultSet result) {

    try {
      Scanner scanner = new Scanner(System.in);
      String chapterNumber = takeStringInput("Enter the Chapter Number: ", scanner);
      String isbn = takeStringInput("Enter the ISBN: ", scanner);
      String title = takeStringInput("Enter the title: ", scanner);
      String date = takeStringInput("Enter the date (YYYY-MM-DD)", scanner);
      String writerId = takeStringInput("Enter the Writer ID: ", scanner);

      String query = String.format("INSERT INTO chapter_details(chapter_number, isbn, title, text, date, writer_id) " +
              "VALUES('%s', '%s', '%s', '%s', '%s', '%s');", chapterNumber, isbn, title, "", date, writerId);
      statement.executeUpdate(query);
      System.out.println("Chapter Entered Successfully");

      query = String.format("select * from chapter_details where chapter_number = '%s' and isbn = '%s'", chapterNumber,
              isbn);
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));
    } catch (SQLException exception) {
      System.out.println("Exception occurred while adding new chapter: " + exception.getCause().getMessage());
    }
  }

  /**
   * This function is responsible for updating an article of a publication in the article_details table
   * (title, journalist's name, topic, date)
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */

  private static void updateArticle(Connection connection, Statement statement, ResultSet result) {

    try {
      Scanner scanner = new Scanner(System.in);
      String attribute = takeStringInput("Enter attribute to be updated (title, text, date, writer_id): ",
              scanner);
      String attrValue = takeStringInput("Enter new value of the attribute: ", scanner);
      String articleNumber = takeStringInput("Enter the Article Number: ", scanner);
      String issueId = takeStringInput("Enter the Issue ID: ", scanner);

      String query = String.format(
              "UPDATE article_details SET %s = '%s' WHERE article_number = '%s' and issue_id = '%s';",
              attribute, attrValue, articleNumber, issueId);
      statement.executeUpdate(query);
      System.out.println("Article Updated Successfully");

      query = String.format("select * from article_details where article_number = '%s' and issue_id = '%s';",
              articleNumber, issueId);
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while updating the article " + exception.getCause().getMessage());
    }
  }

  /**
   * This function is responsible for updating a chapter of a book in the chapter_details table
   * (title, journalist's name, topic, date)
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */

  private static void updateChapter(Connection connection, Statement statement, ResultSet result) {

    try {
      Scanner scanner = new Scanner(System.in);
      String attribute = takeStringInput("Enter attribute to be updated (title, text, date, writer_id): ",
              scanner);
      String attrValue = takeStringInput("Enter new value of the attribute: ", scanner);
      String chapterNumber = takeStringInput("Enter the Chapter Number: ", scanner);
      String isbn = takeStringInput("Enter the ISBN: ", scanner);

      String query = String.format("UPDATE chapter_details SET %s = '%s' WHERE chapter_number = '%s' and isbn = '%s';",
              attribute, attrValue, chapterNumber, isbn);
      statement.executeUpdate(query);
      System.out.println("Chapter Updated Successfully");

      query = String.format("select * from chapter_details where chapter_number = '%s' and isbn = '%s';", chapterNumber,
              isbn);
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while updating the chapter: " + exception.getCause().getMessage());
    }
  }

  /**
   * This function is responsible for updating text of an already existing article of a publication in the article_details table
   * (title, journalist's name, topic, date)
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void updateArticleText(Connection connection, Statement statement, ResultSet result) {
    try {
      Scanner scanner = new Scanner(System.in);
      String title = takeStringInput("Enter article title: ", scanner);
      String text = takeStringInput("Enter article text: ", scanner);
      String articleNumber = takeStringInput("Enter the article number: ", scanner);
      String issueId = takeStringInput("Enter the Issue ID: ", scanner);

      System.out.println("Updating article text..");
      String query = String.format("UPDATE article_details SET title = '%s', text = '%s' " +
              "WHERE article_number = '%s' and issue_id = '%s';", title, text, articleNumber, issueId);
      statement.executeUpdate(query);
      System.out.println("Successfully updated article.");

      query = String.format("select * from article_details where article_number = '%s' and issue_id = '%s';",
              articleNumber, issueId);
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while updating the article text: " + exception.getCause().getMessage());
    }
  }

  /**
   * This function is responsible for updating text of an already existing chapter of a publication in the chapter_details table.
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void updateChapterText(Connection connection, Statement statement, ResultSet result) {
    try {
      Scanner scanner = new Scanner(System.in);
      String title = takeStringInput("Enter chapter title: ", scanner);
      String text = takeStringInput("Enter chapter text: ", scanner);
      String chapterNumber = takeStringInput("Enter the chapter number: ", scanner);
      String isbn = takeStringInput("Enter the ISBN: ", scanner);

      System.out.println("Updating chapter text..");
      String query = String.format("UPDATE chapter_details SET title = '%s', text = '%s' WHERE chapter_number = '%s'" +
              " and isbn = '%s';", title, text, chapterNumber, isbn);
      statement.executeUpdate(query);
      System.out.println("Successfully updated the chapter.");

      query = String.format("select * from chapter_details where chapter_number = '%s' and isbn = '%s';",
              chapterNumber, isbn);
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while updating the chapter text: " + exception.getCause().getMessage());
    }
  }

  /**
   * This function is responsible for finding chapters of the book by topic, date and author's name
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void findChapters(Connection connection, Statement statement, ResultSet result) {
    try {
      Scanner scanner = new Scanner(System.in);
      int choice = takeIntegerInput("Please select your filter:\n" +
              "1. Find chapters of book by topic. \n" +
              "2. Find chapters of book by date. \n" +
              "3. Find chapters of book by author's name. \n", scanner);
      switch (choice) {
        case 1:
          String topic = takeStringInput("Enter topic: ", scanner);

          System.out.println("Finding chapters by topic..");
          String query = String.format(
                  "SELECT publication_details.title AS 'Publication Title',\n" +
                          "       publication_details.topic AS 'Publication Topic',\n" +
                          "       chapter_number            AS 'Chapter Number',\n" +
                          "       chapter_details.isbn      AS 'Book ISBN',\n" +
                          "       chapter_details.text      AS 'Chapter Text',\n" +
                          "       chapter_details.date      AS 'Chapter date',\n" +
                          "       edition_details.date      AS 'Edition published date',\n" +
                          "       writer_details.name       AS 'Author'\n" +
                          "FROM publication_details,\n" +
                          "     edition_details,\n" +
                          "     chapter_details,\n" +
                          "     writer_details\n" +
                          "where publication_details.publication_id = edition_details.publication_id\n" +
                          "  and edition_details.isbn = chapter_details.isbn\n" +
                          "  and chapter_details.writer_id = writer_details.writer_id\n" +
                          "  and publication_details.publication_type = 'book'\n" +
                          "  and publication_details.topic = '%s';",
                  topic);
          result = statement.executeQuery(query);
          System.out.println("Successfully ran the query to find chapters by topic");

          JTable table = new JTable(buildTableModel(result));
          JOptionPane.showMessageDialog(null, new JScrollPane(table));
          break;
        case 2:
          String date = takeStringInput("Enter date (YYYY-MM-DD): ", scanner);

          System.out.println("Finding chapters by date..");
          query = String.format(
                  "SELECT publication_details.title as 'Publication Title',\n" +
                          "       publication_details.topic as 'Publication Topic',\n" +
                          "       chapter_number            as 'Chapter Number',\n" +
                          "       chapter_details.isbn      as 'Book ISBN',\n" +
                          "       chapter_details.text      as 'Chapter Text',\n" +
                          "       chapter_details.date      as 'Chapter date',\n" +
                          "       edition_details.date      as 'Edition published date',\n" +
                          "       writer_details.name       as 'Author'\n" +
                          "FROM publication_details,\n" +
                          "     edition_details,\n" +
                          "     chapter_details,\n" +
                          "     writer_details\n" +
                          "where publication_details.publication_id = edition_details.publication_id\n" +
                          "  and edition_details.isbn = chapter_details.isbn\n" +
                          "  and chapter_details.writer_id = writer_details.writer_id\n" +
                          "  and publication_details.publication_type = 'book'\n" +
                          "  and chapter_details.date = '%s';",
                  date);
          result = statement.executeQuery(query);
          System.out.println("Successfully ran the query to find chapters by date");

          table = new JTable(buildTableModel(result));
          JOptionPane.showMessageDialog(null, new JScrollPane(table));
          break;
        case 3:
          String authorName = takeStringInput("Enter author's name: ", scanner);

          System.out.println("Finding chapters by author's name..");
          query = String.format(
                  "SELECT publication_details.title as 'Publication Title',\n" +
                          "       publication_details.topic as 'Publication Topic',\n" +
                          "       chapter_number            as 'Chapter Number',\n" +
                          "       chapter_details.isbn      as 'Book ISBN',\n" +
                          "       chapter_details.text      as 'Chapter Text',\n" +
                          "       chapter_details.date      as 'Chapter date',\n" +
                          "       edition_details.date      as 'Edition published date',\n" +
                          "       writer_details.name       as 'Author'\n" +
                          "FROM publication_details,\n" +
                          "     edition_details,\n" +
                          "     chapter_details,\n" +
                          "     writer_details\n" +
                          "where publication_details.publication_id = edition_details.publication_id\n" +
                          "  and edition_details.isbn = chapter_details.isbn\n" +
                          "  and chapter_details.writer_id = writer_details.writer_id\n" +
                          "  and publication_details.publication_type = 'book'\n" +
                          "  and writer_details.name = '%s';",
                  authorName);
          result = statement.executeQuery(query);
          System.out.println("Successfully ran the query to find chapters by author's name");

          table = new JTable(buildTableModel(result));
          JOptionPane.showMessageDialog(null, new JScrollPane(table));
          break;
      }

    } catch (SQLException exception) {
      System.out.println("Exception occurred while fetching results: " + exception.getCause().getMessage());
    }
  }

  /**
   * This function is responsible for finding articles by topic, date and journalist's name
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void findArticles(Connection connection, Statement statement, ResultSet result) {

    try {
      Scanner scanner = new Scanner(System.in);
      int choice = takeIntegerInput("Please select your filter:\n" +
              "1. Find articles by topic. \n" +
              "2. Find articles by date. \n" +
              "3. Find articles by journalist's name. \n", scanner);
      switch (choice) {
        case 1:
          String topic = takeStringInput("Enter topic of the article: ", scanner);

          System.out.println("Finding articles by topic..");
          String query = String.format(
                  "select article_details.title     as 'Article Title',\n" +
                          "       article_details.text      as 'Article Text',\n" +
                          "       publication_details.topic as 'Publication Topic',\n" +
                          "       article_details.date      as 'Article Published Date',\n" +
                          "       issue_details.date        as 'Issue Published Date',\n" +
                          "       issue_details.issue_id    as 'Article Issue ID'\n" +
                          "from publication_details,\n" +
                          "     issue_details,\n" +
                          "     article_details\n" +
                          "where publication_details.publication_id = issue_details.publication_id\n" +
                          "  and issue_details.issue_id = article_details.issue_id\n" +
                          "  and publication_type = 'magazine'\n" +
                          "  and topic = '%s';",
                  topic);
          result = statement.executeQuery(query);
          System.out.println("Successfully ran the query to find articles by topic");

          JTable table = new JTable(buildTableModel(result));
          JOptionPane.showMessageDialog(null, new JScrollPane(table));
          break;
        case 2:
          String date = takeStringInput("Enter date (YYYY-MM-DD): ", scanner);

          System.out.println("Finding articles by date..");
          query = String.format(
                  "select article_details.title     as 'Article Title',\n" +
                          "       article_details.text      as 'Article Text',\n" +
                          "       publication_details.topic as 'Publication Topic',\n" +
                          "       article_details.date      as 'Article Published Date',\n" +
                          "       issue_details.date        as 'Issue Published Date',\n" +
                          "       issue_details.issue_id    as 'Article Issue ID'\n" +
                          "from publication_details,\n" +
                          "     issue_details,\n" +
                          "     article_details\n" +
                          "where publication_details.publication_id = issue_details.publication_id\n" +
                          "  and issue_details.issue_id = article_details.issue_id\n" +
                          "  and publication_type = 'magazine'\n" +
                          "  and article_details.date = '%s';",
                  date);
          result = statement.executeQuery(query);
          System.out.println("Successfully ran the query to find articles by date.");

          table = new JTable(buildTableModel(result));
          JOptionPane.showMessageDialog(null, new JScrollPane(table));
          break;

        case 3:
          String journalistName = takeStringInput("Enter journalist's name: ", scanner);

          System.out.println("Finding articles by journalist's name..");
          query = String.format(
                  "select article_details.title     as 'Article Title',\n" +
                          "       article_details.text      as 'Article Text',\n" +
                          "       publication_details.topic as 'Publication Topic',\n" +
                          "       article_details.date      as 'Article Published Date',\n" +
                          "       issue_details.date        as 'Issue Published Date',\n" +
                          "       issue_details.issue_id    as 'Article Issue ID'\n" +
                          "from publication_details,\n" +
                          "     issue_details,\n" +
                          "     article_details,\n" +
                          "     writer_details\n" +
                          "where publication_details.publication_id = issue_details.publication_id\n" +
                          "  and issue_details.issue_id = article_details.issue_id\n" +
                          "  and article_details.writer_id = writer_details.writer_id\n" +
                          "  and publication_type = 'magazine'\n" +
                          "  and writer_details.name = '%s';",
                  journalistName);
          result = statement.executeQuery(query);
          System.out.println("Successfully ran the query to find articles by journalist's name.");

          table = new JTable(buildTableModel(result));
          JOptionPane.showMessageDialog(null, new JScrollPane(table));
          break;
        default:
          System.out.println("Wrong input entered. Please try again");
          break;
      }
    } catch (SQLException exception) {
      System.out.println("Exception occurred while fetching results: " + exception.getCause().getMessage());
    }
  }

  /**
   * This function is responsible for adding payment details for a author, journalist or an editor to the payment_details table
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void addPaymentForWriter(Connection connection, Statement statement, ResultSet result) {

    try {
      Scanner scanner = new Scanner(System.in);
      String writerId = takeStringInput("Enter writer id: ", scanner);
      String paymentDate = takeStringInput("Enter the date for the payment: ", scanner);

      String query = String.format(
              "SELECT count(*) as chapter_count\n" +
                      "FROM (SELECT *\n" +
                      "      FROM chapter_details\n" +
                      "      WHERE chapter_details.writer_id = '%s'\n" +
                      "      UNION ALL\n" +
                      "      SELECT *\n" +
                      "      FROM article_details\n" +
                      "      WHERE article_details.writer_id = '%s'\n" +
                      "     ) AS result WHERE MONTH(date) = MONTH('%s') AND YEAR(date) = YEAR('%s');",
              writerId, writerId, paymentDate, paymentDate);
      result = statement.executeQuery(query);
      result.next();
      int chapter_count = result.getInt("chapter_count");

      query = String.format("select is_invited from writer_details where writer_id = '%s'", writerId);
      result = statement.executeQuery(query);
      result.next();
      int is_invited = result.getInt("is_invited");
      int amount;

      if (is_invited == 1) {
        amount = 200 * chapter_count;
      } else {
        amount = 500 * chapter_count;
      }

      query = String.format("insert into payment_details(payment_date, amount, writer_id) " +
              "values('%s', '%s', '%s');", paymentDate, amount, writerId);
      statement.executeUpdate(query);
      System.out.println("Payment Details Added Successfully");

      query = String.format("select * from payment_details where writer_id = '%s'", writerId);
      result = statement.executeQuery(query);

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while entering payment details: " + exception.getMessage());
    }
  }

  /**
   * This function is responsible for fetching the payment history for a given author, editor or journalist from the payment_details table
   *
   * @param connection - takes database connection as input
   * @param statement  - takes database statement as input to insert, update, delete
   * @param result     - takes database result as input
   */
  private static void checkPaymentHistory(Connection connection, Statement statement, ResultSet result) {
    try {
      Scanner scanner = new Scanner(System.in);
      String writerId = takeStringInput("Enter writer ID: ", scanner);

      System.out.println("Fetching payment history.");
      String query = String.format("select name, type, amount, payment_date from payment_details " +
              "natural join writer_details where writer_details.writer_id = '%s'", writerId);
      result = statement.executeQuery(query);
      System.out.println("Successfully fetched payment history.");

      JTable table = new JTable(buildTableModel(result));
      JOptionPane.showMessageDialog(null, new JScrollPane(table));

    } catch (SQLException exception) {
      System.out.println("Exception occurred while fetching payment history " + exception.getCause().getMessage());
    }
  }

}
