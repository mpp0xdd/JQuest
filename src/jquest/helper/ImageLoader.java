package jquest.helper;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import jglib.util.GameUtilities;

public final class ImageLoader {

  private static final Map<URL, BufferedImage> IMAGE_POOL = new ConcurrentHashMap<>();

  public static BufferedImage loadBufferedImage(URL url) {
    return IMAGE_POOL.computeIfAbsent(url, urlp -> GameUtilities.loadImage(urlp).orElseThrow());
  }

  public static BufferedImage loadBufferedImage(Class<?> clazz, String name) {
    return loadBufferedImage(getResource(clazz, name));
  }

  public static BufferedImage loadBufferedImage(Object object, String name) {
    return loadBufferedImage(object.getClass(), name);
  }

  public static Image loadImage(URL url) {
    return loadBufferedImage(url);
  }

  public static Image loadImage(Class<?> clazz, String name) {
    return loadImage(getResource(clazz, name));
  }

  public static Image loadImage(Object object, String name) {
    return loadImage(object.getClass(), name);
  }

  public static void initialize() {
    IMAGE_POOL.values().forEach(Image::flush);
    IMAGE_POOL.clear();
  }

  private static URL getResource(Class<?> clazz, String name) {
    return GameUtilities.getResource(clazz, name).orElseThrow();
  }

  private ImageLoader() {}
}
