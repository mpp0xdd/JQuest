package jquest.spec.chip;

import jquest.common.Coordinate;

class ChipLocationImpl implements ChipLocation {

  private static Coordinate toCoordinate(ChipCoordinate chipCoordinate) {
    return Coordinate.at(chipCoordinate.x() * Chip.LENGTH, chipCoordinate.y() * Chip.LENGTH);
  }

  private static ChipCoordinate toChipCoordinate(Coordinate coordinate) {
    return ChipCoordinate.at(coordinate.x() / Chip.LENGTH, coordinate.y() / Chip.LENGTH);
  }

  private final ChipCoordinate chipCoordinate;
  private final Coordinate coordinate;

  public ChipLocationImpl(ChipCoordinate chipCoordinate) {
    this.chipCoordinate = chipCoordinate;
    this.coordinate = toCoordinate(chipCoordinate);
  }

  public ChipLocationImpl(Coordinate coordinate) {
    this.coordinate = coordinate;
    this.chipCoordinate = toChipCoordinate(coordinate);
  }

  @Override
  public ChipCoordinate chipCoordinate() {
    return chipCoordinate;
  }

  @Override
  public Coordinate coordinate() {
    return coordinate;
  }
}
