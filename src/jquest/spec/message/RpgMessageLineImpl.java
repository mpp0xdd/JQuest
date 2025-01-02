package jquest.spec.message;

import static jquest.helper.StringValidator.validateAndThrowIfError;
import static jquest.helper.StringValidator.StringPredicates.nonNull;
import static jquest.helper.StringValidator.StringPredicates.notContainLineSeparator;

class RpgMessageLineImpl implements RpgMessageLine {

  private final String line;

  public RpgMessageLineImpl(String line) {
    this.line = validateAndThrowIfError(line, nonNull().and(notContainLineSeparator()));
  }

  @Override
  public String line() {
    return line;
  }

  @Override
  public boolean isEmpty() {
    return line().isEmpty();
  }
}
