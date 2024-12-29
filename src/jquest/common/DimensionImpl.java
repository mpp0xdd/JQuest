package jquest.common;

import static jquest.helper.NumericalValidator.validateAndThrowIfError;
import static jquest.helper.NumericalValidator.IntPredicates.greaterThanOrEqualTo;
import java.util.Objects;

class DimensionImpl implements Dimension {

  private final int width;
  private final int height;

  public DimensionImpl(int width, int height) {
    this.width = validateAndThrowIfError(width, greaterThanOrEqualTo(0));
    this.height = validateAndThrowIfError(height, greaterThanOrEqualTo(0));
  }

  @Override
  public int width() {
    return width;
  }

  @Override
  public int height() {
    return height;
  }

  @Override
  public int hashCode() {
    return Objects.hash(height, width);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    DimensionImpl other = (DimensionImpl) obj;
    return height == other.height && width == other.width;
  }

  @Override
  public String toString() {
    return "Dimension [width=" + width + ", height=" + height + "]";
  }
}
