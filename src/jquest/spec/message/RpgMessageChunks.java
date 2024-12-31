package jquest.spec.message;

import java.awt.Graphics;
import java.util.List;
import jglib.util.StringDrawer;

class RpgMessageChunks {

  private final List<List<String>> messageChunks;
  private int index;

  public RpgMessageChunks(List<List<String>> messageChunks) {
    this.messageChunks = messageChunks;
    this.index = 0;
  }

  public void draw(Graphics g, int x, int y) {
    StringDrawer.LEFT.draw(g, x, y, messageChunks.get(index));
  }

  public boolean hasNext() {
    return index + 1 < messageChunks.size();
  }

  public void next() {
    if (hasNext()) index++;
  }
}
