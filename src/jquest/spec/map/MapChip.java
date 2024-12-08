package jquest.spec.map;

import java.awt.Image;
import jquest.helper.ImageLoader;
import jquest.spec.chip.Chip;
import jquest.spec.chip.ChipCoordinate;

public interface MapChip extends RpgMapConcern, Chip {

  public static MapChip create(String name, RpgMap rpgMap, ChipCoordinate coordinate) {
    Image image = ImageLoader.loadImage(MapChip.class, name);
    MapChipImage mapChipImage = MapChipImage.wrap(image);
    return new MapChipImpl(mapChipImage, rpgMap, coordinate);
  }
}
