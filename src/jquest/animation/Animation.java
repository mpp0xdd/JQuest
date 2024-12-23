package jquest.animation;

import java.util.Objects;

public final class Animation {

  private final Frame frame;

  public Animation(Frame frame) {
    this.frame = Objects.requireNonNull(frame);
  }

  void nextFrame() {
    frame.next();
  }
}
