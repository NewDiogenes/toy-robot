package toyrobot.util;

import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

public class ReaderFactoryTest {
  private static final String TEST_DATA = "src/test/resources/testdata.txt";
  private ReaderFactory readerFactory;

  @Before
  public void setUp() {
    readerFactory = new ReaderFactory();
  }

  @Test
  public void whenGivenAnEmptyArray_getReader_shouldReturnAnInputStreamReader() {
    assertEquals(InputStreamReader.class, readerFactory.getReader(new String[0]).getClass());
  }

  @Test
  public void whenAnArgumentThatIsNotAFileName_getReader_shouldReturnAnInputStreamReader() {
    String[] args = {""};
    assertEquals(InputStreamReader.class, readerFactory.getReader(args).getClass());
  }

  @Test
  public void whenAnArgumentThatIsAFileName_getReader_shouldReturnFileReader() {
    String[] args = {TEST_DATA};
    assertEquals(FileReader.class, readerFactory.getReader(args).getClass());
  }
}