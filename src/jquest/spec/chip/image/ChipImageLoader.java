package jquest.spec.chip.image;

import jquest.spec.chip.Chip;

public interface ChipImageLoader {

  public static ChipImageLoader getLoader(Chip chip) {
    return new ChipImageLoaderImpl(chip);
  }

  ChipImage loadChipImage(String name);
}
