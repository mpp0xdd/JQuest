package jquest.spec.chara;

import jquest.spec.chip.ChipCoordinate;
import jquest.spec.map.RpgMap;
import jquest.spec.map.RpgMapConcernChipBase;

abstract class RpgCharaBase extends RpgMapConcernChipBase implements RpgChara {

  public RpgCharaBase(RpgCharaChipImage image, RpgMap rpgMap, ChipCoordinate coordinate) {
    super(image, rpgMap, coordinate);
  }

  @Override
  public void moveUp() {
    computeChipCoordinate(ChipCoordinate::up);
    turnUp();
  }

  @Override
  public void moveDown() {
    computeChipCoordinate(ChipCoordinate::down);
    turnDown();
  }

  @Override
  public void moveLeft() {
    computeChipCoordinate(ChipCoordinate::left);
    turnLeft();
  }

  @Override
  public void moveRight() {
    computeChipCoordinate(ChipCoordinate::right);
    turnRight();
  }

  @Override
  protected RpgCharaChipImage image() {
    return (RpgCharaChipImage) super.image();
  }

  @Override
  protected ChipCoordinate computeChipCoordinate(ChipCoordinate.UnaryOperator operator) {
    ChipCoordinate computedCoordinate = operator.apply(location().chipCoordinate());
    if (rpgMap().isBlockedOff(computedCoordinate)) {
      return location().chipCoordinate();
    }
    return super.computeChipCoordinate(coordinate -> computedCoordinate);
  }
}
