package jquest.spec.command;

import java.util.Random;
import jquest.spec.chara.RpgChara;

public class RpgCharaWalkCommand extends RpgCharaCommandBase<RpgChara> {

  private static final Random RANDOM = new Random();

  public RpgCharaWalkCommand(RpgChara rpgChara) {
    super(rpgChara);
  }

  @Override
  public void execute() {
    rpgChara().foot();

    if (RANDOM.nextInt(100) >= 20) {
      return;
    }

    switch (RANDOM.nextInt(4)) {
      case 0 -> rpgChara().moveUp();
      case 1 -> rpgChara().moveDown();
      case 2 -> rpgChara().moveLeft();
      case 3 -> rpgChara().moveRight();
    }
  }
}
