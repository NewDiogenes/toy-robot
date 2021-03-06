package toyrobot;

import toyrobot.service.CommandService;
import toyrobot.service.OperationService;
import toyrobot.util.InputReader;

public class Main {

  private static final Integer TABLE_SIZE = 5;

  public static void main(String[] args) {
    OperationService operationService = new OperationService(TABLE_SIZE);
    CommandService commandService = new CommandService(operationService);
    InputReader inputReader = new InputReader();
    Application application = new Application(inputReader, commandService);

    application.start(args);
  }
}
