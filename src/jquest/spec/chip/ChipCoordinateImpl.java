package jquest.spec.chip;

import java.util.Objects;

final class ChipCoordinateImpl implements ChipCoordinate {

  private final int x, y;

  public ChipCoordinateImpl(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int x() {
    return x;
  }

  @Override
  public int y() {
    return y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
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
    ChipCoordinateImpl other = (ChipCoordinateImpl) obj;
    return x == other.x && y == other.y;
  }
}
