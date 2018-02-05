package toyrobot.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import toyrobot.model.Direction;
import toyrobot.model.ToyRobot;

import java.io.PrintStream;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class OperationServiceTest {

  private ToyRobot toyRobot;
  private OperationService operationService;
  private final Integer tableSize = 5;

  @Mock
  private PrintStream printStream;

  @Before
  public void setUp() {
    initMocks(this);
    toyRobot = new ToyRobot();
    operationService = new OperationService(tableSize);
    System.setOut(printStream);
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
    Integer offTable = tableSize + 1;
    operationService.place(offTable, 0, Direction.EAST).accept(toyRobot);
    assertNotEquals(offTable, toyRobot.getXposition());
  }

  @Test
  public void givenAYpositionGreaterThanTheMaximum_place_shouldNotSetTheYposition() {
    Integer offTable = tableSize + 1;
    operationService.place(0, offTable, Direction.EAST).accept(toyRobot);
    assertNotEquals(offTable, toyRobot.getXposition());
  }

  @Test
  public void givenToyRobotIsUninitialized_right_shouldMakeNoChanges() {
    operationService.right().accept(toyRobot);
    assertNull(toyRobot.getYposition());
    assertNull(toyRobot.getXposition());
    assertNull(toyRobot.getFacing());
  }

  @Test
  public void givenFacingIsNotNull_right_shouldRotateToyRobotClockWise() {
    toyRobot.setFacing(Direction.EAST.getAngle());
    operationService.right().accept(toyRobot);
    assertEquals(Direction.SOUTH.getAngle(), toyRobot.getFacing());
  }

  @Test
  public void givenToyRobotIsUninitialized_left_shouldMakeNoChanges() {
    operationService.left().accept(toyRobot);
    assertNull(toyRobot.getYposition());
    assertNull(toyRobot.getXposition());
    assertNull(toyRobot.getFacing());
  }

  @Test
  public void givenFacingIsNotNull_left_shouldRotateToyRobotCounterClockWise() {
    toyRobot.setFacing(Direction.EAST.getAngle());
    operationService.left().accept(toyRobot);
    assertEquals(Direction.NORTH.getAngle(), toyRobot.getFacing());
  }

  @Test
  public void givenToyRobotIsUninitialized_move_shouldMakeNoChanges() {
    operationService.move().accept(toyRobot);
    assertNull(toyRobot.getYposition());
    assertNull(toyRobot.getXposition());
    assertNull(toyRobot.getFacing());
  }

  @Test
  public void givenTheRobotIsFacingNorth_move_shouldIncreaseTheYpositionByOne() {
    toyRobot.setFacing(Direction.NORTH.getAngle());
    Integer startYposition = 1;
    Integer startXposition = 1;
    setPositions(startXposition, startYposition);
    operationService.move().accept(toyRobot);
    assertEquals(startYposition + 1, toyRobot.getYposition().intValue());
  }

  @Test
  public void givenTheRobotIsFacingNorth_move_shouldNotIncreaseTheXposition() {
    toyRobot.setFacing(Direction.NORTH.getAngle());
    Integer startXposition = 1;
    Integer startYposition = 1;
    setPositions(startXposition, startYposition);
    operationService.move().accept(toyRobot);
    assertEquals(startXposition, toyRobot.getXposition());
  }

  @Test
  public void givenTheRobotIsFacingEast_move_shouldIncreaseTheXpositionByOne() {
    toyRobot.setFacing(Direction.EAST.getAngle());
    Integer startXposition = 1;
    Integer startYposition = 0;
    setPositions(startXposition, startYposition);
    operationService.move().accept(toyRobot);
    assertEquals(startXposition + 1, toyRobot.getXposition().intValue());
  }

  @Test
  public void givenTheRobotIsFacingEast_move_shouldNotIncreaseTheYposition() {
    toyRobot.setFacing(Direction.EAST.getAngle());
    Integer startYposition = 1;
    Integer startXposition = 0;
    setPositions(startXposition, startYposition);
    operationService.move().accept(toyRobot);
    assertEquals(startYposition, toyRobot.getYposition());
  }

  @Test
  public void givenTheRobotIsFacingSouth_move_shouldDecreaseTheYpositionByOne() {
    toyRobot.setFacing(Direction.SOUTH.getAngle());
    Integer startYposition = 1;
    Integer startXposition = 1;
    setPositions(startXposition, startYposition);
    operationService.move().accept(toyRobot);
    assertEquals(startYposition - 1, toyRobot.getYposition().intValue());
  }

  @Test
  public void givenTheRobotIsFacingSouth_move_shouldNotDecreaseTheXposition() {
    toyRobot.setFacing(Direction.SOUTH.getAngle());
    Integer startXposition = 1;
    Integer startYposition = 0;
    setPositions(startXposition, startYposition);
    operationService.move().accept(toyRobot);
    assertEquals(startXposition, toyRobot.getXposition());
  }

  @Test
  public void givenTheRobotIsFacingWest_move_shouldDecreaseTheXpositionByOne() {
    toyRobot.setFacing(Direction.WEST.getAngle());
    Integer startXposition = 1;
    Integer startYposition = 0;
    setPositions(startXposition, startYposition);
    operationService.move().accept(toyRobot);
    assertEquals(startXposition - 1, toyRobot.getXposition().intValue());
  }

  @Test
  public void givenTheRobotIsFacingWest_move_shouldNotDecreaseTheYposition() {
    toyRobot.setFacing(Direction.WEST.getAngle());
    Integer startYposition = 1;
    Integer startXposition = 1;
    setPositions(startXposition, startYposition);
    operationService.move().accept(toyRobot);
    assertEquals(startYposition, toyRobot.getYposition());
  }

  @Test
  public void givenTheRobotHasXposition0AndFacesWest_move_shouldNotChangeTheXposition() {
    toyRobot.setFacing(Direction.WEST.getAngle());
    Integer startXposition = 0;
    Integer startYposition = 1;
    setPositions(startXposition, startYposition);
    operationService.move().accept(toyRobot);
    assertEquals(startXposition, toyRobot.getXposition());
  }

  @Test
  public void givenTheRobotHasYposition0AndFacesSouth_move_shouldNotChangeTheYposition() {
    toyRobot.setFacing(Direction.SOUTH.getAngle());
    Integer startYposition = 0;
    Integer startXposition = 0;
    setPositions(startXposition, startYposition);
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

  @Test
  public void givenToyRobotIsUninitialized_report_shouldNotPrintAnything() {
    operationService.move().accept(toyRobot);
    verify(printStream, times(0)).println();
  }

  @Test
  public void givenTheRobotIsOnTheTable_report_shouldPrintTheRobotsCurrentStateToSystemOut() {
    toyRobot.setXposition(3);
    toyRobot.setYposition(2);
    toyRobot.setFacing(Direction.WEST.getAngle());
    operationService.report().accept(toyRobot);
    verify(printStream).println(eq("3, 2, WEST"));
  }

  private void setPositions(Integer xposition, Integer yposition) {
    toyRobot.setXposition(xposition);
    toyRobot.setYposition(yposition);
  }
}