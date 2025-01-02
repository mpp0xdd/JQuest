package jquest.helper;

import java.util.regex.Pattern;

public final class StringHelper {

  private static final Pattern LINE_SEPARATOR_PATTERN = Pattern.compile("\\R");

  public static boolean containsLineSeparator(String string) {
    return LINE_SEPARATOR_PATTERN.matcher(string).find();
  }

  private StringHelper() {}
}
