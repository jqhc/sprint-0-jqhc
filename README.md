# ParseBot and WordCounter2000

## About
A tool for parsing CSVs into self-defined objects, and an accompanying command 
line utility (WordCounter2000) implemented using the parser.

ParseBot takes a user-defined `CreatorFromRow<T>` object, and parses CSV files through
a reader into user-defined T objects.

WordCounter2000 is a command line utility built on ParseBot, which takes a user-provided
CSV file and parses its rows to print the total words, characters, rows, and columns
in the file.

This tool was created for a project from the course CSCI0320 at Brown University. 
The project took an estimated 13 hours to complete.

## Getting Started
### Building the project and running tests
This project is built in [Maven](https://maven.apache.org/index.html). To build the project, open 
your command line and navigate to the project directory. Then, type the command `mvn package` 
and press enter. This will compile, run unit tests, and generate a runnable .jar file. 

If you want to run tests only, instead run `mvn test`.

### Running WordCounter2000
Once you have done this, you can run WordCounter2000 in your command line at any time 
by navigating to the project directory and running the command `./run [--ignorefirst] <filepath>`.

![An example](wordcounter_example.png)

#### Some usage comments:
- Include the `--ignorefirst` if you want WordCounter2000 to ignore the first row of your CSV
(if, say, you have a header row).
- If the file is in the project folder, you can use the relative filepath (i.e. starting from the 
current directory).
- Otherwise, you can use the absolute filepath (i.e. starting from your root directory).
### Running ParseBot
For developers wanting to use ParseBot, you must first define your own class `T`, and a
`T` creator class implementing the provided interface `CreatorFromRow<T>`. 

Then, you can construct a ParseBot object by providing a Reader whose input stream is your CSV, 
as well as the `T` creator class. The third parameter, `ignoreFirst`, should be set to true if 
you want ParseBot to ignore the first row of your CSV.

Call the `createNext()` function to receive `T` objects one-by-one, parsed from individual
rows of the CSV. When ParseBot reaches the end of the CSV, `createNext()` will throw an
`EndOfCSVException`.
#### Some usage comments:
- ParseBot will throw a `CSVReadingException` if it is unable to read the file. This may occur
if the file is deleted after being passed, or the input stream is interrupted during the reading.
- ParseBot will throw a `FactoryFailureException` whenever your `CreatorFromRow` object does (i.e.
when a row cannot be parsed into a `T` object).

## Testing Suites
An extensive set of tests was built to verify the implementation of ParseBot. Below is a brief overview
of the testing suites:
### Testing the Reader parameter
By passing a StringReader, a FileReader, and a BufferedReader, it was verified that a variety of 
user-provided Reader objects could be used with ParseBot.
### Testing the `CreatorFromRow` parameter
By passing three kinds of `CreatorFromRow` objects (`RowCountCreator`, `FruitFactory`, `StarFactory`)
it was verified that a variety of user-provided strategy-based interfaces could be used with ParseBot.
### Testing for `EndOfCSVException`
By calling `createNext()` at the end of the CSV and using `Assert.assertThrows`, it was verified
that ParseBot throws an `EndOfCSVException` once it reaches the end of the CSV.
### Testing for the `ignoreFirst` field
By constructing a ParseBot object with its `ignoreFirst` field set to `true`, it was verified that
ParseBot is able to correctly ignore the first row of the CSV.

## Program Design
This project is centered around the ParseBot class. Within the parser package, 
there are two Exception subclasses, `CSVReadingException` and `EndOfCSVException`, to be thrown 
by ParseBot (see the section about [running ParseBot](####some-usage-comments2)).

WordCounter2000 works by passing the `RowCountCreator` object, which implements the `CreatorFromRow`
interface, to ParseBot. `RowCountCreator` then creates `RowCount` objects, which store information
about the word, character, and column counts of the CSV. These are repeatedly taken and added up to
provide the counts of WordCounter2000.

## A note about CheckStyle errors
There are some CheckStyle errors in this project due to the use of "CSV" in the names of certain
classes, since there can be no more than 1 consecutive capital letter. Unfortunately, this is
unavoidable as this must be fully capitalized.



