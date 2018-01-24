package toyrobot.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import toyrobot.domain.Command;
import toyrobot.domain.InputParser;
import toyrobot.domain.InputValidator;

import java.util.Optional;

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

  private Command command;
  private String cmd;

  @Before
  public void setUp() {
    initMocks(this);
    command = Command.make(inputValidator, inputParser);
    cmd = "test";
  }

  @Test
  public void givenANonNullInput_Command_shouldCallTheValidator() {
    command.apply(cmd);
    verify(inputValidator).test(eq(cmd));
  }

  @Test
  public void givenANullInput_Command_shouldNotCallTheValidator() {
    command.apply(null);
    verify(inputValidator, times(0)).test(any());
  }

  @Test
  public void givenTheValidatorReturnsFalse_Command_shouldNotCallTheParser() {
    when(inputValidator.test(any())).thenReturn(false);
    command.apply(cmd);
    verify(inputParser, times(0)).apply(any());
  }

  @Test
  public void givenTheValidatorReturnsTrue_Command_shouldCallTheParser() {
    when(inputValidator.test(any())).thenReturn(true);
    when(inputParser.apply(any())).thenReturn(Optional.empty());
    command.apply(cmd);
    verify(inputParser).apply(eq(cmd));
  }
}