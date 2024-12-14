package jquest.screen;

import java.util.Timer;
import java.util.TimerTask;

class Animation {

  @FunctionalInterface
  interface Frame {
    void next();
  }

  private final Timer timer;
  private final TimerTask timerTask;

  public Animation(Frame frame) {
    timer = new Timer();
    timerTask =
        new TimerTask() {
          @Override
          public void run() {
            frame.next();
          }
        };
  }

  public void schedule(long delay, long period) {
    timer.schedule(timerTask, delay, period);
  }
}
