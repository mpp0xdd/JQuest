package jquest.spec.message;

import java.awt.Graphics;
import java.util.List;
import jglib.util.StringDrawer;

class RpgMessageChunksImpl implements RpgMessageChunks {

  private final List<List<String>> chunks;
  private int index;

  public RpgMessageChunksImpl(List<List<String>> chunks) {
    this.chunks = chunks;
    this.index = 0;
  }

  @Override
  public void draw(Graphics g, int x, int y) {
    StringDrawer.LEFT.draw(g, x, y, chunks.get(index));
  }

  @Override
  public boolean hasNext() {
    return index + 1 < chunks.size();
  }

  @Override
  public void next() {
    if (hasNext()) index++;
  }
}
