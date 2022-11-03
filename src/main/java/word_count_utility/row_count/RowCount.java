package word_count_utility.row_count;

import java.util.HashMap;

/**
 * A class used to store information about the number of words, characters and columns in a row. For
 * the purpose of WordCounter2000.
 */
public class RowCount {
  private int words;
  private int characters;
  private int columns;

  /**
   * Constructs a RowCount object. Initializes all fields to given values.
   *
   * @param words the number of words in the line.
   * @param characters the number of characters in the line.
   * @param columns the number of columns (i.e. entries) in the line.
   */
  public RowCount(int words, int characters, int columns) {
    this.words = words;
    this.characters = characters;
    this.columns = columns;
  }

  /**
   * Returns a Hashmap containing the word, character, and column counts of the row.
   *
   * @return a HashMap from Strings to Integers, where the keys are "words", "characters", and
   *     "columns", and their corresponding values are the respective counts.
   */
  public HashMap<String, Integer> getCounts() {
    HashMap counts = new HashMap<String, Integer>();
    counts.put("words", this.words);
    counts.put("characters", this.characters);
    counts.put("columns", this.columns);

    return counts;
  }

  /**
   * A self-defined equals() method, for testing purposes. Compares using field equality.
   *
   * @param o The object to be compared with.
   * @return true if o is also a RowCount object, and if the words, characters, columns fields are
   *     equal. else, false.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    RowCount rowCount = (RowCount) o;
    return this.words == rowCount.words
        && this.characters == rowCount.characters
        && this.columns == rowCount.columns;
  }
}
