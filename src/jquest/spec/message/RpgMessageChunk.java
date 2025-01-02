package jquest.spec.message;

import java.awt.Graphics;

interface RpgMessageChunk {

  void draw(Graphics g, int x, int y);

  boolean hasNext();

  boolean next();

  boolean isEmpty();
}
