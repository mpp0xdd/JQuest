package jquest.spec.chara;

import jquest.spec.map.RpgMap;

final class Hero extends RpgCharaBase {

  public Hero(RpgMap rpgMap) {
    super("image/hero.gif", rpgMap, rpgMap.startCoordinate());
  }
}
