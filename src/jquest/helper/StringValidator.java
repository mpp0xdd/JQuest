package jquest.helper;

import java.util.Objects;
import java.util.function.Predicate;

public final class StringValidator {

  public static String validateAndThrowIfError(String s, Predicate<? super String> predicate) {
    if (predicate.test(s)) {
      return s;
    }
    throw new IllegalArgumentException(s);
  }

  public static final class StringPredicates {

    public static Predicate<String> nonNull() {
      return Objects::nonNull;
    }

    public static Predicate<String> containLineSeparator() {
      return StringHelper::containLineSeparator;
    }

    public static Predicate<String> notContainLineSeparator() {
      return StringHelper::notContainLineSeparator;
    }

    private StringPredicates() {}
  }

  private StringValidator() {}
}
