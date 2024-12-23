package jquest.animation;

import java.util.EnumMap;
import java.util.Objects;
import java.util.Optional;
import java.util.Timer;
import java.util.function.Supplier;

public final class AnimationScheduler<N extends Enum<N> & AnimationName> {

  private final EnumMap<N, Animation> animationRegistry;
  private final EnumMap<N, SchedulableAnimation> scheduledAnimations;
  private final EnumMap<N, SchedulableAnimation> pausedAnimations;
  private final Timer animationScheduler;

  public AnimationScheduler(Class<N> clazz) {
    animationRegistry = new EnumMap<>(clazz);
    scheduledAnimations = new EnumMap<>(clazz);
    pausedAnimations = new EnumMap<>(clazz);
    animationScheduler = new Timer();
  }

  public boolean register(N name, Animation animation) {
    return Objects.isNull(animationRegistry.putIfAbsent(name, animation));
  }

  public Optional<Animation> animationOf(N name) {
    return Optional.ofNullable(animationRegistry.get(name));
  }

  public boolean alreadyScheduled(N name) {
    return scheduledAnimationOf(name).isPresent();
  }

  public void scheduleAnimation(N name, long delay, long period) {
    if (alreadyScheduled(name)) {
      throw animationAlreadyScheduledException(name).get();
    }

    SchedulableAnimation schedulableAnimation =
        animationOf(name)
            .map(animation -> new SchedulableAnimation(animation, period))
            .orElseThrow(animationUnregisteredException(name));
    schedule(name, schedulableAnimation, delay, period);
  }

  public void removeScheduledAnimation(N name) {
    scheduledAnimationOf(name)
        .ifPresent(
            scheduledAnimation -> {
              scheduledAnimation.cancel();
              scheduledAnimations.remove(name);
              animationScheduler.purge();
            });
  }

  public void pauseAnimation(N name) {
    SchedulableAnimation scheduledAnimation =
        scheduledAnimationOf(name).orElseThrow(animationNotScheduledException(name));

    pause(name, scheduledAnimation);
  }

  public void resumeAnimation(N name) {
    SchedulableAnimation pausedAnimation =
        pausedAnimationOf(name).orElseThrow(animationNotPausedException(name));
    scheduleAnimation(name, 0, pausedAnimation.period());
    pausedAnimations.remove(name);
  }

  private void schedule(
      N name, SchedulableAnimation schedulableAnimation, long delay, long period) {
    animationScheduler.schedule(schedulableAnimation, delay, period);
    scheduledAnimations.put(name, schedulableAnimation);
    pausedAnimations.remove(name);
  }

  private void pause(N name, SchedulableAnimation pausedAnimation) {
    removeScheduledAnimation(name);
    pausedAnimations.put(name, pausedAnimation);
  }

  private Optional<SchedulableAnimation> scheduledAnimationOf(N name) {
    return Optional.ofNullable(scheduledAnimations.get(name));
  }

  private Optional<SchedulableAnimation> pausedAnimationOf(N name) {
    return Optional.ofNullable(pausedAnimations.get(name));
  }

  private Supplier<RuntimeException> animationAlreadyScheduledException(N name) {
    return () -> new IllegalArgumentException("Animation is already scheduled: " + name.name());
  }

  private Supplier<RuntimeException> animationUnregisteredException(N name) {
    return () -> new IllegalArgumentException("Animation is unregistered: " + name.name());
  }

  private Supplier<RuntimeException> animationNotScheduledException(N name) {
    return () -> new IllegalArgumentException("Animation is not scheduled: " + name.name());
  }

  private Supplier<RuntimeException> animationNotPausedException(N name) {
    return () -> new IllegalArgumentException("Animation is not paused: " + name.name());
  }
}
