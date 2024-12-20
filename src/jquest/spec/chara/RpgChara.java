package jquest.spec.chara;

import java.awt.image.BufferedImage;
import jglib.util.image.IndexableSpriteSheet;
import jquest.helper.ImageLoader;
import jquest.spec.action.move.DownMovable;
import jquest.spec.action.move.FootStompable;
import jquest.spec.action.move.LeftMovable;
import jquest.spec.action.move.RightMovable;
import jquest.spec.action.move.UpMovable;
import jquest.spec.action.turn.DownDirectional;
import jquest.spec.action.turn.LeftDirectional;
import jquest.spec.action.turn.RightDirectional;
import jquest.spec.action.turn.UpDirectional;
import jquest.spec.chara.HeroImage.HeroIndex;
import jquest.spec.chara.KingImage.KingIndex;
import jquest.spec.chip.Chip;
import jquest.spec.chip.ChipCoordinate;
import jquest.spec.map.RpgMap;
import jquest.spec.map.RpgMapConcernChip;

public interface RpgChara
    extends RpgMapConcernChip,
        UpMovable,
        DownMovable,
        LeftMovable,
        RightMovable,
        UpDirectional,
        DownDirectional,
        LeftDirectional,
        RightDirectional,
        FootStompable {

  public static RpgChara mainCharaOf(RpgMap rpgMap, ChipCoordinate coordinate) {
    BufferedImage image = ImageLoader.loadBufferedImage(RpgChara.class, "image/hero.gif");
    IndexableSpriteSheet<HeroIndex> spriteSheet =
        IndexableSpriteSheet.create(
            image, Chip.LENGTH, Chip.LENGTH, 4, 2, HeroIndex.DOWNWARD_FIRST_STEP);
    HeroImage heroImage = new HeroImage(spriteSheet);

    Hero hero = new Hero(heroImage, rpgMap, coordinate);
    return hero;
  }

  public static RpgChara kingOf(RpgMap rpgMap, ChipCoordinate coordinate) {
    BufferedImage image = ImageLoader.loadBufferedImage(RpgChara.class, "image/king.gif");
    IndexableSpriteSheet<KingIndex> spriteSheet =
        IndexableSpriteSheet.create(
            image, Chip.LENGTH, Chip.LENGTH, 4, 2, KingIndex.DOWNWARD_FIRST_STEP);
    KingImage kingImage = new KingImage(spriteSheet);

    King king = new King(kingImage, rpgMap, coordinate);
    return king;
  }
}
