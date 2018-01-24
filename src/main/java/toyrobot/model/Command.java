package toyrobot.model;

import java.util.function.Function;

public interface Command extends Function<String, RobotOperation> {

}
