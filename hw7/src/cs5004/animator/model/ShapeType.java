package cs5004.animator.model;

/**
 * This enum represents all possible types of shape in IShape implementations. The fields of this
 * enum includes a String txt.
 */
public enum ShapeType {
  RECTANGLE("rectangle"),
  ELLIPSE("ellipse"),
  CIRCLE("circle");
  private final String txt;

  /**
   * This constructor creates an ShapeType based on passed in txt.
   *
   * @param txt passed in text indicating which type of enum it would be.
   */
  ShapeType(String txt) {
    this.txt = txt;
  }

  /**
   * a String format representation of this ShapeType enum.
   *
   * @return the txt String (field) as desired.
   */
  @Override
  public String toString() {
    return txt;
  }

}
