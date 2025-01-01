package jquest.spec.message;

import java.awt.Graphics;

interface RpgMessageChunks {

  void draw(Graphics g, int x, int y);

  boolean hasNext();

  void next();
}
