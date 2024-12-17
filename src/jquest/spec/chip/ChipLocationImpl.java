package jquest.spec.chip;

import jquest.common.Coordinate;

class ChipLocationImpl implements ChipLocation {

  private static Coordinate toCoordinate(ChipCoordinate chipCoordinate, int chipLength) {
    return Coordinate.at(chipCoordinate.x() * chipLength, chipCoordinate.y() * chipLength);
  }

  private static ChipCoordinate toChipCoordinate(Coordinate coordinate, int chipLength) {
    return ChipCoordinate.at(coordinate.x() / chipLength, coordinate.y() / chipLength);
  }

  private final int chipLength;
  private final ChipCoordinate chipCoordinate;
  private final Coordinate coordinate;

  public ChipLocationImpl(ChipCoordinate chipCoordinate, int chipLength) {
    this.chipCoordinate = chipCoordinate;
    this.coordinate = toCoordinate(chipCoordinate, chipLength);
    this.chipLength = chipLength;
  }

  public ChipLocationImpl(Coordinate coordinate, int chipLength) {
    this.coordinate = coordinate;
    this.chipCoordinate = toChipCoordinate(coordinate, chipLength);
    this.chipLength = chipLength;
  }

  @Override
  public ChipCoordinate chipCoordinate() {
    return chipCoordinate;
  }

  @Override
  public Coordinate coordinate() {
    return coordinate;
  }

  @Override
  public ChipLocation computeFrom(ChipCoordinate.UnaryOperator operator) {
    return new ChipLocationImpl(operator.apply(chipCoordinate), chipLength);
  }

  @Override
  public ChipLocation computeFrom(Coordinate.UnaryOperator operator) {
    return new ChipLocationImpl(operator.apply(coordinate), chipLength);
  }
}
