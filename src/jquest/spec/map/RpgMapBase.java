package jquest.spec.map;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import jquest.spec.chara.RpgChara;
import jquest.spec.chip.ChipCoordinate;

abstract class RpgMapBase implements RpgMap {

  abstract class ViewportBase implements RpgMap.Viewport {

    @Override
    public ChipCoordinate originCoordinate() {
      int originX = columns() / 2 - mainChara().coordinate().x();
      originX = Math.min(originX, 0);
      originX = Math.max(originX, columns() - RpgMapBase.this.columns());
      originX = Math.abs(originX);

      int originY = rows() / 2 - mainChara().coordinate().y();
      originY = Math.min(originY, 0);
      originY = Math.max(originY, rows() - RpgMapBase.this.rows());
      originY = Math.abs(originY);

      return ChipCoordinate.at(originX, originY);
    }

    @Override
    public int x() {
      return RpgMapBase.this.x()
          + Stream //
              .iterate(originCoordinate().left(), coord -> coord.x() >= 0, ChipCoordinate::left)
              .map(mapChips::get)
              .mapToInt(MapChip::length)
              .sum();
    }

    @Override
    public int y() {
      return RpgMapBase.this.y()
          + Stream //
              .iterate(originCoordinate().up(), coord -> coord.y() >= 0, ChipCoordinate::up)
              .map(mapChips::get)
              .mapToInt(MapChip::length)
              .sum();
    }

    @Override
    public int width() {
      return Stream //
          .iterate(originCoordinate(), ChipCoordinate::right)
          .limit(columns())
          .map(mapChips::get)
          .mapToInt(MapChip::length)
          .sum();
    }

    @Override
    public int height() {
      return Stream //
          .iterate(originCoordinate(), ChipCoordinate::down)
          .limit(rows())
          .map(mapChips::get)
          .mapToInt(MapChip::length)
          .sum();
    }

    @Override
    public void draw(Graphics g) {
      Stream //
          .iterate(originCoordinate(), ChipCoordinate::down)
          .limit(rows())
          .flatMap(coord -> Stream.iterate(coord, ChipCoordinate::right).limit(columns()))
          .map(mapChips::get)
          .forEach(mapChip -> mapChip.draw(g));
    }
  }

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
  public RpgChara mainChara() {
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
    return IntStream //
        .range(0, rows())
        .mapToObj(y -> ChipCoordinate.at(x, y))
        .map(mapChips::get);
  }

  private Stream<MapChip> rowMapChipsStream(int y) {
    return IntStream //
        .range(0, columns())
        .mapToObj(x -> ChipCoordinate.at(x, y))
        .map(mapChips::get);
  }

  private Stream<MapChip> mapChipsStream() {
    return mapChips.values().stream();
  }
}
