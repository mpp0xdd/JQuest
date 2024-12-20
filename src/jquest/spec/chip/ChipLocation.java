package jquest.spec.chip;

import java.util.function.UnaryOperator;
import jquest.common.Coordinate;

public interface ChipLocation {

  public static ChipLocation from(ChipCoordinate chipCoordinate) {
    return new ChipLocationImpl(chipCoordinate);
  }

  public static ChipLocation from(Coordinate coordinate) {
    return new ChipLocationImpl(coordinate);
  }

  ChipCoordinate chipCoordinate();

  Coordinate coordinate();

  ChipLocation computeFromChipCoordinate(UnaryOperator<ChipCoordinate> operator);

  ChipLocation computeFromCoordinate(UnaryOperator<Coordinate> operator);
}
