package jquest.screen;

import java.awt.Graphics;
import java.util.Objects;
import jglib.component.SimpleGameScreen;
import jquest.spec.scene.RpgScene;

public class MainScreen extends SimpleGameScreen {

  private RpgScene rpgScene;

  public MainScreen(RpgScene rpgScene) {
    this.rpgScene = Objects.requireNonNull(rpgScene);
    setScreenSize(rpgScene.width(), rpgScene.height());
  }

  @Override
  protected void paintGameComponent(Graphics g) {
    paintSubGameScreen(g, rpgScene);
  }
}
