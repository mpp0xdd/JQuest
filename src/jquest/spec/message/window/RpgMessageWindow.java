package jquest.spec.message.window;

import jglib.util.spec.Drawable;
import jglib.util.spec.Rectangular;
import jquest.common.Coordinate;
import jquest.common.Dimension;
import jquest.spec.message.RpgMessage;

public interface RpgMessageWindow extends Rectangular, Drawable {

  public static RpgMessageWindow of(Coordinate coordinate, Dimension dimension) {
    return new RpgMessageWindowImpl(coordinate, dimension);
  }

  RpgMessage message();

  void setMessage(RpgMessage message);

  boolean nextMessage();
}
