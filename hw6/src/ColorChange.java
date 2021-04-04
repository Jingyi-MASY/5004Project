import java.awt.*;

public class ColorChange implements Movement{
  IShape shape;
  Color targetColor;
  int startTime;
  int endTime;

  public ColorChange(IShape shape, Color targetColor, int startTime, int endTime) {
    this.shape = shape;
    this.targetColor = targetColor;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public String display() {
    String str = "";
    str += "Shape " + this.shape.getName() + " ";
    str += "changes color from " + this.shape.getColorAt(startTime).getRGB() +
            " to " +targetColor.getRGB() + " from t=" + startTime + " to t=" + endTime + "\n";
    return str;
  }

  @Override
  public int getStartTime() {
    return this.startTime;
  }
}
