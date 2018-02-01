package toyrobot.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionTest {

  @Test
  public void givenAnAngleIsRelativeNorth_getDirection_shouldReturnNorth() {
    int angle = 0;
    assertEquals(Direction.NORTH, Direction.getDirection(angle));
  }

  @Test
  public void givenAnAngleIsRelativeEast_getDirection_shouldReturnEast() {
    int angle = 90;
    assertEquals(Direction.EAST, Direction.getDirection(angle));
  }

  @Test
  public void givenAnAngleIsRelativeSouth_getDirection_shouldReturnSouth() {
    int angle = 180;
    assertEquals(Direction.SOUTH, Direction.getDirection(angle));
  }

  @Test
  public void givenAnAngleIsRelativeWest_getDirection_shouldReturnWest() {
    int angle = 270;
    assertEquals(Direction.WEST, Direction.getDirection(angle));
  }

  @Test(expected = RuntimeException.class)
  public void givenAnAngleDoesNotMapToADirection_getDirection_shouldThrowARuntimeError() {
    int angle = 5;
    Direction.getDirection(angle);
  }

  @Test
  public void givenAnAngleIsGreaterThanOrEqual360_getDirection_shouldReturnTheCorrectDirection() {
    int angle = 360;
    assertEquals(Direction.NORTH, Direction.getDirection(angle));
  }

  @Test
  public void givenAnAngleMapsToADirectionAndIsNegative_getDirection_shouldReturnTheCorrectDirection() {
    int angle = -90;
    assertEquals(Direction.WEST, Direction.getDirection(angle));
  }
}