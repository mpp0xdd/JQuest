package jquest.spec.map;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;
import jquest.spec.chip.ChipCoordinate;

abstract class RpgMapBase implements RpgMap {

  private final int x, y;
  protected final Map<ChipCoordinate, MapChip> mapChips = new HashMap<>();

  public RpgMapBase(int x, int y) {
    this.x = x;
    this.y = y;
    initialize();
  }

  public RpgMapBase() {
    this(0, 0);
  }

  @Override
  public int x() {
    return x;
  }

  @Override
  public int y() {
    return y;
  }

  @Override
  public int width() {
    return rowMapChipsStream(0).mapToInt(MapChip::length).sum();
  }

  @Override
  public int height() {
    return columnMapChipsStream(0).mapToInt(MapChip::length).sum();
  }

  @Override
  public void draw(Graphics g) {
    mapChipsStream().forEach(mapChip -> mapChip.draw(g));
  }

  protected abstract void initialize();

  private Stream<MapChip> columnMapChipsStream(int x) {
    return mapChipsStream(entry -> entry.getKey().x() == x);
  }

  private Stream<MapChip> rowMapChipsStream(int y) {
    return mapChipsStream(entry -> entry.getKey().y() == y);
  }

  private Stream<MapChip> mapChipsStream(Predicate<Map.Entry<ChipCoordinate, MapChip>> filter) {
    return mapChips.entrySet().stream().filter(filter).map(Map.Entry::getValue);
  }

  private Stream<MapChip> mapChipsStream() {
    return mapChips.values().stream();
  }
}
