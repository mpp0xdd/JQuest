package jquest.spec.chip;

import java.awt.Graphics;
import java.util.Objects;
import java.util.function.UnaryOperator;

public abstract class ChipBase implements Chip {

  private ChipImage image;
  private ChipCoordinate coordinate;

  public ChipBase(ChipImage image, ChipCoordinate coordinate) {
    this.image = Objects.requireNonNull(image);
    this.coordinate = Objects.requireNonNull(coordinate);
  }

  @Override
  public ChipCoordinate coordinate() {
    return coordinate;
  }

  @Override
  public int x() {
    return coordinate().x() * length();
  }

  @Override
  public int y() {
    return coordinate().y() * length();
  }

  @Override
  public void draw(Graphics g) {
    image.draw(g, x(), y());
  }

  @Override
  public int length() {
    return 32;
  }

  protected ChipCoordinate computeCoordinate(UnaryOperator<ChipCoordinate> operator) {
    return coordinate = operator.apply(coordinate);
  }
}
