package csv.parser;

import edu.brown.cs.student.csv.CreatorFromRow;
import edu.brown.cs.student.csv.FactoryFailureException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

/**
 * A class for parsing CSV files from a Reader.
 *
 * @param <T> The type to be created from each row. The conversion from row -> T is implemented by
 *     the CreatorFromRow class.
 */
public class ParseBot<T> {
  BufferedReader bReader;
  CreatorFromRow<T> creator;
  boolean ignoreFirst;

  /**
   * Constructor for a ParseBot object.
   *
   * @param CSVReader The Reader object that will be used to read lines from the CSV.
   * @param creator Used to create T objects from rows.
   * @param ignoreFirst true if the first row of the CSV should be ignored, false otherwise.
   */
  public ParseBot(Reader CSVReader, CreatorFromRow<T> creator, boolean ignoreFirst) {
    this.bReader = new BufferedReader(CSVReader);
    this.creator = creator;
    this.ignoreFirst = ignoreFirst;
  }

  /**
   * Used to create T objects from the CSV, one line at a time.
   *
   * @return an object of type T, which is specified by the CreatorFromRow object passed to the
   *     constructor.
   * @throws EndOfCSVException when the parser reaches the end of the CSV and has no more lines to
   *     read.
   * @throws CSVReadingException when the BufferedReader cannot read the CSV (i.e. throws a
   *     IOException).
   * @throws FactoryFailureException when the CreatorFromRow throws a FactoryFailureException.
   */
  public T createNext() throws EndOfCSVException, CSVReadingException, FactoryFailureException {
    T toReturn = null;
    try {
      /*
      if we need to ignore the first row, then we read the first line
      to "throw it away."
       */
      if (this.ignoreFirst) {
        this.bReader.readLine();
        this.ignoreFirst = false;
      }

      String line = this.bReader.readLine();
      if (line != null) {
        toReturn = this.creator.create(Arrays.asList(line.split(",")));
      } else {
        throw new EndOfCSVException();
      }
    } catch (IOException e) { // thrown by BufferedReader.readLine()
      throw new CSVReadingException();
    }
    return toReturn;
  }
}
