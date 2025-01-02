package jquest.spec.message.engine;

import java.awt.Graphics;

interface RpgMessageString {

  RpgMessageString NULL = new NullRpgMessageString();

  public static RpgMessageString of(String string) {
    if (string.isEmpty()) {
      return NULL;
    }
    return new RpgMessageStringImpl(string.split("\\b{g}"));
  }

  void draw(Graphics g, int x, int y);

  boolean hasNextChar();

  boolean nextChar();

  boolean isEmpty();
}
