package jquest.common;

public interface Coordinate {

  Coordinate ZERO = at(0, 0);

  public static Coordinate at(int x, int y) {
    return new CoordinateImpl(x, y);
  }

  int x();

  int y();

  default Coordinate plus(Velocity velocity) {
    return at(x() + velocity.x(), y() + velocity.y());
  }

  default boolean isZero() {
    return this.equals(ZERO);
  }

  @Override
  int hashCode();

  @Override
  boolean equals(Object obj);

  @Override
  String toString();
}
