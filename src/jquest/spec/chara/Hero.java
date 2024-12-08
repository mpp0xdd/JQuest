package jquest.spec.chara;

import jquest.spec.chip.ChipCoordinate;
import jquest.spec.chip.ChipImage;
import jquest.spec.map.RpgMap;

final class Hero extends RpgCharaBase {

  public Hero(ChipImage image, RpgMap rpgMap, ChipCoordinate coordinate) {
    super(image, rpgMap, coordinate);
  }
}
