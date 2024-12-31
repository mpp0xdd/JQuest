package jquest.spec.message;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
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
    this.message = RpgMessage.NULL;
  }

  @Override
  public RpgMessage message() {
    return message;
  }

  @Override
  public void setMessage(RpgMessage message) {
    this.message = Objects.requireNonNull(message);
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
    g2.setColor(Color.WHITE);
    StringDrawer.LEFT.draw(
        g2, windowRect.x, windowRect.y, formatMessageLines(fontMetrics, windowRect.width));
  }

  private List<String> formatMessageLines(FontMetrics fontMetrics, int maxWidth) {
    List<List<String>> result = new ArrayList<>();

    ListIterator<String> messageLines = message().lines().listIterator(message().lines().size());
    while (messageLines.hasPrevious()) {
      String messageLine = messageLines.previous();
      result.add(split(messageLine, fontMetrics, maxWidth));
    }

    Collections.reverse(result);
    return result.stream().flatMap(List::stream).toList();
  }

  private List<String> split(String line, FontMetrics fontMetrics, int maxWidth) {
    if (fontMetrics.stringWidth(line) <= maxWidth) {
      return List.of(line);
    }

    List<String> result = new ArrayList<>();
    StringBuilder buffer = new StringBuilder();

    for (String s : line.split("\\b{g}")) {
      String split = buffer.toString();
      buffer.append(s);
      if (fontMetrics.stringWidth(buffer.toString()) > maxWidth) {
        result.add(split);
        buffer.setLength(0);
        buffer.append(s);
      }
    }
    if (!buffer.isEmpty()) {
      result.add(buffer.toString());
    }

    return result;
  }
}
