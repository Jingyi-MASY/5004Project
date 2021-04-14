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
   * This method gets and returns the initial position of this shape. This might be different from
   * the result from getPositionAt(appearTime) because the later one reflects moving status.
   * @return  initial position of this shape before any change, Point2D type.
   */
  Point2D getPosition();

  /**
   * This method gets and returns the initial color of this shape. This might be different from
   * the result from getColorAt(appearTime) because the later one reflects moving status.
   * @return  initial color of this shape before any change, Color type.
   */
  Color getColor();

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
   * this method returns the only parameter in dimension, initial value before any motion.
   * for a circle, it should be the radius; for a square, it should be the width.
   * @return  the only parameter in dimension.
   */
  int getPara();

  /**
   * this method returns the first parameter in dimension, initial value before any motion..
   * It should be width for a rectangle, and xRadius for a ellipse.
   * @return  first parameter in dimension.
   */
  int getPara1();

  /**
   * this method returns the second parameter in dimension, initial value before any motion..
   * It should be height for a rectangle, and yRadius for a ellipse.
   * @return  second parameter in dimension.
   */
  int getPara2();

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
   * @return a new shape that has the same status of this shape at certain time,
   *         or return null if the time is invalid for this shape.
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
