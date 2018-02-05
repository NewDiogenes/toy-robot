package toyrobot;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import toyrobot.domain.Command;
import toyrobot.service.CommandService;
import toyrobot.service.OperationService;
import toyrobot.util.InputReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
  @Mock
  private OperationService operationService;


  private Application application;
  private List<String> inputStream;
  private String[] args;

  @Before
  public void setUp() throws IOException {
    initMocks(this);
    application = new Application(inputReader, commandService, operationService);
    inputStream = Arrays.asList("move", "place 1 1 NORTH", "move", "left", "move", "report", "right", "move", "report");
    args = new String[]{"test"};

    when(inputReader.readFile(eq(args))).thenReturn(inputStream.stream());
    when(commandService.getCommand(any())).thenReturn(Optional.of(mockCommand));
  }

  @Test
  public void start_shouldPassArgsToInputReader() throws IOException {
    application.start(args);
    verify(inputReader).readFile(eq(args));
  }

  @Test
  public void givenInputReaderReturnsAStreamOfInput_start_shouldPassEachInputToTheCommandService() {
    application.start(args);
    InOrder inOrder = inOrder(commandService);
    inputStream.forEach(in -> inOrder.verify(commandService).getCommand(in));
  }
}