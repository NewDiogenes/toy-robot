package toyrobot;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.io.PrintStream;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class MainIntegrationTest {

  @Mock
  private PrintStream printStream;

  private String[] args;

  @Before
  public void setUp() {
    initMocks(this);
    System.setOut(printStream);
  }

  @Test
  public void shouldPlaceRobotOnTable() throws IOException {
    args = new String[]{makeFilePath("shouldPlaceRobotOnTable")};
    Main.main(args);
    verify(printStream).println("1, 2, SOUTH");
  }

  @Test
  public void shouldPlaceRobotOnTableThenMove() throws IOException {
    args = new String[]{makeFilePath("shouldPlaceRobotOnTableThenMove")};
    Main.main(args);
    verify(printStream).println("1, 1, SOUTH");
  }

  @Test
  public void shouldPlaceRobotOnTableThenMoveThenTurnLeft() throws IOException {
    args = new String[]{makeFilePath("shouldPlaceRobotOnTableThenMoveThenTurnLeft")};
    Main.main(args);
    verify(printStream).println("1, 1, EAST");
  }

  @Test
  public void shouldPlaceRobotOnTableThenMoveThenTurnLeftThenMove() throws IOException {
    args = new String[]{makeFilePath("shouldPlaceRobotOnTableThenMoveThenTurnLeftThenMove")};
    Main.main(args);
    verify(printStream).println("2, 1, EAST");
  }

  @Test
  public void shouldPlaceRobotOnTableThenMoveThenTurnLeftThenMoveThenTurnRight() throws IOException {
    args = new String[]{makeFilePath("shouldPlaceRobotOnTableThenMoveThenTurnLeftThenMoveThenTurnRight")};
    Main.main(args);
    verify(printStream).println("2, 1, SOUTH");
  }

  private String makeFilePath(String fileName) {
    return String.format("src/test/resources/%s.txt", fileName);
  }
}
