package jquest.spec.message;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Objects;
import jquest.common.Coordinate;
import jquest.common.Dimension;

class RpgMessageWindowImpl implements RpgMessageWindow {

  private final Coordinate coordinate;
  private final Dimension dimension;

  private RpgMessage message;
  private RpgMessageChunks messageChunks;

  public RpgMessageWindowImpl(Coordinate coordinate, Dimension dimension) {
    this.coordinate = Objects.requireNonNull(coordinate);
    this.dimension = Objects.requireNonNull(dimension);
    this.message = RpgMessage.NULL;
    this.messageChunks = RpgMessageChunks.NULL;
  }

  @Override
  public RpgMessage message() {
    return message;
  }

  @Override
  public void setMessage(RpgMessage message) {
    this.message = Objects.requireNonNull(message);
    this.messageChunks = RpgMessageChunks.NULL;
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

    if (message().isEmpty()) {
      return;
    }

    FontMetrics fontMetrics = g.getFontMetrics();
    windowRect.grow(-5, -5);

    if (messageChunks.isEmpty()) {
      messageChunks =
          RpgMessageChunks.chunk(
              message, fontMetrics, Dimension.of(windowRect.width, windowRect.height));
    }

    g2.setColor(Color.WHITE);
    messageChunks.draw(g2, windowRect.x, windowRect.y);
  }

  @Override
  public void nextMessage() {
    messageChunks.next();
  }
}
