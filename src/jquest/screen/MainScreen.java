package jquest.screen;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;
import jglib.component.SimpleGameScreen;
import jquest.spec.scene.RpgScene;

public class MainScreen extends SimpleGameScreen {

  private RpgScene rpgScene;

  public MainScreen(RpgScene rpgScene) {
    this.rpgScene = Objects.requireNonNull(rpgScene);
    setScreenSize(rpgScene.width(), rpgScene.height());

    setFocusable(true);
    addKeyListener(
        new KeyAdapter() {
          @Override
          public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
              case KeyEvent.VK_W -> rpgScene.mainChara().moveUp();
              case KeyEvent.VK_S -> rpgScene.mainChara().moveDown();
              case KeyEvent.VK_A -> rpgScene.mainChara().moveLeft();
              case KeyEvent.VK_D -> rpgScene.mainChara().moveRight();
            }
            repaint();
          }
        });
  }

  @Override
  protected void paintGameComponent(Graphics g) {
    paintSubGameScreen(g, rpgScene);
  }
}
