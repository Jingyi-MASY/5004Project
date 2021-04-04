import java.awt.*;

public class Circle extends AbstractShape{
  private int radius;
  private int[] radiusTimeline;

  public Circle(String name, ShapeType type, Point2D position, Color color, int appearTime, int disappearTime, int radius) throws IllegalArgumentException {
    super(name, type, position, color, appearTime, disappearTime);
    this.radius = radius;
    int timeRange = disappearTime - appearTime;
    this.radiusTimeline = new int[timeRange];
    for (int r : radiusTimeline) {
      r = radius;
    }
  }

  /**
   *
   * @return  radius of this circle at every moment of the timeline
   */
  public int[] getRadiusTimeline() {
    return radiusTimeline;
  }

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
      return new Circle(this.name, ShapeType.CIRCLE, this.getPositionAt(time), this.getColorAt(time), this.getAppearTime(), this.disappearTime, this.getRadiusAt(time));
    }
    return null;
  }

  public void addScale(int factor, int startTime, int endTime) throws IllegalArgumentException {
    if (startTime < endTime || startTime < appearTime || endTime > disappearTime) {
      throw new IllegalArgumentException("invalid appearance time range");
    }
    if (checkIfScalingAvailable(startTime, endTime)) {
      int range = endTime - startTime;
      int k = 1;
      int oldRadius = this.getRadiusAt(startTime);
      for (int i = 0; i < endTime - startTime; i++) {
        isScalingStatus[startTime + i] = 1;
        radiusTimeline[startTime + i] = oldRadius + (k / range) * (oldRadius * factor - oldRadius);
        k++;
        //anything else needs to be done here??
        //change radius after since
      }
      for (int j = endTime; j < disappearTime; j++) {
        radiusTimeline[j]= oldRadius * factor;
      }
      this.listOfMovements.add(new Scale(this, factor, startTime, endTime));
    } else {
      throw new IllegalStateException("this shape is not available for a scale");
    }
  }

  @Override
  public IShape statusAt(int time) throws IllegalArgumentException {
    return new Circle(this.name, this.type, this.getPositionAt(time), this.getColorAt(time),
            this.appearTime, this.disappearTime, this.getRadiusAt(time));
  }

  @Override
  public String getDimensionChange(int time, int factor) {
    return "scales from Radius: " + this.getRadiusAt(time) + ", to Radius: " + this.getRadiusAt(time)*factor;
  }

  @Override
  public String toString() {
    String str = "";
    str += "Name: " + this.name + "\n";
    str += "Type: " + this.type.toString() + "\n";
    str += "Center: " + this.getPositionAt(appearTime);
    str += ", Radius: " + this.getRadiusAt(appearTime);
    str += ", Color: " + this.getColorAt(appearTime).toString() + "\n";
    str += "Appears at t=" + this.appearTime + "\n";
    str += "Disappears at t=" + this.disappearTime + "\n";
    return str;
  }


}
