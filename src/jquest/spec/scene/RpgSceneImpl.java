package jquest.spec.scene;

import jquest.spec.chara.RpgChara;
import jquest.spec.map.RpgMap;

class RpgSceneImpl implements RpgScene {

  private RpgMap rpgMap;
  private RpgChara mainChara;

  @Override
  public RpgMap rpgMap() {
    return rpgMap;
  }

  public void setRpgMap(RpgMap rpgMap) {
    this.rpgMap = rpgMap;
  }

  @Override
  public RpgChara mainChara() {
    return mainChara;
  }

  public void setMainChara(RpgChara mainChara) {
    this.mainChara = mainChara;
  }
}
