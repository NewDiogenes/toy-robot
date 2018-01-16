package toyrobot.util;

import org.junit.Test;

import java.io.FileReader;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

public class ReaderFactoryTest {
  private static final String TEST_DATA = "src/test/resources/testdata.txt";

  @Test
  public void whenGivenAnEmptyArray_getReader_shouldReturnAnInputStreamReader() {
    assertEquals(InputStreamReader.class, ReaderFactory.getReader(new String[0]).getClass());
  }

  @Test
  public void whenAnArgumentThatIsNotAFileName_getReader_shouldReturnAnInputStreamReader() {
    String[] args = {""};
    assertEquals(InputStreamReader.class, ReaderFactory.getReader(args).getClass());
  }

  @Test
  public void whenAnArgumentThatIsAFileName_getReader_shouldReturnFileReader() {
    String[] args = {TEST_DATA};
    assertEquals(FileReader.class, ReaderFactory.getReader(args).getClass());
  }
}