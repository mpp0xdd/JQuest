package jquest.spec.map;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Objects;
import jquest.spec.chip.ChipImage;

final class MapChipImage implements ChipImage {

  public static MapChipImage wrap(Image image) {
    return new MapChipImage(image);
  }

  private final Image image;

  private MapChipImage(Image image) {
    this.image = Objects.requireNonNull(image);
  }

  @Override
  public void draw(Graphics g, int x, int y) {
    g.drawImage(image, x, y, null);
  }

  @Override
  public int length() {
    return (image.getWidth(null) + image.getHeight(null)) / 2;
  }
}
