package jquest.spec.message;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import jglib.util.StringDrawer;
import jquest.common.Coordinate;
import jquest.common.Dimension;

class RpgMessageWindowImpl implements RpgMessageWindow {

  private final Coordinate coordinate;
  private final Dimension dimension;

  private RpgMessage message;

  public RpgMessageWindowImpl(Coordinate coordinate, Dimension dimension) {
    this.coordinate = Objects.requireNonNull(coordinate);
    this.dimension = Objects.requireNonNull(dimension);
  }

  @Override
  public Optional<RpgMessage> message() {
    return Optional.ofNullable(message);
  }

  @Override
  public void setMessage(RpgMessage message) {
    this.message = message;
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

    Rectangle windowRect = asRectangle();

    g2.setColor(Color.WHITE);
    g2.fill(windowRect);

    windowRect.grow(-2, -2);
    g2.setColor(Color.BLACK);
    g2.fill(windowRect);

    List<String> messageLines = message().map(RpgMessage::lines).orElse(Collections.emptyList());
    if (messageLines.isEmpty()) {
      return;
    }

    FontMetrics fontMetrics = g.getFontMetrics();

    Rectangle2D messageRect =
        messageLines.stream()
            .map(line -> fontMetrics.getStringBounds(line, g))
            .reduce(
                (r1, r2) -> {
                  r2.setFrame(r2.getX(), r1.getMaxY(), r2.getWidth(), r2.getHeight());
                  return r1.createUnion(r2);
                })
            .orElseThrow();

    windowRect.grow(-5, -5);
    messageRect.setFrame(
        windowRect.getX(), windowRect.getY(), messageRect.getWidth(), messageRect.getHeight());
    if (!windowRect.contains(messageRect)) {
      throw new IllegalStateException();
    }

    g2.setColor(Color.WHITE);
    StringDrawer.LEFT.draw(g2, windowRect.x, windowRect.y, messageLines);
  }
}
