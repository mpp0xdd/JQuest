package jquest;

import jglib.component.GameWindow;
import jglib.core.Game;
import jquest.scene.RpgScene;
import jquest.screen.MainScreen;

public class JQuest extends Game {

  public static void main(String[] args) {
    launch(JQuest.class);
  }

  @Override
  protected void start() {
    GameWindow window = new GameWindow("JQuest");
    MainScreen screen = new MainScreen(RpgScene.prologue());

    window.switchGameScreen(screen);
    window.setVisible(true);
  }
}
