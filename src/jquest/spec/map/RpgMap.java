package jquest.spec.map;

import jglib.util.spec.Cellular;
import jglib.util.spec.Drawable;
import jglib.util.spec.Rectangular;
import jquest.spec.chara.RpgChara;
import jquest.spec.chip.ChipCoordinate;

public interface RpgMap extends Cellular, Rectangular, Drawable {

  public static RpgMap castle() {
    return new Castle();
  }

  RpgChara mainChara();

  void setMainChara(RpgChara chara);

  ChipCoordinate startCoordinate();

  boolean isBlockedOff(ChipCoordinate coordinate);
}
