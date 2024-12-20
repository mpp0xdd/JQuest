package jquest.spec.scene;

import java.awt.Graphics;
import jglib.component.SubGameScreen;
import jglib.util.spec.Updatable;
import jquest.spec.chara.RpgChara;
import jquest.spec.chip.ChipCoordinate;
import jquest.spec.map.RpgMap;

public interface RpgScene extends SubGameScreen, Updatable {

  public static RpgScene prologue() {
    RpgMap rpgMap = RpgMap.castle();

    rpgMap.setMainChara(RpgChara.mainCharaOf(rpgMap, ChipCoordinate.at(1, 1)));
    rpgMap.addNonPlayerChara(RpgChara.kingOf(rpgMap, ChipCoordinate.at(5, 2)));

    return new RpgSceneImpl(rpgMap);
  }

  RpgMap rpgMap();

  RpgChara mainChara();

  @Override
  default void draw(Graphics g) {
    rpgMap().viewport().draw(g);
    rpgMap().nonPlayerCharas().forEach(chara -> chara.draw(g));
    rpgMap().mainChara().draw(g);
  }

  @Override
  default int x() {
    return rpgMap().x();
  }

  @Override
  default int y() {
    return rpgMap().y();
  }

  @Override
  default int width() {
    return rpgMap().viewport().width();
  }

  @Override
  default int height() {
    return rpgMap().viewport().height();
  }

  @Override
  default void update() {
    throw new UnsupportedOperationException();
  }
}
