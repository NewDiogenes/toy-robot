package toyrobot.domain;

import toyrobot.model.Direction;
import toyrobot.model.ToyRobot;
import toyrobot.service.OperationService;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public interface InputParser extends Function<String, Optional<Consumer<ToyRobot>>> {

  static InputParser place(OperationService operationService) {
    return in -> Optional.ofNullable(in)
        .map(i -> i.split(" "))
        .map(i -> operationService.place(Integer.parseInt(i[1]), Integer.parseInt(i[2]), parseDirection(i[3])));
  }

  static Direction parseDirection(String input) {
    switch (input.toLowerCase()) {
      case "north":
        return Direction.NORTH;
      case "east":
        return Direction.EAST;
      case "south":
        return Direction.SOUTH;
      case "west":
        return Direction.WEST;
      default:
        return null;
    }
  }
}
