package jquest.common;

public interface Coordinate {

  @FunctionalInterface
  interface UnaryOperator extends java.util.function.UnaryOperator<Coordinate> {
    @Override
    Coordinate apply(Coordinate t);
  }

  Coordinate ORIGIN = at(0, 0);

  public static Coordinate at(int x, int y) {
    return new CoordinateImpl(x, y);
  }

  int x();

  int y();

  default Coordinate plus(Velocity velocity) {
    return at(x() + velocity.x(), y() + velocity.y());
  }

  default boolean isOrigin() {
    return this.equals(ORIGIN);
  }

  @Override
  int hashCode();

  @Override
  boolean equals(Object obj);
}
