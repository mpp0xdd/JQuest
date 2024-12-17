package jquest.spec.chip;

import jglib.util.spec.Drawable;
import jglib.util.spec.Square;

public interface Chip extends Drawable, Square {

  ChipCoordinate chipCoordinate();
}
