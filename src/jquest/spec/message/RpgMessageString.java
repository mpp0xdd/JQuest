package jquest.spec.message;

import java.awt.Graphics;

interface RpgMessageString {

  void draw(Graphics g, int x, int y);

  boolean hasNextChar();

  boolean nextChar();

  boolean isEmpty();
}
