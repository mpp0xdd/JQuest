package jquest.spec.command;

public interface RpgCommand {

  void execute();

  void undo();

  void redo();
}
