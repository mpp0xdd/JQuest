package jquest.spec.chara;

import java.util.function.UnaryOperator;
import jquest.spec.chip.ChipCoordinate;
import jquest.spec.chip.ChipLocation;
import jquest.spec.map.RpgMap;
import jquest.spec.map.RpgMapConcernChipBase;

abstract class RpgCharaBase extends RpgMapConcernChipBase implements RpgChara {

  public RpgCharaBase(RpgCharaChipImage image, RpgMap rpgMap, ChipCoordinate coordinate) {
    super(image, rpgMap, coordinate);
  }

  @Override
  public void moveUp() {
    computeFromChipCoordinate(ChipCoordinate::up);
    turnUp();
  }

  @Override
  public void moveDown() {
    computeFromChipCoordinate(ChipCoordinate::down);
    turnDown();
  }

  @Override
  public void moveLeft() {
    computeFromChipCoordinate(ChipCoordinate::left);
    turnLeft();
  }

  @Override
  public void moveRight() {
    computeFromChipCoordinate(ChipCoordinate::right);
    turnRight();
  }

  @Override
  protected RpgCharaChipImage image() {
    return (RpgCharaChipImage) super.image();
  }

  @Override
  protected ChipLocation computeFromChipCoordinate(UnaryOperator<ChipCoordinate> operator) {
    ChipCoordinate computedCoordinate = operator.apply(location().chipCoordinate());
    if (rpgMap().isBlockedOff(computedCoordinate)) {
      return location();
    }
    return super.computeFromChipCoordinate(coordinate -> computedCoordinate);
  }
}
