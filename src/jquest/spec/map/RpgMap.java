package jquest.spec.map;

import jglib.util.spec.Drawable;
import jglib.util.spec.Rectangular;
import jquest.spec.chara.RpgChara;
import jquest.spec.chip.ChipCoordinate;

public interface RpgMap extends Drawable, Rectangular {

  public static RpgMap castle() {
    return new Castle();
  }

  int rows();

  int columns();

  RpgChara getMainChara();

  void setMainChara(RpgChara chara);

  ChipCoordinate startCoordinate();

  boolean isBlockedOff(ChipCoordinate coordinate);
}
