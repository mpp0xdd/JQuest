package jquest.spec.message;

public interface RpgMessageLine {

  RpgMessageLine NULL = of("");

  public static RpgMessageLine of(String line) {
    return new RpgMessageLineImpl(line);
  }

  boolean isEmpty();

  @Override
  int hashCode();

  @Override
  boolean equals(Object obj);

  @Override
  String toString();
}
