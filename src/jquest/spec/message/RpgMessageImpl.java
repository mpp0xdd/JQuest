package jquest.spec.message;

import java.util.List;
import java.util.Objects;

class RpgMessageImpl implements RpgMessage {

  private final List<RpgMessageLine> lines;

  public RpgMessageImpl(RpgMessageLine... lines) {
    this.lines = List.of(lines);
  }

  @Override
  public List<RpgMessageLine> lines() {
    return lines;
  }

  @Override
  public boolean isEmpty() {
    return lines().isEmpty() || lines().stream().allMatch(RpgMessageLine::isEmpty);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lines);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    RpgMessageImpl other = (RpgMessageImpl) obj;
    return Objects.equals(lines, other.lines);
  }
}
