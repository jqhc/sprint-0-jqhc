package parser;

import static org.junit.Assert.*;

import edu.brown.cs.student.csv.FactoryFailureException;
import edu.brown.cs.student.stars.Star;
import edu.brown.cs.student.stars.StarFactory;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import org.junit.Test;
import parser.fruits.Fruit;
import parser.fruits.FruitFactory;
import parser.fruits.FruitSizeEnum;
import word_count_utility.row_count.RowCount;
import word_count_utility.row_count.RowCountCreator;

public class ParseBotTest {
  /** * Testing for User Story 2: different kinds of Readers */

  /** * Testing with StringReader */
  @Test
  public void testStringReader()
      throws EndOfCSVException, CSVReadingException, FactoryFailureException {
    // initialize reader, creator, and parser
    StringReader reader = new StringReader("apple,orange,lemon\npear,cherry,goji berry");
    RowCountCreator creator = new RowCountCreator();
    ParseBot parser = new ParseBot(reader, creator, false);

    // tests
    assertEquals(new RowCount(3, 16, 3), parser.createNext());
    assertEquals(new RowCount(4, 20, 3), parser.createNext());
  }

  /** * Testing with FileReader */
  @Test
  public void testFileReader()
      throws FileNotFoundException, EndOfCSVException, CSVReadingException,
          FactoryFailureException {
    // initialize reader, creator, and parser
    FileReader reader = new FileReader("data/word_count_test_csvs/3-char-words-with-spaces.csv");
    RowCountCreator creator = new RowCountCreator();
    ParseBot parser = new ParseBot(reader, creator, false);

    // tests
    assertEquals(new RowCount(4, 16, 4), parser.createNext());
    assertEquals(new RowCount(4, 16, 4), parser.createNext());
  }

  /** * Testing with BufferedReader of FileReader */
  @Test
  public void testBufferedReader()
      throws FileNotFoundException, EndOfCSVException, CSVReadingException,
          FactoryFailureException {
    // initialize reader, creator, and parser
    FileReader fReader = new FileReader("data/word_count_test_csvs/fruits-long.csv");
    BufferedReader bReader = new BufferedReader(fReader);
    RowCountCreator creator = new RowCountCreator();
    ParseBot parser = new ParseBot(bReader, creator, false);

    // tests
    assertEquals(new RowCount(5, 26, 5), parser.createNext());
    assertEquals(new RowCount(8, 56, 5), parser.createNext());
    assertEquals(new RowCount(5, 27, 5), parser.createNext());
    assertEquals(new RowCount(5, 50, 5), parser.createNext());
    assertEquals(new RowCount(6, 36, 5), parser.createNext());
    assertEquals(new RowCount(5, 32, 5), parser.createNext());
  }

  /** * Testing for User Story 3: different kinds of CreatorFromRows */

  /** * Testing with RowCountCreator creator */
  @Test
  public void testRowCountCreator()
      throws FileNotFoundException, EndOfCSVException, CSVReadingException,
          FactoryFailureException {
    // initialize reader, creator, and parser
    FileReader reader = new FileReader("data/word_count_test_csvs/nums-and-symbols.csv");
    RowCountCreator creator = new RowCountCreator();
    ParseBot parser = new ParseBot(reader, creator, false);

    // tests
    assertEquals(new RowCount(8, 28, 4), parser.createNext());
    assertEquals(new RowCount(11, 31, 4), parser.createNext());
  }

  /** * Testing with FruitFactory creator */
  @Test
  public void testFruitFactory()
      throws FileNotFoundException, EndOfCSVException, CSVReadingException,
          FactoryFailureException {
    // initialize reader, creator, and parser
    FileReader reader = new FileReader("src/test/java/parser/fruits/test_csvs/correct-fruits.csv");
    FruitFactory creator = new FruitFactory();
    ParseBot parser = new ParseBot(reader, creator, true);

    // tests
    assertEquals(
        new Fruit("apple", "red", FruitSizeEnum.MEDIUM, 2.00f, false), parser.createNext());
    assertEquals(
        new Fruit("orange", "orange", FruitSizeEnum.MEDIUM, 1.00f, false), parser.createNext());
    assertEquals(
        new Fruit("banana", "yellow", FruitSizeEnum.LARGE, 1.50f, false), parser.createNext());
    assertEquals(new Fruit("lime", "green", FruitSizeEnum.SMALL, 0.50f, true), parser.createNext());
    assertEquals(
        new Fruit("pomelo", "yellow", FruitSizeEnum.LARGE, 6.50f, false), parser.createNext());
  }

  /** * Testing with StarFactory creator */
  @Test
  public void testStarFactory()
      throws FileNotFoundException, EndOfCSVException, CSVReadingException,
          FactoryFailureException {
    // initialize reader, creator, and parser
    FileReader reader = new FileReader("data/stars/ten-star.csv");
    StarFactory creator = new StarFactory();
    ParseBot parser = new ParseBot(reader, creator, true);

    // tests
    assertEquals(new Star(0, "Sol", 0, 0, 0), parser.createNext());
    assertEquals(new Star(1, "", 282.43485, 0.00449, 5.36884), parser.createNext());
    assertEquals(new Star(2, "", 43.04329, 0.00285, -15.24144), parser.createNext());
    assertEquals(new Star(3, "", 277.11358, 0.02422, 223.27753), parser.createNext());
    assertEquals(new Star(3759, "96 G. Psc", 7.26388, 1.55643, 0.68697), parser.createNext());
    assertEquals(
        new Star(70667, "Proxima Centauri", -0.47175, -0.36132, -1.15037), parser.createNext());
    assertEquals(
        new Star(71454, "Rigel Kentaurus B", -0.50359, -0.42128, -1.1767), parser.createNext());
    assertEquals(
        new Star(71457, "Rigel Kentaurus A", -0.50362, -0.42139, -1.17665), parser.createNext());
    assertEquals(
        new Star(87666, "Barnard's Star", -0.01729, -1.81533, 0.14824), parser.createNext());
    assertEquals(new Star(118721, "", -2.28262, 0.64697, 0.29354), parser.createNext());
  }

  /** * Basic testing */

  /** * Tests whether ParseBot throws EndOfCSVException when appropriate */
  @Test
  public void testThrowsEndOfCSVException()
      throws FileNotFoundException, EndOfCSVException, CSVReadingException,
          FactoryFailureException {
    // initialize reader, creator, and parser
    FileReader reader = new FileReader("data/word_count_test_csvs/3-char-words-with-spaces.csv");
    RowCountCreator creator = new RowCountCreator();
    ParseBot parser = new ParseBot(reader, creator, false);

    // tests
    parser.createNext();
    parser.createNext();
    assertThrows(EndOfCSVException.class, () -> parser.createNext());
    assertThrows(EndOfCSVException.class, () -> parser.createNext());
  }

  /** * Tests whether ParseBot's ignoreFirst field actually ignores the first row */
  @Test
  public void testIgnoreFirst()
      throws FileNotFoundException, EndOfCSVException, CSVReadingException,
          FactoryFailureException {
    // initialize reader, creator, and parser
    FileReader reader = new FileReader("data/word_count_test_csvs/fruits-ignore-first.csv");
    RowCountCreator creator = new RowCountCreator();
    ParseBot parser = new ParseBot(reader, creator, true);

    // tests
    assertEquals(new RowCount(4, 19, 4), parser.createNext());
    assertEquals(new RowCount(4, 23, 4), parser.createNext());
    assertEquals(new RowCount(4, 20, 4), parser.createNext());
    assertEquals(new RowCount(4, 18, 4), parser.createNext());
    assertEquals(new RowCount(4, 20, 4), parser.createNext());
  }
}
