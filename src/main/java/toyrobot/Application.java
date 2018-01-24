package toyrobot;

import java.io.IOException;
import toyrobot.util.InputReader;


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
