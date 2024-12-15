package jquest.screen;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;
import jglib.component.GameScreen;
import jglib.util.model.Keystroke;
import jquest.screen.AnimationRegistry.AnimationName;
import jquest.spec.scene.RpgScene;

public class MainScreen extends GameScreen {

  private enum MainScreenAnimationName implements AnimationName {
    MAIN_CHARA_WALKING,
  }

  private Keystroke wKey = Keystroke.RELEASED;
  private Keystroke sKey = Keystroke.RELEASED;
  private Keystroke aKey = Keystroke.RELEASED;
  private Keystroke dKey = Keystroke.RELEASED;

  private RpgScene rpgScene;
  private AnimationRegistry<MainScreenAnimationName> animationRegistry;

  public MainScreen(RpgScene rpgScene) {
    this.rpgScene = Objects.requireNonNull(rpgScene);
    setScreenSize(rpgScene.width(), rpgScene.height());

    addKeyListener(
        new KeyAdapter() {
          @Override
          public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
              case KeyEvent.VK_W -> wKey = Keystroke.PRESSED;
              case KeyEvent.VK_S -> sKey = Keystroke.PRESSED;
              case KeyEvent.VK_A -> aKey = Keystroke.PRESSED;
              case KeyEvent.VK_D -> dKey = Keystroke.PRESSED;
            }
          }
        });
    setFocusable(true);

    animationRegistry = new AnimationRegistry<>(MainScreenAnimationName.class);
    animationRegistry.register(
        MainScreenAnimationName.MAIN_CHARA_WALKING,
        new Animation(
            () -> {
              rpgScene.mainChara().foot();
              repaint();
            }));
    animationRegistry.scheduleAnimation(MainScreenAnimationName.MAIN_CHARA_WALKING, 0, 300L);
  }

  @Override
  protected void paintGameComponent(Graphics g) {
    paintSubGameScreen(g, rpgScene);
  }

  @Override
  protected void runGameLoop() {
    if (wKey.isPressed()) {
      rpgScene.mainChara().moveUp();
      wKey = Keystroke.RELEASED;
    } else if (sKey.isPressed()) {
      rpgScene.mainChara().moveDown();
      sKey = Keystroke.RELEASED;
    } else if (aKey.isPressed()) {
      rpgScene.mainChara().moveLeft();
      aKey = Keystroke.RELEASED;
    } else if (dKey.isPressed()) {
      rpgScene.mainChara().moveRight();
      dKey = Keystroke.RELEASED;
    }
  }
}
