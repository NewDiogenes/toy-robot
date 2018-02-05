package toyrobot.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import toyrobot.model.Direction;
import toyrobot.model.ToyRobot;

import java.util.Optional;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CommandServiceTest {

  @Mock
  private Consumer<ToyRobot> mockOperation;
  @Mock
  private OperationService operationService;
  private final String input = "testInput";
  private CommandService commandService;

  @Before
  public void setUp() {
    initMocks(this);
    commandService = new CommandService(operationService);

  }

  @Test
  public void givenAPlaceCommand_menu_shouldReturnOperationServicePlace() {
    String command = "place 0 0 NORTH";
    when(operationService.place(0, 0, Direction.NORTH)).thenReturn(mockOperation);
    commandService.menu().apply(command);
    assertEquals(mockOperation, commandService.menu().apply(command).get());
  }

  @Test
  public void givenALeftCommand_menu_shouldReturnOperationServiceLeft() {
    String command = "left";
    when(operationService.left()).thenReturn(mockOperation);
    assertEquals(mockOperation, commandService.menu().apply(command).get());
  }

  @Test
  public void givenARightCommand_menu_shouldReturnOperationServiceRight() {
    String command = "right";
    when(operationService.right()).thenReturn(mockOperation);
    assertEquals(mockOperation, commandService.menu().apply(command).get());
  }

  @Test
  public void givenAMoveCommand_menu_shouldReturnOperationServiceMove() {
    String command = "move";
    when(operationService.move()).thenReturn(mockOperation);
    assertEquals(mockOperation, commandService.menu().apply(command).get());
  }

  @Test
  public void givenAReportCommand_menu_shouldReturnOperationServiceReport() {
    String command = "report";
    when(operationService.report()).thenReturn(mockOperation);
    assertEquals(mockOperation, commandService.menu().apply(command).get());
  }
}