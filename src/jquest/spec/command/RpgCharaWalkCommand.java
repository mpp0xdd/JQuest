package jquest.spec.command;

import java.util.Random;
import jquest.spec.chara.RpgChara;

public class RpgCharaWalkCommand extends RpgCharaCommandBase<RpgChara> {

  private Random random;

  public RpgCharaWalkCommand(RpgChara rpgChara) {
    super(rpgChara);
    random = new Random();
  }

  @Override
  public void execute() {
    if (random.nextInt(100) >= 20) {
      // return;
    }

    switch (random.nextInt(4)) {
      case 0 -> rpgChara().moveUp();
      case 1 -> rpgChara().moveDown();
      case 2 -> rpgChara().moveLeft();
      case 4 -> rpgChara().moveRight();
    }
  }
}
