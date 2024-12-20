package jquest.spec.scene;

import java.util.List;
import jquest.spec.chara.RpgChara;
import jquest.spec.map.RpgMap;

class RpgSceneImpl implements RpgScene {

  private RpgMap rpgMap;

  public RpgSceneImpl(RpgMap rpgMap) {
    this.rpgMap = rpgMap;
  }

  @Override
  public RpgMap rpgMap() {
    return rpgMap;
  }

  @Override
  public RpgChara mainChara() {
    return rpgMap.mainChara();
  }

  @Override
  public List<RpgChara> nonPlayerCharas() {
    return rpgMap.nonPlayerCharas();
  }
}
