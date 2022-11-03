package edu.brown.cs.student.main;

import word_count_utility.WordCounter2000;

/** The Main class of our project. This is where execution begins. */
public final class Main {
  /**
   * The initial method called when execution begins.
   *
   * @param args An array of command line arguments
   */
  public static void main(String[] args) {
    new Main(args).run();
  }

  private String[] args;

  private Main(String[] args) {
    this.args = args;
  }

  private void incorrectUsageError() {
    System.out.println(
        "Usage: ./run [--ignorefirst] <CSV filename>.\nInclude "
            + "the first element if you want the first row to be ignored.");
    System.exit(0);
  }

  private void run() {
    /*
    set "default" values so IntelliJ doesn't complain -_-
     */
    boolean ignoreFirst = false;
    String filename = "";
    /*
    incorrect number of arguments
     */
    if (args.length > 2 || args.length == 0) {
      incorrectUsageError();
      System.out.println("incorrect number of args");
      /*
      if there are 2 arguments, set ignoreFirst to true,
      and set filename to second arg.
       */
    } else if (args.length == 2) {
      ignoreFirst = true;
      if (!args[0].equals("--ignorefirst")) {
        System.out.println("not --ignorefirst");
        incorrectUsageError();
      } else {
        filename = args[1];
      }
      /*
      if there is only 1 argument, set filename to that arg.
       */
    } else if (args.length == 1) {
      filename = args[0];
    }

    WordCounter2000 utility = new WordCounter2000(filename, ignoreFirst);
    System.out.println(
        " _    _               _ _____                   _              _____  "
            + "_____  _____  _____ \n| |  | |             | /  __ \\                 "
            + "| |            / __  \\|  _  ||  _  ||  _  |\n| |  | | ___  _ __ __| | /  "
            + "\\/ ___  _   _ _ __ | |_ ___ _ __  `' / /'| |/' || |/' || |/' |\n"
            + "| |/\\| |/ _ \\| '__/ _` | |    / _ \\| | | | '_ \\| __/ _ \\ '__|   "
            + "/ /  |  /| ||  /| ||  /| |\n\\  /\\  / (_) | | | (_| | \\__/\\ (_) | |_| "
            + "| | | | ||  __/ |    ./ /___\\ |_/ /\\ |_/ /\\ |_/ /\n \\/  \\/ \\___/|_|  "
            + "\\__,_|\\____/\\___/ \\__,_|_| |_|\\__\\___|_|    \\_____/ \\___/  \\___/  \\___/ ");
    utility.printCounts();
    System.out.println("Thank you for using WordCounter2000®!\n");

    //    // prints out command line arguments; can remove this
    //    System.out.println(Arrays.toString(args));
    //
    //    // generates galaxy of stars, computes nearest neighbor for all
    //    if (args.length == 2 && args[0].equals("generate_galaxy")) {
    //      int numStars = 0;
    //      try {
    //        numStars = Integer.parseInt(args[1]);
    //      } catch (Exception ignored) {
    //        System.err.println("ERROR: Could not parse number of stars to generate.");
    //      }
    //      List<Star> galaxy = GalaxyGenerator.generate(numStars);
    //      KdTree<Star> starKdTree = new KdTree<>(galaxy, 0);
    //      for (Star star : galaxy) {
    //        PriorityQueue<Star> pq =
    //            starKdTree.kdTreeSearch(
    //                "neighbors", 1, star, new DistanceSorter(star), new HashSet<>());
    //        System.out.println(pq.peek());
    //      }
    //    }
  }
}
