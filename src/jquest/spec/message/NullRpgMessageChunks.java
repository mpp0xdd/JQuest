package jquest.spec.message;

import java.awt.Graphics;

class NullRpgMessageChunks implements RpgMessageChunks {

  @Override
  public void draw(Graphics g, int x, int y) {}

  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public void next() {}

  @Override
  public boolean isEmpty() {
    return true;
  }
}
