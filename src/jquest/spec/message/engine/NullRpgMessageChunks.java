package jquest.spec.message.engine;

import java.awt.Graphics;

class NullRpgMessageChunks implements RpgMessageChunks {

  @Override
  public void draw(Graphics g, int x, int y) {}

  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public boolean next() {
    return false;
  }

  @Override
  public boolean isEmpty() {
    return true;
  }
}
