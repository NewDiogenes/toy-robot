package toyrobot.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import toyrobot.domain.Command;
import toyrobot.domain.InputParser;
import toyrobot.domain.InputValidator;

import java.util.Optional;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CommandTest {

  @Mock
  private InputValidator inputValidator;
  @Mock
  private InputParser inputParser;
  @Mock
  private Consumer<ToyRobot> mockConsumer;

  private Command parsePredicateCommand;
  private Command predicateCommand;
  private String cmd;

  @Before
  public void setUp() {
    initMocks(this);
    parsePredicateCommand = Command.parsePredicateCommand(inputValidator, inputParser);
    predicateCommand = Command.predicateCommand(inputValidator, mockConsumer);
    cmd = "test";
  }

  @Test
  public void givenANonNullInput_parsePredicateCommand_shouldCallTheValidator() {
    parsePredicateCommand.apply(cmd);
    verify(inputValidator).test(eq(cmd));
  }

  @Test
  public void givenANullInput_parsePredicateCommand_shouldNotCallTheValidator() {
    parsePredicateCommand.apply(null);
    verify(inputValidator, times(0)).test(any());
  }

  @Test
  public void givenTheValidatorReturnsFalse_parsePredicateCommand_shouldNotCallTheParser() {
    when(inputValidator.test(any())).thenReturn(false);
    parsePredicateCommand.apply(cmd);
    verify(inputParser, times(0)).apply(any());
  }

  @Test
  public void givenTheValidatorReturnsTrue_parsePredicateCommand_shouldCallTheParser() {
    when(inputValidator.test(any())).thenReturn(true);
    when(inputParser.apply(any())).thenReturn(Optional.empty());
    parsePredicateCommand.apply(cmd);
    verify(inputParser).apply(eq(cmd));
  }

  @Test
  public void givenANonNullInput_predicateCommand_shouldCallTheValidator() {
    predicateCommand.apply(cmd);
    verify(inputValidator).test(eq(cmd));
  }

  @Test
  public void givenANullInput_predicateCommand_shouldNotCallTheValidator() {
    predicateCommand.apply(null);
    verify(inputValidator, times(0)).test(any());
  }

  @Test
  public void givenTheValidatorReturnsFalse_predicateCommand_shouldOptionalEmpty() {
    when(inputValidator.test(any())).thenReturn(false);
    assertEquals(Optional.empty(), predicateCommand.apply(cmd));
  }

  @Test
  public void givenTheValidatorReturnsTrue_predicateCommand_shouldReturnTheConsumer() {
    when(inputValidator.test(any())).thenReturn(true);
    assertEquals(mockConsumer, predicateCommand.apply(cmd).get());
  }

  @Test(expected = IllegalStateException.class)
  public void givenAnInputMatchesToMoreThanOneCommand_commandMenu_shouldThrowIllegalStateException() {
    when(inputValidator.test(any())).thenReturn(true);
    Command.commandMenu(predicateCommand, predicateCommand).apply(cmd);
  }
}