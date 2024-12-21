package jquest.common;

public interface Velocity {

  Velocity ZERO = of(0, 0);

  public static Velocity of(int x, int y) {
    return new VelocityImpl(x, y);
  }

  int x();

  int y();

  default boolean isLeftward() {
    return x() < 0;
  }

  default boolean isRightward() {
    return x() > 0;
  }

  default boolean isUpward() {
    return y() < 0;
  }

  default boolean isDownward() {
    return y() > 0;
  }

  default boolean isZero() {
    return this.equals(ZERO);
  }

  default Velocity invertY() {
    return of(x(), -y());
  }

  default Velocity invertX() {
    return of(-x(), y());
  }

  default Velocity invert() {
    return of(-x(), -y());
  }

  default Velocity plusX(int dx) {
    return of(x() + dx, y());
  }

  default Velocity plusY(int dy) {
    return of(x(), y() + dy);
  }

  default Velocity plus(int dx, int dy) {
    return of(x() + dx, y() + dy);
  }

  default Velocity minusX(int dx) {
    return of(x() - dx, y());
  }

  default Velocity minusY(int dy) {
    return of(x(), y() - dy);
  }

  default Velocity minus(int dx, int dy) {
    return of(x() - dx, y() - dy);
  }

  @Override
  int hashCode();

  @Override
  boolean equals(Object obj);

  @Override
  String toString();
}
