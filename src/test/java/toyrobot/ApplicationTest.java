package toyrobot;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import toyrobot.util.ReaderFactory;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class ApplicationTest {

  @Mock
  private ReaderFactory readerFactory;

  private Application application;

  @Before
  public void setUp() throws Exception {
    initMocks(this);
    application = new Application(readerFactory);
  }

  @Test
  public void start_shouldPassArgsToReaderFactory() {
    String[] args = {"test"};
    application.start(args);
    verify(readerFactory).getReader(eq(args));
  }
}