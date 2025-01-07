package jquest.spec.message;

import java.util.List;

public interface RpgMessageLine {

  RpgMessageLine NULL = of("");

  public static RpgMessageLine of(String line) {
    return new RpgMessageLineImpl(line);
  }

  boolean isEmpty();

  List<String> graphemeClusters();

  @Override
  int hashCode();

  @Override
  boolean equals(Object obj);

  @Override
  String toString();
}
