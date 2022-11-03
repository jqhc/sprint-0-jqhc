package word_count_utility;

import edu.brown.cs.student.csv.FactoryFailureException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import parser.CSVReadingException;
import parser.EndOfCSVException;
import parser.ParseBot;
import word_count_utility.row_count.RowCount;
import word_count_utility.row_count.RowCountCreator;

/**
 * A class that implements a "word count" utility, which prints the word, character, row and column
 * counts of a given CSV file.
 */
public class WordCounter2000 {
  private int words;
  private int characters;
  private int rows;
  private int columns;
  private ParseBot<RowCount> parser;

  /**
   * Constructor for a WordCounter2000 object.
   *
   * @param filename - the path of the CSV file to be analyzed.
   * @param ignoreFirst true if the first row of the CSV should be ignored, false otherwise.
   */
  public WordCounter2000(String filename, boolean ignoreFirst) {
    /*
    initializes all fields to 0.
     */
    this.words = 0;
    this.characters = 0;
    this.rows = 0;
    this.columns = 0;
    /*
    Sets up a ParseBot using the provided filename.
     */
    try {
      this.parser = new ParseBot(new FileReader(filename), new RowCountCreator(), ignoreFirst);
    } catch (FileNotFoundException e) { // thrown by FileReader(filename)
      System.err.println("File not found! Please try again.\n");
      System.exit(0);
    }
  }

  /** Prints the word, character, row and column counts to console. */
  public void printCounts() {
    try {
      /*
      Continues pulling RowCount objects from the ParseBot, and adds
      their counts to the respective fields, until end of CSV file is reached.
       */
      while (true) {
        RowCount count = this.parser.createNext();
        HashMap<String, Integer> countsDict = count.getCounts();
        this.words += countsDict.get("words");
        this.characters += countsDict.get("characters");
        this.rows += 1;
        this.columns = countsDict.get("columns");
      }
    } catch (EndOfCSVException e) { // thrown by ParseBot.createNext()
      System.out.println("Words: " + this.words);
      System.out.println("Characters: " + this.characters);
      System.out.println("Rows: " + this.rows);
      System.out.println("Columns: " + this.columns);
    } catch (CSVReadingException e) { // thrown by ParseBot.createNext()
      System.err.println("File not found! Please try again.\n");
      System.exit(0);
    } catch (FactoryFailureException e) { // thrown by ParseBot.createNext()
      System.out.println("How did you even get here?? RowCountCreator doesn't throw this error.");
      System.exit(0);
    }
  }
}
