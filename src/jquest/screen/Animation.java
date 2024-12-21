package jquest.screen;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

class Animation {

  @FunctionalInterface
  interface Frame {
    void next();
  }

  private final Frame frame;

  private final Timer scheduler;
  private TimerTask animation;

  public Animation(Frame frame) {
    this.frame = Objects.requireNonNull(frame);

    scheduler = new Timer();
    animation = null;
  }

  public void schedule(long delay, long period) {
    prepareAnimation();
    scheduler.schedule(animation, delay, period);
  }

  public void pause() {
    throw new UnsupportedOperationException();
  }

  public void resume() {
    throw new UnsupportedOperationException();
  }

  private void prepareAnimation() {
    if (Objects.nonNull(animation)) {
      throw new IllegalStateException("Animation is already scheduled.");
    }

    animation =
        new TimerTask() {
          @Override
          public void run() {
            frame.next();
          }
        };
  }
}
