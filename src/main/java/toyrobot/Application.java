package toyrobot;

import java.io.IOException;
import java.util.stream.Stream;

import toyrobot.domain.Command;
import toyrobot.domain.InputParser;
import toyrobot.domain.InputValidator;
import toyrobot.service.CommandService;
import toyrobot.service.OperationService;
import toyrobot.util.InputReader;


public class Application {

  private InputReader inputReader;
  private CommandService commandService;
  private OperationService operationService;

  public Application(InputReader readerFactory, CommandService commandService, OperationService operationService) {
    this.inputReader = readerFactory;
    this.commandService = commandService;
    this.operationService = operationService;
  }

  public void start(String[] args) {
    commandService.addCommand("place", placeCommand());
    Stream<String> inputStream = Stream.empty();
    try {
      inputStream = inputReader.readFile(args);
    } catch (IOException e) {
      e.printStackTrace();
    }
    inputStream.forEach(commandService::getCommand);
  }

  private Command placeCommand() {
    return Command.make(InputValidator.place(), InputParser.place(operationService));
  }
}
