package toyrobot;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import toyrobot.domain.Command;
import toyrobot.model.ToyRobot;
import toyrobot.service.CommandService;
import toyrobot.util.InputReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ApplicationTest {

  @Mock
  private InputReader inputReader;
  @Mock
  private CommandService commandService;
  @Mock
  private Command mockCommand;

  private Application application;
  private List<String> inputStream;
  private String[] args;

  @Before
  public void setUp() throws IOException {
    initMocks(this);
    application = new Application(inputReader, commandService);
    inputStream = Arrays.asList("move", "place 1 1 NORTH", "move", "left", "move", "report", "right", "move", "report");
    args = new String[]{"test"};

    when(inputReader.readFile(eq(args))).thenReturn(inputStream.stream());
    when(commandService.menu()).thenReturn(mockCommand);
    when(mockCommand.apply(any())).thenReturn(Optional.of((ToyRobot tr) -> {
    }));
  }

  @Test
  public void start_shouldPassArgsToInputReader() throws IOException {
    application.start(args);
    verify(inputReader).readFile(eq(args));
  }

  @Test
  public void givenInputReaderReturnsAStreamOfInput_start_shouldPassEachInputToTheCommandService() {
    application.start(args);
    InOrder inOrder = inOrder(mockCommand);
    inputStream.forEach(in -> inOrder.verify(mockCommand).apply(in));
  }
}