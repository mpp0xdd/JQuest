package jquest.helper;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public final class ImageValidator {

  public static void validateSizeAndThrowIfError(
      Image image, int expectedWidth, int expectedHeight) {

    final String errorMessageFormat = "expected image %s <%s>, but got <%s>.";
    final List<String> errorMessages = new ArrayList<>();

    // validate size
    final int actualWidth = image.getWidth(null);
    if (actualWidth != expectedWidth) {
      errorMessages.add(String.format(errorMessageFormat, "width", expectedWidth, actualWidth));
    }
    final int actualHeight = image.getHeight(null);
    if (actualHeight != expectedHeight) {
      errorMessages.add(String.format(errorMessageFormat, "height", expectedHeight, actualHeight));
    }

    // throw if error
    if (!errorMessages.isEmpty()) {
      throw new IllegalArgumentException(String.join(" ", errorMessages));
    }
  }

  public static void validateSizeAndThrowIfError(Image image, int length) {
    validateSizeAndThrowIfError(image, length, length);
  }

  private ImageValidator() {}
}
