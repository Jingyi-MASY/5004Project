import java.awt.*;
import java.util.LinkedList;

/**
 * This interface includes all the operations that apply to an Animation class. An animation
 * implementation can add various shapes and various movements into it.
 */
public interface IAnimation {

  /**
   * This method initialize and adds a Circle shape into this animation. The shape to be added
   * must appears within time range [0, 100], and the name of the circle must be unique.
   *
   * @param name   name of the shape, String type; must be unique comparing to all shapes names in
   *               this animation.
   * @param type   shape type of the shape to be added, it should always be a ShapeType.CIRCLE.
   * @param position  position of (x, y) of the center of the circle to be added, Point2D type.
   * @param color   the color of the circle to be added to the animation, Color class type.
   * @param appearTime  time that this shape would appear in this animation, int type.
   * @param disappearTime   time that this shape would disappear in this animation, int type.
   * @param radius  radius of the circle to be added to this animation, int type.
   * @throws IllegalArgumentException  if the appearance time is out of range or the shape name
   *                                   is not unique or the any parameter that passed in is null.
   */
  public void addCircle(String name, ShapeType type, Point2D position, Color color, int appearTime,
                                     int disappearTime, int radius) throws IllegalArgumentException;

  /**
   * This method initialize and adds a Oval shape into this animation. The shaped to be added
   * must appears within time range [0, 100], and the name of the oval must be unique.
   *
   * @param name   name of the shape, String type; must be unique comparing to all shapes names in
   *               this animation.
   * @param type   shape type of the shape to be added, it should always be a ShapeType.OVAL.
   * @param position  position of (x, y) of the center of the oval to be added, Point2D.
   * @param color   the color of the oval to be added to the animation, Color class type.
   * @param appearTime  time that this oval shape would appear in this animation, int type.
   * @param disappearTime   time that this oval shape would disappear in this animation, int type.
   * @param xRadius  radius on x-axis of the oval shape to be added to this animation, int type.
   * @param yRadius  radius on y-axis of the oval shape to be added to this animation, int type.
   * @throws IllegalArgumentException  if the appearance time is out of range or the shape name
   *                                   is not unique or the any parameter that passed in is null.
   */
  public void addOval(String name, ShapeType type, Point2D position, Color color, int appearTime,
                        int disappearTime, int xRadius, int yRadius) throws IllegalArgumentException;

  /**
   * This method initialize and adds a rectangle shape into this animation. The shaped to be added
   * must appears within time range [0, 100], and the name of the rectangle must be unique.
   *
   * @param name   name of the shape, String type; must be unique comparing to all shapes names in
   *               this animation.
   * @param type   shape type of the shape to be added, it should always be a ShapeType.RECTANGLE.
   * @param position  position of (x, y) of lower left corner the rectangle to be added, Point2D.
   * @param color   the color of the rectangle to be added to the animation, Color class type.
   * @param appearTime  time that this rectangle shape would appear in this animation, int type.
   * @param disappearTime   time that this rectangle would disappear in this animation, int type.
   * @param width  width of the rectangle shape to be added to this animation, int type.
   * @param height  height of the rectangle shape to be added to this animation, int type.
   * @throws IllegalArgumentException  if the appearance time is out of range or the shape name
   *                                   is not unique or the any parameter that passed in is null.
   */
  public void addRectangle(String name, ShapeType type, Point2D position, Color color, int appearTime,
                      int disappearTime, int width, int height) throws IllegalArgumentException;

  /**
   * This method allows certain shape in this animation changes its color to the target color, and
   * this animation starts at the a start time point, and complete at another time point.
   * @param shape   shape to complete this color change movement, IShape type.
   * @param targetColor  the target color that the shape is changing to, Color type.
   * @param startTime   the start time point of this color changing movement, int type.
   * @param endTime   the end time point of this color changing movement, int type.
   */
  public void changeColor(IShape shape, Color targetColor, int startTime, int endTime);

  /**
   * This method allows certain shape in this animation scales its dimension by certain scaling
   * factor, and this animation starts at a start time point, and ends at another time point.
   * @param shape   shape to complete this scaling movement, IShape type.
   * @param scalingFactor  the factor that the shape is scaling based on, int type.
   * @param startTime   the start time point of this scaling movement, int type.
   * @param endTime   the end time point of this scaling movement, int type.
   */
  public void scale(IShape shape, int scalingFactor, int startTime, int endTime);

  /**
   * This method allows certain shape in this animation to move its position to a new position, and
   * this animation starts at a start time point and ends at another time point.
   * @param shape   shape that complete this moving movement, IShape type.
   * @param targetPosition   the target position to be move to, Point2D type.
   * @param startTime   the start time point of this moving movement, int type.
   * @param endTime   the end time point of this moving movement, int type.
   */
  public void Move(IShape shape, Point2D targetPosition, int startTime, int endTime);

  /**
   * This method displays in text description of the initial status of all the shapes that are in
   * this animation.
   * @return  text description of all the shapes initial status, String type.
   */
  public String showAllShapes();

  /**
   * This method generates a list of all movements that applies to all shapes in this animation.
   * @return  list of all movements in this animation, LinkedList<Movement> type.
   */
  public LinkedList<Movement> getAllMovement();

  /**
   * this method displays all movements for all shapes within the animation, in a text description
   * format.
   * @return  text description of all movements in this animation, String type.
   */
  public String displayAll();

  /**
   * This method generate a list of all shapes that are appeared at certain point of time.
   * @param time  this is the time point that the list of shapes will be created based on, int type.
   * @return  a list of all shapes that are appeared at the passed in time, LinkedList<IShape> type.
   */
  public LinkedList<IShape> getShapeStatusAtTime(int time);

  /**
   * This method display and show status of all the shapes that are appeared at certain point of
   * time, the status including thire name, type, color, dimension, position, appearance time.
   * @param time  the time point that the list of shapes will be created based on, int type.
   * @return  a text description of the status of all the shapes that are appeared at the passed in
   *          point of time.
   */
  public String showStatusAt(int time);
}
