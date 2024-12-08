package jquest.spec.chara;

import jquest.spec.chip.ChipCoordinate;
import jquest.spec.chip.ChipImage;
import jquest.spec.map.RpgMap;
import jquest.spec.map.RpgMapConcernChipBase;

abstract class RpgCharaBase extends RpgMapConcernChipBase implements RpgChara {

  public RpgCharaBase(ChipImage image, RpgMap rpgMap, ChipCoordinate coordinate) {
    super(image, rpgMap, coordinate);
  }

  @Override
  public void moveUp() {
    computeCoordinate(ChipCoordinate::up);
  }

  @Override
  public void moveDown() {
    computeCoordinate(ChipCoordinate::down);
  }

  @Override
  public void moveLeft() {
    computeCoordinate(ChipCoordinate::left);
  }

  @Override
  public void moveRight() {
    computeCoordinate(ChipCoordinate::right);
  }
}
