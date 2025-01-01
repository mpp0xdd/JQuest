package jquest.scene;

import java.util.List;
import java.util.Optional;
import jquest.common.Coordinate;
import jquest.common.Dimension;
import jquest.spec.chara.RpgChara;
import jquest.spec.map.RpgMap;
import jquest.spec.message.RpgMessage;
import jquest.spec.message.RpgMessageWindow;

class RpgSceneImpl implements RpgScene {

  private RpgMap rpgMap;
  private RpgMessageWindow messageWindow;

  public RpgSceneImpl(RpgMap rpgMap) {
    this.rpgMap = rpgMap;
    this.messageWindow = null;
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

  @Override
  public void showMessageWindow(Coordinate coordinate, Dimension dimension, RpgMessage message) {
    RpgMessageWindow messageWindow = RpgMessageWindow.of(coordinate, dimension);
    messageWindow.setMessage(message);
    this.messageWindow = messageWindow;
  }

  @Override
  public void closeMessageWindow() {
    messageWindow = null;
  }

  @Override
  public Optional<RpgMessageWindow> messageWindow() {
    return Optional.ofNullable(messageWindow);
  }
}
