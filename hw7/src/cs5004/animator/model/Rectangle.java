package cs5004.animator.model;

/**
 * this Rectangle class represents a rectangle shape, and this class includes all the fields and
 * methods that are specific to the rectangle class. The fields of this class includes height,
 * width, heightTimeline and widthTimeline.
 */
public class Rectangle extends AbstractShape {

  /**
   * This rectangle class constructor initialize a rectangle object based on the passed in info.
   *
   * @param name          name of the shape, String type.
   * @param type          type of the shape, ShapeType enum type.
   * @throws IllegalArgumentException if the appearance time range is invalid.
   */
  public Rectangle(String name, ShapeType type) {
    super(name, type);
    if (!type.equals(ShapeType.RECTANGLE)) {
      throw new IllegalArgumentException("This should be a rectangle");
    }
  }
}
