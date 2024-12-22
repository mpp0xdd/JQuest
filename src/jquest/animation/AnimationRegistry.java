package jquest.animation;

import java.util.EnumMap;
import java.util.Objects;
import java.util.Optional;

public class AnimationRegistry<N extends Enum<N> & AnimationName> {

  private final EnumMap<N, Animation> animationRegistry;

  public AnimationRegistry(Class<N> clazz) {
    animationRegistry = new EnumMap<>(clazz);
  }

  public boolean register(N name, Animation animation) {
    return Objects.isNull(animationRegistry.putIfAbsent(name, animation));
  }

  public Optional<Animation> animationOf(N name) {
    return Optional.ofNullable(animationRegistry.get(name));
  }

  public void scheduleAnimation(N name, long delay, long period) {
    animationOf(name).orElseThrow().schedule(delay, period);
  }
}
