package jquest.spec.message;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Objects;
import jquest.common.Coordinate;
import jquest.common.Dimension;

class RpgMessageWindowImpl implements RpgMessageWindow {

  private final Coordinate coordinate;
  private final Dimension dimension;

  public RpgMessageWindowImpl(Coordinate coordinate, Dimension dimension) {
    this.coordinate = Objects.requireNonNull(coordinate);
    this.dimension = Objects.requireNonNull(dimension);
  }

  @Override
  public int width() {
    return dimension.width();
  }

  @Override
  public int height() {
    return dimension.height();
  }

  @Override
  public int x() {
    return coordinate.x();
  }

  @Override
  public int y() {
    return coordinate.y();
  }

  @Override
  public void draw(Graphics g) {
    if (!(g instanceof Graphics2D g2)) {
      return;
    }

    Rectangle rect = asRectangle();

    g2.setColor(Color.WHITE);
    g2.fill(rect);

    rect.grow(-2, -2);
    g2.setColor(Color.BLACK);
    g2.fill(rect);
  }
}
