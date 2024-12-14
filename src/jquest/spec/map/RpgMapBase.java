package jquest.spec.map;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;
import jquest.spec.chara.RpgChara;
import jquest.spec.chip.ChipCoordinate;

abstract class RpgMapBase implements RpgMap {

  private final int x, y;
  private RpgChara mainChara;
  protected final Map<ChipCoordinate, MapChip> mapChips = new HashMap<>();

  public RpgMapBase(int x, int y) {
    this.x = x;
    this.y = y;
    this.mainChara = null;
    initialize();
  }

  public RpgMapBase() {
    this(0, 0);
  }

  @Override
  public int rows() {
    return (int) rowMapChipsStream(0).count();
  }

  @Override
  public int columns() {
    return (int) columnMapChipsStream(0).count();
  }

  @Override
  public RpgChara getMainChara() {
    return mainChara;
  }

  @Override
  public void setMainChara(RpgChara chara) {
    mainChara = chara;
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
