package jquest.spec.chara;

import java.awt.Graphics;
import java.util.Objects;
import jglib.util.image.IndexableSpriteSheet;
import jglib.util.image.IndexableSpriteSheet.Index;

final class KingImage extends RpgCharaChipImageBase {

  enum KingIndex implements Index<KingIndex> {
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

    private KingIndex(int index) {
      this.index = index;
    }

    @Override
    public int index() {
      return index;
    }

    @Override
    public KingIndex next() {
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
    public KingIndex previous() {
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

  private final IndexableSpriteSheet<KingIndex> image;

  public KingImage(IndexableSpriteSheet<KingIndex> image) {
    this.image = Objects.requireNonNull(image);
  }

  @Override
  public void draw(Graphics g, int x, int y) {
    image.draw(g, x, y);
  }

  @Override
  public void switchLeftward() {
    if (!isLeftward()) {
      image.setIndex(KingIndex.LEFTWARD_FIRST_STEP);
    }
  }

  @Override
  public void switchRightward() {
    if (!isRightward()) {
      image.setIndex(KingIndex.RIGHTWARD_FIRST_STEP);
    }
  }

  @Override
  public void switchUpward() {
    if (!isUpward()) {
      image.setIndex(KingIndex.UPWARD_FIRST_STEP);
    }
  }

  @Override
  public void switchDownward() {
    if (!isDownward()) {
      image.setIndex(KingIndex.DOWNWARD_FIRST_STEP);
    }
  }

  @Override
  public void foot() {
    image.next();
  }

  @Override
  public boolean isLeftward() {
    return image.getIndex().isLeftward();
  }

  @Override
  public boolean isRightward() {
    return image.getIndex().isRightward();
  }

  @Override
  public boolean isUpward() {
    return image.getIndex().isUpward();
  }

  @Override
  public boolean isDownward() {
    return image.getIndex().isDownward();
  }
}
