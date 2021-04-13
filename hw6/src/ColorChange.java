import java.awt.Color;

/**
 * This class represents a ColorChange movement that implements the movement interface, this class
 * includes all the fields and methods that are specific to a a ColorChange movement. The fields of
 * this class includes a shape, a target color, a start time, and an end time.
 */
public class ColorChange implements Movement {
  IShape shape;
  Color targetColor;
  int startTime;
  int endTime;

  /**
   * This constructor initialize a ColorChange movement based on the following parameters.
   *
   * @param shape       shape that would conduct this movement, IShape type.
   * @param targetColor target color that the shape would turn to after completing this movement,
   *                    Color type.
   * @param startTime   the start time of this movement, int type.
   * @param endTime     the end time of this movement, int type.
   */
  public ColorChange(IShape shape, Color targetColor, int startTime, int endTime) {
    this.shape = shape;
    this.targetColor = targetColor;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public String display() {
    //old color is the color at the moment before the start time of this motion starts
    //which is at the end of last time unit ends (t = startTime - 1)
    Color oldColor;
    if (startTime == this.shape.getAppearTime()) {
      oldColor = this.shape.getColor();
    } else {
      oldColor = this.shape.getColorAt(startTime - 1);
    }
    String str = "";
    str += "Shape " + this.shape.getName() + " ";
    str += "changes color from " + oldColor.getRGB()
            + " to " + targetColor.getRGB() + " from t=" + startTime + " to t="
            + endTime + System.lineSeparator();
    return str;
  }

  @Override
  public int getStartTime() {
    return this.startTime;
  }
}
