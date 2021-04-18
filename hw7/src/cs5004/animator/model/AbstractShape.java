package cs5004.animator.model;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

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
  protected int appearTime; //appear time
  protected int disappearTime;//disappear time

  protected List<Integer> isMovingStatus;
  protected List<Integer> isChangingColorStatus;
  protected List<Integer> isPara1ScalingStatus;
  protected List<Integer> isPara2ScalingStatus;

  protected List<Point2D> positionTimeline;
  protected List<Color> colorTimeline;
  protected List<Integer> para1Timeline;
  protected List<Integer> para2Timeline;

  protected LinkedList<Movement> listOfMovements;

  /**
   * This is the constructor for an abstract shape. To create a shape, a name (String type) and
   * a shape type need to be passed in.
   * @param name  name of this shape, string type.
   * @param type  the shape type of this shape, ShapeType type.
   */
  public AbstractShape(String name, ShapeType type) {
    if (name == null) {
      throw new IllegalArgumentException("invalid name");
    } else if (type == null) {
      throw new IllegalArgumentException("invalid shape type");
    }
    this.name = name;
    this.type = type;

    //below are default value (needs to be changed when the first motion has been added)
    this.appearTime = -1;
    this.disappearTime = -1;

    this.isMovingStatus = new LinkedList<Integer>();
    this.isChangingColorStatus = new LinkedList<Integer>();
    this.isPara1ScalingStatus = new LinkedList<Integer>();
    this.isPara2ScalingStatus = new LinkedList<Integer>();

    this.positionTimeline = new LinkedList<Point2D>();
    this.colorTimeline = new  LinkedList<Color>();
    this.para1Timeline = new LinkedList<Integer>();
    this.para2Timeline = new LinkedList<Integer>();

    this.listOfMovements = new LinkedList<Movement>();
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
  public int getAppearTime() {
    return appearTime;
  }

  @Override
  public int getDisappearTime() {
    return disappearTime;
  }

  @Override
  public Point2D getPositionAt(int time) throws IllegalArgumentException {
    if (time < this.appearTime || time > this.disappearTime) {
      throw new IllegalArgumentException("At this time the shape is not appeared.");
    }
    int timeIndex = time - appearTime;
    return positionTimeline.get(timeIndex);
  }

  @Override
  public Color getColorAt(int time) throws IllegalArgumentException {
    if (time < appearTime || time > disappearTime) {
      throw new IllegalArgumentException("At this time the shape is not appeared.");
    }
    int timeIndex = time - appearTime;
    return colorTimeline.get(timeIndex);
  }

  @Override
  public int getPara1At(int time) throws IllegalArgumentException {
    if (time < appearTime || time > disappearTime) {
      throw new IllegalArgumentException("At this time the shape is not appeared.");
    }
    int timeIndex = time - appearTime;
    return para1Timeline.get(timeIndex);
  }

  @Override
  public int getPara2At(int time) throws IllegalArgumentException {
    if (time < appearTime || time > disappearTime) {
      throw new IllegalArgumentException("At this time the shape is not appeared.");
    }
    int timeIndex = time - appearTime;
    return para2Timeline.get(timeIndex);
  }

  /*
   * This method gets and returns the moving status of every unit of time in a list.
   *
   * @return list of 1 or 0 indicating whether if the shape is in certain moving movement. 1
   *         indicates moving in progress, NOT available to add new move. 0 indicates moving not in
   *         progress, available for new move.
   */
  protected List<Integer> getMovingStatus() {
    return isMovingStatus;
  }

  /*
   * This method gets and returns the color-changing status of every unit of time in a list.
   *
   * @return list of 1 or 0 indicating whether if the shape is in certain color-changing movement.
   *         1 indicates color changing in progress, NOT available to add new color change.
   *         0 indicates color changing not in progress, available for new color change.
   */
  protected List<Integer> getColorChangingStatus() {
    return isChangingColorStatus;
  }

  /*
   * This method gets and returns the scaling status of every unit of time in a list for width.
   *
   * @return list of 1 or 0 indicating whether if the shape is in certain scaling movement. 1
   *         indicates scaling in progress, NOT available to add new scaling.
   *         0 indicates scaling not in progress, available for new scaling.
   */
  protected List<Integer> getPara1ScalingStatus() {
    return isPara1ScalingStatus;
  }

  /*
   * This method gets and returns the scaling status of every unit of time in a list, for height.
   * @return list of 1 or 0 indicating is height is scaling status.
   */
  protected List<Integer> getPara2ScalingStatus() {
    return isPara2ScalingStatus;
  }

  /*
   * This method gets and returns the Color status of this shape at all time of its appearance.
   *
   * @return a list of Color elements that indicates the color status of this shape at every time
   *         unit of its appearance time.
   */
  protected List<Color> getColorTimeline() {
    return colorTimeline;
  }

  /*
   * This method gets and returns the position status of this shape at all time of its appearance.
   *
   * @return a list of Position2D elements that indicates the position of this shape at every time
   *         unit of its appearance time.
   */
  protected List<Point2D> getMovingTimeline() {
    return positionTimeline;
  }


  /*
   * Thie gets and returns the width status timeline.
   */
  protected List<Integer> getPara1Timeline() {
    return this.para1Timeline;
  }

  /*
   * Thie gets and returns the height status timeline.
   */
  protected List<Integer> getPara2Timeline() {
    return this.para2Timeline;
  }


  /*
   * This method checks if changing color movement is available for this shape from start time to
   * end time, which means check if the time range has been occupied for color changing movement.
   *
   * @param startTime target start time to have a color change movement, int type.
   * @param endTime   target end time to have a color change movement, int type.
   * @return true if this shape is available for this color change move, false otherwise.
   */
  protected boolean checkIfChangingColorAvailable(int startTime, int endTime) {
    int from = startTime - appearTime;
    int to = endTime - appearTime;
    for (int i = from; i < to; i++) {
      if (isChangingColorStatus.get(i) == 1) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void updateAppearance(int start, int end) {
    if (this.appearTime == -1 && this.disappearTime == -1) {
      updateTimeline(start, end);
      this.appearTime = start;
      this.disappearTime = end;
    } else {
      if (start < appearTime) {
        updateTimeline(start, disappearTime);
        this.appearTime = start;
      }
      if (end > disappearTime) {
        updateTimeline(appearTime, end);
        this.disappearTime = end;
      }
    }
  }

  /*
   * Thie this a helper method that update and append the status recording timeline lists.
   */
  protected void updateTimeline(int newAppear, int newDisappear) {
    if (this.appearTime == -1 || this.disappearTime == -1) {
      //no appearance time yet, -1 is default value
      int range = newDisappear - newAppear;
      for (int i = 0; i < range; i++) {
        isChangingColorStatus.add(0);
        isMovingStatus.add(0);
        isPara1ScalingStatus.add(0);
        isPara2ScalingStatus.add(0);
        positionTimeline.add(0, null);
        colorTimeline.add(0, null);
        para1Timeline.add(0, null);
        para2Timeline.add(0, null);
      }
    } else { //there was an available appearance time
      //append at front (appear time changes to earlier)
      int frontDiff = appearTime - newAppear;
      for (int i = 0; i < frontDiff; i++) {
        isChangingColorStatus.add(0, 0);
        isMovingStatus.add(0, 0);
        isPara1ScalingStatus.add(0, 0);
        isPara2ScalingStatus.add(0, 0);
        positionTimeline.add(0, null);
        colorTimeline.add(0, null);
        para1Timeline.add(0, null);
        para2Timeline.add(0, null);
      }
      //append at back (disappear time changes to later)
      int backDiff = newDisappear - disappearTime;
      for (int i = 0; i < backDiff; i++) {
        isChangingColorStatus.add(isChangingColorStatus.size(), 0);
        isMovingStatus.add(isMovingStatus.size(), 0);
        isPara1ScalingStatus.add(isPara1ScalingStatus.size(), 0);
        isPara2ScalingStatus.add(isPara2ScalingStatus.size(), 0);
        positionTimeline.add(positionTimeline.size(), null);
        colorTimeline.add(colorTimeline.size(), null);
        para1Timeline.add(para1Timeline.size(), null);
        para2Timeline.add(para2Timeline.size(), null);
      }
    }
  }

  @Override
  public void updateColor(Color color, int startTime, int endTime) {
    int range = endTime - startTime;
    for (int i = 0; i < range; i++) {
      colorTimeline.set(startTime - appearTime + i, color);
    }
  }


  @Override
  public void addChangeColor(Color oldColor, Color newColor, int startTime, int endTime)
          throws IllegalStateException {
    if (!checkIfChangingColorAvailable(startTime, endTime)) {
      throw new IllegalStateException("this shape is not available for a color change");
    }
    int range = endTime - startTime;
    int k = 0;
    for (int i = 0; i < range; i++) {
      isChangingColorStatus.set(startTime - appearTime + i, 1);
      int newRGB = k * (newColor.getRGB() - oldColor.getRGB()) / range;//difference
      colorTimeline.set(startTime - appearTime + i, new Color(oldColor.getRGB() + newRGB));
      k++;
    }
    this.listOfMovements.add(new ColorChange(this, oldColor, newColor, startTime, endTime));
  }

  @Override
  public void updatePosition(Point2D position, int startTime, int endTime) {
    int range = endTime - startTime;
    for (int i = 0; i < range; i++) {
      positionTimeline.set(startTime - appearTime + i, position);
    }
  }

  /*
   * This method checks if moving movement is available for this shape form start time to end time,
   * which means to check if the time range has been occupied for moving movement.
   *
   * @param startTime target start time to have a moving movement, int type.
   * @param endTime   target end time to have a moving movement, int type.
   * @return true if the shape is available for a moving movement, false otherwise.
   */
  protected boolean checkIfMovingAvailable(int startTime, int endTime) {
    for (int i = 0; i < endTime - startTime; i++) {
      if (isMovingStatus.get(startTime - appearTime + i) == 1) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void addMove(Point2D oldPosition, Point2D newPosition, int startTime, int endTime)
          throws IllegalArgumentException {
    if (!checkIfMovingAvailable(startTime, endTime)) {
      throw new IllegalStateException("this shape is not available for moving");
    }
    int range = endTime - startTime;
    int k = 0;
    int oldX = oldPosition.getX();
    int oldY = oldPosition.getY();
    for (int i = 0; i < range; i++) {
      isMovingStatus.set(startTime - appearTime + i, 1);
      int newX = (k * (newPosition.getX() - oldX)) / range;
      int newY = (k * (newPosition.getY() - oldY)) / range;
      positionTimeline.set(startTime - appearTime + i, new Point2D(oldX + newX, oldY + newY));
      k++;
    }
    this.listOfMovements.add(new Move(this, oldPosition, newPosition, startTime, endTime));
  }

  @Override
  public void updateWidth(int width, int startTime, int endTime) {
    int range = endTime - startTime;
    for (int i = 0; i < range; i++) {
      para1Timeline.set(startTime - appearTime + i, width);
    }
  }

  @Override
  public void updateHeight(int height, int startTime, int endTime) {
    int range = endTime - startTime;
    for (int i = 0; i < range; i++) {
      para2Timeline.set(startTime - appearTime + i, height);
    }
  }

  /*
   * This method checks if scaling on width is available for this shape from start time to end time,
   * which means to check if the time range has been occupied for scaling movement.
   *
   * @param startTime target start time to have a scaling scaling movement, int type.
   * @param endTime   target end time to have a scaling movement, int type.
   * @return true if the shape if available for a scaling movement, false otherwise.
   */
  protected boolean checkIfScalingWidthAvailable(int startTime, int endTime) {
    for (int i = 0; i < endTime - startTime; i++) {
      if (isPara1ScalingStatus.get(startTime - appearTime + i) == 1) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void addWidthScale(int oldWidth, int newWidth, int startTime, int endTime)
          throws IllegalArgumentException {
    if (!checkIfScalingWidthAvailable(startTime, endTime)) {
      throw new IllegalStateException("this shape is not available for moving");
    }
    int range = endTime - startTime;
    int k = 0;
    for (int i = 0; i < range; i++) {
      isPara1ScalingStatus.set(startTime - appearTime + i, 1);
      int diff = (k * (newWidth - oldWidth)) / range;
      para1Timeline.set(startTime - appearTime + i, oldWidth + diff);
      k++;
    }
    this.listOfMovements.add(new WidthScale(this, oldWidth, newWidth, startTime, endTime));
  }

  /*
   * This method checks if scaling on height is available for this shape from startTime to endTime,
   * which means to check if the time range has been occupied for scaling movement.
   *
   * @param startTime target start time to have a scaling scaling movement, int type.
   * @param endTime   target end time to have a scaling movement, int type.
   * @return true if the shape if available for a scaling movement, false otherwise.
   */
  protected boolean checkIfScalingHeightAvailable(int startTime, int endTime) {
    for (int i = 0; i < endTime - startTime; i++) {
      if (isPara2ScalingStatus.get(startTime - appearTime + i) == 1) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void addHeightScale(int oldHeight, int newHeight, int startTime, int endTime)
          throws IllegalArgumentException {
    if (!checkIfScalingHeightAvailable(startTime, endTime)) {
      throw new IllegalStateException("this shape is not available for moving");
    }
    int range = endTime - startTime;
    int k = 0;
    for (int i = 0; i < range; i++) {
      isPara2ScalingStatus.set(startTime - appearTime + i, 1);
      int diff = (k * (newHeight - oldHeight)) / range;
      para2Timeline.set(startTime - appearTime + i, oldHeight + diff);
      k++;
    }
    this.listOfMovements.add(new HeightScale(this, oldHeight, newHeight, startTime, endTime));
  }

  @Override
  public LinkedList<Movement> getMovementList() {
    return listOfMovements;
  }
}
