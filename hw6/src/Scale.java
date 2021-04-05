/**
 * This class represents a Scale movement that implements the movement interface, this class
 * includes all the fields and methods that are specific to a a Scale movement.
 * The fields of this class includes a shape, a scaling factor, a start time, and an end time.
 */
public class Scale implements Movement {
  IShape shape;
  int scalingFactor;
  int startTime;
  int endTime;

  /**
   * this constructor initializes this Scale movement based on the following parameters.
   * @param shape   shape that would conduct this movement, IShape type.
   * @param scalingFactor   the scaling factor, int type.
   * @param startTime   the start time of this movement, int type.
   * @param endTime   the end time of this movement, int type.
   */
  public Scale(IShape shape, int scalingFactor, int startTime, int endTime) throws IllegalArgumentException {
    if (scalingFactor <= 0) {
      throw new IllegalArgumentException("Scaling factor cannot be zero or negative");
    }
    this.shape = shape;
    this.scalingFactor = scalingFactor;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public int getStartTime() {
    return this.startTime;
  }

  @Override
  public String display() {
    String str = "";
    str += "Shape " + this.shape.getName() + " ";
    if (this.shape.getType() == ShapeType.CIRCLE) {
      str += this.shape.getDimensionChange(startTime, scalingFactor) + " from t=" + startTime
              + " to t=" + endTime + System.lineSeparator();
    }
    return str;
  }
}
