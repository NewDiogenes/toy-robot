package toyrobot.model;

import java.util.function.Function;
import java.util.function.Predicate;

public interface InputParser extends Function<String, RobotOperation> {
}
