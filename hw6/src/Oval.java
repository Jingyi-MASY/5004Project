import java.awt.*;

public class Oval extends AbstractShape{
  int xRadius;
  int yRadius;
  int[] xRadiusTimeline;
  int[] yRadiusTimeline;

  public Oval(String name, ShapeType type, Point2D position, Color color, int appearTime, int disappearTime, int xRadius, int yRadius) {
    super(name, type, position, color, appearTime, disappearTime);
    this.xRadius = xRadius;
    this.yRadius = yRadius;
    int timeRange = disappearTime - appearTime;
    this.xRadiusTimeline = new int[timeRange];
    for (int r : xRadiusTimeline) {
      r = xRadius;
    }
    for (int r : yRadiusTimeline) {
      r = yRadius;
    }
  }

  /**
   *
   * @return  radius of this circle at every moment of the timeline
   */
  public int[] getXRadiusTimeline() {
    return xRadiusTimeline;
  }

  public int[] getyRadiusTimeline() {
    return yRadiusTimeline;
  }

  public int getXRadiusAt(int time) throws IllegalArgumentException {
    if (time < appearTime || time > disappearTime) {
      throw new IllegalArgumentException("At this time the shape is not appeared.");
    }
    int timeIndex = time - appearTime;
    return this.xRadiusTimeline[timeIndex];
  }

  public int getYRadiusAt(int time) throws IllegalArgumentException {
    if (time < appearTime || time > disappearTime) {
      throw new IllegalArgumentException("At this time the shape is not appeared.");
    }
    int timeIndex = time - appearTime;
    return this.yRadiusTimeline[timeIndex];
  }

  public void addScale(int factor, int startTime, int endTime) throws IllegalArgumentException {
    if (startTime < endTime || startTime < appearTime || endTime > disappearTime) {
      throw new IllegalArgumentException("invalid appearance time range");
    }
    if (checkIfScalingAvailable(startTime, endTime)) {
      int range = endTime - startTime;
      int k = 1;
      int oldXRadius = this.getXRadiusAt(startTime);
      int oldYRadius = this.getYRadiusAt(startTime);

      for (int i = 0; i < endTime - startTime; i++) {
        isScalingStatus[startTime + i] = 1;
        xRadiusTimeline[startTime + i] = oldXRadius + (k / range) * (oldXRadius * factor - oldXRadius);
        yRadiusTimeline[startTime + i] = oldYRadius + (k / range) * (oldYRadius * factor - oldYRadius);
        k++;
        //anything else needs to be done here?
      }
      //change radius after since
      for (int j = endTime; j < disappearTime; j++) {
        xRadiusTimeline[j] = oldXRadius * factor;
        yRadiusTimeline[j] = oldYRadius * factor;
      }
    }
  }

  @Override
  public Oval getCopy(int time) {
    if (time > appearTime && time < disappearTime) {
      return new Oval(this.name, ShapeType.CIRCLE, this.getPositionAt(time), this.getColorAt(time), this.getAppearTime(), this.disappearTime, this.getXRadiusAt(time), this.getYRadiusAt(time));
    }
    return null;
  }

  @Override
  public IShape statusAt(int time) throws IllegalArgumentException {
    return new Oval(this.name, this.type, this.getPositionAt(time), this.getColorAt(time),
            this.appearTime, this.disappearTime, this.getXRadiusAt(time), this.getYRadiusAt(time));
  }

  @Override
  public String toString() {
    String str = "";
    str += "Name: " + this.name + "\n";
    str += "Type: " + this.type.toString() + "\n";
    str += "Center: " + this.position.toString() + ", x Radius: " + this.xRadius
            + ", y Radius: " + this.yRadius + "Color: " + this.color.toString() + "\n";
    str += "Appears at: t=" + this.appearTime + "\n";
    str += "Disappears at t=" + this.disappearTime + "\n";
    return str;
  }
}
