package jquest.spec.map;

import java.util.Objects;
import jquest.spec.chip.ChipBase;
import jquest.spec.chip.ChipCoordinate;
import jquest.spec.chip.ChipImage;

public abstract class RpgMapConcernChipBase extends ChipBase implements RpgMapConcernChip {

  private RpgMap rpgMap;

  public RpgMapConcernChipBase(ChipImage image, RpgMap rpgMap, ChipCoordinate coordinate) {
    super(image, coordinate);
    this.rpgMap = Objects.requireNonNull(rpgMap);
  }

  @Override
  public RpgMap rpgMap() {
    return rpgMap;
  }

  @Override
  public int x() {
    return super.x() + rpgMap().x();
  }

  @Override
  public int y() {
    return super.y() + rpgMap().y();
  }
}
