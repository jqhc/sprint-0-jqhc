package word_count_utility.row_count;

import edu.brown.cs.student.csv.CreatorFromRow;
import java.util.List;

/**
 * A class implementing the interface CreatorFromRow. Used to create RowCount object from a row of
 * the CSV. Passed to ParseBot for the purpose of WordCounter2000.
 */
public class RowCountCreator implements CreatorFromRow<RowCount> {

  /** Sole constructor. Takes no arguments. */
  public RowCountCreator() {}

  /**
   * Given a row of the CSV, returns a RowCount object containing information about the number of
   * words, characters, and columns in that row.
   *
   * @param row A row of the CSV, represented as a List of Strings, each of which is one of the
   *     entries in that row.
   * @return A RowCount object which contains information about the number of words, characters and
   *     columns in the row.
   */
  @Override
  public RowCount create(List<String> row) {
    int words = 0;
    int characters = 0;
    int columns = 0;
    for (String str : row) {
      columns += 1; // the number of strings will be the number of columns
      words += str.trim().split(" ").length; // split by spaces and add to word count
      characters += str.length(); // add length of string to characters
    }
    return new RowCount(words, characters, columns);
  }
}
