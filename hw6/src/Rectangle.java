import java.awt.*;

public class Rectangle extends AbstractShape{
  int height;
  int width;
  int[] heightTimeline;
  int[] widthTimeline;

  public Rectangle(String name, ShapeType type, Point2D position, Color color, int appearTime, int disappearTime, int width, int height) {
    super(name, type, position, color, appearTime, disappearTime);
    this.width = width;
    this.height = height;
    int timeRange = disappearTime - appearTime;
    this.widthTimeline = new int[timeRange];
    for (int r : widthTimeline) {
      r = width;
    }
    for (int r : heightTimeline) {
      r = height;
    }
  }

  /**
   *
   * @return  radius of this circle at every moment of the timeline
   */
  public int[] getwidthTimeline() {
    return widthTimeline;
  }

  public int[] getheightTimeline() {
    return heightTimeline;
  }

  public int getWidthAt(int time) throws IllegalArgumentException {
    if (time < appearTime || time > disappearTime) {
      throw new IllegalArgumentException("At this time the shape is not appeared.");
    }
    int timeIndex = time - appearTime;
    return this.widthTimeline[timeIndex];
  }

  public int getHeightAt(int time) throws IllegalArgumentException {
    if (time < appearTime || time > disappearTime) {
      throw new IllegalArgumentException("At this time the shape is not appeared.");
    }
    int timeIndex = time - appearTime;
    return this.heightTimeline[timeIndex];
  }

  public void addScale(int factor, int startTime, int endTime) throws IllegalArgumentException {
    if (startTime < endTime || startTime < appearTime || endTime > disappearTime) {
      throw new IllegalArgumentException("invalid appearance time range");
    }
    if (checkIfScalingAvailable(startTime, endTime)) {
      int range = endTime - startTime;
      int k = 1;
      int oldwidth = this.getWidthAt(startTime);
      int oldheight = this.getHeightAt(startTime);

      for (int i = 0; i < endTime - startTime; i++) {
        isScalingStatus[startTime + i] = 1;
        widthTimeline[startTime + i] = oldwidth + (k / range) * (oldwidth * factor - oldwidth);
        heightTimeline[startTime + i] = oldheight + (k / range) * (oldheight * factor - oldheight);
        k++;
        //anything else needs to be done here?
      }
      //change radius after since
      for (int j = endTime; j < disappearTime; j++) {
        widthTimeline[j] = oldwidth * factor;
        heightTimeline[j] = oldheight * factor;
      }
      this.listOfMovements.add(new Scale(this, factor, startTime, endTime));
    } else {
      throw new IllegalStateException("this shape is not available for a scale");
    }
  }

  @Override
  public Rectangle getCopy(int time) {
    if (time > appearTime && time < disappearTime) {
      return new Rectangle(this.name, ShapeType.CIRCLE, this.getPositionAt(time), this.getColorAt(time), this.getAppearTime(), this.disappearTime, this.getWidthAt(time), this.getHeightAt(time));
    }
    return null;
  }

  @Override
  public IShape statusAt(int time) throws IllegalArgumentException {
    return new Oval(this.name, this.type, this.getPositionAt(time), this.getColorAt(time),
            this.appearTime, this.disappearTime, this.getWidthAt(time), this.getHeightAt(time));
  }
  
  @Override
  public String toString() {
    String str = "";
    str += "Name: " + this.getName() + "\n";
    str += "Type: " + this.type.toString() + "\n";
    str += "Lower left corner: " + this.position.toString() + ", Width: " + this.width
            + ", Height: " + this.height + "Color: " + this.color.toString() + "\n";
    str += "Appears at: t=" + this.appearTime + "\n";
    str += "Disappears at t=" + this.disappearTime + "\n";
    return str;
  }
}
