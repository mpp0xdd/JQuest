package jquest.spec.map;

import jquest.spec.chip.ChipCoordinate;

final class Castle extends RpgMapBase {

  private int[][] mapData;

  @Override
  public int rows() {
    return mapData.length;
  }

  @Override
  public int columns() {
    return mapData[0].length;
  }

  @Override
  public ChipCoordinate startCoordinate() {
    return ChipCoordinate.at(1, 1);
  }

  @Override
  public boolean isBlockedOff(ChipCoordinate coordinate) {
    return mapData[coordinate.y()][coordinate.x()] == 1;
  }

  @Override
  protected void initialize() {
    mapData = loadMapData();
    mapChips.clear();
    for (int y = 0; y < rows(); y++) {
      for (int x = 0; x < columns(); x++) {
        ChipCoordinate coordinate = ChipCoordinate.at(x, y);
        mapChips.put(coordinate, MapChip.create(toName(mapData[y][x]), this, coordinate));
      }
    }
  }

  private int[][] loadMapData() {
    return new int[][] {
      {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
      {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
      {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
      {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
      {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1},
      {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
      {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
      {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
      {1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
      {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
      {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
      {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
      {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1},
      {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
      {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
      {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
      {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };
  }

  private String toName(int number) {
    return switch (number) {
      case 0 -> "image/floor.gif";
      case 1 -> "image/wall.gif";
      default -> throw new IllegalArgumentException("Unexpected value: " + number);
    };
  }
}
