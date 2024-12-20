package jquest.spec.chara;

import jglib.util.image.IndexableSpriteSheet.Index;

enum DefaultRpgCharaIndex implements Index<DefaultRpgCharaIndex> {
  LEFTWARD_FIRST_STEP(0),
  LEFTWARD_SECOND_STEP(1),
  RIGHTWARD_FIRST_STEP(2),
  RIGHTWARD_SECOND_STEP(3),
  UPWARD_FIRST_STEP(4),
  UPWARD_SECOND_STEP(5),
  DOWNWARD_FIRST_STEP(6),
  DOWNWARD_SECOND_STEP(7),
  ;

  private final int index;

  private DefaultRpgCharaIndex(int index) {
    this.index = index;
  }

  @Override
  public int index() {
    return index;
  }

  @Override
  public DefaultRpgCharaIndex next() {
    return switch (this) {
      case LEFTWARD_FIRST_STEP -> LEFTWARD_SECOND_STEP;
      case LEFTWARD_SECOND_STEP -> LEFTWARD_FIRST_STEP;
      case RIGHTWARD_FIRST_STEP -> RIGHTWARD_SECOND_STEP;
      case RIGHTWARD_SECOND_STEP -> RIGHTWARD_FIRST_STEP;
      case UPWARD_FIRST_STEP -> UPWARD_SECOND_STEP;
      case UPWARD_SECOND_STEP -> UPWARD_FIRST_STEP;
      case DOWNWARD_FIRST_STEP -> DOWNWARD_SECOND_STEP;
      case DOWNWARD_SECOND_STEP -> DOWNWARD_FIRST_STEP;
      default -> this;
    };
  }

  @Override
  public DefaultRpgCharaIndex previous() {
    return next();
  }

  public boolean isLeftward() {
    return this == LEFTWARD_FIRST_STEP || this == LEFTWARD_SECOND_STEP;
  }

  public boolean isRightward() {
    return this == RIGHTWARD_FIRST_STEP || this == RIGHTWARD_SECOND_STEP;
  }

  public boolean isUpward() {
    return this == UPWARD_FIRST_STEP || this == UPWARD_SECOND_STEP;
  }

  public boolean isDownward() {
    return this == DOWNWARD_FIRST_STEP || this == DOWNWARD_SECOND_STEP;
  }
}
