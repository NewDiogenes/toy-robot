package toyrobot.model;

import java.util.Optional;
import java.util.function.Function;

public interface Command extends Function<String, Optional<RobotOperation>> {

  static Command make(InputValidator inputValidator, InputParser inputParser){
    return in -> Optional.ofNullable(in)
        .filter(inputValidator)
        .map(inputParser);
  }
}
