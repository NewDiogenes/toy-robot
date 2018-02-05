package toyrobot.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class InputReader {

  public Stream<String> readFile(String[] args) throws IOException {
    return Files.lines(Optional.ofNullable(args)
        .filter(ar -> ar.length > 0)
        .map(ar -> ar[0])
        .map(Paths::get)
        .orElseThrow(() -> new IllegalArgumentException("Unable to read file, no file name was given")));
  }
}
