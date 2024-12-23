package jquest.animation;

import static java.util.Objects.requireNonNull;
import static jquest.helper.NumericalValidator.validateAndThrowIfError;
import static jquest.helper.NumericalValidator.LongPredicates.greaterThanOrEqualTo;
import java.util.TimerTask;

final class SchedulableAnimation extends TimerTask {

  private final Animation animation;
  private final long period;

  public SchedulableAnimation(Animation animation, long period) {
    this.animation = requireNonNull(animation);
    this.period = validateAndThrowIfError(period, greaterThanOrEqualTo(0L));
  }

  @Override
  public void run() {
    animation.nextFrame();
  }

  public long period() {
    return period;
  }
}
