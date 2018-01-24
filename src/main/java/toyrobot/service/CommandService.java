package toyrobot.service;

import toyrobot.domain.Command;

import java.util.Map;
import java.util.Optional;

public class CommandService {
  private Map<String, Command> commands;

  public CommandService(Map<String, Command> commands) {
    this.commands = commands;
  }

  public Optional<Command> getCommand(String command) {
    return Optional.ofNullable(command)
        .map(cmd -> cmd.split(" "))
        .map(cmd -> cmd[0])
        .map(cmd -> commands.get(cmd));
  }
}
