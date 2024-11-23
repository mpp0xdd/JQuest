package jquest.spec.chip;

import java.awt.Graphics;
import java.util.Objects;
import java.util.function.UnaryOperator;
import jquest.spec.chip.image.ChipImage;
import jquest.spec.chip.image.ChipImageLoader;

public abstract class ChipBase implements Chip {

  private ChipImage image;
  private ChipCoordinate coordinate;

  public ChipBase(String name, ChipCoordinate coordinate) {
    this.image = ChipImageLoader.getLoader(this).loadChipImage(name);
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
