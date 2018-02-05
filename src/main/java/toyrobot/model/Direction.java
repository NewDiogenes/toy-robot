package toyrobot.model;

import java.util.Optional;

public enum Direction {
  NORTH(0),
  EAST(90),
  SOUTH(180),
  WEST(270);

  private int angle;
  public static final Integer ROTATION_ANGLE = 90;
  private static final int FULL_CIRCLE = 360;

  Direction(int direction) {
    this.angle = direction;
  }

  public int getAngle() {
    return angle;
  }

  public static Direction getDirection(int facing) {
    return Optional.of(facing)
        .map(f -> f + FULL_CIRCLE)
        .map(f -> f % FULL_CIRCLE)
        .filter(f -> f % ROTATION_ANGLE == 0)
        .map(f -> f / ROTATION_ANGLE)
        .map(f -> Direction.values()[f])
        .orElseThrow(() -> new IllegalArgumentException("Could not map the angle to a direction."));
  }

  public static Integer rotateRight(Integer angle) {
    return Optional.ofNullable(angle)
        .map(f -> f + ROTATION_ANGLE)
        .orElseThrow(() -> new IllegalArgumentException("Angle must not be null"));
  }

  public static Integer rotateLeft(Integer angle) {
    return Optional.ofNullable(angle)
        .map(f -> f - ROTATION_ANGLE)
        .orElseThrow(() -> new IllegalArgumentException("Angle must not be null"));
  }
}
