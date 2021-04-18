package cs5004.animator.model;

/**
 * This class represents a Scale movement that implements the movement interface, this class
 * includes all the fields and methods that are specific to a a Scale movement. The fields of this
 * class includes a shape, a scaling factor, a start time, and an end time.
 */
public class WidthScale implements Movement {
  protected IShape shape;
  protected int oldPara;
  protected int newPara;
  protected int startTime;
  protected int endTime;

  /**
   * this constructor initializes this Scale movement based on the following parameters.
   *
   * @param shape         shape that would conduct this movement, IShape type.
   * @param oldPara       the initial length of the parameter.
   * @param newPara       the target length of the parameter.
   * @param startTime     the start time of this movement, int type.
   * @param endTime       the end time of this movement, int type.
   */
  public WidthScale(IShape shape, int oldPara, int newPara, int startTime, int endTime)
          throws IllegalArgumentException {
    this.shape = shape;
    this.oldPara = oldPara;
    this.newPara = newPara;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public String display() {
    String str = shape.getName() + " ";
    if (shape.getType() == ShapeType.RECTANGLE) { //if shape is a rectangle
      str += "changes width from " + this.oldPara + " to " + this.newPara;
      str += " from time t=" + startTime + " to t=" + endTime;
      return str;
    } else { //if shape is a ellipse
      str += "changes xDiameter from " + this.oldPara + " to " + this.newPara;
      str += " from time t=" + startTime + " to t=" + endTime;
      return str;
    }
  }

  @Override
  public int getStartTime() {
    return this.startTime;
  }

  @Override
  public int getEndTime() {
    return this.endTime;
  }

  @Override
  public String getMotionType() {
    return "widthScale";
  }

  public int getOldPara() {
    return oldPara;
  }

  public int getNewPara() {
    return newPara;
  }
}
