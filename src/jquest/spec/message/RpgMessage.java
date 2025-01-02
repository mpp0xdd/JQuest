package jquest.spec.message;

import java.util.List;

public interface RpgMessage {

  RpgMessage NULL = new NullRpgMessage();

  public static RpgMessage of(RpgMessageLine... lines) {
    if (lines.length == 0) {
      return NULL;
    }

    return new RpgMessageImpl(lines);
  }

  public static RpgMessage of(String... lines) {
    RpgMessageLine[] rmlines = new RpgMessageLine[lines.length];
    for (int i = 0; i < rmlines.length; i++) {
      rmlines[i] = RpgMessageLine.of(lines[i]);
    }

    return new RpgMessageImpl(rmlines);
  }

  List<RpgMessageLine> lines();

  boolean isEmpty();

  @Override
  int hashCode();

  @Override
  boolean equals(Object obj);
}
