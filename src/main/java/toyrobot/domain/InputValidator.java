package toyrobot.domain;


import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

public interface InputValidator extends Predicate<String> {

  static InputValidator place() {
    return in -> Optional.ofNullable(in)
        .map(i -> i.split(" "))
        .filter(i -> i.length >= 4)
        .filter(i -> i[0].equals("place"))
        .filter(i -> StringUtils.isNumeric(i[1]))
        .filter(i -> StringUtils.isNumeric(i[2]))
        .filter(i -> Arrays.asList(new String[]{"north", "south", "east", "west"})
            .contains(i[3].toLowerCase()))
        .isPresent();
  }

  static InputValidator right() {
    return matchString("right");
  }

  static InputValidator left() {
    return matchString("left");
  }

  static InputValidator move() {
    return matchString("move");
  }

  static InputValidator matchString(String match) {
    return in -> Optional.ofNullable(in)
        .filter(i -> i.equals(match))
        .isPresent();
  }
}
