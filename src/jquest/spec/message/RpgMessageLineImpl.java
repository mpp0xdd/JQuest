package jquest.spec.message;

import static jquest.helper.StringValidator.validateAndThrowIfError;
import static jquest.helper.StringValidator.StringPredicates.nonNull;
import static jquest.helper.StringValidator.StringPredicates.notContainLineSeparator;
import java.util.Objects;

class RpgMessageLineImpl implements RpgMessageLine {

  private final String line;

  public RpgMessageLineImpl(String line) {
    this.line = validateAndThrowIfError(line, nonNull().and(notContainLineSeparator()));
  }

  @Override
  public boolean isEmpty() {
    return line.isEmpty();
  }

  @Override
  public int hashCode() {
    return Objects.hash(line);
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
    RpgMessageLineImpl other = (RpgMessageLineImpl) obj;
    return Objects.equals(line, other.line);
  }

  @Override
  public String toString() {
    return line;
  }
}
