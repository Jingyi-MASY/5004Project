import java.awt.Color;

/**
 * this class represents an oval shape, and it includes all fields and methods that are specific to
 * an oval shape. The fields of this class includes xRadius, yRadius, xRadiusTimeline, and
 * yRadiusTimeline.
 */
public class Oval extends AbstractShape {
  int xRadius;
  int yRadius;
  int[] xRadiusTimeline;
  int[] yRadiusTimeline;

  /**
   * This Oval class constructor initialize a Oval object based on the passed in info.
   *
   * @param name          name of the shape, String type.
   * @param type          type of the shape, ShapeType enum type.
   * @param position      position of the center of the Oval, Point2D type.
   * @param color         color of this Oval, Color type.
   * @param appearTime    time that this Oval shows up in certain animation, int type.
   * @param disappearTime time that this Oval disappears in certain animation, int type.
   * @param xRadius       initial radius on x-axis of this Oval, int type.
   * @param yRadius       initial radius on y-axis of this oval, int type.
   * @throws IllegalArgumentException if the appearance time range is invalid.
   */
  public Oval(String name, ShapeType type, Point2D position, Color color, int appearTime,
              int disappearTime, int xRadius, int yRadius) {

    super(name, type, position, color, appearTime, disappearTime, xRadius, yRadius);
    if (type != ShapeType.OVAL) {
      throw new IllegalArgumentException("This should be an oval");
    }
    this.xRadius = xRadius;
    this.yRadius = yRadius;
    int timeRange = disappearTime - appearTime + 1;
    this.xRadiusTimeline = new int[timeRange];
    this.yRadiusTimeline = new int[timeRange];
    for (int i = 0; i < timeRange; i++) {
      xRadiusTimeline[i] = xRadius;
    }
    for (int i = 0; i < timeRange; i++) {
      yRadiusTimeline[i] = yRadius;
    }
  }

  /**
   * this method gets and returns a list of xRadius of this oval during the entire time of its
   * appearance.
   *
   * @return xRadius of this oval at every moment of the timeline
   */
  public int[] getXRadiusTimeline() {
    return xRadiusTimeline;
  }

  /**
   * this method gets and returns a list of yRadius of this oval during the entire time of its
   * appearance.
   *
   * @return yRadius of this oval at every moment of the timeline
   */
  public int[] getYRadiusTimeline() {
    return yRadiusTimeline;
  }

  /**
   * this methods returns the xRadius value at certain point of time.
   *
   * @param time time that is looking for.
   * @return the xRadius of this oval at the passed in time.
   * @throws IllegalArgumentException if the passed in time if out of range.
   */
  public int getXRadiusAt(int time) throws IllegalArgumentException {
    if (time < appearTime || time > disappearTime) {
      throw new IllegalArgumentException("At this time the shape is not appeared.");
    }
    int timeIndex = time - appearTime;
    return this.xRadiusTimeline[timeIndex];
  }

  /**
   * this method returns the yRadius value at certain point of time.
   *
   * @param time time that is looking for
   * @return yRadius of this oval at the passed in time.
   * @throws IllegalArgumentException if the passed in time if out of range.
   */
  public int getYRadiusAt(int time) throws IllegalArgumentException {
    if (time < appearTime || time > disappearTime) {
      throw new IllegalArgumentException("At this time the shape is not appeared.");
    }
    int timeIndex = time - appearTime;
    return this.yRadiusTimeline[timeIndex];
  }

  /**
   * This method adds a scaling movement to this oval shape.
   *
   * @param factor    factor to use to measure dimension. new dimension = old dimension * factor.
   * @param startTime target start time of this scaling movement, int type.
   * @param endTime   target end time of this scaling movement, int type.
   * @throws IllegalArgumentException if the start time and end time stays out of the appearance
   *                                  time range.
   */
  public void addScale(int factor, int startTime, int endTime) throws IllegalArgumentException {
    if (startTime > endTime || startTime < appearTime || endTime > disappearTime) {
      throw new IllegalArgumentException("invalid appearance time range");
    }
    if (checkIfScalingAvailable(startTime, endTime)) {
      int range = endTime - startTime;
      int k = 1;
      int oldXRadius = this.getXRadiusAt(startTime);
      int oldYRadius = this.getYRadiusAt(startTime);

      for (int i = 0; i < endTime - startTime; i++) {
        isScalingStatus[startTime - appearTime + i] = 1;
        xRadiusTimeline[startTime - appearTime + i] =
                oldXRadius + (k / range) * (oldXRadius * factor - oldXRadius);
        yRadiusTimeline[startTime - appearTime + i] =
                oldYRadius + (k / range) * (oldYRadius * factor - oldYRadius);
        k++;
      }
      //change radius after since
      for (int j = endTime; j < disappearTime; j++) {
        xRadiusTimeline[j - appearTime] = oldXRadius * factor;
        yRadiusTimeline[j - appearTime] = oldYRadius * factor;
      }
      this.listOfMovements.add(new Scale(this, factor, startTime, endTime));
    } else {
      throw new IllegalStateException("this shape is not available for a scale");
    }
  }

  @Override
  public Oval getCopy(int time) {
    if (time > appearTime && time < disappearTime) {
      return new Oval(this.name, ShapeType.CIRCLE, this.getPositionAt(time),
              this.getColorAt(time), this.getAppearTime(), this.disappearTime,
              this.getXRadiusAt(time), this.getYRadiusAt(time));
    }
    return null;
  }

  @Override
  public String getDimensionChange(int time, int factor) {
    int oldXRadius = this.getXRadiusAt(time);
    int oldYRadius = this.getYRadiusAt(time);
    return "scales from xRadius: " + oldXRadius + ", yRadius: " + oldYRadius
            + ", to xRadius: " + oldXRadius * factor + ", yRadius: " + oldYRadius * factor;
  }


  /**
   * This gets and returns a specific string representation desired for this oval.
   *
   * @return a string representation of this oval.
   */
  @Override
  public String toString() {
    String str = "";
    str += "Name: " + this.name + System.lineSeparator();
    str += "Type: " + this.type.toString() + System.lineSeparator();
    str += "Center: " + this.getPositionAt(appearTime).toString() + ", x Radius: "
            + this.getXRadiusAt(appearTime)
            + ", y Radius: " + this.getYRadiusAt(appearTime) + " Color: "
            + this.getColorAt(appearTime).toString() + System.lineSeparator();
    str += "Appears at: t=" + this.appearTime + System.lineSeparator();
    str += "Disappears at t=" + this.disappearTime + System.lineSeparator();
    return str;
  }
}
