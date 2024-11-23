package jquest.spec.scene;

import java.awt.Graphics;
import jglib.component.SubGameScreen;
import jquest.spec.chara.RpgChara;
import jquest.spec.map.RpgMap;

public interface RpgScene extends SubGameScreen {

  public static RpgScene prologue() {
    RpgSceneImpl scene = new RpgSceneImpl();

    scene.setRpgMap(RpgMap.castle());
    scene.setMainChara(RpgChara.mainCharaOf(scene.rpgMap()));

    return scene;
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
