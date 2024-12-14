package jquest.spec.chara;

import jquest.spec.chip.ChipCoordinate;
import jquest.spec.map.RpgMap;

final class Hero extends RpgCharaBase {

  public Hero(HeroImage image, RpgMap rpgMap, ChipCoordinate coordinate) {
    super(image, rpgMap, coordinate);
  }

  @Override
  public void turnUp() {
    image().switchUpward();
  }

  @Override
  public void turnDown() {
    image().switchDownward();
  }

  @Override
  public void turnLeft() {
    image().switchLeftward();
  }

  @Override
  public void turnRight() {
    image().switchRightward();
  }

  @Override
  public void foot() {
    image().foot();
  }
}
