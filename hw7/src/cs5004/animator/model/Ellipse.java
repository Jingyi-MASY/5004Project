package cs5004.animator.model;

/**
 * this class represents an Elipse shape, it includes all fields and methods that are specific to
 * an Ellipse shape. The fields of this class includes xRadius, yRadius, xRadiusTimeline, and
 * yRadiusTimeline.
 */
public class Ellipse extends AbstractShape {

  /**
   * This Ellipse class constructor initialize a Ellipse object based on the passed in info.
   *
   * @param name name of the shape, String type.
   * @param type type of the shape, ShapeType enum type.
   * @throws IllegalArgumentException if the appearance time range is invalid.
   */
  public Ellipse(String name, ShapeType type) {

    super(name, type);
    if (type != ShapeType.ELLIPSE) {
      throw new IllegalArgumentException("This should be an Ellipse");
    }
  }
}