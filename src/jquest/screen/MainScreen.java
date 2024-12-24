package jquest.screen;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;
import jglib.component.SimpleGameScreen;
import jglib.util.model.Keystroke;
import jquest.animation.Animation;
import jquest.animation.AnimationName;
import jquest.animation.AnimationScheduler;
import jquest.spec.chara.RpgChara;
import jquest.spec.command.RpgCharaRandomWalkCommand;
import jquest.spec.command.RpgCommand;
import jquest.spec.scene.RpgScene;

public class MainScreen extends SimpleGameScreen {

  private enum MainScreenAnimationName implements AnimationName {
    KEYSTROKE_PROCESSING_ANIMATION,
    MAIN_CHARA_FOOT_STAMPING,
    NON_PLAYER_CHARA_FOOT_STAMPING,
    NON_PLAYER_CHARA_WALKING,
  }

  private volatile Keystroke wKey = Keystroke.RELEASED;
  private volatile Keystroke sKey = Keystroke.RELEASED;
  private volatile Keystroke aKey = Keystroke.RELEASED;
  private volatile Keystroke dKey = Keystroke.RELEASED;

  private RpgScene rpgScene;
  private AnimationScheduler<MainScreenAnimationName> animationScheduler;

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

    animationScheduler = new AnimationScheduler<>(MainScreenAnimationName.class);
    animationScheduler.register(
        MainScreenAnimationName.KEYSTROKE_PROCESSING_ANIMATION,
        new Animation(
            () -> {
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
              repaint();
            }));
    animationScheduler.register(
        MainScreenAnimationName.MAIN_CHARA_FOOT_STAMPING,
        new Animation(
            () -> {
              rpgScene.mainChara().foot();
              repaint();
            }));
    animationScheduler.register(
        MainScreenAnimationName.NON_PLAYER_CHARA_FOOT_STAMPING,
        new Animation(
            () -> {
              rpgScene.nonPlayerCharas().forEach(RpgChara::foot);
              repaint();
            }));
    animationScheduler.register(
        MainScreenAnimationName.NON_PLAYER_CHARA_WALKING,
        new Animation(
            () -> {
              rpgScene.nonPlayerCharas().stream()
                  .map(RpgCharaRandomWalkCommand::new)
                  .forEach(RpgCommand::execute);
              repaint();
            }));

    animationScheduler.scheduleAnimation(
        MainScreenAnimationName.KEYSTROKE_PROCESSING_ANIMATION, 0, 20L);
    animationScheduler.scheduleAnimation(MainScreenAnimationName.MAIN_CHARA_FOOT_STAMPING, 0, 300L);
    animationScheduler.scheduleAnimation(
        MainScreenAnimationName.NON_PLAYER_CHARA_FOOT_STAMPING, 30L, 300L);
    animationScheduler.scheduleAnimation(
        MainScreenAnimationName.NON_PLAYER_CHARA_WALKING, 30L, 300L);
  }

  @Override
  protected void paintGameComponent(Graphics g) {
    paintSubGameScreen(g, rpgScene);
  }
}
