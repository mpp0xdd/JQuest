package jquest.spec.map;

import jquest.spec.chip.Chip;
import jquest.spec.chip.ChipCoordinate;

public interface MapChip extends RpgMapConcern, Chip {

  public static MapChip create(String name, RpgMap rpgMap, ChipCoordinate coordinate) {
    return new MapChipImpl(name, rpgMap, coordinate);
  }
}
