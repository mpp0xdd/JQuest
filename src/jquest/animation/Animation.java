package jquest.animation;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public final class Animation {

  private final Frame frame;

  private final Timer scheduler;
  private long period;
  private TimerTask animation;

  private AnimationState state;

  public Animation(Frame frame) {
    this.frame = Objects.requireNonNull(frame);

    scheduler = new Timer();
    period = 0L;
    animation = null;

    state = AnimationState.NEW;
  }

  public boolean nowPlaying() {
    return state == AnimationState.NOW_PLAYING;
  }

  public boolean onPause() {
    return state == AnimationState.ON_PAUSE;
  }

  void schedule(long delay, long period) {
    state = state.play();

    prepareAnimation();
    this.period = period;
    startAnimation(delay);
  }

  void pause() {
    state = state.pause();

    animation.cancel();
    animation = null;
  }

  void resume() {
    state = state.resume();

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
}
