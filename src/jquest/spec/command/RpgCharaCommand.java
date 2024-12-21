package jquest.spec.command;

import jquest.spec.chara.RpgChara;

public interface RpgCharaCommand<C extends RpgChara> extends RpgCommand {

  C rpgChara();
}
