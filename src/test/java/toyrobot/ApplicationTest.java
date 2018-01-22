package toyrobot;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import toyrobot.util.InputReader;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class ApplicationTest {

  @Mock
  private InputReader inputReader;

  private Application application;

  @Before
  public void setUp() {
    initMocks(this);
    application = new Application(inputReader);
  }

  @Test
  public void start_shouldPassArgsToInputReader() throws IOException{
    String[] args = {"test"};
    application.start(args);
    verify(inputReader).readFile(eq(args));
  }
}