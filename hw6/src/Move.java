/**
 * This class represents a Move movement class that implements movement interface, it includes all
 * fields and methods that are specific for this class. the fields of this class includes a shape, a
 * target position, a start time and an end time.
 */
public class Move implements Movement {
  IShape shape;
  Point2D targetPosition;
  int startTime;
  int endTime;

  /**
   * this constructor initialize a Move movement based on the following parameters.
   *
   * @param shape          shape that will conduct this move, IShape type.
   * @param targetPosition the target position of where this movement would move the shape to,
   *                       Point2D type.
   * @param startTime      the start time of this move, int type.
   * @param endTime        the end time of this move, int type.
   */
  public Move(IShape shape, Point2D targetPosition, int startTime, int endTime) {
    this.shape = shape;
    this.targetPosition = targetPosition;
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
    str += "moves from " + this.shape.getPositionAt(startTime)
            + " to " + this.targetPosition + " from t=" + startTime
            + " to t=" + endTime + System.lineSeparator();
    return str;
  }
}
