public enum ShapeType {
  RECTANGLE, OVAL, TRIANGLE, CIRCLE;

  @Override
  public String toString() {
    if (this == ShapeType.OVAL) {
      return "oval";
    } else if (this == CIRCLE) {
      return "circle";
    } else if (this == RECTANGLE) {
      return "rectangle";
    }
    throw new IllegalArgumentException("Invalid ShapeType");
  }
}
