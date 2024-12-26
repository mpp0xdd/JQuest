package jquest.screen;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Objects;
import jglib.component.SimpleGameScreen;
import jglib.util.model.Key;
import jglib.util.model.Keyboard;
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

  private enum OperationKey implements Key {
    W(KeyEvent.VK_W),
    S(KeyEvent.VK_S),
    A(KeyEvent.VK_A),
    D(KeyEvent.VK_D),
    ;

    private final int code;

    private OperationKey(int code) {
      this.code = code;
    }

    @Override
    public int code() {
      return code;
    }
  }

  private Keyboard<OperationKey> keyboard;
  private RpgScene rpgScene;
  private AnimationScheduler<MainScreenAnimationName> animationScheduler;

  public MainScreen(RpgScene rpgScene) {
    this.rpgScene = Objects.requireNonNull(rpgScene);
    setScreenSize(rpgScene.width(), rpgScene.height());

    keyboard = Keyboard.create(OperationKey.values());
    addKeyListener(keyboard.asKeyPressListener());
    setFocusable(true);

    animationScheduler = new AnimationScheduler<>(MainScreenAnimationName.class);
    animationScheduler.register(
        MainScreenAnimationName.KEYSTROKE_PROCESSING_ANIMATION,
        new Animation(
            () -> {
              if (keyboard.isPressed(OperationKey.W)) {
                rpgScene.mainChara().moveUp();
                keyboard.release(OperationKey.W);
              } else if (keyboard.isPressed(OperationKey.S)) {
                rpgScene.mainChara().moveDown();
                keyboard.release(OperationKey.S);
              } else if (keyboard.isPressed(OperationKey.A)) {
                rpgScene.mainChara().moveLeft();
                keyboard.release(OperationKey.A);
              } else if (keyboard.isPressed(OperationKey.D)) {
                rpgScene.mainChara().moveRight();
                keyboard.release(OperationKey.D);
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
