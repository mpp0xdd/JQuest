package jquest.helper;

import java.util.function.Predicate;

public final class StringValidator {

  public static String validateAndThrowIfError(String s, Predicate<? super String> predicate) {
    if (predicate.test(s)) {
      return s;
    }
    throw new IllegalArgumentException(s);
  }

  private StringValidator() {}
}
