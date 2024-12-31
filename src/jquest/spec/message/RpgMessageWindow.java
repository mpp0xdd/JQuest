package jquest.spec.message;

import java.util.Optional;
import jglib.util.spec.Drawable;
import jglib.util.spec.Rectangular;
import jquest.common.Coordinate;
import jquest.common.Dimension;

public interface RpgMessageWindow extends Rectangular, Drawable {

  public static RpgMessageWindow of(Coordinate coordinate, Dimension dimension) {
    return new RpgMessageWindowImpl(coordinate, dimension);
  }

  Optional<RpgMessage> message();

  void setMessage(RpgMessage message);
}
