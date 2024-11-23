package jquest.spec.chip.image;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Objects;

final class ChipImageImpl implements ChipImage {

  public static ChipImageImpl wrap(Image image) {
    return new ChipImageImpl(image);
  }

  private final Image image;

  private ChipImageImpl(Image image) {
    this.image = Objects.requireNonNull(image);
  }

  @Override
  public void draw(Graphics g, int x, int y) {
    g.drawImage(image, x, y, null);
  }
}
