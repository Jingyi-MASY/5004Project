import java.awt.Color;

/**
 * this Rectangle class represents a rectangle shape, and this class includes all the fields and
 * methods that are specific to the rectangle class. The fields of this class includes height,
 * width, heightTimeline and widthTimeline.
 */
public class Rectangle extends AbstractShape {
  int height;
  int width;
  int[] heightTimeline;
  int[] widthTimeline;

  /**
   * This rectangle class constructor initialize a rectangle object based on the passed in info.
   *
   * @param name          name of the shape, String type.
   * @param type          type of the shape, ShapeType enum type.
   * @param position      position of the lower left corner of the rectangle, Point2D type.
   * @param color         color of this rectangle, Color type.
   * @param appearTime    time that this rectangle shows up in certain animation, int type.
   * @param disappearTime time that this rectangle disappears in certain animation, int type.
   * @param width         initial width of this rectangle, int type.
   * @param height        initial height of this rectangle, int type.
   * @throws IllegalArgumentException if the appearance time range is invalid.
   */
  public Rectangle(String name, ShapeType type, Point2D position, Color color, int appearTime,
                   int disappearTime, int width, int height) {
    super(name, type, position, color, appearTime, disappearTime, width, height);
    if (!type.equals(ShapeType.RECTANGLE)) {
      throw new IllegalArgumentException("This should be a rectangle");
    }
    this.width = width;
    this.height = height;
    int timeRange = disappearTime - appearTime + 1;
    this.widthTimeline = new int[timeRange];
    this.heightTimeline = new int[timeRange];
    for (int i = 0; i < timeRange; i++) {
      widthTimeline[i] = width;
    }
    for (int i = 0; i < timeRange; i++) {
      heightTimeline[i] = height;
    }
  }

  /**
   * this method gets and returns a list of width of this rectangle during the entire time of its
   * appearance.
   *
   * @return width of this rectangle at every moment of the timeline
   */
  protected int[] getWidthTimeline() {
    return widthTimeline;
  }

  /**
   * this method gets and returns a list of height of this rectangle during the entire time of its
   * appearance.
   *
   * @return height of this rectangle at every moment of the timeline
   */
  protected int[] getHeightTimeline() {
    return heightTimeline;
  }

  /**
   * this methods returns the width value at certain point of time.
   *
   * @param time time that is looking for.
   * @return the width of this rectangle at the passed in time.
   * @throws IllegalArgumentException if the passed in time if out of range.
   */
  public int getWidthAt(int time) throws IllegalArgumentException {
    if (time < appearTime || time > disappearTime) {
      throw new IllegalArgumentException("At this time the shape is not appeared.");
    }
    int timeIndex = time - appearTime;
    return this.widthTimeline[timeIndex];
  }

  /**
   * this method returns the height value at certain point of time.
   *
   * @param time time that is looking for
   * @return height of this rectangle at the passed in time.
   * @throws IllegalArgumentException if the passed in time if out of range.
   */
  public int getHeightAt(int time) throws IllegalArgumentException {
    if (time < appearTime || time > disappearTime) {
      throw new IllegalArgumentException("At this time the shape is not appeared.");
    }
    int timeIndex = time - appearTime;
    return this.heightTimeline[timeIndex];
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
    if (startTime > endTime || startTime < appearTime || endTime > disappearTime) {
      throw new IllegalArgumentException("invalid appearance time range");
    }
    if (checkIfScalingAvailable(startTime, endTime)) {
      int range = endTime - startTime;
      int k = 1;
      int oldWidth = this.getWidthAt(startTime);
      int oldHeight = this.getHeightAt(startTime);

      for (int i = 0; i < endTime - startTime; i++) {
        isScalingStatus[startTime - appearTime + i] = 1;
        widthTimeline[startTime - appearTime + i] = oldWidth
                + (k / range) * (oldWidth * factor - oldWidth);
        heightTimeline[startTime - appearTime + i] = oldHeight
                + (k / range) * (oldHeight * factor - oldHeight);
        k++;
        //anything else needs to be done here?
      }
      //change radius after since
      for (int j = endTime; j < disappearTime; j++) {
        widthTimeline[j - appearTime] = oldWidth * factor;
        heightTimeline[j - appearTime] = oldHeight * factor;
      }
      this.listOfMovements.add(new Scale(this, factor, startTime, endTime));
    } else {
      throw new IllegalStateException("this shape is not available for a scale");
    }
  }

  @Override
  public Rectangle getCopy(int time) {
    if (time > appearTime && time < disappearTime) {
      return new Rectangle(this.name, ShapeType.RECTANGLE, this.getPositionAt(time),
              this.getColorAt(time), this.getAppearTime(), this.disappearTime,
              this.getWidthAt(time), this.getHeightAt(time));
    }
    return null;
  }

  @Override
  public String getDimensionChange(int time, int factor) {
    int oldWidth = this.getWidthAt(time);
    int oldHeight = this.getHeightAt(time);
    return "scales from Width: " + oldWidth + ", Height: " + oldHeight
            + ", to Width: " + oldWidth * factor + ", Height: " + oldHeight * factor;
  }


  /**
   * This gets and returns a specific string representation desired for this rectangle.
   *
   * @return a string representation of this rectangle.
   */
  @Override
  public String toString() {
    String str = "";
    str += "Name: " + this.getName() + System.lineSeparator();
    str += "Type: " + this.type.toString() + System.lineSeparator();
    str += "Lower left corner: " + this.getPositionAt(appearTime).toString() + ", Width: "
            + this.getWidthAt(appearTime)
            + ", Height: " + this.getHeightAt(appearTime) + " Color: "
            + this.getColorAt(appearTime).toString() + System.lineSeparator();
    str += "Appears at: t=" + this.appearTime + System.lineSeparator();
    str += "Disappears at t=" + this.disappearTime + System.lineSeparator();
    return str;
  }
}
