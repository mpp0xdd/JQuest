package jquest.spec.map;

import jquest.spec.chip.ChipCoordinate;
import jquest.spec.chip.ChipImage;

class MapChipImpl extends RpgMapConcernChipBase implements MapChip {

  public MapChipImpl(ChipImage image, RpgMap rpgMap, ChipCoordinate coordinate) {
    super(image, rpgMap, coordinate);
  }
}
