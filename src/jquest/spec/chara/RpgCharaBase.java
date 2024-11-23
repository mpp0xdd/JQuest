package jquest.spec.chara;

import jquest.spec.chip.ChipCoordinate;
import jquest.spec.map.RpgMap;
import jquest.spec.map.RpgMapConcernChipBase;

abstract class RpgCharaBase extends RpgMapConcernChipBase implements RpgChara {

  public RpgCharaBase(String name, RpgMap rpgMap, ChipCoordinate coordinate) {
    super(name, rpgMap, coordinate);
  }
}
