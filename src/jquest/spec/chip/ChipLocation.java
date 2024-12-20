package jquest.spec.chip;

import java.awt.Rectangle;
import java.util.Collections;
import java.util.HashSet;
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
    if (overlapExactlyWithOtherChips()) {
      return Collections.emptySet();
    }

    Rectangle thisChipRect =
        new Rectangle(coordinate().x(), coordinate().y(), Chip.LENGTH, Chip.LENGTH);

    ChipLocation upperLeftChipLocation = ChipLocation.from(chipCoordinate());
    Rectangle upperLeftChipRect =
        new Rectangle(
            upperLeftChipLocation.coordinate().x(),
            upperLeftChipLocation.coordinate().y(),
            Chip.LENGTH,
            Chip.LENGTH);

    Rectangle boundRect = thisChipRect.union(upperLeftChipRect);
    int minX = (int) boundRect.getMinX();
    int minY = (int) boundRect.getMinY();
    int maxX = (int) boundRect.getMaxX();
    int maxY = (int) boundRect.getMaxY();

    Set<ChipCoordinate> result = new HashSet<>();
    result.add(toChipCoordinate(Coordinate.at(minX, minY)));
    result.add(toChipCoordinate(Coordinate.at(minX, maxY)));
    result.add(toChipCoordinate(Coordinate.at(maxX, minY)));
    result.add(toChipCoordinate(Coordinate.at(maxX, maxY)));
    return result;
  }

  default ChipLocation computeFromChipCoordinate(UnaryOperator<ChipCoordinate> operator) {
    return from(operator.apply(chipCoordinate()));
  }

  default ChipLocation computeFromCoordinate(UnaryOperator<Coordinate> operator) {
    return from(operator.apply(coordinate()));
  }
}
