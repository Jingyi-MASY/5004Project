package cs5004.animator;
import java.awt.Color;

/**
 * This Circle class represents a class of Circle, it includes fields and methods that are specific
 * to Circle class. Fields of this class include radius(int) and radius timeline (list of int) that
 * tracks radius status along the time of appearance of this shape.
 */
public class Circle extends AbstractShape {
  int radius;
  private int[] radiusTimeline;

  /**
   * This Circle class constructor initialize a circle object based on the passed in info.
   *
   * @param name          name of the shape, String type.
   * @param type          type of the shape, ShapeType enum type.
   * @param position      position of the center of the circle, Point2D type.
   * @param color         color of this circle, Color type.
   * @param appearTime    time that this circle shows up in certain animation, int type.
   * @param disappearTime time that this circle disappears in certain animation, int type.
   * @param radius        initial radius of this circle, int type.
   * @throws IllegalArgumentException if the appearance time range is invalid.
   */
  public Circle(String name, ShapeType type, Point2D position, Color color, int appearTime,
                int disappearTime, int radius) throws IllegalArgumentException {
    super(name, type, position, color, appearTime, disappearTime, radius);
    if (type != ShapeType.CIRCLE) {
      throw new IllegalArgumentException("This should be a circle");
    }
    this.radius = radius;
    int timeRange = disappearTime - appearTime;
    this.radiusTimeline = new int[timeRange];
    for (int i = 0; i < timeRange; i++) {
      radiusTimeline[i] = radius;
    }
  }

  /*
   * This method returns a list of radius status of this circle to track the radius status during
   * the appearance time of this circle.
   * @return  radius of this circle at every moment of the appearance time.
   */
  protected int[] getRadiusTimeline() {
    return radiusTimeline;
  }

  /**
   * This method gets and returns the radius of this circle at certain point of time.
   *
   * @param time time that is looking for, int type.
   * @return the radius of this circle at the passed in time.
   * @throws IllegalArgumentException if the time is out of the appearance time range.
   */
  public int getRadiusAt(int time) throws IllegalArgumentException {
    if (time < appearTime || time > disappearTime) {
      throw new IllegalArgumentException("At this time the shape is not appeared.");
    }
    int timeIndex = time - appearTime;
    return this.radiusTimeline[timeIndex];
  }

  @Override
  public Circle getCopy(int time) {
    if (time > appearTime && time < disappearTime) {
      return new Circle(this.name, ShapeType.CIRCLE, this.getPositionAt(time),
              this.getColorAt(time), this.getAppearTime(), this.disappearTime,
              this.getRadiusAt(time));
    }
    return null;
  }

  /**
   * This method adds a scaling movement to this circle.
   *
   * @param factor    factor to use to measure dimension. new dimension = old dimension * factor.
   * @param startTime target start time of this scaling movement, int type.
   * @param endTime   target end time of this scaling movement, int type.
   * @throws IllegalArgumentException if the start time and end time stays out of the appearance
   *                                  time range.
   */
  public void addScale(int factor, int startTime, int endTime) throws IllegalArgumentException {
    if (factor <= 0) {
      throw new IllegalArgumentException("scaling factor cannot be zero or negative number");
    }
    if (startTime > endTime || startTime < appearTime || endTime > disappearTime) {
      throw new IllegalArgumentException("invalid appearance time range");
    }
    if (!checkIfScalingAvailable(startTime, endTime)) {
      throw new IllegalStateException("this shape is not available for a scale");
    }
    int range = endTime - startTime;
    int k = 1;
    int oldRadius = this.getRadiusAt(startTime);
    for (int i = 0; i < endTime - startTime; i++) {
      isScalingStatus[startTime - appearTime + i] = 1;
      radiusTimeline[startTime - appearTime + i] = oldRadius
              + ((k * (oldRadius * factor - oldRadius)) / range);
      k++;
    }
    //change radius after since
    for (int j = endTime; j < disappearTime; j++) {
      radiusTimeline[j - appearTime] = oldRadius * factor;
    }
    this.listOfMovements.add(new Scale(this, factor, startTime, endTime));
  }

  @Override
  public String getDimensionChange(int time, int factor) {
    //old dimension is the dimension at the moment before the start time starts
    //which is at the end of last time unit ends (t = startTime - 1)
    int oldRadius;
    if (time == appearTime) {
      oldRadius = this.radius;
    } else {
      oldRadius = this.getRadiusAt(time - 1);
    }
    return "scales from Radius: " + oldRadius + ", to Radius: " + oldRadius * factor;
  }

  /**
   * This gets and returns a specific string representation desired for this circle.
   *
   * @return a string representation of this circle.
   */
  @Override
  public String toString() {
    String str = "";
    str += "Name: " + this.name + System.lineSeparator();
    str += "Type: " + this.type.toString() + System.lineSeparator();
    str += "Center: " + this.getPositionAt(appearTime);
    str += ", Radius: " + this.getRadiusAt(appearTime);
    str += ", Color: " + this.getColorAt(appearTime).toString() + System.lineSeparator();
    str += "Appears at t=" + this.appearTime + System.lineSeparator();
    str += "Disappears at t=" + this.disappearTime + System.lineSeparator();
    return str;
  }
}
