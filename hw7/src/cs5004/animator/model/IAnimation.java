package cs5004.animator.model;

import java.util.List;

/**
 * This interface includes all the operations that apply to an Animation class. An animation
 * implementation can add various shapes and various movements into it.
 */
public interface IAnimation {

  /**
   * This methods sets the bounds of this canvas for this animation.
   */
  void setBounds(int x, int y, int width, int height);

  /**
   * This method gets and returns the bounds of this canvas for this animation.
   * @return  list of 4 integers represents its position (x, y) and width and height of the canvas.
   */
  int[] getBounds();

  /**
   * this gets and returns the list of shapes that are in this animation.
   * where all the data is stored.
   * @return  list of shapes in this animation, List of IShape type.
   */
  List<IShape> getListOfShapes();

  /**
   * This methods creates and declares a shape in this animation.
   * @param name  name of the shape to be added, the name must be unique in this animation.
   * @param type  the type of this shape, can be "circle", "rectangle", or "ellipse".
   */
  void declareShape(String name, String type);

  /**
   * This methods adds a motion to an existing shape in the animation.
   *
   * @param name The name of the shape.
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
   */
  void addMotion(String name, int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
                 int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2);
}
