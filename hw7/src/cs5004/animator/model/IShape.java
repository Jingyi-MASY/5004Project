package cs5004.animator.model;

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
   * @throws IllegalArgumentException if the passed in time if out of this shape's appearance time.
   */
  Color getColorAt(int time) throws IllegalArgumentException;

  int getPara1At(int time) throws IllegalArgumentException;

  int getPara2At(int time) throws IllegalArgumentException;

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
   * this method is used to update the appearance time and disappearance time for this shape
   * that is existing in an animation. The updates includes this shape's appear time, disappear time
   * the changing status for each motion (isMovingStatus, isChangeingColorStatus, isScaling), and
   * the status timeline for each attributes(color, position, dimensions).
   * @param start the start time of certain motion, int type.
   * @param end   the end time of certain motion, int type.
   */
  void updateAppearance(int start, int end);

  /**
   * this method is used to update the color of this shape from startTime to endTime.
   * @param color   the color to fill in this shape from start time to end time, Color type.
   * @param startTime  start time of this filling/updating, int type.
   * @param endTime   end time of this filling/updating, int type.
   */
  void updateColor(Color color, int startTime, int endTime);

  /**
   * This method adds a color change to this shape to a target color, starting at a start
   * time and completes at an end time.
   *
   * @param color1    original color at this shape at startTime, Color type.
   * @param color2     target color after color change movement, Color type.
   * @param startTime start time of this color change movement, int type.
   * @param endTime   end time of this color change movement, int type.
   * @throws IllegalStateException if the target time range is not available for this shape to take
   *                               a color change move.
   */
  void addChangeColor(Color color1, Color color2, int startTime, int endTime)
          throws IllegalStateException;

  /**
   * this method is used to fill in and update the position of this shape.
   * @param position  the position to fill in for this shape, Point2D type.
   * @param startTime   the start time to fill in the position to this shape, int type.
   * @param endTime   the end time to fill in the position to this shape, int type.
   */
  void updatePosition(Point2D position, int startTime, int endTime);

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
  void addMove(Point2D oldPosition, Point2D newPosition, int startTime, int endTime)
          throws IllegalArgumentException;

  /**
   * this method is used to fill in and update the width of this shape.
   * @param width  the width to fill in for this shape, int type.
   * @param startTime   the start time to fill in the position to this shape, int type.
   * @param endTime   the end time to fill in the position to this shape, int type.
   */
  void updateWidth(int width, int startTime, int endTime);

  /**
   * this methods adds a scale motion on this shape's width from the initial width to the target.
   * width from startTime to endTime.
   * @param initialWidth  the initial width of the shape when the shape starts the motion, int type.
   * @param targetWidth   the target width of this shape when the shape ends the motion, int type.
   * @param startTime   the start time of this motion, int type.
   * @param endTime   the end time of this motion, int type.
   */
  void addWidthScale(int initialWidth, int targetWidth, int startTime, int endTime);

  /**
   * this method is used to fill in and update the height of this shape.
   * @param height  the height to fill in for this shape, int type.
   * @param startTime   the start time to fill in the position to this shape, int type.
   * @param endTime   the end time to fill in the position to this shape, int type.
   */
  void updateHeight(int height, int startTime, int endTime);

  /**
   * this methods adds a scale motion on this shape's width from the initial height to the target
   * height from startTime to endTime.
   * @param initialHeight  the initial height of the shape when the shape starts the motion.
   * @param targetHeight   the target height of this shape when the shape ends the motion.
   * @param startTime   the start time of this motion, int type.
   * @param endTime   the end time of this motion, int type.
   */
  void addHeightScale(int initialHeight, int targetHeight, int startTime, int endTime);

  /**
   * This method gets and returns a list of all movements of this shape.
   *
   * @return list of all movements that happens to this shape, LinkedList type.
   */
  LinkedList<Movement> getMovementList();

}
