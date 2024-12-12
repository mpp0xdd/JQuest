package jquest.spec.chara;

import java.awt.Graphics;
import java.util.Objects;
import jglib.util.image.IndexableSpriteSheet;
import jglib.util.image.IndexableSpriteSheet.Index;
import jquest.spec.chip.ChipImage;

final class HeroImage implements ChipImage {

  enum HeroIndex implements Index {
    DOWN(1),
    ;

    private final int index;

    private HeroIndex(int index) {
      this.index = index;
    }

    @Override
    public int index() {
      return index;
    }
  }

  private final IndexableSpriteSheet<HeroIndex> image;

  public HeroImage(IndexableSpriteSheet<HeroIndex> image) {
    this.image = Objects.requireNonNull(image);
  }

  @Override
  public void draw(Graphics g, int x, int y) {
    image.draw(g, x, y);
  }

  @Override
  public int length() {
    return (image.width() + image.height()) / 2;
  }

  public void switchDownward() {
    image.setIndex(HeroIndex.DOWN);
  }
}
