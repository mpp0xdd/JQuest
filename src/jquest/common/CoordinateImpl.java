package jquest.common;

import java.util.Objects;

class CoordinateImpl implements Coordinate {

  private final int x;
  private final int y;

  public CoordinateImpl(int x, int y) {
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
    CoordinateImpl other = (CoordinateImpl) obj;
    return x == other.x && y == other.y;
  }
}
