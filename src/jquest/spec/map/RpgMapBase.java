package jquest.spec.map;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;
import jquest.common.Coordinate;
import jquest.spec.chara.RpgChara;
import jquest.spec.chip.ChipCoordinate;
import jquest.spec.chip.ChipLocation;

abstract class RpgMapBase implements RpgMap {

  abstract class ViewportBase implements RpgMap.Viewport {

    @Override
    public ChipLocation originChipLocation() {
      int originX = width() / 2 - camera().location().coordinate().x();
      originX = Math.min(originX, 0);
      originX = Math.max(originX, width() - RpgMapBase.this.width());
      originX = Math.abs(originX);

      int originY = height() / 2 - camera().location().coordinate().y();
      originY = Math.min(originY, 0);
      originY = Math.max(originY, height() - RpgMapBase.this.height());
      originY = Math.abs(originY);

      return ChipLocation.from(Coordinate.at(originX, originY));
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
    public final int width() {
      return RpgMap.Viewport.super.width();
    }

    @Override
    public final int height() {
      return RpgMap.Viewport.super.height();
    }

    @Override
    public void draw(Graphics g) {
      Stream.iterate(originChipLocation().chipCoordinate(), ChipCoordinate::down)
          .limit(rows() + 1)
          .flatMap(coord -> Stream.iterate(coord, ChipCoordinate::right).limit(columns() + 1))
          .map(mapChips::get)
          .filter(Objects::nonNull)
          .forEach(mapChip -> mapChip.draw(g));
    }
  }

  private final int x, y;
  private RpgMapCamera camera;
  private RpgChara mainChara;
  private final List<RpgChara> nonPlayerCharas = new ArrayList<>();
  protected final Map<ChipCoordinate, MapChip> mapChips = new HashMap<>();

  public RpgMapBase(int x, int y) {
    this.x = x;
    this.y = y;
    this.mainChara = null;
    this.camera = null;
    initialize();
  }

  public RpgMapBase() {
    this(0, 0);
  }

  @Override
  public RpgMapCamera camera() {
    return camera;
  }

  @Override
  public void setCamera(RpgMapCamera camera) {
    this.camera = camera;
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
  public void addNonPlayerChara(RpgChara chara) {
    nonPlayerCharas.add(chara);
  }

  @Override
  public List<RpgChara> nonPlayerCharas() {
    return Collections.unmodifiableList(nonPlayerCharas);
  }

  @Override
  public List<RpgChara> allCharas() {
    List<RpgChara> result = new ArrayList<>();
    result.add(mainChara());
    result.addAll(nonPlayerCharas());
    return Collections.unmodifiableList(result);
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
  public final int width() {
    return RpgMap.super.width();
  }

  @Override
  public final int height() {
    return RpgMap.super.height();
  }

  protected abstract void initialize();
}
