package parser.fruits;

import java.util.Objects;

/**
 * Class that represents a kind of fruit. Stores information about its name, colour, size, price,
 * and whether it is sour or not.
 */
public class Fruit {
  private String name;
  private String colour;
  private FruitSizeEnum size;
  private float price;
  private boolean sour;

  /**
   * Constructor for a Fruit object.
   *
   * @param name The name of the fruit.
   * @param colour The colour of the fruit.
   * @param size The size of the fruit as an enum. Values are tiny, small, medium, large and huge.
   * @param price The price of the fruit in dollars.
   * @param sour true if the fruit is sour, else false.
   */
  public Fruit(String name, String colour, FruitSizeEnum size, float price, boolean sour) {
    this.name = name;
    this.colour = colour;
    this.size = size;
    this.price = price;
    this.sour = sour;
  }

  /**
   * A self-defined equals() method, for testing purposes. Compares using field equality.
   *
   * @param o The object to be compared with.
   * @return true if o is also a Fruit object, and if the name, colour, size, price, and sourness
   *     fields are all equal.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    Fruit fruit = (Fruit) o;
    return Float.compare(fruit.price, this.price) == 0
        && this.sour == fruit.sour
        && Objects.equals(this.name, fruit.name)
        && Objects.equals(this.colour, fruit.colour)
        && this.size == fruit.size;
  }
}
