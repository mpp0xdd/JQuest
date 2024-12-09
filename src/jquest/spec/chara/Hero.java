package jquest.spec.chara;

import jquest.spec.chip.ChipCoordinate;
import jquest.spec.map.RpgMap;

final class Hero extends RpgCharaBase {

  public Hero(HeroImage image, RpgMap rpgMap, ChipCoordinate coordinate) {
    super(image, rpgMap, coordinate);
  }

  @Override
  protected HeroImage image() {
    return (HeroImage) super.image();
  }

  @Override
  public void turnUp() {
    // TODO
  }

  @Override
  public void turnDown() {
    image().switchDownward();
  }

  @Override
  public void turnLeft() {
    // TODO
  }

  @Override
  public void turnRight() {
    // TODO
  }
}
