package toyrobot;

import toyrobot.util.ReaderFactory;

import java.util.Optional;

public class Application {

  private ReaderFactory readerFactory;

  public Application(ReaderFactory readerFactory) {
    this.readerFactory = readerFactory;
  }

  public void start(String[] args) {
    Optional.ofNullable(args).map(readerFactory::getReader);
  }
}
