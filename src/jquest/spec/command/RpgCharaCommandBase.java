package jquest.spec.command;

import java.util.Objects;
import jquest.spec.chara.RpgChara;

abstract class RpgCharaCommandBase<C extends RpgChara> implements RpgCharaCommand<C> {

  private final C rpgChara;

  public RpgCharaCommandBase(C rpgChara) {
    this.rpgChara = Objects.requireNonNull(rpgChara);
  }

  @Override
  public final C rpgChara() {
    return rpgChara;
  }
}
