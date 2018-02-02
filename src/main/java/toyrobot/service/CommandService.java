package toyrobot.service;

import toyrobot.domain.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class CommandService {
  private Map<String, Command> commandList;

  public CommandService() {
    this.commandList = new HashMap<>();
  }

  public Optional<Command> getCommand(String command) {
    return Optional.ofNullable(command)
        .map(cmd -> cmd.split(" "))
        .map(cmd -> cmd[0])
        .map(cmd -> commandList.get(cmd));
  }

  public void addCommand(String commandName, Command command) {
    Optional.ofNullable(commandName)
        .filter(cmd -> Objects.nonNull(command))
        .filter(commandList::containsKey)
        .map(cmd -> commandList.put(cmd, command))
        .orElseThrow(() -> new IllegalArgumentException("Unable able to add new command"));
  }

  public Map<String, Command> getCommandList() {
    return commandList;
  }

  public void setCommandList(Map<String, Command> commandList) {
    this.commandList = commandList;
  }
}
