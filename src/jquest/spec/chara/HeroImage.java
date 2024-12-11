package jquest.spec.chara;

import java.awt.Graphics;
import java.util.Objects;
import jglib.util.image.SpriteSheet;
import jquest.spec.chip.ChipImage;

final class HeroImage implements ChipImage {

  private final SpriteSheet image;

  public HeroImage(SpriteSheet image) {
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
    image.first();
  }
}
