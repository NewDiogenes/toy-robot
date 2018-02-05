package toyrobot;

import toyrobot.model.ToyRobot;
import toyrobot.service.CommandService;
import toyrobot.util.InputReader;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;


public class Application {

  private InputReader inputReader;
  private CommandService commandService;

  public Application(InputReader readerFactory, CommandService commandService) {
    this.inputReader = readerFactory;
    this.commandService = commandService;
  }

  public void start(String[] args) {
    Stream<String> inputStream = Stream.empty();
    try {
      inputStream = inputReader.readFile(args);
    } catch (IOException e) {
      e.printStackTrace();
    }
    inputStream.map(commandService.menu())
        .filter(Optional::isPresent)
        .map(Optional::get)
        .reduce(Consumer::andThen)
        .ifPresent(con -> con.accept(new ToyRobot()));
  }
}
