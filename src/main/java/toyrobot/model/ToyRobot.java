package toyrobot.model;

public class ToyRobot {
  private int xposition;
  private int yposition;
  private Direction facing;

  public int getXposition() {
    return xposition;
  }

  public void setXposition(int xposition) {
    this.xposition = xposition;
  }

  public int getYposition() {
    return yposition;
  }

  public void setYposition(int yposition) {
    this.yposition = yposition;
  }

  public Direction getFacing() {
    return facing;
  }

  public void setFacing(Direction facing) {
    this.facing = facing;
  }
}
