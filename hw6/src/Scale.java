import java.awt.*;

public class Scale implements Movement{
  IShape shape;
  int scalingFactor;
  int startTime;
  int endTime;

  public Scale(IShape shape, int scalingFactor, int startTime, int endTime) {
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
      int oldradius =
      str += "scales from " + this.shape.get+
              " to " + " " + " from t=" + startTime + " to t=" + endTime + "\n";
    }

    return str;
  }
}
