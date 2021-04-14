package cs5004.animator.view;

import java.util.List;

import cs5004.animator.IAnimation;
import cs5004.animator.IShape;

public interface IView {
  /**
   * this method takes in a list shape and print out in text form of the shape initials.
   * The initials of a shape includes its type, color, name, position, dimensions.
   * @param listOfShapes  a list of IShape objects, possible shapes are rectangle, ellipse and
   *                      circle.
   */
  void showShapeInitials(List<IShape> listOfShapes);

  void showShapeAppearance(List<IShape> listOfShapes);

  void showMotions(List<IShape> listOfShapes);

  void showAll(IAnimation animation);
  void showOneShape(IAnimation animation, String shapeName);
  void showStatusAt(IAnimation animation, int time);
  //void showOptions();
  //void showStringEntry();
  //void showOptionError();
}
