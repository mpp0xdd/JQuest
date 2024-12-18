package jquest.spec.map;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import jquest.common.Coordinate;
import jquest.spec.chara.RpgChara;
import jquest.spec.chip.ChipCoordinate;
import jquest.spec.chip.ChipLocation;

abstract class RpgMapBase implements RpgMap {

  abstract class ViewportBase implements RpgMap.Viewport {

    @Override
    public ChipLocation originChipLocation() {
      int originX = width() / 2 - mainChara().location().coordinate().x();
      originX = Math.min(originX, 0);
      originX = Math.max(originX, width() - RpgMapBase.this.width());
      originX = Math.abs(originX);

      int originY = height() / 2 - mainChara().location().coordinate().y();
      originY = Math.min(originY, 0);
      originY = Math.max(originY, height() - RpgMapBase.this.height());
      originY = Math.abs(originY);

      return ChipLocation.from(Coordinate.at(originX, originY), mainChara().length());
    }

    @Override
    public int x() {
      return RpgMapBase.this.x() + originChipLocation().coordinate().x();
    }

    @Override
    public int y() {
      return RpgMapBase.this.y() + originChipLocation().coordinate().y();
    }

    @Override
    public int width() {
      return Stream.iterate(ChipCoordinate.at(0, 0), ChipCoordinate::right)
          .limit(columns())
          .map(mapChips::get)
          .mapToInt(MapChip::length)
          .sum();
    }

    @Override
    public int height() {
      return Stream.iterate(ChipCoordinate.at(0, 0), ChipCoordinate::down)
          .limit(rows())
          .map(mapChips::get)
          .mapToInt(MapChip::length)
          .sum();
    }

    @Override
    public void draw(Graphics g) {
      Stream.iterate(originChipLocation().chipCoordinate(), ChipCoordinate::down)
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
}
