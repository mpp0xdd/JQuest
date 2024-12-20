package jquest.spec.chara;

import java.util.stream.Stream;
import jquest.common.Velocity;
import jquest.spec.chip.ChipCoordinate;
import jquest.spec.chip.ChipLocation;
import jquest.spec.map.RpgMap;
import jquest.spec.map.RpgMapConcernChipBase;

abstract class RpgCharaBase extends RpgMapConcernChipBase implements RpgChara {

  private int speed = 5;

  public RpgCharaBase(RpgCharaChipImage image, RpgMap rpgMap, ChipCoordinate coordinate) {
    super(image, rpgMap, coordinate);
  }

  @Override
  public void moveUp() {
    turnUp();

    ChipLocation computedLocation =
        location().computeFromCoordinate(coord -> coord.plus(Velocity.of(0, -speed)));
    if (cannotMoveAt(computedLocation)) {
      return;
    }

    computeLocation(location -> computedLocation);
  }

  @Override
  public void moveDown() {
    turnDown();

    ChipLocation computedLocation =
        location().computeFromCoordinate(coord -> coord.plus(Velocity.of(0, speed)));
    if (cannotMoveAt(computedLocation)) {
      return;
    }

    computeLocation(location -> computedLocation);
  }

  @Override
  public void moveLeft() {
    turnLeft();

    ChipLocation computedLocation =
        location().computeFromCoordinate(coord -> coord.plus(Velocity.of(-speed, 0)));
    if (cannotMoveAt(computedLocation)) {
      return;
    }

    computeLocation(location -> computedLocation);
  }

  @Override
  public void moveRight() {
    turnRight();

    ChipLocation computedLocation =
        location().computeFromCoordinate(coord -> coord.plus(Velocity.of(speed, 0)));
    if (cannotMoveAt(computedLocation)) {
      return;
    }

    computeLocation(location -> computedLocation);
  }

  @Override
  protected RpgCharaChipImage image() {
    return (RpgCharaChipImage) super.image();
  }

  private boolean cannotMoveAt(Stream<ChipCoordinate> destinations) {
    return destinations.anyMatch(rpgMap()::isBlockedOff);
  }

  private boolean cannotMoveAt(ChipLocation location) {
    return cannotMoveAt(location.overlappedChipCoordinates().stream());
  }
}
