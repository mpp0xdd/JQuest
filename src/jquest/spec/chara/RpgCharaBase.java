package jquest.spec.chara;

import java.util.function.UnaryOperator;
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
    turnUp();
  }

  @Override
  public void moveDown() {
    computeCoordinate(ChipCoordinate::down);
    turnDown();
  }

  @Override
  public void moveLeft() {
    computeCoordinate(ChipCoordinate::left);
    turnLeft();
  }

  @Override
  public void moveRight() {
    computeCoordinate(ChipCoordinate::right);
    turnRight();
  }

  @Override
  protected ChipCoordinate computeCoordinate(UnaryOperator<ChipCoordinate> operator) {
    ChipCoordinate computedCoordinate = operator.apply(coordinate());
    if (rpgMap().isBlockedOff(computedCoordinate)) {
      return coordinate();
    }
    return super.computeCoordinate(coordinate -> computedCoordinate);
  }
}
