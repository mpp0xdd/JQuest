package jquest.spec.chip;

import java.awt.Graphics;
import java.util.Objects;
import java.util.function.UnaryOperator;

public abstract class ChipBase implements Chip {

  private ChipImage image;
  private ChipCoordinate chipCoordinate;

  public ChipBase(ChipImage image, ChipCoordinate chipCoordinate) {
    this.image = checkChipImage(image);
    this.chipCoordinate = Objects.requireNonNull(chipCoordinate);
  }

  @Override
  public ChipCoordinate chipCoordinate() {
    return chipCoordinate;
  }

  @Override
  public int x() {
    return chipCoordinate().x() * length();
  }

  @Override
  public int y() {
    return chipCoordinate().y() * length();
  }

  @Override
  public void draw(Graphics g) {
    image.draw(g, x(), y());
  }

  @Override
  public int length() {
    return 32;
  }

  protected ChipImage image() {
    return image;
  }

  protected ChipCoordinate computeChipCoordinate(UnaryOperator<ChipCoordinate> operator) {
    return chipCoordinate = operator.apply(chipCoordinate);
  }

  private ChipImage checkChipImage(ChipImage image) {
    Objects.requireNonNull(image);

    if (image.length() != this.length()) {
      throw new IllegalArgumentException(
          String.format("expected image size <%s>, but got <%s>", this.length(), image.length()));
    }

    return image;
  }
}
