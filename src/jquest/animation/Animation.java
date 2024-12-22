package jquest.animation;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Animation {

  @FunctionalInterface
  public interface Frame {
    void next();
  }

  private final Frame frame;

  private final Timer scheduler;
  private long period;
  private TimerTask animation;

  public Animation(Frame frame) {
    this.frame = Objects.requireNonNull(frame);

    scheduler = new Timer();
    period = -1L;
    animation = null;
  }

  public boolean isPlaying() {
    return Objects.nonNull(animation);
  }

  public boolean onPause() {
    return !isPlaying() && period >= 0;
  }

  public void schedule(long delay, long period) {
    throwIfIsPlaying();
    prepareAnimation();

    this.period = period;
    startAnimation(delay);
  }

  public void pause() {
    throwIfOnPause();
    animation.cancel();
    animation = null;
  }

  public void resume() {
    throwIfNotOnPause();
    prepareAnimation();
    startAnimation(0);
  }

  private void prepareAnimation() {
    animation =
        new TimerTask() {
          @Override
          public void run() {
            frame.next();
          }
        };
  }

  private void startAnimation(long delay) {
    scheduler.schedule(animation, delay, period);
  }

  private void throwIfIsPlaying() {
    if (isPlaying()) {
      throw new IllegalStateException("Animation is playing");
    }
  }

  private void throwIfIsNotPlaying() {
    if (!isPlaying()) {
      throw new IllegalStateException("Animation is not playing");
    }
  }

  private void throwIfOnPause() {
    if (onPause()) {
      throw new IllegalStateException("Animation is not on pause");
    }
  }

  private void throwIfNotOnPause() {
    if (!onPause()) {
      throw new IllegalStateException("Animation is not on pause");
    }
  }
}
