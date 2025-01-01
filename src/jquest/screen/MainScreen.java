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
import jquest.common.Coordinate;
import jquest.common.Dimension;
import jquest.scene.RpgScene;
import jquest.spec.chara.RpgChara;
import jquest.spec.command.RpgCharaRandomWalkCommand;
import jquest.spec.command.RpgCommand;
import jquest.spec.message.RpgMessage;
import jquest.spec.message.RpgMessageWindow;

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
    SPACE(KeyEvent.VK_SPACE),
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
              if (keyboard.release(OperationKey.W)) {
                rpgScene.mainChara().moveUp();
              } else if (keyboard.release(OperationKey.S)) {
                rpgScene.mainChara().moveDown();
              } else if (keyboard.release(OperationKey.A)) {
                rpgScene.mainChara().moveLeft();
              } else if (keyboard.release(OperationKey.D)) {
                rpgScene.mainChara().moveRight();
              } else if (keyboard.release(OperationKey.SPACE)) {
                rpgScene
                    .messageWindow()
                    .ifPresentOrElse(
                        RpgMessageWindow::nextMessage,
                        () ->
                            rpgScene.showMessageWindow(
                                Coordinate.at(10, 140),
                                Dimension.of(300, 100),
                                RpgMessage.of(
                                    "いろはにほへと　ちりぬるを",
                                    "わかよたれそ　　つねならむ",
                                    "うゐのおくやま　けふこえて",
                                    "あさきゆめみし　ゑひもせす",
                                    "色は匂へど　散りぬるを",
                                    "我が世誰ぞ　常ならむ",
                                    "有為の奥山　今日越えて",
                                    "浅き夢見し　酔ひもせず",
                                    "The quick brown fox jumps over the lazy dog",
                                    "寿限無、寿限無五劫の擦り切れ海砂利水魚の水行末雲来末風来末食う寝る処に住む処やぶら小路の藪柑子パイポパイポパイポのシューリンガンシューリンガンのグーリンダイグーリンダイのポンポコピーのポンポコナーの長久命の長助")));
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
