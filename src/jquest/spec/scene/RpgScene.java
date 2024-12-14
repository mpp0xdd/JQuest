package jquest.spec.scene;

import java.awt.Graphics;
import jglib.component.SubGameScreen;
import jquest.spec.chara.RpgChara;
import jquest.spec.map.RpgMap;
import jquest.spec.map.RpgMapConcern;

public interface RpgScene extends SubGameScreen {

  interface Viewport extends SubGameScreen, RpgMapConcern {}

  public static RpgScene prologue() {
    RpgMap rpgMap = RpgMap.castle();
    RpgChara mainChara = RpgChara.mainCharaOf(rpgMap);
    rpgMap.setMainChara(mainChara);

    return new RpgSceneImpl(rpgMap);
  }

  RpgMap rpgMap();

  RpgChara mainChara();

  @Override
  default void draw(Graphics g) {
    rpgMap().draw(g);
    mainChara().draw(g);
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
    return rpgMap().width();
  }

  @Override
  default int height() {
    return rpgMap().height();
  }
}
