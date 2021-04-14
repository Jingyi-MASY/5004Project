package cs5004.animator.model;
import java.awt.Color;
import java.util.LinkedList;

/**
 * This interface includes all the operations that apply to an Animation class. An animation
 * implementation can add various shapes and various movements into it.
 */
public interface IAnimation {

  /**
   * This method adds a shape into this animation. The shaped to be added must appears within time
   * range [0, 100], and the name of the rectangle must be unique.
   *
   * @param shape a shape object to be added to the list
   * @throws IllegalArgumentException if the name already exists, or shape invalid
   */
  void addShape(IShape shape);

  /**
   * This method allows certain shape in this animation changes its color to the target color, and
   * this animation starts at the a start time point, and complete at another time point.
   *
   * @param name        name of shape to complete this color change movement, String type.
   * @param targetColor the target color that the shape is changing to, Color type.
   * @param startTime   the start time point of this color changing movement, int type.
   * @param endTime     the end time point of this color changing movement, int type.
   */
  void changeColor(String name, Color targetColor, int startTime, int endTime);

  /**
   * This method allows certain shape in this animation scales its dimension by certain scaling
   * factor, and this animation starts at a start time point, and ends at another time point.
   *
   * @param name          name of shape to complete this scaling movement, String type.
   * @param scalingFactor the factor that the shape is scaling based on, int type.
   * @param startTime     the start time point of this scaling movement, int type.
   * @param endTime       the end time point of this scaling movement, int type.
   */
  void scale(String name, int scalingFactor, int startTime, int endTime);

  /**
   * This method allows certain shape in this animation to move its position to a new position, and
   * this animation starts at a start time point and ends at another time point.
   *
   * @param name           name of shape that complete this moving movement, String type.
   * @param targetPosition the target position to be move to, Point2D type.
   * @param startTime      the start time point of this moving movement, int type.
   * @param endTime        the end time point of this moving movement, int type.
   */
  void move(String name, Point2D targetPosition, int startTime, int endTime);

  /**
   * This method displays in text description of the initial status of all the shapes that are in
   * this animation.
   *
   * @return text description of all the shapes initial status, String type.
   */
  String showAllShapes();

  /**
   * This method generates a list of all movements that applies to all shapes in this animation.
   *
   * @return list of all movements in this animation, LinkedList type.
   */
  LinkedList<Movement> getAllMovement();

  /**
   * this method displays all movements for all shapes within the animation, in a text description
   * format.
   *
   * @return text description of all movements in this animation, String type.
   */
  String displayAll();

  /**
   * This method generate a list of all shapes that are appeared at certain point of time.
   *
   * @param time this is the time point that the list of shapes will be created based on, int type.
   * @return a list of all shapes that are appeared at the passed in time, LinkedList type.
   */
  LinkedList<IShape> getShapeStatusAtTime(int time);

  /**
   * This method display and show status of all the shapes that are appeared at certain point of
   * time, the status including their name, type, color, dimension, position, appearance time.
   *
   * @param time the time point that the list of shapes will be created based on, int type.
   * @return a text description of the status of all the shapes that are appeared at the passed in
   *         point of time.
   */
  String showStatusAt(int time);
}
