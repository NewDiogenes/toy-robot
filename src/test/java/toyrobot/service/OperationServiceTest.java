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

  @Test
  public void givenTheRobotIsFacingNorth_move_shouldIncreaseTheYpositionByOne() {
    toyRobot.setFacing(Direction.NORTH.getAngle());
    int startYposition = 1;
    toyRobot.setYposition(startYposition);
    operationService.move().accept(toyRobot);
    assertEquals(startYposition + 1, toyRobot.getYposition());
  }
  @Test
  public void givenTheRobotIsFacingNorth_move_shouldNotIncreaseTheXposition() {
    toyRobot.setFacing(Direction.NORTH.getAngle());
    int startXposition = 1;
    toyRobot.setXposition(startXposition);
    operationService.move().accept(toyRobot);
    assertEquals(startXposition, toyRobot.getXposition());
  }

  @Test
  public void givenTheRobotIsFacingEast_move_shouldIncreaseTheXpositionByOne() {
    toyRobot.setFacing(Direction.EAST.getAngle());
    int startXposition = 1;
    toyRobot.setXposition(startXposition);
    operationService.move().accept(toyRobot);
    assertEquals(startXposition + 1, toyRobot.getXposition());
  }
  @Test
  public void givenTheRobotIsFacingEast_move_shouldNotIncreaseTheYposition() {
    toyRobot.setFacing(Direction.EAST.getAngle());
    int startYposition = 1;
    toyRobot.setYposition(startYposition);
    operationService.move().accept(toyRobot);
    assertEquals(startYposition, toyRobot.getYposition());
  }
  @Test
  public void givenTheRobotIsFacingSouth_move_shouldDecreaseTheYpositionByOne() {
    toyRobot.setFacing(Direction.SOUTH.getAngle());
    int startYposition = 1;
    toyRobot.setYposition(startYposition);
    operationService.move().accept(toyRobot);
    assertEquals(startYposition - 1, toyRobot.getYposition());
  }
  @Test
  public void givenTheRobotIsFacingSouth_move_shouldNotDecreaseTheXposition() {
    toyRobot.setFacing(Direction.SOUTH.getAngle());
    int startXposition = 1;
    toyRobot.setXposition(startXposition);
    operationService.move().accept(toyRobot);
    assertEquals(startXposition, toyRobot.getXposition());
  }

  @Test
  public void givenTheRobotIsFacingWest_move_shouldDencreaseTheXpositionByOne() {
    toyRobot.setFacing(Direction.WEST.getAngle());
    int startXposition = 1;
    toyRobot.setXposition(startXposition);
    operationService.move().accept(toyRobot);
    assertEquals(startXposition - 1, toyRobot.getXposition());
  }
  @Test
  public void givenTheRobotIsFacingWest_move_shouldNotDecreaseTheYposition() {
    toyRobot.setFacing(Direction.WEST.getAngle());
    int startYposition = 1;
    toyRobot.setYposition(startYposition);
    operationService.move().accept(toyRobot);
    assertEquals(startYposition, toyRobot.getYposition());
  }

  @Test
  public void givenTheRobotHasXposition0AndFacesWest_move_shouldNotChangeTheXposition() {
    toyRobot.setFacing(Direction.WEST.getAngle());
    int startXposition = 0;
    toyRobot.setXposition(startXposition);
    operationService.move().accept(toyRobot);
    assertEquals(startXposition, toyRobot.getXposition());
  }

  @Test
  public void givenTheRobotHasYposition0AndFacesSouth_move_shouldNotChangeTheYposition() {
    toyRobot.setFacing(Direction.SOUTH.getAngle());
    int startYposition = 0;
    toyRobot.setYposition(startYposition);
    operationService.move().accept(toyRobot);
    assertEquals(startYposition, toyRobot.getYposition());
  }

  @Test
  public void givenTheRobotHasXpositionEqualToTableSizeAndFacesEast_move_shouldNotChangeTheXposition() {
    toyRobot.setFacing(Direction.EAST.getAngle());
    toyRobot.setXposition(tableSize);
    operationService.move().accept(toyRobot);
    assertEquals(tableSize, toyRobot.getXposition());
  }

  @Test
  public void givenTheRobotHasYpositionEqualToTableSizeAndFacesNorth_move_shouldNotChangeTheYposition() {
    toyRobot.setFacing(Direction.NORTH.getAngle());
    toyRobot.setYposition(tableSize);
    operationService.move().accept(toyRobot);
    assertEquals(tableSize, toyRobot.getYposition());
  }
}