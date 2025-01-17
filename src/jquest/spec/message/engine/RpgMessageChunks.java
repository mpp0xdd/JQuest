package jquest.spec.message.engine;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import jquest.common.Dimension;
import jquest.spec.message.RpgMessage;
import jquest.spec.message.RpgMessageLine;

public interface RpgMessageChunks {

  RpgMessageChunks NULL = new NullRpgMessageChunks();

  public static RpgMessageChunks chunk(
      RpgMessage message, FontMetrics fontMetrics, Dimension dimension) {
    if (message.isEmpty()) {
      return NULL;
    }

    int height = fontMetrics.getMaxDescent() + fontMetrics.getMaxAscent();
    int chunkSize = dimension.height() / height;

    List<List<String>> chunks = new ArrayList<>();
    List<String> chunk = new ArrayList<>();
    for (String line : format(message, fontMetrics, dimension.width())) {
      if (chunk.size() < chunkSize) {
        chunk.add(line);
        continue;
      }
      chunks.add(List.copyOf(chunk));
      chunk.clear();
      chunk.add(line);
    }
    if (!chunk.isEmpty()) {
      chunks.add(List.copyOf(chunk));
    }

    return new RpgMessageChunksImpl(chunks);
  }

  private static List<String> format(RpgMessage message, FontMetrics fontMetrics, int maxWidth) {
    List<List<String>> result = new ArrayList<>();

    ListIterator<RpgMessageLine> lines = message.lines().listIterator(message.lines().size());
    while (lines.hasPrevious()) {
      RpgMessageLine line = lines.previous();
      result.add(split(line, fontMetrics, maxWidth));
    }

    Collections.reverse(result);
    return result.stream().flatMap(List::stream).toList();
  }

  private static List<String> split(RpgMessageLine line, FontMetrics fontMetrics, int maxWidth) {
    if (fontMetrics.stringWidth(line.toString()) <= maxWidth) {
      return List.of(line.toString());
    }

    List<String> result = new ArrayList<>();
    StringBuilder buffer = new StringBuilder();

    for (String s : line.graphemeClusters()) {
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

  void draw(Graphics g, int x, int y);

  boolean hasNext();

  boolean next();

  boolean isEmpty();
}
