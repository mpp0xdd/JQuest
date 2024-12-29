package jquest.common;

public interface Dimension {

  Dimension ZERO = of(0, 0);

  public static Dimension of(int width, int height) {
    return new DimensionImpl(width, height);
  }

  int width();

  int height();

  default boolean isZero() {
    return this.equals(ZERO);
  }

  @Override
  int hashCode();

  @Override
  boolean equals(Object obj);

  @Override
  String toString();
}
