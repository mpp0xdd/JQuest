package jquest.spec.chip.image;

import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import jglib.util.GameUtilities;
import jquest.spec.chip.Chip;

final class ChipImageLoaderImpl implements ChipImageLoader {

  private static final Map<URL, ChipImage> chipImagePool = new ConcurrentHashMap<>();

  private final Chip chip;

  public ChipImageLoaderImpl(Chip chip) {
    this.chip = Objects.requireNonNull(chip);
  }

  @Override
  public ChipImage loadChipImage(String name) {
    return chipImagePool.computeIfAbsent(
        chip.getClass().getResource(name), this::loadImageAndCheckSize);
  }

  private ChipImage loadImageAndCheckSize(URL url) {
    return GameUtilities.loadImage(url)
        .filter(image -> image.getWidth() == chip.length())
        .filter(image -> image.getHeight() == chip.length())
        .map(ChipImageImpl::wrap)
        .orElseThrow();
  }
}
