package jquest.spec.chip;

import jglib.util.spec.Drawable;
import jglib.util.spec.Square;

public interface Chip extends Drawable, Square {

  int LENGTH = 32;

  ChipLocation location();

  @Override
  default int length() {
    return LENGTH;
  }
}
