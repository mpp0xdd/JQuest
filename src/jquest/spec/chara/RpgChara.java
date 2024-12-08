package jquest.spec.chara;

import jquest.spec.action.move.DownMovable;
import jquest.spec.action.move.LeftMovable;
import jquest.spec.action.move.RightMovable;
import jquest.spec.action.move.UpMovable;
import jquest.spec.chip.Chip;
import jquest.spec.map.RpgMap;
import jquest.spec.map.RpgMapConcern;

public interface RpgChara
    extends RpgMapConcern, Chip, UpMovable, DownMovable, LeftMovable, RightMovable {

  public static RpgChara mainCharaOf(RpgMap rpgMap) {
    return new Hero(rpgMap);
  }
}
