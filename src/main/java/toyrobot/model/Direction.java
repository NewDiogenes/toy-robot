package toyrobot.model;

public enum Direction {
  NORTH(0),
  EAST(90),
  SOUTH(180),
  WEST(270);

  private int angle;

  Direction(int direction) {
    this.angle = direction;
  }

  public int getAngle() {
    return angle;
  }
}
