package jquest.spec.chip;

import java.util.Objects;
import jquest.common.Coordinate;

class ChipLocationImpl implements ChipLocation {

  private final ChipCoordinate chipCoordinate;
  private final Coordinate coordinate;

  public ChipLocationImpl(ChipCoordinate chipCoordinate, Coordinate coordinate) {
    this.chipCoordinate = Objects.requireNonNull(chipCoordinate);
    this.coordinate = Objects.requireNonNull(coordinate);
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
