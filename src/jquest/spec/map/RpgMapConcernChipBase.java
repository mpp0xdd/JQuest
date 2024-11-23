package jquest.spec.map;

import jquest.spec.chip.Chip;
import jquest.spec.chip.ChipBase;
import jquest.spec.chip.ChipCoordinate;

public abstract class RpgMapConcernChipBase extends ChipBase implements RpgMapConcern, Chip {

  private RpgMap rpgMap;

  public RpgMapConcernChipBase(String name, RpgMap rpgMap, ChipCoordinate coordinate) {
    super(name, coordinate);
    this.rpgMap = rpgMap;
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
