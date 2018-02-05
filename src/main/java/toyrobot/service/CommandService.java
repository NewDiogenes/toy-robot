package toyrobot.service;

import toyrobot.domain.Command;
import toyrobot.domain.InputParser;
import toyrobot.domain.InputValidator;

public class CommandService {
  private OperationService operationService;

  public CommandService(OperationService operationService) {
    this.operationService = operationService;
  }

  public Command menu() {
    return Command.commandMenu(
        place(),
        left(),
        right(),
        move(),
        report());
  }

  private Command place() {
    return Command.parsePredicateCommand(InputValidator.place(), InputParser.place(operationService));
  }

  private Command left() {
    return Command.predicateCommand(InputValidator.left(), operationService.left());
  }

  private Command right() {
    return Command.predicateCommand(InputValidator.right(), operationService.right());
  }

  private Command move() {
    return Command.predicateCommand(InputValidator.move(), operationService.move());
  }

  private Command report() {
    return Command.predicateCommand(InputValidator.report(), operationService.report());
  }


}
