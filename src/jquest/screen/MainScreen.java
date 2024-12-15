package jquest.screen;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Objects;
import jglib.component.GameScreen;
import jglib.util.model.Key;
import jglib.util.model.Keyboard;
import jquest.screen.AnimationRegistry.AnimationName;
import jquest.spec.scene.RpgScene;

public class MainScreen extends GameScreen {

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

  private enum MainScreenAnimationName implements AnimationName {
    MAIN_CHARA_WALKING,
  }

  private RpgScene rpgScene;
  private Keyboard<OperationKey> keyboard;
  private AnimationRegistry<MainScreenAnimationName> animationRegistry;

  public MainScreen(RpgScene rpgScene) {
    this.rpgScene = Objects.requireNonNull(rpgScene);
    setScreenSize(rpgScene.width(), rpgScene.height());

    keyboard = Keyboard.create(OperationKey.values());
    addKeyListener(keyboard);
    setFocusable(true);

    setGameLoopInterval(100L);

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
    if (keyboard.isPressed(OperationKey.W)) {
      rpgScene.mainChara().moveUp();
    } else if (keyboard.isPressed(OperationKey.S)) {
      rpgScene.mainChara().moveDown();
    } else if (keyboard.isPressed(OperationKey.A)) {
      rpgScene.mainChara().moveLeft();
    } else if (keyboard.isPressed(OperationKey.D)) {
      rpgScene.mainChara().moveRight();
    }
  }
}
