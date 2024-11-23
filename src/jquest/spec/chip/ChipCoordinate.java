package jquest.spec.chip;

public interface ChipCoordinate {

  public static ChipCoordinate at(int x, int y) {
    return new ChipCoordinateImpl(x, y);
  }

  int x();

  int y();

  @Override
  int hashCode();

  @Override
  boolean equals(Object obj);
}
