import java.awt.*;

public class Move implements Movement{
  IShape shape;
  Point2D targetPosition;
  int startTime;
  int endTime;

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
    str += "moves from " + this.shape.getPositionAt(startTime) +
            " to " + this.targetPosition + " from t=" + startTime + " to t=" + endTime + "\n";
    return str;
  }
}
