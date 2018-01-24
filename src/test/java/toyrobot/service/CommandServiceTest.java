package toyrobot.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import toyrobot.domain.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class CommandServiceTest {

  @Mock
  private Command command;
  private final String input = "testInput";
  private CommandService commandService;

  @Before
  public void setUp() {
    initMocks(this);
    Map<String, Command> commands = new HashMap<>();
    commands.put(input, command);
    commandService = new CommandService(commands);
  }

  @Test
  public void givenAnInputWithNoCorrespondingCommand_getCommand_shouldReturnEmpty() {
    assertEquals(Optional.empty(), commandService.getCommand(""));
  }

  @Test
  public void givenAnInputWithACorrespondingCommand_getCommand_shouldReturnTheCommand() {
    assertEquals(command, commandService.getCommand(input).get());
  }

  @Test
  public void givenAnInputWithMultipleCommands_getCommand_shouldReturnTheFirstCommand() {
    assertEquals(command, commandService.getCommand(input + " test2").get());
  }
}