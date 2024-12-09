package jquest.spec.chara;

import jglib.image.SpriteSheet;
import jglib.util.GameUtilities;
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
    SpriteSheet spriteSheet =
        GameUtilities.loadSpriteSheet(RpgChara.class, "image/hero.gif", 32, 32, 1, 2).orElseThrow();
    spriteSheet.first();
    HeroImage heroImage = new HeroImage(spriteSheet);
    return new Hero(heroImage, rpgMap, rpgMap.startCoordinate());
  }
}
