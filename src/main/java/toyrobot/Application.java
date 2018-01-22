package toyrobot;

import toyrobot.util.InputReader;

import java.io.IOException;
import java.util.Optional;

public class Application {

  private InputReader inputReader;

  public Application(InputReader readerFactory) {
    this.inputReader = readerFactory;
  }

  public void start(String[] args) {
    try {
      inputReader.readFile(args);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
