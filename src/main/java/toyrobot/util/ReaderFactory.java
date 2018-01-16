package toyrobot.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Optional;

public class ReaderFactory {

  public static Reader getReader(String[] args) {
    return Optional.ofNullable(args)
        .filter(ar -> ar.length > 0)
        .map(ar -> ar[0])
        .map(ar -> {
          try {
            return (Reader) new FileReader(ar);
          } catch (FileNotFoundException e) {
            return null;
          }
        })
        .orElse(new InputStreamReader(System.in));
  }
}
