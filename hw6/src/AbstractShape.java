import java.awt.Color;
import java.util.LinkedList;

/**
 * This is an abstract class that implements IShape. This class includes all common fields and
 * common methods that applies to all shapes. Fields of this AbstractShape class includes: name of
 * the shape, type of the shape, position (Point2D) of this shape, color of this shape (Color),
 * appear time of this shape (int), disappear time of this shape (int), moving status (int[]),
 * changing color status (int[]), scaling status (int[]), positions timeline, color status timeline,
 * dimension status timeline (lists)
 */
public abstract class AbstractShape implements IShape {
  protected String name;
  protected ShapeType type;
  protected Point2D position;
  protected Color color;
  protected int appearTime;
  protected int disappearTime;
  protected int[] isMovingStatus;
  protected int[] isChangingColorStatus;
  protected int[] isScalingStatus;
  protected Point2D[] positionTimeline;
  protected Color[] colorTimeline;
  protected LinkedList<Movement> listOfMovements;

  /**
   * The constructor of AbstractShape create an abstract shape based on the passed in parameters.
   *
   * @param name          name of this shape, String type.
   * @param type          type of this shape, ShapeType enum.
   * @param position      the position of this shape, Point2D type.
   * @param color         color of this shape, Color type.
   * @param appearTime    appear time of this shape, int.
   * @param disappearTime disappear time of this shape, int.
   * @param para          parameter of a given shape
   * @throws IllegalArgumentException if the appear time and disappear time is invalid.
   */
  public AbstractShape(String name, ShapeType type, Point2D position, Color color, int appearTime,
                       int disappearTime, int para) throws IllegalArgumentException {
    if (para <= 0) {
      throw new IllegalArgumentException("invalid shape parameter");
    }
    ShapeBuilder(name, type, position, color, appearTime, disappearTime);
  }

  /**
   * The constructor of AbstractShape create an abstract shape based on the passed in parameters.
   *
   * @param name          name of this shape, String type.
   * @param type          type of this shape, ShapeType enum.
   * @param position      the position of this shape, Point2D type.
   * @param color         color of this shape, Color type.
   * @param appearTime    appear time of this shape, int.
   * @param disappearTime disappear time of this shape, int.
   * @param para1         first parameter of a given shape
   * @param para2         second parameter of a given shape
   * @throws IllegalArgumentException if the appear time and disappear time is invalid.
   */
  public AbstractShape(String name, ShapeType type, Point2D position, Color color, int appearTime,
                       int disappearTime, int para1, int para2) throws IllegalArgumentException {
    if (para1 <= 0 || para2 <= 0) {
      throw new IllegalArgumentException("invalid shape parameter");
    }
    ShapeBuilder(name, type, position, color, appearTime, disappearTime);
  }


  private void ShapeBuilder(String name, ShapeType type, Point2D position, Color color, int appearTime,
                            int disappearTime) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException("invalid name");
    } else if (type == null) {
      throw new IllegalArgumentException("invalid shape type");
    } else if (position == null) {
      throw new IllegalArgumentException("invalid position");
    } else if (color == null) {
      throw new IllegalArgumentException("invalid color");
    }

    if (disappearTime < appearTime || appearTime < 0 || disappearTime > 100) {
      throw new IllegalArgumentException("invalid appearance time range");
    }
    int timeRange = disappearTime - appearTime;

    this.name = name;
    this.type = type;
    this.position = position;
    this.appearTime = appearTime;
    this.disappearTime = disappearTime;
    this.isMovingStatus = new int[timeRange];
    this.isChangingColorStatus = new int[timeRange];
    this.isScalingStatus = new int[timeRange];
    this.positionTimeline = new Point2D[timeRange];
    for (Point2D p : positionTimeline) {
      p = position;
    }
    this.colorTimeline = new Color[timeRange];
    for (Color c : colorTimeline) {
      c = color;
    }
    this.listOfMovements = new LinkedList<>();
  }


  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public ShapeType getType() {
    return this.type;
  }

  @Override
  public Point2D getPositionAt(int time) throws IllegalArgumentException {
    if (time < appearTime || time > disappearTime) {
      throw new IllegalArgumentException("At this time the shape is not appeared.");
    }
    int timeIndex = time - appearTime;
    return positionTimeline[timeIndex];
  }

  @Override
  public Color getColorAt(int time) throws IllegalArgumentException {
    if (time < appearTime || time > disappearTime) {
      throw new IllegalArgumentException("At this time the shape is not appeared.");
    }
    int timeIndex = time - appearTime;
    return colorTimeline[timeIndex];
  }

  @Override
  public int getAppearTime() {
    return appearTime;
  }

  @Override
  public int getDisappearTime() {
    return disappearTime;
  }

  @Override
  public int[] getMovingStatus() {
    return isMovingStatus;
  }

  @Override
  public int[] getColorChangingStatus() {
    return isChangingColorStatus;
  }

  @Override
  public int[] getScalingStatus() {
    return isScalingStatus;
  }

  @Override
  public Color[] getColorTimeline() {
    return colorTimeline;
  }

  @Override
  public Point2D[] getMovingTimeline() {
    return positionTimeline;
  }


  /**
   * This method checks if changing color movement is available for this shape from start time to
   * end time, which means check if the time range has been occupied for color changing movement.
   *
   * @param startTime target start time to have a color change movement, int type.
   * @param endTime   target end time to have a color change movement, int type.
   * @return true if this shape is available for this color change move, false otherwise.
   */
  protected boolean checkIfChangingColorAvailable(int startTime, int endTime) {
    for (int i = 0; i < endTime - startTime; i++) {
      if (isChangingColorStatus[i] == 1) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void addChangeColor(Color color, int startTime, int endTime) throws IllegalStateException {
    if (startTime < endTime || startTime < appearTime || endTime > disappearTime) {
      throw new IllegalArgumentException("invalid appearance time range");
    }
    if (checkIfChangingColorAvailable(startTime, endTime)) {
      int fromIndex = startTime - appearTime;
      int toIndex = endTime - appearTime;
      int range = endTime - startTime;
      int k = 1;
      Color oldColor = this.getColorAt(startTime);
      for (int i = 0; i < endTime - startTime; i++) {
        isChangingColorStatus[startTime + i] = 1;
        int newRGB = (k / range) * (color.getRGB() - oldColor.getRGB());//difference
        colorTimeline[startTime + i] = new Color(oldColor.getRGB() + newRGB);
        k++;
      }
      //change color since after
      for (int j = endTime; j < disappearTime; j++) {
        colorTimeline[j] = color;
      }
      this.listOfMovements.add(new ColorChange(this, color, startTime, endTime));
    } else {
      throw new IllegalStateException("this shape is not available for a color change");
    }

  }

  /**
   * This method checks if scaling movement is available for this shape from start time to end time,
   * which means to check if the time range has been occupied for scaling movement.
   *
   * @param startTime target start time to have a scaling scaling movement, int type.
   * @param endTime   target end time to have a scaling movement, int type.
   * @return true if the shape if available for a scaling movement, false otherwise.
   */
  protected boolean checkIfScalingAvailable(int startTime, int endTime) {
    for (int i = 0; i < endTime - startTime; i++) {
      if (isScalingStatus[startTime + i] == 1) {
        return false;
      }
    }
    return true;
  }

  /**
   * This method checks if moving movement is available for this shape form start time to end time,
   * which means to check if the time range has been occupied for moving movement.
   *
   * @param startTime target start time to have a moving movement, int type.
   * @param endTime   target end time to have a moving movement, int type.
   * @return true if the shape is available for a moving movement, false otherwise.
   */
  protected boolean checkIfMovingAvailable(int startTime, int endTime) {
    for (int i = 0; i < endTime - startTime; i++) {
      if (isMovingStatus[startTime + i] == 1) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void addMove(Point2D newPosition, int startTime, int endTime)
          throws IllegalArgumentException {
    if (startTime < endTime || startTime < appearTime || endTime > disappearTime) {
      throw new IllegalArgumentException("invalid appearance time range");
    }
    if (checkIfMovingAvailable(startTime, endTime)) {
      int range = endTime - startTime;
      int k = 1;
      int oldX = this.getPositionAt(startTime).getX();
      int oldY = this.getPositionAt(startTime).getY();
      for (int i = 0; i < endTime - startTime; i++) {
        isMovingStatus[startTime + i] = 1;
        int newX = (k / range) * (newPosition.getX() - oldX);
        int newY = (k / range) * (newPosition.getY() - oldY);
        positionTimeline[startTime + i] = new Point2D(oldX + newX, oldY + newY);
        k++;
      }
      //change position since after
      for (int j = endTime; j < disappearTime; j++) {
        positionTimeline[j] = newPosition;
      }
      this.listOfMovements.add(new Move(this, newPosition, startTime, endTime));
    } else {
      throw new IllegalStateException("this shape is not available for moving");
    }
  }

  @Override
  public LinkedList<Movement> getMovementList() {
    return listOfMovements;
  }
}
