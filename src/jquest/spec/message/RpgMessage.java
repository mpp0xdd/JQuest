package jquest.spec.message;

import java.util.List;

public interface RpgMessage {

  public static RpgMessage of(String... lines) {
    return new RpgMessageImpl(lines);
  }

  List<String> lines();
}
