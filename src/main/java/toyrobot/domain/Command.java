package toyrobot.domain;

import toyrobot.model.ToyRobot;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Command extends Function<String, Optional<Consumer<ToyRobot>>> {

  static Command parsePredicateCommand(InputValidator inputValidator, InputParser inputParser) {
    return in -> Optional.ofNullable(in)
        .filter(inputValidator)
        .flatMap(inputParser);
  }

  static Command predicateCommand(InputValidator inputValidator, Consumer<ToyRobot> operation) {
    return in -> Optional.ofNullable(in)
        .filter(inputValidator)
        .map(i -> operation);
  }

  static Command commandMenu(Command... commands) {
    return in -> Arrays.stream(commands)
        .map(cmd -> cmd.apply(in))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .findAny();
  }
}
