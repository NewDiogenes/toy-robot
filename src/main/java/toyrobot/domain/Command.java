package toyrobot.domain;

import toyrobot.model.ToyRobot;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Command extends Function<String, Optional<Consumer<ToyRobot>>> {

  static Command make(InputValidator inputValidator, InputParser inputParser) {
    return in -> Optional.ofNullable(in)
        .filter(inputValidator)
        .flatMap(inputParser);
  }
}
