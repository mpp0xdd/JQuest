package jquest.spec.chara;

import jquest.spec.chip.ChipImage;

interface RpgCharaChipImage extends ChipImage {

  void switchLeftward();

  void switchRightward();

  void switchUpward();

  void switchDownward();

  void foot();
}
