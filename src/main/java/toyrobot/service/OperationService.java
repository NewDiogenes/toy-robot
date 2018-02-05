package toyrobot.service;

import org.apache.commons.lang3.math.NumberUtils;
import toyrobot.model.Direction;
import toyrobot.model.ToyRobot;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class OperationService {

  private Integer tableSize;

  public OperationService(Integer tableSize) {

    this.tableSize = tableSize;
  }

  public Consumer<ToyRobot> place(Integer xposition, Integer yposition, Direction facing) {
    return tr -> allOf(
        setXposition(xposition),
        setYposition(yposition),
        setFacing(facing))
        .ifPresent(op -> op.accept(tr));
  }

  public Consumer<ToyRobot> right() {
    return rotate(Direction::rotateRight);
  }

  public Consumer<ToyRobot> left() {
    return rotate(Direction::rotateLeft);
  }

  public Consumer<ToyRobot> move() {
    return tr -> Optional.ofNullable(tr)
        .filter(this::checkIfInitialized)
        .map(ToyRobot::getFacing)
        .map(Math::toRadians)
        .flatMap(r -> allOf(moveX(r), moveY(r)))
        .ifPresent(f -> f.accept(tr));
  }

  public Consumer<ToyRobot> report() {
    return tr -> Optional.ofNullable(tr)
        .map(t -> String.format("%d, %d, %s",
            t.getXposition(), t.getYposition(), Direction.getDirection(t.getFacing())))
        .ifPresent(System.out::println);
  }

  private Consumer<ToyRobot> setXposition(Integer xposition) {
    return tr -> Optional.ofNullable(tr)
        .ifPresent(t -> t.setXposition(NumberUtils.min(xposition, tableSize)));
  }

  private Consumer<ToyRobot> setYposition(Integer yposition) {
    return tr -> Optional.ofNullable(tr)
        .ifPresent(t -> t.setYposition(NumberUtils.min(yposition, tableSize)));
  }

  private Consumer<ToyRobot> setFacing(Direction facing) {
    return tr -> Optional.ofNullable(tr)
        .ifPresent(t -> t.setFacing(facing.getAngle()));
  }

  private Optional<Consumer<ToyRobot>> allOf(Consumer<ToyRobot>... operations) {
    return Arrays.stream(operations).reduce(Consumer::andThen);
  }

  private Consumer<ToyRobot> rotate(Function<Integer, Integer> rotatorFunction) {
    return tr -> Optional.ofNullable(tr)
        .map(ToyRobot::getFacing)
        .map(rotatorFunction)
        .ifPresent(tr::setFacing);
  }

  private Consumer<ToyRobot> moveX(Double radians) {
    return tr -> Optional.ofNullable(tr)
        .map(ToyRobot::getXposition)
        .map(x -> x += (int) Math.sin(radians))
        .flatMap(this::trimPosition)
        .ifPresent(tr::setXposition);
  }

  private Consumer<ToyRobot> moveY(Double radians) {
    return tr -> Optional.ofNullable(tr)
        .map(ToyRobot::getYposition)
        .map(y -> y += (int) Math.cos(radians))
        .flatMap(this::trimPosition)
        .ifPresent(tr::setYposition);
  }

  private Optional<Integer> trimPosition(Integer position) {
    return Optional.of(position)
        .map(p -> Math.max(p, 0))
        .map(p -> Math.min(p, tableSize));
  }

  private boolean checkIfInitialized(ToyRobot toyRobot) {
    return Optional.ofNullable(toyRobot)
        .filter(tr -> Objects.nonNull(tr.getXposition()))
        .filter(tr -> Objects.nonNull(tr.getYposition()))
        .filter(tr -> Objects.nonNull(tr.getFacing()))
        .isPresent();
  }
}
