import java.awt.Color;
import java.util.LinkedList;

/**
 * this interface contains all operations that applies to an Shape interface.
 */
public interface IShape {

  /**
   * this method gets and returns the name of this shape.
   *
   * @return name of this shape, String type.
   */
  String getName();

  /**
   * this method gets and return the ShapeType of this shape.
   *
   * @return ShapeType of this shape, ShapeType enum.
   */
  ShapeType getType();

  /**
   * this method gets and returns the position of this shape at certain point of time.
   *
   * @param time time that is looking for based on, int type.
   * @return the position of this shape at the passed in point of time, Point2D type.
   * @throws IllegalArgumentException if the passed in time is out of this shape's appearance time.
   */
  Point2D getPositionAt(int time) throws IllegalArgumentException;

  /**
   * this method gets and returns the color of this shape at certain point of time.
   *
   * @param time time that is looking for, int type.
   * @return the color of this shape that at the passed in point of time, Color type.
   * @throws IllegalArgumentException if the passed in time if outr of this shape's appearance
   *                                  time.
   */
  Color getColorAt(int time) throws IllegalArgumentException;

  /**
   * this methods gets and returns the appearTime of this shape.
   *
   * @return the time that this shape is starting to show up in certain animation, int type.
   */
  int getAppearTime();

  /**
   * this methods gets and returns the disappear time of this shape.
   *
   * @return the time that this shape disappears in certain animation, int type.
   */
  int getDisappearTime();

  /**
   * This method gets and returns the moving status of every unit of time in a list.
   *
   * @return list of 1 or 0 indicating whether if the shape is in certain moving movement. 1
   * indicates moving in progress, NOT available to add new move. 0 indicates moving not in
   * progress, available for new move.
   */
  int[] getMovingStatus();

  /**
   * This method gets and returns the color-changing status of every unit of time in a list.
   *
   * @return list of 1 or 0 indicating whether if the shape is in certain color-changing movement. 1
   * indicates color changing in progress, NOT available to add new color change. 0 indicates color
   * changing not in progress, available for new color change.
   */
  int[] getColorChangingStatus();

  /**
   * This method gets and returns the scaling status of every unit of time in a list.
   *
   * @return list of 1 or 0 indicating whether if the shape is in certain scaling movement. 1
   * indicates scaling in progress, NOT available to add new scaling. 0 indicates scaling not in
   * progress, available for new scaling.
   */
  int[] getScalingStatus();

  /**
   * This method gets and returns the Color status of this shape at all time of its appearance.
   *
   * @return a list of Color elements that indicates the color status of this shape at every time
   * unit of its appearance time.
   */
  Color[] getColorTimeline();

  /**
   * This method gets and returns the position status of this shape at all time of its appearance.
   *
   * @return a list of Position2D elements that indicates the position of this shape at every time
   * unit of its appearance time.
   */
  Point2D[] getMovingTimeline();

  /**
   * This method gets and returns the status of all aspects of this shape at certain point of time.
   *
   * @param time time that is looking for, int type.
   * @return a new shape object that is created based on the status of all aspect of this shape at
   * passed in time, IShape type.
   * @throws IllegalArgumentException if the time if out of this shape's appearance time.
   */
  IShape statusAt(int time) throws IllegalArgumentException;

  /**
   * This method checks if changing color movement is available for this shape from start time to
   * end time, which means check if the time range has been occupied for color changing movement.
   *
   * @param startTime target start time to have a color change movement, int type.
   * @param endTime   target end time to have a color change movement, int type.
   * @return true if this shape is available for this color change move, false otherwise.
   */
  boolean checkIfChangingColorAvailable(int startTime, int endTime);

  /**
   * This method add a color change movement to this shape to a target color, starting at a start
   * time and completes at an end time.
   *
   * @param color     target color after color change movement, Color type.
   * @param startTime start time of this color change movement, int type.
   * @param endTime   end time of this color change movement, int type.
   * @throws IllegalStateException if the target time range is not available for this shape to take
   *                               a color change move.
   */
  void addChangeColor(Color color, int startTime, int endTime) throws IllegalStateException;

  /**
   * This method checks if scaling movement is available for this shape from start time to end time,
   * which means to check if the time range has been occupied for scaling movement.
   *
   * @param startTime target start time to have a scaling scaling movement, int type.
   * @param endTime   target end time to have a scaling movement, int type.
   * @return true if the shape if available for a scaling movement, false otherwise.
   */
  boolean checkIfScalingAvailable(int startTime, int endTime);

  /**
   * This method adds a scaling movement to this shape to a certain new dimension, starting at a
   * start time and completes at an end time.
   *
   * @param factor    factor to use to measure dimension. new dimension = old dimension * factor.
   * @param startTime target start time of this scaling movement, int type.
   * @param endTime   target end time of this scaling movement, int type.
   * @throws IllegalArgumentException if the target time range is not available for this shape to
   *                                  take a scaling movement.
   */
  void addScale(int factor, int startTime, int endTime) throws IllegalArgumentException;

  /**
   * This method checks if moving movement is available for this shape form start time to end time,
   * which means to check if the time range has been occupied for moving movement.
   *
   * @param startTime target start time to have a moving movement, int type.
   * @param endTime   target end time to have a moving movement, int type.
   * @return true if the shape is available for a moving movement, false otherwise.
   */
  boolean checkIfMovingAvailable(int startTime, int endTime);

  /**
   * This method adds a moving movement to this shape so that it moves to a new position, starting
   * at a start time and completes at an end time.
   *
   * @param newPosition target position that this shape is to move to by the end of movement.
   * @param startTime   target start time of this moving movement, int type.
   * @param endTime     target end time of this moving movement, int type.
   * @throws IllegalArgumentException if the target time range is not available for this shape to
   *                                  take a moving movement.
   */
  void addMove(Point2D newPosition, int startTime, int endTime)
          throws IllegalArgumentException;

  /**
   * This method generates a copy of this shape for all its status at certain point of time.
   *
   * @param time time that is looking for, int type.
   * @return a new shape that has the same status of this shape at certain time, or return null if
   * the time is invalid for this shap.
   */
  IShape getCopy(int time);

  /**
   * This method gets and returns a list of all movements of this shape.
   *
   * @return list of all movements that happens to this shape, LinkedList type.
   */
  LinkedList<Movement> getMovementList();

  /**
   * This method display in String format of how its dimensions changes during a scaling movement.
   *
   * @param time   the start time of the scaling movement, int type.
   * @param factor the scaling factor. new dimensions = old dimension * factor, int type.
   * @return the text description of how the dimensions have changes during a scaling movement.
   */
  String getDimensionChange(int time, int factor);
}
