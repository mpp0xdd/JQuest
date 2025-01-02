package jquest.spec.message;

import java.awt.Graphics;
import java.util.List;
import jglib.util.StringDrawer;

class RpgMessageStringImpl implements RpgMessageString {

  private final List<String> chars;
  private int index;

  public RpgMessageStringImpl(String... chars) {
    this.chars = List.of(chars);
    this.index = 0;
  }

  @Override
  public void draw(Graphics g, int x, int y) {
    StringDrawer.LEFT.draw(g, x, y, chars.subList(0, index + 1));
  }

  @Override
  public boolean hasNextChar() {
    return index + 1 < chars.size();
  }

  @Override
  public boolean nextChar() {
    if (hasNextChar()) {
      index++;
      return true;
    }

    return false;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }
}
