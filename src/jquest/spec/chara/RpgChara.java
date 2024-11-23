package jquest.spec.chara;

import jquest.spec.chip.Chip;
import jquest.spec.map.RpgMap;
import jquest.spec.map.RpgMapConcern;

public interface RpgChara extends RpgMapConcern, Chip {

  public static RpgChara mainCharaOf(RpgMap rpgMap) {
    return new Hero(rpgMap);
  }
}
