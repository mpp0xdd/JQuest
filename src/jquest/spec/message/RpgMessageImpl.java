package jquest.spec.message;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

class RpgMessageImpl implements RpgMessage {

  private static final Pattern LINE_SEPARATOR = Pattern.compile("\\R");

  private final List<String> lines;

  public RpgMessageImpl(String... lines) {
    this.lines = List.of(lines);
    validateAndThrowIfError();
  }

  @Override
  public List<String> lines() {
    return lines;
  }

  @Override
  public int hashCode() {
    return Objects.hash(lines);
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
    RpgMessageImpl other = (RpgMessageImpl) obj;
    return Objects.equals(lines, other.lines);
  }

  private void validateAndThrowIfError() {
    boolean anyLineContainslineSeparator =
        lines.stream().map(LINE_SEPARATOR::splitAsStream).anyMatch(line -> line.count() > 1);

    if (anyLineContainslineSeparator) {
      throw new IllegalArgumentException(
          "Messages cannot contain newline characters: " + Arrays.toString(lines.toArray()));
    }
  }
}
