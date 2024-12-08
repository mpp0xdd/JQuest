package jquest.spec.chara;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Objects;
import jquest.spec.chip.ChipImage;

final class CharaChipImage implements ChipImage {

  public static CharaChipImage wrap(Image image) {
    return new CharaChipImage(image);
  }

  private final Image image;

  private CharaChipImage(Image image) {
    this.image = Objects.requireNonNull(image);
  }

  @Override
  public void draw(Graphics g, int x, int y) {
    g.drawImage(image, x, y, null);
  }
}
