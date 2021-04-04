import java.awt.*;
import java.awt.geom.Dimension2D;
import java.util.LinkedList;

public interface IShape {

  public String getName();

  public ShapeType getType();

  public Point2D getPositionAt(int time) throws IllegalArgumentException;

  public Color getColorAt(int time) throws IllegalArgumentException;

  public int getAppearTime();

  public int getDisappearTime();

  /**
   * This method returns the moving status of every unit of time in a list.
   * @return  list of 1 or 0 indicating whether if the shape is in certain moving movement.
   *          1 indicates moving in progress, NOT available to add new move.
   *          0 indicates moving not in progress, availble for new move.
   */
  public int[] getMovingStatus();

  /**
   * This method returns the color-changing status of every unit of time in a list.
   * @return  list of 1 or 0 indicating whether if the shape is in certain color-changing movement.
   *          1 indicates color changing in progress, NOT available to add new color change.
   *          0 indicates color changing not in progress, availble for new color change.
   */
  public int[] getColorChangingStatus();

  /**
   * This method returns the scaling status of every unit of time in a list.
   * @return   list of 1 or 0 indicating whether if the shape is in certain scaling movement.
   *           1 indicates scaling in progress, NOT available to add new scaling.
   *           0 indicates scaling not in progress, availble for new scaling.
   */
  public int[] getScalingStatus();

  //public Dimension2D[] getDimensionTimeline();

  public Color[] getColorTimeline();

  public Point2D[] getMovingTimeline();

  public IShape statusAt(int time) throws IllegalArgumentException;

  public boolean checkIfChangingColorAvailable(int startTime, int endTime);

  public void addChangeColor(Color Color, int startTime, int endTime) throws IllegalStateException;

  public boolean checkIfScalingAvailable(int startTime, int endTime);

  public void addScale(int factor, int startTime, int endTime) throws IllegalArgumentException;

  public boolean checkIfMovingAvailable(int startTime, int endTime);

  public void addMove(Point2D newPosition, int startTime, int endTime) throws IllegalArgumentException;

  public IShape getCopy(int time);

  public LinkedList<Movement> getMovementList();

  public String getDimensionChange(int time, int factor);
}
