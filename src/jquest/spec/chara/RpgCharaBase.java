package jquest.spec.chara;

import jquest.common.Velocity;
import jquest.spec.chip.ChipCoordinate;
import jquest.spec.map.RpgMap;
import jquest.spec.map.RpgMapConcernChipBase;

abstract class RpgCharaBase extends RpgMapConcernChipBase implements RpgChara {

  private int speed = 5;

  public RpgCharaBase(RpgCharaChipImage image, RpgMap rpgMap, ChipCoordinate coordinate) {
    super(image, rpgMap, coordinate);
  }

  @Override
  public void moveUp() {
    turnUp();
    computeLocationFromCoordinate(coord -> coord.plus(Velocity.of(0, -speed)));
  }

  @Override
  public void moveDown() {
    turnDown();
    computeLocationFromCoordinate(coord -> coord.plus(Velocity.of(0, speed)));
  }

  @Override
  public void moveLeft() {
    turnLeft();
    computeLocationFromCoordinate(coord -> coord.plus(Velocity.of(-speed, 0)));
  }

  @Override
  public void moveRight() {
    turnRight();
    computeLocationFromCoordinate(coord -> coord.plus(Velocity.of(speed, 0)));
  }

  @Override
  protected RpgCharaChipImage image() {
    return (RpgCharaChipImage) super.image();
  }
}
