package toyrobot.service;

import org.junit.Before;
import org.junit.Test;
import toyrobot.model.Direction;
import toyrobot.model.ToyRobot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class OperationServiceTest {

  private ToyRobot toyRobot;
  private OperationService operationService;
  private final int tableSize = 5;

  @Before
  public void setUp() {
    toyRobot = new ToyRobot();
    operationService = new OperationService(tableSize);
  }

  @Test
  public void givenAValidXposition_place_shouldSetTheRobotsXposition() {
    operationService.place(tableSize, 0, Direction.NORTH).accept(toyRobot);
    assertEquals(tableSize, toyRobot.getXposition());
  }

  @Test
  public void givenAValidYposition_place_shouldSetTheRobotsYposition() {
    operationService.place(0, tableSize, Direction.NORTH).accept(toyRobot);
    assertEquals(tableSize, toyRobot.getYposition());
  }

  @Test
  public void givenAFacingDirection_place_shouldSetTheRobotsFacingDirection() {
    operationService.place(0, 0, Direction.EAST).accept(toyRobot);
    assertEquals(Direction.EAST.getAngle(), toyRobot.getFacing());
  }

  @Test
  public void givenAnXpositionGreaterThanTheMaximum_place_shouldNotSetTheXposition() {
    int offTable = tableSize + 1;
    operationService.place(offTable, 0, Direction.EAST).accept(toyRobot);
    assertNotEquals(offTable, toyRobot.getXposition());
  }

  @Test
  public void givenAYpositionGreaterThanTheMaximum_place_shouldNotSetTheYposition() {
    int offTable = tableSize + 1;
    operationService.place(0, offTable, Direction.EAST).accept(toyRobot);
    assertNotEquals(offTable, toyRobot.getXposition());
  }

  @Test
  public void right_shouldRotateToyRobotClockWise() {
    toyRobot.setFacing(Direction.EAST.getAngle());
    operationService.right().accept(toyRobot);
    assertEquals(Direction.SOUTH.getAngle(), toyRobot.getFacing());
  }

  @Test
  public void left_shouldRotateToyRobotCounterClockWise() {
    toyRobot.setFacing(Direction.EAST.getAngle());
    operationService.left().accept(toyRobot);
    assertEquals(Direction.NORTH.getAngle(), toyRobot.getFacing());
  }
}