package jquest.spec.chip;

import java.awt.Graphics;
import java.util.Objects;
import java.util.function.UnaryOperator;

public abstract class ChipBase implements Chip {

  private ChipImage image;
  private ChipLocation location;

  public ChipBase(ChipImage image, ChipCoordinate chipCoordinate) {
    this.image = Objects.requireNonNull(image);
    this.location = ChipLocation.from(chipCoordinate);
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
  public final int length() {
    return Chip.super.length();
  }

  protected ChipImage image() {
    return image;
  }

  protected ChipLocation computeLocation(UnaryOperator<ChipLocation> operator) {
    return location = operator.apply(location);
  }
}
