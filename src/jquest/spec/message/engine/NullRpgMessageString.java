package jquest.spec.message.engine;

import java.awt.Graphics;

class NullRpgMessageString implements RpgMessageString {

  @Override
  public void draw(Graphics g, int x, int y) {}

  @Override
  public boolean hasNextChar() {
    return false;
  }

  @Override
  public boolean nextChar() {
    return false;
  }

  @Override
  public boolean isEmpty() {
    return true;
  }
}
