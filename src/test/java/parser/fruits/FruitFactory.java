package parser.fruits;

import edu.brown.cs.student.csv.CreatorFromRow;
import edu.brown.cs.student.csv.FactoryFailureException;
import java.util.List;

/**
 * A class implementing the CreatorFromRow interface. Used to create Fruit object from a row of a
 * CSV of fruits.
 */
public class FruitFactory implements csv.parser.CreatorFromRow<Fruit> {

  /** Sole constructor. Takes no arguments. */
  public FruitFactory() {}

  /**
   * Given a row of the CSV, returns a Fruit object containing the information provided in the row.
   *
   * @param row a row of the CSV, represented as a List of Strings, each of which is one of the
   *     entries in that row. The entries must be in the following order: name, colour, size, price,
   *     and sour?.
   * @return a Fruit object which contains the information provided in the row.
   * @throws FactoryFailureException if the contents of one of the entries does not match with its
   *     expected type (ex. if the price column does not contain a float).
   */
  @Override
  public Fruit create(List<String> row) throws FactoryFailureException {
    /* Throws FactoryFailureException if the row does not contain the correct
    number of elements. */
    if (row.size() != 5) {
      throw new FactoryFailureException(row);
    }

    String name = row.get(0);
    String colour = row.get(1);
    /* Try to cast size entry to an FruitSizeEnum. If it does not fit one of
    the categories, throw a FactoryFailureException. */
    FruitSizeEnum size;
    try {
      size = FruitSizeEnum.valueOf(row.get(2).toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new FactoryFailureException(row);
    }
    /* Try to cast price entry to a float. If the entry cannot be cast,
    throw a FactoryFailureException. */
    float price;
    try {
      price = Float.parseFloat(row.get(3));
    } catch (NullPointerException e) {
      throw new FactoryFailureException(row);
    } catch (NumberFormatException e) {
      throw new FactoryFailureException(row);
    }
    /* Check if sour? entry is either yes or no. If so, match it to true
    or false boolean, respectively. If not, throw a FactoryFailureException. */
    String sourString = row.get(4);
    boolean sour;
    if (sourString.equals("yes")) {
      sour = true;
    } else if (sourString.equals("no")) {
      sour = false;
    } else {
      throw new FactoryFailureException(row);
    }

    return new Fruit(name, colour, size, price, sour);
  }
}
