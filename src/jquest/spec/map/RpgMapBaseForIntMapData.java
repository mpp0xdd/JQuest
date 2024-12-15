package jquest.spec.map;

import jquest.spec.chip.ChipCoordinate;

abstract class RpgMapBaseForIntMapData extends RpgMapBase {

  abstract class ViewportBaseForIntMapData extends RpgMapBase.ViewportBase {}

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

  protected int mapData(int x, int y) {
    return mapData[y][x];
  }

  protected abstract int[][] loadMapData();

  protected abstract String toName(int mapData);
}
