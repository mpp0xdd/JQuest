package jquest.helper;

import java.util.regex.Pattern;

public final class StringHelper {

  private static final Pattern LINE_SEPARATOR_PATTERN = Pattern.compile("\\R");

  public static boolean containLineSeparator(String string) {
    return LINE_SEPARATOR_PATTERN.matcher(string).find();
  }

  public static boolean notContainLineSeparator(String string) {
    return !containLineSeparator(string);
  }

  private StringHelper() {}
}
