package toyrobot.domain;

import toyrobot.service.OperationService;

import java.util.Optional;
import java.util.function.Function;

public interface InputParser extends Function<String, Optional<RobotOperation>> {

  static InputParser place(OperationService operationService) {
    return in -> Optional.ofNullable(in)
        .map(i -> i.split(" "))
        .map(i -> operationService.place(Integer.parseInt(i[1]), Integer.parseInt(i[2]), i[3]));
  }
}
