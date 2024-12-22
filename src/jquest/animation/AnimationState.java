package jquest.animation;

public enum AnimationState {
  NEW,
  NOW_PLAYING,
  ON_PAUSE,
  END,
  ;

  public AnimationState play() {
    if (this == NEW) {
      return NOW_PLAYING;
    }
    throw new IllegalStateException(this.name());
  }

  public AnimationState pause() {
    if (this == NOW_PLAYING) {
      return ON_PAUSE;
    }
    throw new IllegalStateException(this.name());
  }

  public AnimationState resume() {
    if (this == ON_PAUSE) {
      return NOW_PLAYING;
    }
    throw new IllegalStateException(this.name());
  }

  public AnimationState end() {
    if (this != END) {
      return END;
    }
    throw new IllegalStateException(this.name());
  }
}
