package jquest.spec.map;

import jquest.spec.chip.ChipCoordinate;

class MapChipImpl extends RpgMapConcernChipBase implements MapChip {

  public MapChipImpl(String name, RpgMap rpgMap, ChipCoordinate coordinate) {
    super(name, rpgMap, coordinate);
  }
}
