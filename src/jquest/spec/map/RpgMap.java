package jquest.spec.map;

import java.util.List;
import jglib.util.spec.Cellular;
import jglib.util.spec.Drawable;
import jglib.util.spec.Rectangular;
import jquest.spec.chara.RpgChara;
import jquest.spec.chip.Chip;
import jquest.spec.chip.ChipCoordinate;
import jquest.spec.chip.ChipLocation;

public interface RpgMap extends Cellular, Rectangular {

  interface Viewport extends Cellular, Rectangular, Drawable {

    ChipLocation originChipLocation();

    @Override
    default int width() {
      return columns() * Chip.LENGTH;
    }

    @Override
    default int height() {
      return rows() * Chip.LENGTH;
    }
  }

  public static RpgMap castle() {
    return new Castle();
  }

  RpgChara mainChara();

  void setMainChara(RpgChara chara);

  void addNonPlayerChara(RpgChara chara);

  List<RpgChara> nonPlayerCharas();

  boolean isBlockedOff(ChipCoordinate coordinate);

  Viewport viewport();

  @Override
  default int width() {
    return columns() * Chip.LENGTH;
  }

  @Override
  default int height() {
    return rows() * Chip.LENGTH;
  }
}
