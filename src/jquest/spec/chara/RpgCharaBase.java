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
    computeLocationFromCoordinate(coord -> coord.plus(Velocity.of(0, -speed)));
    turnUp();
  }

  @Override
  public void moveDown() {
    computeLocationFromCoordinate(coord -> coord.plus(Velocity.of(0, speed)));
    turnDown();
  }

  @Override
  public void moveLeft() {
    computeLocationFromCoordinate(coord -> coord.plus(Velocity.of(-speed, 0)));
    turnLeft();
  }

  @Override
  public void moveRight() {
    computeLocationFromCoordinate(coord -> coord.plus(Velocity.of(speed, 0)));
    turnRight();
  }

  @Override
  protected RpgCharaChipImage image() {
    return (RpgCharaChipImage) super.image();
  }
}
