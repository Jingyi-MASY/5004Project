package cs5004.animator.model;

import java.awt.Color;

/**
 * This class represents a ColorChange movement that implements the movement interface, this class
 * includes all the fields and methods that are specific to a a ColorChange movement. The fields of
 * this class includes a shape, a target color, a start time, and an end time.
 */
public class ColorChange implements Movement {
  protected IShape shape;
  protected Color initialColor;
  protected Color targetColor;
  protected int startTime;
  protected int endTime;

  /**
   * This constructor initialize a ColorChange movement based on the following parameters.
   *
   * @param shape       shape that would conduct this movement, IShape type.
   * @param initialColor  initial color that the shape would change from, Color type.
   * @param targetColor target color that the shape would turn to after completing this movement,
   *                    Color type.
   * @param startTime   the start time of this movement, int type.
   * @param endTime     the end time of this movement, int type.
   */
  public ColorChange(IShape shape, Color initialColor, Color targetColor, int startTime,
                     int endTime) {
    this.shape = shape;
    this.initialColor = initialColor;
    this.targetColor = targetColor;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public String display() {
    int r1 = initialColor.getRed();
    int g1 = initialColor.getGreen();
    int b1 = initialColor.getBlue();
    int r2 = targetColor.getRed();
    int g2 = targetColor.getGreen();
    int b2 = targetColor.getBlue();
    String str = shape.getName() + " ";
    str += "changes from " + "Color(r=" + r1 + ",g=" + g1 + ",b=" + b1 + ")";
    str += " to " + "Color(r=" + r2 + ",g=" + g2 + ",b=" + b2 + ")";
    str += " from time t=" + startTime + " to t="
            + endTime;
    return str;
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
    return "color";
  }

  public Color getInitial() {
    return initialColor;
  }

  public Color getTargetColor() {
    return targetColor;
  }
}
