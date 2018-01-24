package toyrobot.service;

import toyrobot.model.Direction;
import toyrobot.model.ToyRobot;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;

import static org.apache.commons.lang3.math.NumberUtils.min;

public class OperationService {

  private int tableSize;
  private static final int TURN_ANGLE = 90;

  public OperationService(int tableSize) {

    this.tableSize = tableSize;
  }

  public Consumer<ToyRobot> place(int xposition, int yposition, Direction facing) {
    return tr -> allOf(
        setXposition(xposition),
        setYposition(yposition),
        setFacing(facing))
        .ifPresent(op -> op.accept(tr));
  }

  private Consumer<ToyRobot> setXposition(int xposition) {
    return tr -> Optional.ofNullable(tr)
        .ifPresent(t -> t.setXposition(min(xposition, tableSize)));
  }

  private Consumer<ToyRobot> setYposition(int yposition) {
    return tr -> Optional.ofNullable(tr)
        .ifPresent(t -> t.setYposition(min(yposition, tableSize)));
  }

  private Consumer<ToyRobot> setFacing(Direction facing) {
    return tr -> Optional.ofNullable(tr)
        .ifPresent(t -> t.setFacing(facing.getAngle()));
  }

  private Optional<Consumer<ToyRobot>> allOf(Consumer<ToyRobot>... operations) {
    return Arrays.stream(operations).reduce(Consumer::andThen);
  }

  public Consumer<ToyRobot> right() {
    return rotate(TURN_ANGLE);
  }

  public Consumer<ToyRobot> left() {
    return rotate(-TURN_ANGLE);
  }

  public Consumer<ToyRobot> rotate(int angle) {
    return tr -> Optional.ofNullable(tr)
        .map(ToyRobot::getFacing)
        .map(x -> x += angle)
        .ifPresent(tr::setFacing);
  }
}
