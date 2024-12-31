package jquest.spec.message;

import java.util.List;

public interface RpgMessage {

  RpgMessage NULL = new NullRpgMessage();

  public static RpgMessage of(String... lines) {
    return new RpgMessageImpl(lines);
  }

  List<String> lines();

  boolean isEmpty();

  @Override
  int hashCode();

  @Override
  boolean equals(Object obj);
}
