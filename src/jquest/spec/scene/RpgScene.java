package jquest.spec.scene;

import java.awt.Graphics;
import java.util.List;
import jglib.component.SubGameScreen;
import jquest.spec.chara.RpgChara;
import jquest.spec.chip.ChipCoordinate;
import jquest.spec.map.RpgMap;

public interface RpgScene extends SubGameScreen {

  public static RpgScene prologue() {
    RpgMap rpgMap = RpgMap.castle();

    rpgMap.setMainChara(RpgChara.heroOf(rpgMap, ChipCoordinate.at(1, 1)));
    rpgMap.addNonPlayerChara(RpgChara.kingOf(rpgMap, ChipCoordinate.at(5, 2)));

    rpgMap.setCamera(rpgMap.mainChara());

    return new RpgSceneImpl(rpgMap);
  }

  RpgMap rpgMap();

  RpgChara mainChara();

  List<RpgChara> nonPlayerCharas();

  @Override
  default void draw(Graphics g) {
    rpgMap().viewport().draw(g);
    nonPlayerCharas().forEach(chara -> chara.draw(g));
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
    return rpgMap().viewport().width();
  }

  @Override
  default int height() {
    return rpgMap().viewport().height();
  }
}
