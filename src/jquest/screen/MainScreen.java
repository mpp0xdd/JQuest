package jquest.screen;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;
import jglib.component.SimpleGameScreen;
import jquest.screen.AnimationRegistry.AnimationName;
import jquest.spec.scene.RpgScene;

public class MainScreen extends SimpleGameScreen {

  private enum MainScreenAnimationName implements AnimationName {
    MAIN_CHARA_WALKING,
  }

  private RpgScene rpgScene;
  private AnimationRegistry<MainScreenAnimationName> animationRegistry;

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
}
