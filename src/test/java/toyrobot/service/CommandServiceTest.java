package toyrobot.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import toyrobot.domain.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

public class CommandServiceTest {

  @Mock
  private Command command;
  private final String input = "testInput";
  private CommandService commandService;
  private Map<String, Command> commandList;

  @Before
  public void setUp() {
    initMocks(this);
    commandList = new HashMap<>();
    commandList.put(input, command);
    commandService = new CommandService();
  }

  @Test
  public void givenAnInputWithNoCorrespondingCommand_getCommand_shouldReturnEmpty() {
    assertEquals(Optional.empty(), commandService.getCommand(""));
  }

  @Test(expected = IllegalArgumentException.class)
  public void givenServiceDoesNotAlreadyHaveCommandWithName_addCommand_shouldAddANewCommand() {
    String commandName = "command";
    commandService.addCommand(commandName, command);
    assertTrue(commandService.getCommandList().containsKey(commandName));
  }

  @Test
  public void givenAnInputWithACorrespondingCommand_getCommand_shouldReturnTheCommand() {
    commandService.setCommandList(commandList);
    assertEquals(command, commandService.getCommand(input).get());
  }

  @Test
  public void givenAnInputWithMultipleCommands_getCommand_shouldReturnTheFirstCommand() {
    commandService.setCommandList(commandList);
    assertEquals(command, commandService.getCommand(input + " test2").get());
  }

  @Test(expected = IllegalArgumentException.class)
  public void givenCommandNameIsNull_addCommand_shouldThrowIllegalArgumentException() {
    commandService.addCommand(null, command);
  }

  @Test(expected = IllegalArgumentException.class)
  public void givenCommandIsNull_addCommand_shouldThrowIllegalArgumentException() {
    commandService.addCommand("command", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void givenServiceAlreadyHasCommandWithName_addCommand_shouldThrowIllegalArgumentException() {
    commandService.addCommand("command", command);
    commandService.addCommand("command", command);
  }


}