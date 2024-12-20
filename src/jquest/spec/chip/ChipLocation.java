package jquest.spec.chip;

import java.util.Set;
import java.util.function.UnaryOperator;
import jquest.common.Coordinate;

public interface ChipLocation {

  public static ChipLocation from(ChipCoordinate chipCoordinate) {
    return new ChipLocationImpl(chipCoordinate, toCoordinate(chipCoordinate));
  }

  public static ChipLocation from(Coordinate coordinate) {
    return new ChipLocationImpl(toChipCoordinate(coordinate), coordinate);
  }

  private static Coordinate toCoordinate(ChipCoordinate chipCoordinate) {
    return Coordinate.at(chipCoordinate.x() * Chip.LENGTH, chipCoordinate.y() * Chip.LENGTH);
  }

  private static ChipCoordinate toChipCoordinate(Coordinate coordinate) {
    return ChipCoordinate.at(coordinate.x() / Chip.LENGTH, coordinate.y() / Chip.LENGTH);
  }

  ChipCoordinate chipCoordinate();

  Coordinate coordinate();

  default boolean overlapExactlyWithOtherChips() {
    return coordinate().x() % Chip.LENGTH == 0 && coordinate().y() % Chip.LENGTH == 0;
  }

  default Set<ChipCoordinate> overlappedChipCoordinates() {
    throw new UnsupportedOperationException();
  }

  default ChipLocation computeFromChipCoordinate(UnaryOperator<ChipCoordinate> operator) {
    return from(operator.apply(chipCoordinate()));
  }

  default ChipLocation computeFromCoordinate(UnaryOperator<Coordinate> operator) {
    return from(operator.apply(coordinate()));
  }
}
