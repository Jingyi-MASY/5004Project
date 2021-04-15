package cs5004.animator;

import java.util.List;

/**
 * This interface includes all the operations that apply to an Animation class. An animation
 * implementation can add various shapes and various movements into it.
 */
public interface IAnimation {

  /**
   * This methods sets bounds for this animation.
   */
  void setBounds(int x, int y, int width, int height);

  /**
   * This method gets and returns the bounds for this animation.
   */
  int[] getBounds();

  /**
   * this gets and returns the list of shapes that are in this animation,
   * where all the data is stored.
   * @return  list of shapes in this animation, List<IShape> type.
   */
  List<IShape> getListOfShapes();

  /**
   * This methods returns the
   * @param name  name of the shape to be added, the name must be unique in this animation.
   * @param type  the type of this shape, can be "circle", "rectangle", or "ellipse".
   */
  void declareShape(String name, String type);

  /**
   * This methods adds a motion to an existing shape in the animation.
   *
   * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
   * @param t1   The start time of this transformation
   * @param x1   The initial x-position of the shape
   * @param y1   The initial y-position of the shape
   * @param w1   The initial width of the shape
   * @param h1   The initial height of the shape
   * @param r1   The initial red color-value of the shape
   * @param g1   The initial green color-value of the shape
   * @param b1   The initial blue color-value of the shape
   * @param t2   The end time of this transformation
   * @param x2   The final x-position of the shape
   * @param y2   The final y-position of the shape
   * @param w2   The final width of the shape
   * @param h2   The final height of the shape
   * @param r2   The final red color-value of the shape
   * @param g2   The final green color-value of the shape
   * @param b2   The final blue color-value of the shape
   * @return This {@link AnimationBuilder}
   */
  void addMotion(String name, int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2);

  /*
   * This method adds a shape into this animation. The shaped to be added must appears within time
   * range [0, 100], and the name of the rectangle must be unique.
   *
   * @param shape a shape object to be added to the list
   * @throws IllegalArgumentException if the name already exists, or shape invalid
   */
  //void addShape(IShape shape);


  /**
   * This method allows certain shape in this animation changes its color to the target color, and
   * this animation starts at the a start time point, and complete at another time point.
   *
   * @param name        name of shape to complete this color change movement, String type.
   * @param targetColor the target color that the shape is changing to, Color type.
   * @param startTime   the start time point of this color changing movement, int type.
   * @param endTime     the end time point of this color changing movement, int type.
   */
  //void changeColor(String name, Color targetColor, int startTime, int endTime);

  /**
   * This method allows certain shape in this animation scales its dimension by certain scaling
   * factor, and this animation starts at a start time point, and ends at another time point.
   *
   * @param name          name of shape to complete this scaling movement, String type.
   * @param scalingFactor the factor that the shape is scaling based on, int type.
   * @param startTime     the start time point of this scaling movement, int type.
   * @param endTime       the end time point of this scaling movement, int type.
   */
  //void scale(String name, int scalingFactor, int startTime, int endTime);

  /**
   * This method allows certain shape in this animation to move its position to a new position, and
   * this animation starts at a start time point and ends at another time point.
   *
   * @param name           name of shape that complete this moving movement, String type.
   * @param targetPosition the target position to be move to, Point2D type.
   * @param startTime      the start time point of this moving movement, int type.
   * @param endTime        the end time point of this moving movement, int type.
   */
  //void move(String name, Point2D targetPosition, int startTime, int endTime);

  /**
   * This method displays in text description of the initial status of all the shapes that are in
   * this animation.
   *
   * @return text description of all the shapes initial status, String type.
   */
  //String showAllShapes();

  /**
   * This method generates a list of all movements that applies to all shapes in this animation.
   *
   * @return list of all movements in this animation, LinkedList type.
   */
  //LinkedList<Movement> getAllMovement();

  /**
   * this method displays all movements for all shapes within the animation, in a text description
   * format.
   *
   * @return text description of all movements in this animation, String type.
   */
  //String displayAll();

  /**
   * This method generate a list of all shapes that are appeared at certain point of time.
   *
   * @param time this is the time point that the list of shapes will be created based on, int type.
   * @return a list of all shapes that are appeared at the passed in time, LinkedList type.
   */
  //LinkedList<IShape> getShapeStatusAtTime(int time);

  /**
   * This method display and show status of all the shapes that are appeared at certain point of
   * time, the status including their name, type, color, dimension, position, appearance time.
   *
   * @param time the time point that the list of shapes will be created based on, int type.
   * @return a text description of the status of all the shapes that are appeared at the passed in
   *         point of time.
   */
  //String showStatusAt(int time);
}
