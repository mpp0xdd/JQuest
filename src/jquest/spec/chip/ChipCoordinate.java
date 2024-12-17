package jquest.spec.chip;

public interface ChipCoordinate {

  @FunctionalInterface
  interface UnaryOperator extends java.util.function.UnaryOperator<ChipCoordinate> {
    @Override
    ChipCoordinate apply(ChipCoordinate t);
  }

  public static ChipCoordinate at(int x, int y) {
    return new ChipCoordinateImpl(x, y);
  }

  int x();

  int y();

  default ChipCoordinate up() {
    return at(x(), y() - 1);
  }

  default ChipCoordinate down() {
    return at(x(), y() + 1);
  }

  default ChipCoordinate left() {
    return at(x() - 1, y());
  }

  default ChipCoordinate right() {
    return at(x() + 1, y());
  }

  @Override
  int hashCode();

  @Override
  boolean equals(Object obj);
}
