package jquest.spec.message;

public interface RpgMessageLine {

  RpgMessageLine NULL = of("");

  public static RpgMessageLine of(String line) {
    return new RpgMessageLineImpl(line);
  }

  String line();

  boolean isEmpty();
}
