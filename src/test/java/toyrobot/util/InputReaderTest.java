package toyrobot.util;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class InputReaderTest {
  private static final String TEST_DATA = "src/test/resources/testdata.txt";
  private InputReader inputReader;

  @Before
  public void setUp() {
    inputReader = new InputReader();
  }

  @Test(expected = NoSuchElementException.class)
  public void whenGivenAnEmptyArray_getReader_shouldThrowNoSuchElementException() throws IOException {
    inputReader.readFile(new String[0]);
  }

  @Test(expected = IOException.class)
  public void whenAnArgumentThatIsNotAFileName_getReader_shouldThrowException() throws IOException {
    String[] args = {"notAFile"};
    inputReader.readFile(args);
  }

  @Test
  public void whenAnArgumentThatIsAFileName_getReader_shouldReturnFIleContentsAsStream() throws IOException {
    String[] args = {TEST_DATA};
    Stream<String> expectedReturn = Stream.of("place 0 0 north", "move", "left", "right", "report");
    assertArrayEquals(expectedReturn.toArray(), inputReader.readFile(args).toArray());
  }
}