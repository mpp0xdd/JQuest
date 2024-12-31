package jquest.spec.message;

import java.util.Arrays;
import java.util.List;
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

  private void validateAndThrowIfError() {
    boolean anyLineContainslineSeparator =
        lines.stream().map(LINE_SEPARATOR::splitAsStream).anyMatch(line -> line.count() > 1);

    if (anyLineContainslineSeparator) {
      throw new IllegalArgumentException(
          "Messages cannot contain newline characters: " + Arrays.toString(lines.toArray()));
    }
  }
}
