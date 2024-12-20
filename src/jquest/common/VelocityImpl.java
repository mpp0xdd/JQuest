package jquest.common;

import java.util.Objects;

class VelocityImpl implements Velocity {

  private final int x;
  private final int y;

  public VelocityImpl(int x, int y) {
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
    VelocityImpl other = (VelocityImpl) obj;
    return x == other.x && y == other.y;
  }

  @Override
  public String toString() {
    return "Velocity [x=" + x + ", y=" + y + "]";
  }
}
