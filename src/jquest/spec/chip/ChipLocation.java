package jquest.spec.chip;

import jquest.common.Coordinate;

public interface ChipLocation {

  public static ChipLocation from(ChipCoordinate chipCoordinate, int chipLength) {
    return new ChipLocationImpl(chipCoordinate, chipLength);
  }

  public static ChipLocation from(Coordinate coordinate, int chipLength) {
    return new ChipLocationImpl(coordinate, chipLength);
  }

  ChipCoordinate chipCoordinate();

  Coordinate coordinate();

  ChipLocation computeFromChipCoordinate(ChipCoordinate.UnaryOperator operator);

  ChipLocation computeFromCoordinate(Coordinate.UnaryOperator operator);
}
