package jquest.helper;

import java.util.function.IntPredicate;
import java.util.function.LongPredicate;

public final class NumericalValidator {

  public static int validateAndThrowIfError(int n, IntPredicate predicate) {
    if (predicate.test(n)) {
      return n;
    }
    throw new IllegalArgumentException(String.valueOf(n));
  }

  public static long validateAndThrowIfError(long n, LongPredicate predicate) {
    if (predicate.test(n)) {
      return n;
    }
    throw new IllegalArgumentException(String.valueOf(n));
  }

  public static final class IntPredicates {

    public static IntPredicate greaterThanOrEqualTo(int v) {
      return n -> n >= v;
    }

    private IntPredicates() {}
  }

  public static final class LongPredicates {

    public static LongPredicate greaterThanOrEqualTo(long v) {
      return n -> n >= v;
    }

    private LongPredicates() {}
  }

  private NumericalValidator() {}
}
