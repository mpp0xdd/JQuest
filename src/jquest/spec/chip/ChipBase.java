package jquest.spec.chip;

import java.awt.Graphics;
import java.util.Objects;
import java.util.function.UnaryOperator;
import jquest.common.Coordinate;

public abstract class ChipBase implements Chip {

  private ChipImage image;
  private ChipLocation location;

  public ChipBase(ChipImage image, ChipCoordinate chipCoordinate) {
    this.image = checkChipImage(image);
    this.location = ChipLocation.from(chipCoordinate, length());
  }

  @Override
  public ChipLocation location() {
    return location;
  }

  @Override
  public int x() {
    return location().coordinate().x();
  }

  @Override
  public int y() {
    return location().coordinate().y();
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

  protected ChipLocation computeLocationFromChipCoordinate(UnaryOperator<ChipCoordinate> operator) {
    return location = location.computeFromChipCoordinate(operator);
  }

  protected ChipLocation computeLocationFromCoordinate(UnaryOperator<Coordinate> operator) {
    return location = location.computeFromCoordinate(operator);
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
