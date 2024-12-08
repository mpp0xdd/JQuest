package jquest.spec.chara;

import java.awt.Image;
import jquest.helper.ImageLoader;
import jquest.spec.action.move.DownMovable;
import jquest.spec.action.move.LeftMovable;
import jquest.spec.action.move.RightMovable;
import jquest.spec.action.move.UpMovable;
import jquest.spec.chip.Chip;
import jquest.spec.map.RpgMap;
import jquest.spec.map.RpgMapConcern;

public interface RpgChara
    extends RpgMapConcern, Chip, UpMovable, DownMovable, LeftMovable, RightMovable {

  public static RpgChara mainCharaOf(RpgMap rpgMap) {
    Image image = ImageLoader.loadImage(RpgChara.class, "image/hero.gif");
    CharaChipImage charaChipImage = CharaChipImage.wrap(image);
    return new Hero(charaChipImage, rpgMap, rpgMap.startCoordinate());
  }
}
