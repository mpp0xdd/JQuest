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

  @Override
  public int hashCode() {
    return Objects.hash(chipCoordinate, coordinate);
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
    ChipLocationImpl other = (ChipLocationImpl) obj;
    return Objects.equals(chipCoordinate, other.chipCoordinate)
        && Objects.equals(coordinate, other.coordinate);
  }

  @Override
  public String toString() {
    return "ChipLocation [chipCoordinate=" + chipCoordinate + ", coordinate=" + coordinate + "]";
  }
}
