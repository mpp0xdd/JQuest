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

  private final Timer timer;
  private TimerTask timerTask;

  public Animation(Frame frame) {
    this.frame = Objects.requireNonNull(frame);
    timer = new Timer();
    timerTask = null;
  }

  public void schedule(long delay, long period) {
    prepareTimerTask();
    timer.schedule(timerTask, delay, period);
  }

  private void prepareTimerTask() {
    if (Objects.nonNull(timerTask)) {
      throw new IllegalStateException("Animation is already scheduled.");
    }

    timerTask =
        new TimerTask() {
          @Override
          public void run() {
            frame.next();
          }
        };
  }
}
