package jquest.spec.chara;

import java.awt.Graphics;
import java.util.Objects;
import jglib.util.image.IndexableSpriteSheet;

final class HeroImage extends RpgCharaChipImageBase {

  private final IndexableSpriteSheet<DefaultRpgCharaIndex> image;

  public HeroImage(IndexableSpriteSheet<DefaultRpgCharaIndex> image) {
    this.image = Objects.requireNonNull(image);
  }

  @Override
  public void draw(Graphics g, int x, int y) {
    image.draw(g, x, y);
  }

  @Override
  public void switchLeftward() {
    if (!isLeftward()) {
      image.setIndex(DefaultRpgCharaIndex.LEFTWARD_FIRST_STEP);
    }
  }

  @Override
  public void switchRightward() {
    if (!isRightward()) {
      image.setIndex(DefaultRpgCharaIndex.RIGHTWARD_FIRST_STEP);
    }
  }

  @Override
  public void switchUpward() {
    if (!isUpward()) {
      image.setIndex(DefaultRpgCharaIndex.UPWARD_FIRST_STEP);
    }
  }

  @Override
  public void switchDownward() {
    if (!isDownward()) {
      image.setIndex(DefaultRpgCharaIndex.DOWNWARD_FIRST_STEP);
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
