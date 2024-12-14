package jquest.spec.map;

import jglib.util.spec.Drawable;
import jglib.util.spec.Rectangular;
import jquest.spec.chara.RpgChara;
import jquest.spec.chip.ChipCoordinate;

public interface RpgMap extends Drawable, Rectangular {

  interface Viewport extends Drawable, Rectangular, RpgMapConcern {}

  public static RpgMap castle() {
    return new Castle();
  }

  RpgChara getMainChara();

  void setMainChara(RpgChara chara);

  ChipCoordinate startCoordinate();

  boolean isBlockedOff(ChipCoordinate coordinate);

  default Viewport currentViewport() {
    throw new UnsupportedOperationException();
  }
}
